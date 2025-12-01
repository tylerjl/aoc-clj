(ns aoc-clj.2015.day1
  (:require
   [clojure.core.match :refer [match]]
   [clojure.string :as s]))

(defn part1 [input]
  (->> input
       seq
       (map #(match %1 \) -1 \( 1))
       (reduce +)))

(comment
  (part1 (slurp "resources/2015/day1.txt")))
