(ns aoc-clj.2025.day5
  (:require
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]
   [helins.interval.set :as iset]))

(defn parse [input]
  (let [[ranges ids] (->> (s/split input #"\R\R") (map s/split-lines))]
    [(->> ranges
          (map #(->> (s/split % #"-")
                     (map read-string)))
          (reduce #(-> %1
                       (iset/mark (first %2) (inc (second %2))))
                  iset/empty))
     (map read-string ids)]))

(defn part1 [input]
  (let [[ranges ids] (parse input)]
    (->> ids
         (filter #(get ranges %))
         (count))))

(defn part2 [input]
  (->> (parse input)
       (first)
       (pmap #(- (second %) (first %)))
       (fold +)))

(comment
  (time (part1' (slurp "test/aoc_clj/2025/day5.txt")))
  (time (part2' (slurp "test/aoc_clj/2025/day5.txt")))
  (time (part1' (slurp "resources/2025/day5.txt")))
  (time (part2' (slurp "resources/2025/day5.txt"))))
