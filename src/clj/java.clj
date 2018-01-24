(ns core.java)

;; java interop

(.toUpperCase "Clojure")

(.getName String)

(.-x (java.awt.Point. 1 2))
(.-y (new java.awt.Point 1 2))

(System/getProperty "java.vm.version")

(Math/PI)

(.. System
    (getProperties)
    (get "os.name"))

(-> (System/getProperties)
    (.get "os.name"))

(doto
 (new java.util.HashMap)
  (.put "a" 1)
  (.put "b" 2))

;;clojure to java

(java.util.ArrayList. [1 2 3])
(.get (java.util.ArrayList. [1 2 3]) 0)

(java.util.HashMap. {"a" 1 "b" 2})
(.get (java.util.HashMap. {"a" 1 "b" 2}) "a")

;;java to clojure 

(into [] (java.util.ArrayList. [1 2 3]))
(into #{} (java.util.HashSet. #{1 2 3}))
(into '() (java.util.LinkedList. '(1 2 3)))
(into {} (java.util.HashMap. {:a 1 :b 2}))