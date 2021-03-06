(ns jarvis.environment)
;; Deals with environment variables

;; Code that deals with extracting values from environment variables.
;; These must all be defined anywhere this app is run.

;; POSTGRES_URL
;; REDIS_QUEUE_URL
;; REDIS_STORE_URL

;;(def vars
;;  (let [env-vars (System/getenv)]
;;    (if (every? #(contains? env-vars %)
;;                ["BASEURL" "READAPIKEY" "READAPISECRET"])
;;      {:BASEURL (get env-vars "BASEURL")
;;       :READAPIKEY (get env-vars "READAPIKEY")
;;       :READAPISECRET (get env-vars "READAPISECRET")}
;;      (throw (Exception. "Please Define All Environment Variables")))))
