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
