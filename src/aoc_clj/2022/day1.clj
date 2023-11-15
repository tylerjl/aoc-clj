(ns aoc-clj.2022.day1
  (:require [clojure.string :as str]))

(defn solve
  "Intermediate solution"
  [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map #(map read-string %))
       (map #(reduce + %))))

(defn part1
  "Solve part 1"
  [content]
  (->> (solve content)
       (apply max)))

(defn part2
  "Solve part 1"
  [content]
  (->> (solve content)
       (sort)
       (reverse)
       (take 3)
       (reduce +)))
