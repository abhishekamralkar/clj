(ns core.transducer)

;; avoid generating temporary seq data structure after every HOF operation

(filter odd?) ;; returns a transducer that filters odd
(map inc)     ;; returns a mapping transducer for incrementing
(take 5)      ;; returns a transducer that will take the first 5 values


(->> (range 100)
     (filter odd?)
     (map inc)
     (take 5))

(def xf (comp (filter odd?) (map inc)))

(transduce xf + (range 5))
     ;; => 6
(transduce xf + 100 (range 5))


;; eduction - bringing transduced output to iterator structure
(def iter (eduction xf (range 5)))
(reduce + 0 iter)

;; apply a transducer to an input collection and construct a new output collection
(into [] xf (range 1000))

;; bringing transduced output to sequence structure
(sequence xf (range 1000))

