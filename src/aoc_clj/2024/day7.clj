(ns aoc-clj.2024.day7
  (:require [clojure.string :as s]
            [clojure.core.reducers :as r]
            [clojure.core.match :refer [match]]))

(defn parse [input]
  (let [p-line (fn [line]
                 (let [[goal operators] (s/split line #": ")]
                   [(read-string goal)
                    (map read-string (s/split operators #" "))]))]
    (->> input
         (s/split-lines)
         (map p-line))))

(defn solve [goal sum all-operators op-fn]
  (let [operation (first all-operators)
        operations (rest all-operators)]
    (if (nil? operation)
      (= goal sum)
      (if (> sum goal)
        false
        (some #(solve goal (% sum operation) operations op-fn) op-fn)))))

(defn valid [fns goal operations]
  (if (solve goal (first operations) (rest operations) fns) goal 0))

(defn part-n [input fns]
  (->> (parse input)
       (pmap (partial apply valid fns))
       (r/fold +)))

(defn part1 [input]
  (part-n input [* +]))

(defn part2 [input]
  (part-n input [* + (comp read-string str)]))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day7.txt")))
  (time (part1 (slurp "resources/2024/day7.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day7.txt")))
  (time (part2 (slurp "resources/2024/day7.txt"))))
