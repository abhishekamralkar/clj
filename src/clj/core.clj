(ns clj.core)

;; s-expression with prefix notation
;; (operation/function arguments)
;; special form if, let, do, quote, loop, recur, throw, try


;; which clojure version repl is running on ?
(clojure-version)

;; i like to see document for given function from repl
(doc map)
(doc +)
(doc println)

;; print it on console/repl
(println \a) ;;character
(println "TWO in String") ;;string
(println 2) ;; long
(println 2.5555557) ;; double
(println 22/7) ;;fraction
(println true) ;;boolean
(println *ns*)
(println 0x64)  ;; byte

(println :a)
(println (keyword "a"))
(println (keyword "a" "b"))
(println (keyword 123)) ;; nil

;; tell my type or typed me out to something
(type \b)
(type "Aham Brahmasmi")

(type 1)
(type (short 1))
(type 2233.445)

(type 1000000000000000000000000)
(type 10000000000000000.12345600000000)
(type 1000000000000000000000000000000000.12345600000000)

(type map)
(type print)

(type true)
(type false)

(type 'iAmSymbol)
(type :a)

;; Arithmetic Operations

;; Add
(+ 1)
(+ 1 2)
(+ 1 2 3 4 5)

;; Substract
(- 2)
(- 2 3)
(- 10 5 1)

;; Multiply
(* 2)
(* 2 3)
(* 2 10 15 20)

;; Divide - returns fraction values
(/ 1)
(/ 1 2)
(/ 1 2 3)

;; Divide - returns near precise values
(/ 1. 2)
(/ 1. 2 3)
(float (/ 1 2 3))
(double (/ 1 2 3))

;; modulus
(mod 11 3)

;; Define variable
(def ^:dynamic x 45)
(def ^:dynamic y 54)
(+ x y)

(binding [x 2 y 3]
  (+ x y))
  
;; define constant
(def ^:const p 10)
(print p)

;; define local scoped variables
(let [a 10
      b 20]
  (- b a))

;; if condition, behaves like ternary operator
(if (odd? 1)
  1
  2)

(if nil
  1
  2)

(if []
  :then
  :else)

;; switch like condition   
(let [grade 75]
  (cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else "F"))

(let [value 2]
  (condp = value
    1 "one"
    2 "two"
    3 "three"
    (str "unexpected value, \"" value \")))

(condp #(%1 %2) :foo
  string? "it's a string"
  keyword? "it's a keyword"
  symbol? "it's a symbol"
  fn? "it's a function"
  "something else!")

(condp some [1 2 3 4]
  #{0 6 7} :>> inc
  #{4 5 9} :>> dec
  #{1 2 3} :>> #(+ % 3))

;; for loop
(for [a (range 5)]
  a)

(for [a (range 5)
      b (range 5)]
  (* a b))

(for [x (range 100)
      :let [y (* x 3)]
      :when (even? y)]
  y)

(for [x (range 3)
      y (range 3)
      :while (not= x 1)]
  [x y])

;;recursion loop
(loop [a 0 b 1 cnt 10]
  (if (= cnt 0)
    a
    (recur (+ a b) a (dec cnt))))

;; Blocks
(do
  (def x 90)
  (def y 160)
  (+ x y)
  (println (+ x y))
  #{y x})

;; block under controlled loop iteration
(dotimes [i 10]
  (println (str "This is loop " i)))

(dorun 5 
  (repeatedly #(println "hi")))  

;; quot, quote, syntax-quote and unquote
(quot 50 10) ;;arithmetic

;(+ 23 78)
(quote
 (+ 23 78))

(println '(+ 23 78))

(println `(+ 23 78)) ;; as code

(let [operation '+]
  `(~operation 1 5 10))

(let [x '(1 5 10)]
  `(~@x 15))

;; try, catch, finally

(try
  (/ 1 0)
  (catch ArithmeticException e (str "caught exception: " (.getMessage e)))
  (finally (prn "final exception.")))