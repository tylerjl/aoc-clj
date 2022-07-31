(ns aoc-clj.2019.day1
  (:require
   [clojure.string :refer [split-lines]]
   [clojure.math :refer [floor]]))

(defn fuel-for
  "Calculate req'd fuel for given mass"
  [mass] (max 0 (- (int (floor (/ mass 3))) 2)))

(defn backfuel
  "Recursively add fuel"
  [mass]
  (loop [fuel (fuel-for mass)
         acc 0]
     (if (zero? fuel)
       acc
       (recur (fuel-for fuel) (+ fuel acc)))))

(defn part1
  "Solves part 1."
  [input]
  (->> (split-lines input)
       (map (comp fuel-for read-string))
       (reduce +)))

(defn part2
  "Solves part 2."
  [input]
  (->> (split-lines input)
       (map (comp backfuel read-string))
       (reduce +)))
