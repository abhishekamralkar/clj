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
(defn substract [a b]
  {:pre [(pos? a) (pos? b)]
   :post [(< % (max a b))]}
  (- a b))

(substract -2 -3)
(substract 2 -3)
(substract -2 3)
(substract 3 2)

;; type hints performance

(set! *warn-on-reflection* true)

(defn len [x]
  (.length x))

(time (reduce + (map len (repeat 1000000 "asdf"))))

(defn len1 [^String x]
  (.length x))

(time (reduce + (map len1 (repeat 1000000 "asdf"))))

(defn foo [n]
  (loop [i 0]
    (if (< i n)
      (recur (inc i))
      i)))

(time (foo 1000000))

(defn foo2 [n]
  (let [n (int n)]
    (loop [i (int 0)]
      (if (< i n)
        (recur (inc i))
        i))))

(time (foo2 1000000))