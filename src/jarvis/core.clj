;; Core isn't used in the application, but it's nice for the repl
;; because it loads in all the namespaces for the project.
;; This does mean the prereqs for things like environment and the db
;; stuff needs to be setup and recognizer will get allocated so if you
;; don't want this stuff just load whatever namespace you're working
;; on directly.

(ns jarvis.core
  (:require [jarvis.recognizer.core :as recognizer]
            [jarvis.environment :as environment]
            [jarvis.resources :as resources]
            [jarvis.db.postgres :as pg]
            [jarvis.db.redis :as redis]
            [jarvis.web :as web]
            [jarvis.worker :as worker]
            [jarvis.audio :as audio]
            [jarvis.rules :as rules]))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
