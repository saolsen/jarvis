(ns jarvis.models.app)
;; Deals with apps

;; Called by jarvis.transcriber.jammer when it need to get all of the
;; rules and stuff to set up the files.
;; Probably something like,
;; - Get all apps and their rules,
;; - Convert all their rules to JSGF grammars
;; - Return giant list of maps
;; {:app-id id, :grammars [ { :name :text } ]}
;; Rule texts can refer to earlier rule-names so the order of the rule
;; list counts.

;; STUB, used for testing. Do not really use.
(defn get-all-app-grammars
  []
  (let [grammar {:name "num1"
                 :text (str "(oh | zero | one | two | three | "
                            "four | five | six | seven | eight | "
                            "nine) * ;")}]
    [{:app-id "numbers" :grammars [grammar]}]))
