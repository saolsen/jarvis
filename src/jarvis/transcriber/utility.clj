(ns jarvis.transcriber.utility
  (:require clojure.java.io)
  (:import
   (java.io File)
   (edu.cmu.sphinx.util.props ConfigurationManager)
   (edu.cmu.sphinx.recognizer Recognizer)))

(defn get-cm
  "Gets a configuration Manager"
  []
  (let [config-file (clojure.java.io/resource "config-file.xml")]
    (ConfigurationManager. config-file)))

(defn get-recognizer
  "Creates a recognizer"
  [cm]
  (doto (.lookup cm "recognizer")
    (.allocate)))
