(ns jarvis.transcriber.utility
  (:require clojure.java.io)
  (:import
   (java.io File)
   (edu.cmu.sphinx.util.props ConfigurationManager)
   (edu.cmu.sphinx.recognizer Recognizer)
   (com.sun.speech.engine.recognition BaseRecognizer)))

(defn get-cm
  "Gets a configuration Manager"
  []
  (let [config-file (clojure.java.io/resource "config-file.xml")]
    (ConfigurationManager. config-file)))

(defn get-grammar
  "Gets the jsgf grammar"
  [cm]
  (.lookup cm "jsgfGrammar"))

(defn get-grecon
  [cm grammar]
  (BaseRecognizer. (.getGrammarManager grammar)))

(defn get-recognizer
  [cm]
  (.lookup cm "recognizer"))

(defn allocate
  [rec]
  (.allocate rec))