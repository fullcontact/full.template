(ns leiningen.new.full-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))


(def render (renderer "full-template"))


(defn full-template
  "Generates new full.template project"
  [name]
  (let [data {:name name
              :capitalized (clojure.string/capitalize name)
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh full.template project.")
    (->files data
      ["src/{{sanitized}}/api.clj" (render "api.clj" data)]
      ["test/{{sanitized}}/t_api.clj" (render "t_api.clj" data)]
      ["project.clj" (render "project.clj" data)]
      ["README.md" (render "README.md" data)]

      ; Config
      ["dev.yaml" (render "dev.yaml" data)]

      ; Log
      ["log.xml" (render "log.xml" data)])))
