(ns clj.core)

;; s-expression with prefix notation
;; (operation/function arguments)

;; which clojure version repl is running on ?
(clojure-version)

;; i like to see document for given function from repl
(doc map)
(doc +)
(doc println)

;; print it on console/repl
(println \a)
(println "TWO in String")
(println 2)
(println 2.5555557)
(println 22/7) ;;fraction

;; tell my type or typed me out to something
(type \b)
(type "Aham Brahmasmi")

(type 1)
(type (short 1))

(type 1000000000000000000000000)
(type 10000000000000000.12345600000000)
(type 1000000000000000000000000000000000.12345600000000)

(type map)
(type print)

(type true)
(type false)


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


;; Define variable

(def x 45)
(def y 54)
(def x 450)
(+ x y)


;; define constant

(def ^:const p 10)
(print p)
(def p 100)
(print p)

;; Data structures
;; (print (1 2 3)) ;; raises exception
(print '(1 2 3))

(map identity '(1 2 3))
(map odd? '(1 2 3))

;; define simple function 
(defn add [a b]
  (+ a b))

(reduce add '(1 2 3 4 5))
(reduce add (range 5))  

;; lambda function
(def one_to_100 (range 100))
(reduce #(+ %1 %2) one_to_100)
(filter #(not (even? %)) one_to_100)  