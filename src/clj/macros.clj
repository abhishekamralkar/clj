(ns core.macros)

;; reduce boiler plate code,  new features to language

(macroexpand '(when boolean-expression
                expression-1
                expression-2
                expression-3))

(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(infix (1 + 1))

(macroexpand '(infix (1 + 1)))