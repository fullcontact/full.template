(ns {{ name }}.t-api
  (:require [midje.sweet :refer :all]
            [{{ name }}.api :refer :all]))


(facts "Name formatting"
  (format-name "Joe") => "Hello, Joe")
