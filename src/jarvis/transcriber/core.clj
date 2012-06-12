(ns jarvis.transcriber.core
  (:import
   (edu.cmu.sphinx.recognizer Recognizer)))

;; This is the lest functional part of the code as it deals with
;; pre-existing java classes.

;; A sphinx4 recognizer is a java object, the state of which is
;; managed internally in this namespace. Because of this a few things
;; happen.
;; 1) When this namespace is loaded a Recognizer is created and allocated.
;;    - This happens when a worker node is spun up.
;; 2) When the function transcribe is called the grammars for the app
;;    are loaded into the recognizer and it is run against the audio.
;; 3) Because the grammars can be swapped like this we don't have to
;;    create a new recognizer each time the app changes. This is fast
;;    and works quite nicely.

(defonce recognizer (recog/get-recognizer))

(defn hello
  []
  (println "hello world"))
