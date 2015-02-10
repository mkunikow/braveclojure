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


;;  3.1. Calling Functions

(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

;; Return value of "or" is first truthy value, and + is truthy
(or + -)

((or + -) 1 2 3)
; => 6

;; Return value of "and" is first falsey value or last truthy value.
;; + is the last truthy value
((and (= 1 1) +) 1 2 3)

;; Return value of "first" is the first element in a sequence
((first [+ 0]) 1 2 3)

;However, these aren't valid function calls:
;; Numbers aren't functions
;(1 2 3 4)

;; Neither are strings
;("test" 1 2 3)

;; The "inc" function increments a number by 1
(inc 1.1)
; => 2.1

;Take the map function (not to be confused with the map data structure). map creates a new list by applying
; a function to each member of a collection:
(map inc [0 1 2 3])
; => (1 2 3 4)


; Clojure evaluates all function arguments recursively before passing them to the function.

;; Here's the function call. It kicks off the evaluation process
(+ (inc 199) (/ 100 (- 7 2)))

;; All sub-forms are evaluated before applying the "+" function
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation

;: 3.3.2. Parameters


;Clojure functions can be defined with zero or more parameters:

(defn no-params
  []
  (str "I take no parameters!"))

(defn one-param
      [x]
      (str "I take one param: " x " It'd better be a string!"))

(defn two-params
      [x y]
      (str "Two parameters! That's nothing! Pah! I will smoosh them "
           "together to spite you! " x y))

;Here's the general form of a multiple-arity function definition

(defn do-things
      [& args]
  (str args))

(defn multi-arity
      ;; 3-arity arguments and body
      ([first-arg second-arg third-arg]
        (do-things first-arg second-arg third-arg))
      ;; 2-arity arguments and body
      ([first-arg second-arg]
        (do-things first-arg second-arg))
      ;; 1-arity arguments and body
      ([first-arg]
        (do-things first-arg)))

;Overloading by arity is one way to provide default values for arguments.
(defn x-chop
      "Describe the kind of chop you're inflicting on someone"
      ([name chop-type]
        (str "I " chop-type " chop " name "! Take that!"))
      ([name]
        (x-chop name "karate")))

(x-chop "Kanye West" "slap")
; => "I slap chop Kanye West! Take that!"

(x-chop "Kanye East")
; => "I karate chop Kanye East! Take that!"


;You can also make each arity do something completely unrelated:
(defn weird-arity
      ([]
        "Destiny dressed you this morning my friend, and now Fear is
        trying to pull off your pants. If you give up, if you give in,
        you're gonna end up naked with Fear just standing there laughing
        at your dangling unmentionables! - the Tick")
      ([number]
        (inc number)))

;Clojure also allows you to define variable-arity functions by including a "rest-param"
(defn codger-communication
      [whippersnapper]
      (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
      [& whippersnappers] ;; the ampersand indicates the "rest-param"
      (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")
; =>
; ("Get off my lawn, Billy!!!"
;  "Get off my lawn, Anne-Marie!!!"
;  "Get off my lawn, The Incredible Bulk!!!")

;You can mix rest-params with normal params, but the rest-param has to come last:

;; 3.3.3. Destructuring

;The basic idea behind destructuring is that it lets you concisely bind symbols to values within a collection.

;; Return the first element of a collection
(defn my-first
      [[first-thing]] ; Notice that first-thing is within a vector
      first-thing)

(my-first ["oven" "bike" "waraxe"])
; => "oven"

;Here's how you would accomplish the same thing without destructuring:
(defn my-other-first
      [collection]
      (first collection))
(my-other-first ["nickel" "hair"])
; => "nickel"

;When destructuring a vector or list, you can name as many elements as you want and also use rest params:
(defn chooser
      [[first-choice second-choice & unimportant-choices]]
      (println (str "Your first choice is: " first-choice))
      (println (str "Your second choice is: " second-choice))
      (println (str "We're ignoring the rest of your choices. "
                    "Here they are in case you need to cry over them: "
                    (clojure.string/join ", " unimportant-choices))))
(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
; =>
; Your first choice is: Marmalade
; Your second choice is: Handsome Jack
; We're ignoring the rest of your choices. Here they are in case \
; you need to cry over them: Pigpen, Aquaman

;Deatructing map
(defn announce-treasure-location
      [{lat :lat lng :lng}]
      (println (str "Treasure lat: " lat))
      (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 28.22 :lng 81.33})
; =>
; Treasure lat: 28.22
; Treasure lng: 81.33

;; Works the same as above.
(defn announce-treasure-location
      [{:keys [lat lng]}]
      (println (str "Treasure lat: " lat))
      (println (str "Treasure lng: " lng)))

;; Works the same as above.

(defn steer-ship!
  [& args]
  identity args)

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))

;; 3.3.4. Function body

;Your function body can contain any forms. Clojure automatically returns the last form evaluated:

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")
(illustrative-function)
; => "joe"

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 5)
; => "That number's OK, I guess"

(number-comment 7)
; => "Oh my gosh! What a big number!"

;; 3.4. Anonymous Functions

;There are two ways to create anonymous functions. The first is to use the fn form:
;; This looks a lot like defn, doesn't it?
;(fn [param-list]
;  function body)

;; Example
(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

;; Another example
((fn [x] (* x 3)) 8)
; => 24

;You could even associate your anonymous function with a name, which shouldn't come as a surprise:
(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)
; => 36

;There's another, more compact way to create anonymous functions:
;; Whoa this looks weird.
#(* % 3)

;; Apply this weird looking thing
(#(* % 3) 8)
; => 24

;; Another example
(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

;If your anonymous function takes multiple arguments, you can distinguish them like this: %1, %2, %3, etc. % is equivalent to %1:
(#(str %1 " and " %2) "corn bread" "butter beans")
; => "corn bread and butter beans"

;You can also pass a rest param:
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)

;The main difference between this form and fn is that this form can easily become unreadable and is best used for short functions.


;; 3.5. Returning Functions

; Functions can return other functions. The returned functions are closures,
; which means that they can access all the variables that were in scope when the function was created.

;; inc-by is in scope, so the returned function has access to it even
;; when the returned function is used outside inc-maker
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10

;; 4. Pulling It All Together

;; 4.1. The Shire's Next Top Model

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts
            final-body-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (recur remaining (conj final-body-parts (make-matching-part part)))
          (recur remaining final-body-parts))))))

(symmetrize-body-parts asym-hobbit-body-parts)
; => the following is the return value
[{:name "head", :size 3}
 {:name "left-eye", :size 1}
 {:name "right-eye", :size 1}
 {:name "left-ear", :size 1}
 {:name "right-ear", :size 1}
 {:name "mouth", :size 1}
 {:name "nose", :size 1}
 {:name "neck", :size 2}
 {:name "left-shoulder", :size 3}
 {:name "right-shoulder", :size 3}
 {:name "left-upper-arm", :size 3}
 {:name "right-upper-arm", :size 3}
 {:name "chest", :size 10}
 {:name "back", :size 10}
 {:name "left-forearm", :size 3}
 {:name "right-forearm", :size 3}
 {:name "abdomen", :size 6}
 {:name "left-kidney", :size 1}
 {:name "right-kidney", :size 1}
 {:name "left-hand", :size 2}
 {:name "right-hand", :size 2}
 {:name "left-knee", :size 2}
 {:name "right-knee", :size 2}
 {:name "left-thigh", :size 4}
 {:name "right-thigh", :size 4}
 {:name "left-lower-leg", :size 3}
 {:name "right-lower-leg", :size 3}
 {:name "left-achilles", :size 1}
 {:name "right-achilles", :size 1}
 {:name "left-foot", :size 2}
 {:name "right-foot", :size 2}]

;; 4.2. let

;(let [[part & remaining] remaining-asym-parts
;      final-body-parts (conj final-body-parts part)]
;  some-stuff)

(let [x 3]
  x)
; => 3

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"]) ; and 97 more...
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
; => ("Pongo" "Perdita")

;let also introduces a new scope:
(def x 0)
(let [x 1] x)
; => 1

;However, you can reference existing bindings in your let binding:
(def x 0)
(let [x (inc x)] x)
; => 1

;You can also use rest-params in let, just like you can in functions:
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])
; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]

