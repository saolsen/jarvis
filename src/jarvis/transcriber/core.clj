(ns jarvis.transcriber.core
  (:require [jarvis.transcriber.utility :as util])
  (:import (java.io File)))

;; This is the lest functional part of the code as it deals with
;; pre-existing java classes.

;; A sphinx4 recognizer is a java object, the state of which is
;; managed internally in this namespace. Because of this a few things
;; happen.
;; 1) Call allocate to set everything up.
;; 2) Then you can load audio files and grammars and call recognize.
;;    - or
;; 3) Once finished call deallocate.

;; This execution flow is really messed up because I'm trying to
;; follow the java examples that I have. Once it's working it should
;; be gone over and made a bit friendlier/verbose/flexable

(defonce config-manager (util/get-cm))
(defonce grammar (util/get-grammar config-manager))
(defonce recognizer (atom nil))
(defonce grecon (atom nil))

(defn allocate
  "Creates and allocates recognizer, must be called first."
  []
  (do
    (reset! recognizer (util/get-recognizer config-manager))
    (reset! grecon (util/get-grecon config-manager grammar))
    (util/allocate @grecon)
    (util/allocate @recognizer)))

(defn load-audio
  "Takes a File object."
  [audiofile]
  (doto (.lookup config-manager "audioFileDataSource")
    (.setAudioFile audiofile nil)))

(defn load-grammar-rules
  "Takes a list of strings that are potential matches."
  [lst]
  nil)

(defn recognize
  "Recognizes the currently loaded audio with the currently loaded grammar"
  []
  (.getBestResultNoFiller (.recognize @recognizer)))

(defn load-and-recognize
  "Loads a grammar and a file and recognizes it"
  [audiofile grammar]
  (do
    (load-audio audiofile)
    (load-grammar-rules [])
    (recognize)))

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