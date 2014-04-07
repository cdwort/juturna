(ns juturna.core
  (:gen-class))

(def default-current-age 27)

(def default-inflation 1.03) 

(def default-return 0.02)

(def default-investment-rate 0.12) ;; Investment rate of 12%

(def default-salary 25000)

(defn annual-capital-gains 
  [curr-net-worth annual-inflation annual-return]

  (* curr-net-worth annual-inflation annual-return )
)

(defn annual-investment
  [salary investment-rate]

  (* salary investment-rate )
)

(defn next-year-net-worth 
  [curr-net-worth]

  (+ (* curr-net-worth default-investment-rate)
     (annual-investment default-salary default-investment-rate)
     (annual-capital-gains curr-net-worth default-inflation default-return))
)

(defn net-worth-at
  "Determines the individual's net worth at the given age considering current age and net worth"
  [target-age curr-age curr-net-worth]

  (def years-to-target (- target-age curr-age))

  (loop [net-worth       curr-net-worth 
         years-remaining years-to-target]
    (if (> years-remaining 0) 
      net-worth
      (recur (next-year-net-worth net-worth) (dec years-remaining))))
)

(defn -main
  "I am documentation"
  [& args]

  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))

  (println (str "Your current Net Worth is:" (first args)))
  (println (str "Your projected Net Worth at 95: " (net-worth-at 95 default-current-age (first args))))
  (println (str "Your projected Net Worth at 100: " (net-worth-at 100 default-current-age (first args))))
  )
