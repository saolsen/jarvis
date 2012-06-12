(ns jarvis.worker)

;; The worker reads from a redis work queue.
;; There are two tasks that the worker will do.
;; Transcribe Audio
;; - retrieves search graph from redis,
;; - - If the model doesn't exist it does the App Change logic then continues
;; - plugs search graph into recognizer
;; - transcribes text
;; - publishes result in client's result queue
;; App Change
;; - retrieves the postgres rules for the app
;; - generates a new search graph (for use in the recognizer)
;; - sticks the model in redis

(defn -main
  "Start the worker process."
  [& args]
  (println "Starting Worker Process."))
