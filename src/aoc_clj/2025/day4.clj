(ns aoc-clj.2025.day4
  (:require
   [aoc-clj.utils :refer [into-grid neighbors]]
   [clojure.math :as m]
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]))

(defn reachable [points [point _]]
  (< (->> (neighbors point)
          (filter #(= \@ (get points %)))
          (count)) 4))

(defn rolls [points] (filter #(= \@ (second %)) points))

(defn solve [points point] (reachable points point))

(defn part1 [input]
  (let [points (into-grid input)]
    (->> (into-grid input)
         (rolls)
         (filter (partial solve points))
         (count))))

(defn part2 [input]
  (let [points (into-grid input)
        initial (count (rolls points))]
    (loop [points points]
      (let [new-rolls (->> (rolls points) (filter (partial solve points)))]
        (if (zero? (count new-rolls))
          (- initial (count (rolls points)))
          (recur (apply dissoc points (map first new-rolls))))))))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day4.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day4.txt")))
  (time (part1 (slurp "resources/2025/day4.txt")))
  (time (part2 (slurp "resources/2025/day4.txt"))))
