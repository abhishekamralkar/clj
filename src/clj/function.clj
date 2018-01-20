(ns core.function)

;; simple function, type hints are optional but helpful
(defn ^Long add [^Long a ^Long b]
  (+ a b))

(add 1 2)

;; function with var args    
(defn add [a b & c]
  (+ a b (reduce + c)))

(add 1 2 3 4 5)

;; function with arity
(defn add
  (^Long [] 0)
  (^Long [a] a)
  (^Long [a b & c]
   (+ a b (reduce + c))))

(add)
(add 10)
(add 1 5 10 15)

;; Contract based programming - function with pre post condition
(defn add [a b]
    {:pre [(pos? a) (pos? b)]
     :post [(< (* a b))]}
    (+ a b))

(add -2 -3)
(add 2 -3)
(add 2 -3)
(add 2 3)
