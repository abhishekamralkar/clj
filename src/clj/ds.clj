(ns clj.ds)

;; Data structures - persistent (with structural sharing), immutable by default 

(print (1 2 3)) ;; raises exception
(type '(1 2 3))
(print '(1 2 3))  ;;list
(type [1 2 3])
(print [1 2 3])   ;; vector


(list 12 34 65)
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
(clojure.set/intersection #{1 2 3} #{2 3 4 5})
(clojure.set/union #{1 2 3} #{2 3 4 5})

;; sequences
(cons 1 nil)
(cons 1 (cons 2 nil))

(cons 1 '(2 3 4 5 6))
(cons 1 [2 3 4])

;; destructuring 
(let [[a b c] '(:a :b)
      [p q] [10 20]
      {x :x y :y} {:x 60 :y 70}
      {:keys [l m] :or {l "London"}} {:m "Mumbai" :n "New York"}
      {first 0 last-but-one 3} [100 200 300 400 500]]
  (for [var [a b c p q x y l m first last-but-one]]
    (println var)))

;; transients

(defn vrange [n]
  (loop [i 0 v []]
    (if (< i n)
      (recur (inc i) (conj v i))
      v)))

(defn vrange2 [n]
  (loop [i 0 v (transient [])]
    (if (< i n)
      (recur (inc i) (conj! v i))
      (persistent! v))))

(time 
  (def v (vrange 1000000)))

(time 
  (def v2 (vrange2 1000000)))


(def t-map (transient {:a 1 :b 2}))

(assoc! t-map :a 10)

(get :a t-map)