(ns braveclojure.organization)

;1. Your Project as a Library
(ns-name *ns*)
; => user

;2. Storing Objects with def
(def great-books ["East of Eden" "The Glass Bead Game"])
; => #'user/great-books
great-books
;["East of Eden" "The Glass Bead Game"]

;; Return map of interned Vars
(ns-interns *ns*)
; => {great-books #'user/great-books}

;; Get a specific Var
(get (ns-interns *ns*) 'great-books)
; => #'user/great-books

(ns-map *ns*)
; => very large map which I won't print here; try it out!

;; The symbol 'great-books is mapped to the Var we created above
(get (ns-map *ns*) 'great-books)
; => #'user/great-books

(ns-map *ns*)
; => very large map which I won't print here; try it out!

;; The symbol 'great-books is mapped to the Var we created above
(get (ns-map *ns*) 'great-books)
; => #'user/great-books

(deref #'user/great-books)
; => ["East of Eden" "The Glass Bead Game"]

great-books
; => ["East of Eden" "The Glass Bead Game"]

(def great-books ["The Power of Bees" "Journey to Upstairs"])
great-books
; => ["The Power of Bees" "Journey to Upstairs"]


;3. Creating and Switching to Namespaces
;; Creates the namespace if it doesn't exist and return
;user> (create-ns 'cheese.taxonomy)
; => #<Namespace cheese.taxonomy>

;; Returns the namespace if it already exists
;user> (create-ns 'cheese.taxonomy)
; => #<Namespace cheese.taxonomy>

;; Pass the returned namespace as an argument
; (ns-name (create-ns 'cheese.taxonomy))
; => cheese.taxonomy

;user> (in-ns 'cheese.analysis)
; => #<Namespace cheese.analysis>

;(in-ns 'cheese.taxonomy)
;cheese.taxonomy> (def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
;cheese.taxonomy> (in-ns 'cheese.analysis)

;; We get an exception if we try to refer to the cheese.taxonomy
;; namespace's cheddars from within cheese.analysis

;cheese.analysis> cheddars
; => Exception: Unable to resolve symbol: cheddars in this context

;; But using the fully-qualified symbol works:
;cheese.analysis> cheese.taxonomy/cheddars
; => ["mild" "medium" "strong" "sharp" "extra sharp"]



;user> (in-ns 'cheese.taxonomy)
;cheese.taxonomy> (def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
;cheese.taxonomy> (def bries ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"])
;cheese.taxonomy> (in-ns 'cheese.analysis)
;cheese.analysis> (clojure.core/refer 'cheese.taxonomy)
;cheese.analysis> bries
; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
;cheese.analysis> cheddars
; => ["mild" "medium" "strong" "sharp" "extra sharp"]

;cheese.analysis> (clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'bries)
;#'cheese.taxonomy/bries
;
;cheese.analysis> (clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'cheddars)
;#'cheese.taxonomy/cheddars



;;; :only example
;cheese.analysis> (clojure.core/refer 'cheese.taxonomy :only ['bries])
;cheese.analysis> bries
;; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
;cheese.analysis> cheddars
;; => RuntimeException: Unable to resolve symbol: cheddars
;
;;; :exclude example
;cheese.analysis> (clojure.core/refer 'cheese.taxonomy :exclude ['bries])
;cheese.analysis> bries
;; => RuntimeException: Unable to resolve symbol: bries
;cheese.analysis> cheddars
;; => ["mild" "medium" "strong" "sharp" "extra sharp"]
;
;;; :rename example
;cheese.analysis> (clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
;cheese.analysis> bries
;; => RuntimeException: Unable to resolve symbol: bries
;cheese.analysis> yummy-bries
;; => ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]


(in-ns 'cheese.analysis)
;; Notice the dash after "defn"
(defn- private-function
  "Just an example function that does nothing"
  [])


;cheese.analysis> (clojure.core/alias 'taxonomy 'cheese.taxonomy)
;cheese.analysis> taxonomy/bries
;["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"]
