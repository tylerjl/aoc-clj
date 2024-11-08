(ns aoc-clj.2023.day14
  (:require [clojure.string :as s]
            [clojure.core.match :refer [match]]))

(defn step [[acc span] [idx slot]]
  (match slot
         \O [acc (inc span)]
         \# (let [adjusted (inc idx)]
              [(+ acc (reduce + (range (- adjusted span) adjusted))) 0])
         :else [acc span]))

(defn solve [input]
  (->> (s/split-lines input)
       (map seq)
       (apply aoc-clj.utils/transpose)
       (map (comp first
                  (partial reduce step [0 0])
                  (partial map-indexed vector)
                  reverse
                  (partial cons \#)))
       (reduce +)))

(defn part1 [input] (solve input))

(defn part2 [input] (solve input))

(comment
  (time (part1 (slurp "test/aoc_clj/2023/day14_test.txt")))
  (time (part1 (slurp "resources/2023/day14.txt")))
  (time (part2 (slurp "test/aoc_clj/2023/day14_test.txt"))))