;Let's have another look at the let form in our symmetrizing function so we can understand exactly what's going on:

;; Associate "part" with the first element of "remaining-asym-parts"
;; Associate "remaining" with the rest of the elements in "remaining-asym-parts"
;; Associate "final-body-parts" with the result of (conj final-body-parts part)
;(let [[part & remaining] remaining-asym-parts
;      final-body-parts (conj final-body-parts part)]
;  (if (needs-matching-part? part)
;    (recur remaining (conj final-body-parts (make-matching-part part)))
;    (recur remaining final-body-parts)))


  ;(if (needs-matching-part? (first remaining-asym-parts))
  ;  (recur (rest remaining-asym-parts)
  ;       (conj (conj final-body-parts (first remaining-asym-parts))
  ;             (make-matching-part (first remaining-asym-parts))))
    ;(recur (rest remaining-asm-parts)
    ;     (conj (conj final-body-parts (first remaining-asym-parts)))))


;; 4.3. loop

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))
; =>
;Iteration 0
;Iteration 1
;Iteration 2
;Iteration 3
;Iteration 4
;Goodbye!

;You could in fact accomplish the same thing just using functions:

(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (> iteration 3)
      (println "Goodbye!")
      (recursive-printer (inc iteration)))))

(recursive-printer)
; =>
;Iteration 0
;Iteration 1
;Iteration 2
;Iteration 3
;Iteration 4
;Goodbye!

