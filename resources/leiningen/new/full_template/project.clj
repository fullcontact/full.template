(defproject fullcontact/{{ name }} "latest"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [fullcontact/full.core "0.10.1" :exclusions [org.clojure/clojurescript]]
                 [fullcontact/camelsnake "0.9.0"]
                 [fullcontact/full.json "0.10.1"]
                 [fullcontact/full.metrics "0.11.4" :exclusions [fullcontact/full.async]]
                 [fullcontact/full.cache "1.0.1" :exclusions [fullcontact/full.async]]
                 [fullcontact/full.http "1.0.1" :exclusions [fullcontact/full.async]]
                 [fullcontact/full.async "1.0.0" :exclusions [org.clojure/clojurescript]]]
  :main {{ name }}.api
  :resource-paths ["resources"]
  :target-path "target/%s"
  :plugins [[lein-midje "3.1.3"]]
  :profiles {:uberjar {:aot [{{ name }}.api]}
             :dev {:source-paths ["dev"]
                   :dependencies [[midje "1.8.3"]]}})
