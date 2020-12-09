(ns aoc-clj.2020.day1
  (:require [clojure.math.combinatorics :as combo]))

(defn solve
  "Performs the actual solution logic."
  [count values]
  (first
   (->> (combo/combinations values count)
        (map (fn [x] [(apply + x) x]))
        (filter (fn [x] (== 2020 (first x))))
        (map second)
        (map (fn [x] (apply * x))))))

(defn part1
  "Solves part 1."
  [content]
  (solve 2 (map read-string (clojure.string/split-lines content))))

(defn part2
  "Solves part 2."
  [content]
  (solve 3 (map read-string (clojure.string/split-lines content))))
