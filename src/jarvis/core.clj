;; Core isn't used in the application, but it's nice for the repl
;; because it lets me load in all the namespaces.

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
