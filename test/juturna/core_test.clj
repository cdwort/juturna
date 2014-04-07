(ns juturna.core-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [juturna.core :refer :all]))

(fact "default current age is set correctly"
  default-current-age => 27)
