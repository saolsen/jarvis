(ns jarvis.transcriber.core
  (:require [jarvis.transcriber.utility :as util]
            [jarvis.transcriber.grammar :as grammar])
  (:import (java.io File)))

;; This is the lest functional part of the code as it deals with
;; pre-existing java classes.

;; A sphinx4 recognizer is a java object, the state of which is
;; managed internally in this namespace. Because of this a few things
;; happen.
;; 1) Call allocate to set everything up.
;; 2) Pass app-grammars to setup-grammars to save them to disk.
;; 2) Then you can load audio files and grammars and call recognize.
;;    - or call load-and-recognize
;; 3) Once finished call deallocate.

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

(defn setup-grammars
  "Saves the app grammars to local filesystem so they can be read later"
  [app-grammars]
  (grammar/load-grammars app-grammars))

(defn load-audio
  "Takes a File object."
  [audiofile]
  (doto (.lookup config-manager "audioFileDataSource")
    (.setAudioFile audiofile nil)))

(defn load-grammar
  "Loads a grammar"
  [name]
  ;; Currently I think the only way to do this is from the local
  ;; filesystem so this will need working on.
  ;; Add in a "get the grammar to the filesystem step"
  (.loadJSGF grammar name))

(defn recognize
  "Recognizes the currently loaded audio with the currently loaded grammar"
  []
  (.getBestResultNoFiller (.recognize @recognizer)))

(defn load-and-recognize
  "Loads a grammar and a file and recognizes it"
  [audiofile app-id]
  (do
    (load-audio audiofile)
    (load-grammar app-id)
    (recognize)))

(defn deallocate
  "Deallocates the recognizer"
  []
  (.deallocate @recognizer))