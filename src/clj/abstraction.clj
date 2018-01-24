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
(assoc Julia :first-name1 "Alice") ;; open to add new attributes
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

(full-moon-behavior (->WereWolf "dracula" "blood sucker"))

(defprotocol P
  (foo [x])
  (bar-me [x] [x y]))

;; type - Types with constructor, can have mutable fields, have no identity(equals, hashcode)
(deftype Foo [a b c]
  P
  (foo [x] a)
  (bar-me [x] b)
  (bar-me [x y] (+ c y)))

(bar-me (Foo. 1 2 3))
(bar-me (Foo. 1 2 3) 42)
(assoc (Foo. 1 2 3) :new-attr "value") ;;exception, not allowed

;;reify
(foo
 (let [x 42]
   (reify P ;; implement protocol at run time
     (foo [this] 17)
     (bar-me [this] x)
     (bar-me [this y] x))))

;;Creates a new Java interface with the given name and method sigs.                        
(defprotocol IEditName
  (get-name [this])
  (set-name! [this val]))

(deftype PersonName [^:volatile-mutable lname]
  IEditName
  (get-name [this] (. this lname))
  (set-name! [this val] (set! lname val)))

(def pname (PersonName. "hoge"))
;=> #'user/pname

(set-name! pname "fuge")
;=> "fuge"

(get-name pname)

;; extend in built types
(defprotocol Dateable
  (to-ms [t]))

(extend java.util.Date
  Dateable
  {:to-ms #(.getTime %)})

(to-ms (java.util.Date.))

;; multimethods : direct, flexible and dynamic way to introduce polymorphism
(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))

(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))

(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})

(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})