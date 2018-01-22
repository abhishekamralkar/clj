(ns core.abstraction)

;; record- type with hashmap support
(defrecord Person [first-name last-name address])

(defrecord Address [street city state zip])

(def Julia (Person. "Julia" "Roberts"
                    (Address. "Elm Street" "New York" "NY" 27965)))
(type Julia)
(type Person)
(:last-name Julia)
(.last-name Julia)
(-> Julia :address :city)
(assoc Julia :first-name "Jennifer")
(update-in Julia [:address :zip] (fn [old-zip] 45321))

(def Christina (->Person "Christina" "Johnson"
                         (->Address "Owa Street" "Cleveland" "ohio" 21154)))
(.first-name Christina)
(:first-name Christina)

(defprotocol WereCreature
  (full-moon-behavior [x]))

(defrecord WereWolf [name title]
  WereCreature
  (full-moon-behavior [x]
    (str name " will howl and murder")))

(defprotocol P
  (foo [x])
  (bar-me [x] [x y]))


;; type - Types with constructor
(deftype Foo [a b c]
  P
  (foo [x] a)
  (bar-me [x] b)
  (bar-me [x y] (+ c y)))

(bar-me (Foo. 1 2 3) 42)

(foo
 (let [x 42]
   (reify P ;; implement protocol at run time
     (foo [this] 17)
     (bar-me [this] x)
     (bar-me [this y] x))))