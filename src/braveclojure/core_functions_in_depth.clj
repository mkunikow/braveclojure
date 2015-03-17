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
