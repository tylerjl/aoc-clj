(ns aoc-clj.2024.day1
  (:require
   [aoc-clj.utils :refer [transpose]]
   [clojure.string :as s]))

(defn parse [input]
  (->> (s/split-lines input)
       (map (comp
             (partial map Integer/parseInt)
             #(s/split % #"\s+")))
       (apply transpose)))

(defn part1 [input]
  (->> (parse input)
       (map sort)
       (apply map -)
       (map abs)
       (reduce +)))

(defn part2 [input]
  (let [columns (parse input)
        occurrences (frequencies (second columns))]
    (reduce + (map #(* %1 (get occurrences %1 0)) (first columns)))))

(comment
  (time (part1 (slurp "resources/2024/day1.txt")))
  (time (part2 (slurp "resources/2024/day1.txt"))))
