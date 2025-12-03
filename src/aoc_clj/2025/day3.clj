(ns aoc-clj.2025.day3
  (:require
   [clojure.math :as m]
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]))

(defn parse [input]
  (->> (s/split-lines input)
       (map (partial map #(Character/digit % 10)))))

(defn max-indexed [n coll]
  (apply max-key second (reverse (drop-last n (map-indexed vector coll)))))

(defn max-jolt [goal digits]
  (loop [digits digits
         total (transient [])]
    (if (= goal (count total))
      (persistent! total)
      (let [[idx digit] (max-indexed (- (dec goal) (count total)) digits)]
        (recur (drop (inc idx) digits) (conj! total digit))))))

(defn solve [input n]
  (->> (parse input)
       (pmap (comp read-string
                   (partial apply str)
                   (partial max-jolt n)))
       (fold +)))

(defn part1 [input] (solve input 2))

(defn part2 [input] (solve input 12))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day3.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day3.txt")))
  (time (part1 (slurp "resources/2025/day3.txt")))
  (time (part2 (slurp "resources/2025/day3.txt"))))
