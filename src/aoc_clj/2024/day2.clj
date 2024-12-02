(ns aoc-clj.2024.day2
  (:require
   [clojure.string :as s]
   [clojure.set :refer [superset?]]))

(def step-down #{-1 -2 -3})
(def step-up #{1 2 3})

(defn is-safe [numbers]
  (let [steps (set (map (partial apply -) (partition 2 1 numbers)))]
    (or (superset? step-up steps) (superset? step-down steps))))

(defn sublists [numbers]
  (map (comp
        #(concat (first %) (rest (second %)))
        #(split-at % numbers))
       (range 0 (count numbers))))

(defn solve [p input]
  (->> (s/split-lines input)
       (pmap (comp
              p
             #(map read-string %)
             #(s/split % #" ")))
       (filter identity)
       (count)))

(defn part1 [input] (solve is-safe input))

(defn part2 [input]
  (solve #(some is-safe (cons % (sublists %))) input))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day2.txt")))
  (time (part1 (slurp "resources/2024/day2.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day2.txt")))
  (time (part2 (slurp "resources/2024/day2.txt"))))
