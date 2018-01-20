(ns clj.ds)

;; Data structures
;; (print (1 2 3)) ;; raises exception
(print '(1 2 3))  ;;list
(print [1 2 3])   ;; vector

(first '(7 8 9))
(second '(7 8 9))
(rest '(7 8 9))
(nth '(7 8 9 10 11 12) 3)
(last '(7 8 9))

(first [7 8 9])
(second [7 8 9])
(rest [7 8 9])
(nth [7 8 9 10 11 12] 3)
(last [7 8 9])

(map identity '(1 2 3))
(map odd? [1 2 3])

;; define simple function 
(def add (fn [a b]
           (+ a b)))

(defn add [a b]
  (+ a b))

(reduce add '(1 2 3 4 5))
(reduce add (range 5))

;; lambda function
(def one_to_100 (range 100))
(reduce #(+ %1 %2) one_to_100)
(filter #(not (even? %)) one_to_100)
(keep #(if (odd? %) (+ 1000 %)) (range 10))

;; maps
(println {:a 1 :b 2})
(:a {:a 1 :b 2})
(:b {:a 1 :b 2})
(get-in {:a {:c 1} :b 2} [:a :c])
(keys {:a 1 :b 5 :c 10})
(vals {:a 1 :b 5 :c 10})

;; sets
(println #{:a :b :c})
(println #{1 2 3})
(println #{1 :b "Three"})
