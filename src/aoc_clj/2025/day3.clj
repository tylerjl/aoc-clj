(ns aoc-clj.2025.day3
  (:require
   [clojure.math :as m]
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]))

(defn max-jolt [digits]
  (let [begin (apply max (butlast digits))
        [_ end] (split-with (comp not (partial = begin)) digits)]
    [(first end) (apply max (rest end))]))

(defn solve [input]
  (->> (s/split-lines input)
       (map (partial map #(Character/digit % 10)))
       (map max-jolt)
       (map (comp read-string (partial apply str)))
       (fold +)))

(defn part1 [input]
  (solve input))

(defn part2 [input]
  (count (s/split-lines input)))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day3.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day3.txt")))
  (time (part1 (slurp "resources/2025/day3.txt")))
  (time (part2 (slurp "resources/2025/day3.txt"))))
