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
    (annual-investment 25000 -0.1) => -2500.0))

(deftest testing-annual-capital-gains
  (fact "it computes gains given typical inflation and return"
    (annual-capital-gains 3000 1.03 0.02) => (roughly 61.8))
  (fact "it handles deflation"
    (annual-capital-gains 3000 0.95 0.02) => 57.0))

(deftest testing-next-year-net-worth
  (facts "using default values"
    (fact "it computes net worth with reasonable values"
      (next-year-net-worth 3000) => (roughly 6151.8))
    (fact "it computes net worth with small principal"
      (next-year-net-worth 12) => (roughly 3013.4))
    (fact "it computes net worth with significant principal"
      (next-year-net-worth 4000000) => 4205400.0)))

(deftest testing-net-worth-at
  (facts "using default values"
    (fact "it computes future net worth with reasonable values"
      (net-worth-at 65 25 3000) => (roughly 389357.66))))
