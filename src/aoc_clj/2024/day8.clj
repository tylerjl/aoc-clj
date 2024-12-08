(ns aoc-clj.2024.day8
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [clojure.math.combinatorics :refer [combinations]]))

(defn alphanumeric? [c]
  (or (Character/isLetter c)
      (Character/isDigit c)))

(defn parse [input]
  (->> (s/split-lines input)
       (map #(map vector (range) %))
       (map vector (range))
       (mapcat (partial apply
                        (fn [coords letter]
                          (->> (filter #(alphanumeric? (second %)) letter)
                               (map (partial
                                     apply
                                     #(assoc {} %2 #{(vector %1 coords)})))))))
       (reduce (partial merge-with union))))

(defn left? [[x1 y2] [x2 y2]]
  ())

(defn antinode [p1 p2]
  (let [[[x1 y1] [x2 y2]] (if (< (first p1) (first p2)) [p1 p2] [p2 p1])
        delta-x (- x2 x1)
        delta-y (- y2 y1)
        shift-x (abs delta-x)
        shift-y (abs delta-y)
        slope (/ delta-y delta-x)
        [f-a f-b op-a op-b] (if (neg? slope)
                              [max min + -]
                              [min max - +])
        backstep (fn [[x y]] [(- x shift-x) (op-a y shift-y)])
        upstep (fn [[x y]] [(+ x shift-x) (op-b y shift-y)])]
    (vector (iterate backstep (vector (min x1 x2) (f-a y1 y2))) 
            (iterate upstep (vector (max x2 x1) (f-b y1 y2))))))

(defn in-bounds? [x-limit y-limit x y]
  (and (>= x 0) (>= y 0)
       (< x x-limit) (< y y-limit)))

(defn antinodes [f rows cols [antenna points]]
  (->> (combinations points 2)
       (mapcat (comp (partial apply f rows cols)
                     (partial apply antinode)))
       (filter (partial apply in-bounds? rows cols))
       (assoc {} antenna)))

(defn solve [input f]
  (let [rows (count (s/split-lines input))
        cols (count (first (s/split-lines input)))
        points (->> (parse input)
                    (map (partial antinodes f rows cols))
                    (map #(->> (vals %) (first) (into #{})))
                    (apply union))]
    (count points)))

(defn part1 [input]
  (solve input (fn [r c back forward]
                 (concat (take 1 (drop 1 back))
                         (take 1 (drop 1 forward))))))

(defn part2 [input]
  (solve input (fn [rows cols back forward]
                 (let [check (fn [[x y]] (in-bounds? rows cols x y))]
                   (concat (take-while check back)
                           (take-while check forward))))))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day8.txt")))
  (time (part1 (slurp "resources/2024/day8.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day8.txt")))
  (time (part2 (slurp "resources/2024/day8.txt"))))
