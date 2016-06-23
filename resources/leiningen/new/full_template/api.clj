(ns {{ name }}.api
  (:require [full.core.log :as log]
            [full.core.config :refer [opt] :as config]
            [full.core.dev :refer [start-nstracker]]
            [full.http.server :as serv]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.params :refer [wrap-params]])
  (:gen-class))


(def port (opt :port :default 8080))
(def request-logger (org.slf4j.LoggerFactory/getLogger "{{ name }}.request"))


(serv/defroutes app-routes
  (serv/GET "/public" _
    {:status 200 :body {:message "Hello"}})

  (serv/GET "/hello/:username" {:keys [headers params]}
    {:status 200 :body {:message (str "Hello, " (:username params))}})

  (serv/ANY "*" _ {:status 404 :body {:message "Four-oh-Four"}}))

(defn api
  "API middleware"
  [routes]
  (-> (serv/json-response> routes)
      (serv/log-track-request> :logger request-logger)
      wrap-keyword-params
      wrap-params))

(defn -main [& _]
  (config/configure)
  (log/configure)
  (start-nstracker)
  (serv/run-server (api #'app-routes) {:port @port})
  (println "{{ name }} running on" @port))
