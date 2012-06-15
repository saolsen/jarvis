(ns jarvis.transcriber.grammar
  (:require [clojure.java.io :as io]))
;; There is no really good way to do this yet, right now Sphinx has to
;; read the grammar from the local directory so we need to spit out a
;; file for every grammar we have. This probably won't work in the
;; long run because we'll have way too many apps.
;; Works for now to get up and running though.


;; Takes grammar
;; {:name :text}
;; and returns a jsgf format to be written to file
;; public <name> = text
(defn to-JSGF-format
  [rule]
  (str "public <" (:name rule) "> = " (:text rule) "\n"))

;; Takes an app-grammar
;; {:app-id "name" :grammars [grammar]}
;; Writes them to files in the local directory.
(defn to-grammar-file
  [app-grammar]
  (let [rules (map to-JSGF-format (:grammars app-grammar))
        full-text (str "#JSGF V1.0\n"
                       "grammar " (:app-id app-grammar) ";\n"
                       (apply str rules))]
    (with-open [wrtr (io/writer (str "src/jarvis/transcriber/"
                                     (:app-id app-grammar) ".gram"))]
      (.write wrtr full-text))))

(defn load-grammars
  "Creates files for all the grammars."
  [app-grammar-lst]
    (doseq [app app-grammar-lst] (to-grammar-file app)))