;; 4.4. Regular Expressions

;; pound, open quote, close quote
#"regular-expression"

(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(needs-matching-part? {:name "left-eye"})
; => true
(needs-matching-part? {:name "neckbeard"})
; => false

;make-matching-part uses a regex to replace "left-" with "right-":
(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(make-matching-part {:name "left-eye" :size 1})
; => {:name "right-eye" :size 1}]

;; 4.5. Symmetrizer

;def asym-hobbit-body-parts [{:name "head" :size 3}
;                            {:name "left-eye" :size 1}
;                            {:name "left-ear" :size 1}
;                            {:name "mouth" :size 1}
;                            {:name "nose" :size 1}
;                            {:name "neck" :size 2}
;                            {:name "left-shoulder" :size 3}
;                            {:name "left-upper-arm" :size 3}
;                            {:name "chest" :size 10}
;                            {:name "back" :size 10}
;                            {:name "left-forearm" :size 3}
;                            {:name "abdomen" :size 6}
;                            {:name "left-kidney" :size 1}
;                            {:name "left-hand" :size 2}
;                            {:name "left-knee" :size 2}
;                            {:name "left-thigh" :size 4}
;                            {:name "left-lower-leg" :size 3}
;                            {:name "left-achilles" :size 1}
;                            {:name "left-foot" :size 2}]

;(defn needs-matching-part?
;  [part]
;  (re-find #"^left-" (:name part)))
;
;(defn make-matching-part
;  [part]
;  {:name (clojure.string/replace (:name part) #"^left-" "right-")
;   :size (:size part)})

; ~~~1~~~
;(defn symmetrize-body-parts
;  "Expects a seq of maps which have a :name and :size"
;  [asym-body-parts] ;
;  (loop [remaining-asym-parts asym-body-parts ; ~~~2~~~
;         final-body-parts []]
;    (if (empty? remaining-asym-parts) ; ~~~3~~~
;      final-body-parts
;      (let [[part & remaining] remaining-asym-parts ; ~~~4~~~
;            final-body-parts (conj final-body-parts part)]
;        (if (needs-matching-part? part) ; ~~~5~~~
;          (recur remaining (conj final-body-parts (make-matching-part part))) ; ~~~6~~~
;          (recur remaining final-body-parts))))))

;; 4.6. Shorter Symmetrizer with Reduce

;; sum with reduce
(reduce + [1 2 3 4])
; => 10

(reduce + 15 [1 2 3 4])

(defn my-reduce
  ([f initial coll]
    (loop [result initial
           remaining coll]
      (let [[current & rest] remaining]
        (if (empty? remaining)
          result
          (recur (f result current) rest)))))
  ([f [head & tail]]
    (my-reduce f (f head (first tail)) (rest tail))))

(defn better-symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))


;; 4.7. Hobbit Violence
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + 0 (map :size sym-parts))
        target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur rest (+ accumulated-size (:size part)))))))

(hit asym-hobbit-body-parts)
; => {:name "right-upper-arm", :size 3}

(hit asym-hobbit-body-parts)
; => {:name "chest", :size 10}

(hit asym-hobbit-body-parts)
; => {:name "left-eye", :size 1}