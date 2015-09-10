(defproject fullcontact/{{ name }} "latest"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [fullcontact/full.bootstrap "0.8.6"]]
  :main {{ name }}.api
  :resource-paths ["src/resources"]
  :target-path "target/%s"
  :plugins [[lein-midje "3.1.3"]]
  :profiles {:uberjar {:aot [{{ name }}.api]}
             :dev {:source-paths ["dev"]
                   :dependencies [[midje "1.6.3"]
                                  [midje-junit-formatter "0.1.0-SNAPSHOT"]]}})
