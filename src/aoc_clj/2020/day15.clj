(ns aoc-clj.2020.day15
  (:require [clojure.string :as str]))

(def solve
  (fn [n input]
    (let [nums (map read-string (str/split input #","))
          recent (last nums)]
      (loop [records (transient (into (hash-map) (map-indexed
                                          #(vector %2 (inc %1))
                                          (drop-last nums))))
             cursor recent
             time (count nums)]
        (if (= n time)
          cursor
          (if-let [history (get records cursor)]
            (let [say (- time history)]
              (recur (assoc! records cursor time) say (inc time)))
            (recur (assoc! records cursor time) 0 (inc time))))))))

(def part1
  (fn [input]
    (solve 2020 input)))

(def part2
  (fn [input]
    (solve 30000000 input)))
