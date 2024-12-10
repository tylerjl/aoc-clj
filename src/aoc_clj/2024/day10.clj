(ns aoc-clj.2024.day10
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]))

(defn adjacent  [row col] (list [row (+ col 1)]
                                [(+ row 1) col]
                                [row (- col 1)]
                                [(- row 1) col]))

(defn count-hikes [solved pred level row col]
  (if (= level 9)
    (solved [[row col]])
    (map (partial apply count-hikes solved pred (inc level))
         (filter (partial pred level)
                 (adjacent row col)))))

(defn solve [solution combine input]
  (let [grid (->> (s/split-lines input)
                  (map (partial map (comp read-string str))))
        ascending? (fn [level [row col]]
                     (let [step (nth (nth grid row []) col nil)]
                       (= (+ level 1) step)))
        hike (fn [row-idx col-idx level]
               (if (= 0 level)
                 (->> (count-hikes solution ascending? 0 row-idx col-idx)
                      (flatten)
                      (combine))
                 nil))
        traverse (fn [row-idx row]
                   (map-indexed (partial hike row-idx) row))]
    ;; (map-indexed traverse grid)
    (->> (map-indexed traverse grid)
         (flatten)
         (filter identity))))

(defn part1 [input]
  (->> (solve set #(->> (apply union %) (count)) input)
       (reduce +)))

(defn part2 [input]
  (->> (solve (constantly true) identity input)
       (count)))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day10.txt")))
  (time (part1 (slurp "resources/2024/day10.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day10.txt")))
  (time (part2 (slurp "resources/2024/day10.txt"))))
