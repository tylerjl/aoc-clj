(ns aoc-clj.2024.day3
  (:require [clojure.string :as s]
            [clojure.core.reducers :refer [fold]]
            [clojure.core.match :refer [match]]))

(defn part1 [input]
  (->> (re-seq #"mul\((\d+),(\d+)\)" input)
       (map (comp (partial apply *) (partial map read-string) rest))
       (fold +)))

(defn part2 [input]
  (->> (s/split input #"do\(\)")
       (map (comp first #(s/split % #"don't\(\)")))
       (apply str)
       (part1)))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day3.txt")))
  (time (part1 (slurp "resources/2024/day3.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day3-2.txt")))
  (time (part2 (slurp "resources/2024/day3.txt"))))

