(ns aoc-clj.2024.day23
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [clojure.core.reducers :refer [fold]]))

(defn part1 [input]
  (->> (s/split-lines input)
       (map #(s/split % #"-"))
       (map #(into {} (vector %)))))

(defn part2 [input]
  (->> (s/split-lines input)
       (count)))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day23.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day23.txt"))))
