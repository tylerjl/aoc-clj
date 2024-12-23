(ns aoc-clj.2024.day12
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]))

(defn adjacent  [row col] (list [row (+ col 1)]
                                [(+ row 1) col]
                                [row (- col 1)]
                                [(- row 1) col]))

(defn edge [plot id [row col]]
  (let [neighbor (nth (nth plot row []) col nil)]
    (or (nil? neighbor) (not (= id neighbor)))))

(defn traverse [plot seen traversed [row-idx col-idx]]
  (if (or (contains? traversed [row-idx col-idx]) (contains? seen [row-idx col-idx]))
    {}
    (let [plant (nth (nth plot row-idx) col-idx)
          edges (group-by (partial edge plot plant) (adjacent row-idx col-idx))]
      (merge {[row-idx col-idx] (count (get edges true))}
             (->> (map #(traverse plot seen (union traversed (set [[row-idx col-idx]])) %)
                       (get edges false))
                  (apply merge))))))

(defn solve [input]
  (let [plots (map seq (s/split-lines input))
        [rows cols] [(count plots) (count (first plots))]]
    (loop [seen (transient #{})
           perimeters '()
           row-idx 0
           col-idx 0]
      (cond
        (>= row-idx rows) perimeters
        (>= col-idx cols) (recur seen perimeters (inc row-idx) 0)
        (contains? seen [row-idx col-idx]) (recur seen perimeters row-idx (inc col-idx))
        true (let [perimeter (traverse plots seen {} [row-idx col-idx])]
               (recur (reduce conj! seen (set (keys perimeter)))
                      (cons [(count (keys perimeter)) (reduce + (vals perimeter))] perimeters)
                      row-idx
                      (inc col-idx)))))))

(defn part1 [input]
  (->> (solve input)
       (map (partial apply *))
       (reduce +)))

(defn part2 [input]
  (solve input))

(comment
  (time (part1 "AAB"))
  (time (part1 "AAAA\nBBCD\nBBCC\nEEEC"))
  (time (part1 (slurp "test/aoc_clj/2024/day12.txt")))
  (time (part1 (slurp "resources/2024/day12.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day12.txt")))
  (time (part2 (slurp "resources/2024/day12.txt"))))
