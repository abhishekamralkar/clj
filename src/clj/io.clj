(ns core.io
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as string]))

(def path ".")

(def file (io/file path))

(.exists file)
(.isDirectory file)
(.list file)

;; get all files and directories as sequence recursively inside directory
(file-seq file)
(count (file-seq file))

(str file)

;; filter only files
(map str
     (filter
      #(.isFile ^java.io.File %)
      (file-seq file)))

;; get all contents of file      
(slurp "./project.clj")

;; get content of file line by line
(with-open [rdr (io/reader (io/file "./LICENSE"))]
  (count (line-seq rdr)))

(with-open [rdr (io/reader "./LICENSE")]
  (str (line-seq rdr)))

;; filter lines of file  which has some digits
(defn grep-lines [file regex]
  (let [pattern (re-pattern regex)]
    (with-open [rdr (io/reader file)]
      (doall
       (filter
        #(re-find pattern %)
        (line-seq rdr))))))

(grep-lines "./LICENSE" "\\d+")
(grep-lines "./LICENSE" "or")
(grep-lines "./src/clj/io.clj" "or")

(defn grep-directory [directory regex]
  (->> (file-seq (io/file directory))
       (filter #(.isFile ^java.io.File %))
       (map #(grep-lines % regex))
       (filter #(not (empty? %)))))

(time
 (grep-directory "." "defn"))

;; create file with data  
(spit "foo.data"
      "A long
multi-line string.
Bye.")

;; overwrite file contents
(spit "foo.data"
    (println-str (grep-directory "." "defn")))

;; append if file exists otherwise create file and append
(spit "foo.data" "Extra data appended" :append true)    