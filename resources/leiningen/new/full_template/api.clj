(ns {{ name }}.api
  (:require [full.core.log :as log]
            [full.core.config :refer [opt] :as config]
            [full.core.dev :refer [start-nstracker]]
            [full.http.server :as serv])
  (:gen-class))


(def port (opt :port :default 8080))
(def request-logger (org.slf4j.LoggerFactory/getLogger "{{ name }}.request"))


(defn format-name [n]
  (str "Hello, " n))


(serv/defroutes app-routes
  (serv/GET "/" _
    {:status 200 :body {:message "Hello."}})

  (serv/GET "/hello/:username" {:keys [headers params]}
    {:status 200 :body {:message (format-name (:username params))}})

  (serv/ANY "*" _ {:status 404 :body {:message "Four-oh-Four"}}))

(defn -main [& _]
  (config/configure)
  (log/configure)
  (start-nstracker)
  (serv/run-server (serv/json-api #'app-routes :logger request-logger)
                   {:port @port})
  (println "{{ name }} running on" @port))
