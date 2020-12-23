(ns aoc-clj.2020.day15
  (:require [clojure.string :as str]))

(def part1
  (fn [input]
    (let [nums (map read-string (str/split input #","))
          initial-map (into {} (map-indexed
                                #(vector %2 (list (inc %1)))
                                (drop-last nums)))
          recent (last nums)]
    (loop [records initial-map
           cursor recent
           time (count nums)]
      (if (= 2020 time)
        cursor
        (if-let [history (get records cursor)]
          (let [prev (take 2 (conj history time))
                say (- (first prev) (second prev))]
            (recur (assoc records cursor prev) say (inc time)))
          (recur (assoc records cursor (list time)) 0 (inc time))))))))

