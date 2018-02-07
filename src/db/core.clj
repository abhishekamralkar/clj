(ns db.core
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as string])
  (:require [taoensso.nippy :as nippy])
  (:require [clojure.data.csv :as csv])
  (:require [clojure.string :as str]))

;; insert data
(print nippy/stress-data)

(defn serialize [data]
  (nippy/freeze data))

(defn de-serialize [data]
  (nippy/thaw data))

;; Create Data


(.mkdir (java.io.File. "./data"))
(.mkdir (java.io.File. "./data/db"))
(.mkdir (java.io.File. "./data/db/table"))

;; read csv 
(with-open [reader (io/reader "./data/FL_insurance_sample.csv")]
  (print (vec (take 2 (csv/read-csv reader)))))

;; read csv and create files per record
(with-open [reader (io/reader "./data/FL_insurance_sample.csv")]
  (let [csv-seq (csv/read-csv reader)
        columns (first csv-seq)
        data (rest csv-seq)]
    (doall (for [record data
                 :let [record-object (zipmap columns record)
                       file-name (str "./data/db/table/" (first record))]]
             (spit file-name record-object)))))

(defn search-string [file word]
  (with-open [rdr (io/reader file)]
    (doall
     (filter
      #(str/includes? % word)
      (line-seq rdr)))))

(defn search-table [directory regex]
  (count (for [file (file-seq (io/file "./data/db/table"))]
           (when (.isFile ^java.io.File file)
             (search-string file regex)))))
(time
 (count
  (search-string "./data/FL_insurance_sample.csv" "COUNTY")))

(time
 (search-table "./data/db/table" "COUNTY"))