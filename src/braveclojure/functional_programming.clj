(ns braveclojure.functional-programming)

;1. Pure Functions, What and Why
(get [:chumbawumba] 0)
; => :chumbawumba

(reduce + [1 10 5])
; => 16

(str "wax on " "wax off")
; => "wax on wax off"

;1.1. Pure Functions Are Referentially Transparent
(+ 1 2)
; => 3

(defn wisdom
  [words]
  (str words ", Daniel-san"))
(wisdom "Always bathe on Fridays")
; => "Always bathe on Fridays, Daniel-san"

;Not referntail transparent examples

(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(defn analysis
  [text]
  (str "Character count: " (count text)))

(defn analyze-file
  [filename]
  (analysis (slurp filename)))

;1.2. Pure Functions Have No Side Effects


;2. Living with Immutable Data Structures

;2.1. Recursion instead of for/while

(defn no-mutation
  [x]
  (println x)

  ;; let creates a new scope
  (let [x "Kafka Human"]
    (println x))

  ;; Exiting the let scope, x is the same
  (println x))
(no-mutation "Existential Angst Person")
; =>
; Existential Angst Person
; Kafka Human
; Existential Angst Person

(defn sum
  ([vals] (sum vals 0)) ;; ~~~1~~~
  ([vals accumulating-total]
   (if (empty? vals) ;; ~~~2~~~
     accumulating-total
     (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1]) ; single-arity body calls 2-arity body
(sum [39 5 1] 0)
(sum [5 1] 39)
(sum [1] 44)
(sum [] 45) ; base case is reached, so return accumulating-total
; => 45

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

;2.2. Functional Composition instead of Attribute Mutation

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "My boa constrictor is so sassy lol!  ")
; => "My boa constrictor is so sassy LOL!"


;3. Cool Things to do With Pure Functions
((comp clojure.string/lower-case clojure.string/trim) " Unclean string ")
; => "unclean string"

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;; + is referentially transparent. You can replace this...
(+ 3 (+ 5 8))

;; ...with this...
(+ 3 13)

;; ...or this...
16

;; and the program will have the same behavior

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
;(sleepy-identity "Mr. Fantastico")
;; => "Mr. Fantastico" after 1 second
;(sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second

(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second
(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" immediately
