(ns jarvis.transcriber.recognizer
  (:require clojure.java.io)
  (:import
   (java.io File)
   (edu.cmu.sphinx.util.props ConfigurationManager)
   (edu.cmu.sphinx.recognizer Recognizer)))

(def file "10001-90210-01803.wav")

(defn get-cm
  "Gets a configuration Manager"
  []
  (let [config-file (clojure.java.io/resource "config-file.xml")]
    (ConfigurationManager. config-file)))

(def cm (get-cm))

(defn get-recognizer
  "Creates a recognizer"
  []
  (doto (.lookup cm "recognizer")
    (.allocate)))

(defn set-audiofile
  [filename]
  (let [resource (clojure.java.io/resource filename)]
    (doto (.lookup cm "audioFileDataSource")
      (.setAudioFile (File. filename) nil))))

(defn go!
  [rec]
  (.getBestResultNoFiller (.recognize rec)))