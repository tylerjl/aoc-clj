(ns aoc-clj.2019.day1
  (:require
   [clojure.string :refer [split-lines]]
   [clojure.math :refer [floor]]))

(defn fuel-for
  "Calculate req'd fuel for given mass"
  [mass] (- (int (floor (/ mass 3))) 2))

(defn solve
  "Performs the actual solution logic."
  [input]
  (fuel-for input))

(defn part1
  "Solves part 1."
  [input]
  (->> (split-lines input)
       (map (comp solve read-string))
       (reduce +)))

(defn part2
  "Solves part 2."
  [_]
  (println "unimplemented"))
