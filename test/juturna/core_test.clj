(ns juturna.core-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [juturna.core :refer :all]))

(fact "default current age is set correctly"
  default-current-age => 27)

(deftest testing-annual-investment
  (fact "it computes reasonable investment rates"
    (annual-investment 25000 0.2) => 5000.0)
  (fact "it handles negative investment rates"
    (annual-investment 25000 -0.1) => -2500.0)
)

(deftest testing-annual-capital-gains
  (fact "it computes gains given typical inflation and return"
    (annual-capital-gains 3000 1.03 0.02) => (roughly 61.8))
  (fact "it handles deflation"
    (annual-capital-gains 3000 0.95 0.02) => 57.0)
)
