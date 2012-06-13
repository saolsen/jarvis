(ns jarvis.transcriber.core
  (:require [jarvis.transcriber.utility :as util])
  (:import (java.io File)))

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
(defonce config-manager (util/get-cm))
(defonce recognizer (atom nil))

(defn allocate
  "Creates and allocates recognizer, must be called first"
  []
  (reset! recognizer (util/get-recognizer config-manager)))

(defn load-audio
  "Takes a File object."
  [audiofile]
  (doto (.lookup config-manager "audioFileDataSource")
    (.setAudioFile audiofile nil)))

(defn load-grammar
  []
  nil)

(defn recognize
  "Recognizes the currently loaded audio with the currently loaded grammar"
  []
  (.getBestResultNoFiller (.recognize @recognizer)))

(defn deallocate
  "Deallocates the recognizer"
  []
  (.deallocate @recognizer))

;; Tests should be moved elsewhere
(def audiofile "10001-90210-01803.wav")
(defn quicktest
  []
  (do
    (load-audio (File. audiofile))
    (recognize)))