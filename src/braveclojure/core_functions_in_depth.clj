(ns braveclojure.core-functions-in-depth)

;1. Programming to Abstractions

;1.1. Using One Function on Different Data Structures

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hamsters" "Ragnarok"])
; => ("Hamsters for the Brave and True" "Ragnarok for the Brave and True")

(map titleize '("Empathy" "Decorating"))
; => ("Empathy for the Brave and True" "Decorating for the Brave and True")

(map titleize #{"Elbows" "Soap Carving"})
; => ("Soap Carving for the Brave and True" "Elbows for the Brave and True")



; What's less obvious is that you can also use the map function on map data structures.
; When you do this, each key/value pair gets passed to the mapping function
(defn label-key-val
  [[key val]]
  (str "key: " key ", val: " val))

(map label-key-val {:name "Edward"
                    :occupation "perennial high-schooler"})
; => ("key: :name, val: Edward" "key: :occupation, val: perennial high-schooler")

(map (fn [[key val]] [key (inc val)])
     {:max 30 :min 10})
; => ([:max 31] [:min 11])

(into {}
      (map (fn [[key val]] [key (inc val)])
           {:max 30 :min 10}))
; => {:max 31, :min 11}


;1.2. Distinguishing Abstraction and Implementation

;2. The Sequence Abstraction
;2.1. Seq Functions Convert Data Structures to Seqs

(identity "Stefan Salvatore from Vampire Diaries")
; => "Stefan Salvatore from Vampire Diaries"

(map identity {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])


; 2.2. Seq Function Examples
; 2.2.1. map

(map inc [1 2 3])
; => (2 3 4)

(map str ["a" "b" "c"] ["A" "B" "C"]) ; == (list (str "a" "A") (str "b" "B") (str "c" "C"))
; => ("aA" "bB" "cC")

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)
; => ({:human 8.1, :critter 0.0}
;{:human 7.3, :critter 0.2}
;{:human 6.6, :critter 0.3}
;{:human 5.0, :critter 1.1})


(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])
; => (17 3 17/3)

(stats [80 1 44 13 6])
; => (144 5 144/5)

;2.2.2. reduce

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})
; => {:max 31, :min 11}

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})
; {:human 4.1}

;2.2.3. take, drop, take-while, drop-while
(take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)

(drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)


(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
; => ({:month 1 :day 1 :human 5.3 :critter 2.3}
;{:month 1 :day 2 :human 5.1 :critter 2.0}
;{:month 2 :day 1 :human 4.9 :critter 2.1}
;{:month 2 :day 2 :human 5.0 :critter 2.5})

(drop-while #(< (:month %) 3) food-journal)
; => ({:month 3 :day 1 :human 4.2 :critter 3.3}
;{:month 3 :day 2 :human 4.0 :critter 3.8}
;{:month 4 :day 1 :human 3.7 :critter 3.9}
;{:month 4 :day 2 :human 3.7 :critter 3.6})


(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))
; => ({:month 2 :day 1 :human 4.9 :critter 2.1}
;{:month 2 :day 2 :human 5.0 :critter 2.5}
;{:month 3 :day 1 :human 4.2 :critter 3.3}
;{:month 3 :day 2 :human 4.0 :critter 3.8})

;2.2.4. filter, some

(filter #(< (:human %) 5) food-journal)
; => ({:month 2 :day 1 :human 4.9 :critter 2.1}
;{:month 3 :day 1 :human 4.2 :critter 3.3}
;{:month 3 :day 2 :human 4.0 :critter 3.8}
;{:month 4 :day 1 :human 3.7 :critter 3.9}
;{:month 4 :day 2 :human 3.7 :critter 3.6})


(filter #(< (:month %) 3) food-journal)
; => ({:month 1 :day 1 :human 5.3 :critter 2.3}
;{:month 1 :day 2 :human 5.1 :critter 2.0}
;{:month 2 :day 1 :human 4.9 :critter 2.1}
;{:month 2 :day 2 :human 5.0 :critter 2.5})


(some #(> (:critter %) 5) food-journal)
; => nil

(some #(> (:critter %) 3) food-journal)
; => true

(some #(and (> (:critter %) 3) %) food-journal)
; => {:month 3 :day 1 :human 4.2 :critter 3.3}


;2.2.5. sort, sort-by
(sort [3 1 2])
; => (1 2 3)

(sort-by count ["aaa" "c" "bb"])
; => ("c" "bb" "aaa")

;2.2.6. concat
(concat [1 2] [3 4])
; => (1 2 3 4)

;2.3. Lazy Seqs

;2.3.1. Demonstrating Lazy Seq Efficiency

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(time (identify-vampire (range 0 1000000)))
;"Elapsed time: 32019.912 msecs"
; => {:name "Damon Salvatore", :makes-blood-puns? true, :has-pulse? false}

;2.3.2. Infinite Sequences
(concat (take 8 (repeat "na")) ["Batman!"])
; => ("na" "na" "na" "na" "na" "na" "na" "na" "Batman!")

(take 3 (repeatedly (fn [] (rand-int 10))))
; => (1 4 0)

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))
; => (0 2 4 6 8 10 12 14 16 18)

