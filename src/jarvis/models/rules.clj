(ns jarvis.models.rules)
;; All validation and translation of jarvis rules, the main jarvis
;; model.
;; Handles translations between the clojure representation, what's
;; stored in postgres and jsgf grammars.

;; Type declaration

(defn to-db
  "Takes a rule and creates a field that can be stored in postgres"
  [rule]
  nil)

(defn from-db
  "Takes the row from the database and creates a rule from it"
  [row]
  nil)

(defn to-jsgf
  "Takes a rule and creates a jsgf grammar"
  [rule]
  nil)

(defn match-rule
  "Takes a result sentance and matches it to one of the rules
   with the values of it's variables"
  [match-string rule-lst]
  nil)
