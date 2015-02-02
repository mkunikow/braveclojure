(ns braveclojure.do-things)

;; 1.1. Forms

1
"a string"
["a" "vector" "of" "strings"]


(+ 1 2 3)

;; 1.2. Control Flow

;;   1.2.1. if

(if true
  "abra cadabra"
  "hocus pocus")
; => "abra cadabra"

;;   1.2.2. do

(if true
  (do (println "Success!")
      "abra cadabra")
  (do (println "Failure :(")
      "hocus pocus"))
; => Success!
; => "abra cadabra"

;;   1.2.3. when

(when true
  (println "Success!")
  "abra cadabra")
; => Success!
; => "abra cadabra"

;;   1.3. Naming Things with def

(def failed-protagonist-names
  ["Larry Potter"
   "Doreen the Explorer"
   "The Incredible Bulk"])

(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOMED!")))

;; 2. Data Structures

;failed_protagonist_names = [
;                            "Larry Potter",
;                            "Doreen the Explorer",
;                            "The Incredible Bulk"
;                            ]
;failed_protagonist_names[0] = "Gary Potter"
;failed_protagonist_names
;=> [
;    "Gary Potter",
;    "Doreen the Explorer",
;    "The Incredible Bulk"
;    ]

;; 2.1. nil, true, false, Truthiness, Equality

(nil? 1)
; => false

(nil? nil)
; => true

(= 1 1)
; => true

(= nil nil)
; => true

(= 1 2)
; => false

;; 2.2. Numbers

93
1.2
1/5

;; 2.3. Strings

"Lord Voldemort"
"\"He who must not be named\""
"\"Great cow of Moscow!\" - Hermes Conrad"

(def name_arg "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " name_arg)
; => "Uggllglglglglglglglll" - Chewbacca

;; 2.4. Maps

;; An empty map
{}

;; ":a", ":b", ":c" are keywords and we'll cover them in the next section
{:a 1
 :b "boring example"
 :c []}

;; Associate "string-key" with the "plus" function
{"string-key" +}

;; Maps can be nested
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}

(get {:a 0 :b 1} :b)
; => 1

(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}

(get {:a 0 :b 1} :c)
; => nil

(get {:a 0 :b 1} :c "UNICORNS")
; => "UNICORNS"

(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
; => "ho hum"

;Treat map as function
({:name "The Human Coffee Pot"} :name)
; => "The Human Coffee Pot"

;; 2.5. Keywords

:a
:rumplestiltsken
:34
:_?

;; Look up :a in map
(:a {:a 1 :b 2 :c 3})
; => 1

;; This is equivalent to:
(get {:a 1 :b 2 :c 3} :a)
; => 1

;; Provide a default value, just like get:
(:d {:a 1 :b 2 :c 3} "FAERIES")
; => "FAERIES

(hash-map :a 1 :b 2)
; => {:a 1 :b 2}

;; 2.6. Vectors

;; Here's a vector literal
[3 2 1]

;; Here we're returning an element of a vector
(get [3 2 1] 0)
; => 3

;; Another example of getting by index. Notice as well that vector
;; elements can be of any type and you can mix types.
(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
; => {:name "Pugsley Winterbottom"}

(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]

(conj [1 2 3] 4)
; => [1 2 3 4]

;; 2.7. Lists

;; Here's a list - note the preceding single quote
'(1 2 3 4)
; => (1 2 3 4)
;; Notice that the REPL prints the list without a quote. This is OK,
;; and it'll be explained later.


;; Doesn't work for lists
(get '(100 200 300 400) 0)

;; This works but has different performance characteristics which we
;; don't care about right now.
(nth '(100 200 300 400) 3)
; => 400

(list 1 2 3 4)
; => (1 2 3 4)

(conj '(1 2 3) 4)
; => (4 1 2 3)

;; 2.8. Sets

;; Literal notation
#{"hannah montanna" "miley cyrus" 20 45}

;; If you try to add :b to a set which already contains :b,
;; the set still only has one :b
(conj #{:a :b} :b)
; => #{:a :b}

;; You can check whether a value exists in a set
(get #{:a :b} :a)
; => :a

(:a #{:a :b})
; => :a

(get #{:a :b} "hannah montanna")
; => nil

(set [3 3 3 4 4])
; => #{3 4}

;; 3 exists in vector
(get (set [3 3 3 4 4]) 3)
; => 3

;; but 5 doesn't
(get (set [3 3 3 4 4]) 5)
; => nil

(hash-set 1 1 3 1 2)
; => #{1 2 3}

(sorted-set :b :a :c)
; => #{:a :b :c}

;; 2.9. Symbols and Naming

(def failed-movie-titles ["Gone With the Moving Air" "Swellfellas"])

;; Identity returns its argument
(identity 'test)
; => test

;; 2.10. Quoting

failed-protagonist-names
; => ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

(first failed-protagonist-names)
; => "Larry Potter"

'failed-protagonist-names
; => failed-protagonist-names

(eval 'failed-protagonist-names)
; => ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

;(first 'failed-protagonist-names)
; => Throws exception!

(first ['failed-protagonist-names 'failed-antagonist-names])
; => failed-protagonist-names

'(failed-protagonist-names 0 1)
; => (failed-protagonist-names 0 1)

(first '(failed-protagonist-names 0 1))
; => failed-protagonist-names

(second '(failed-protagonist-names 0 1))
; => 0

;; 2.11. Simplicity

;It is better to have 100 functions operate on one data structure
;than 10 functions on 10 data structures.
;
;-- Alan Perlis