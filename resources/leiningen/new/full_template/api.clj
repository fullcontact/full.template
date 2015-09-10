(ns {{ name }}.api
  (:require [full.core.log :as log]
            [full.core.config :refer [opt] :as config]
            [full.dev :refer [start-nstracker]]
            [full.http.server :as serv]
            [full.async :refer [<?]])
  (:gen-class))

(def port (opt [:port] :default 8080))


(serv/defroutes app-routes
  (serv/GET "/public" _ {:body "OHAI"})

  (serv/GET "/hello/:username" {:keys [headers params]}
    (str "Hello" (:username params)))

  (serv/ANY "*" _ {:status 404 :body "Four-oh-Four"}))


(defn -main [& _]
  (config/configure)
  (log/configure)
  (start-nstracker)
  (serv/run-server #'app-routes {:port @port})
  (println "Hello."))
