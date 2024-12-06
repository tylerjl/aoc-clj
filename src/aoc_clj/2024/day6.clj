(ns aoc-clj.2024.day6
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [clojure.core.match :refer [match]]))

(defn parse [input]
  (->> (s/split-lines input)
       (map #(map vector (range) %))
       (map vector (range))
       (mapcat (partial apply
                        (fn [coords letter]
                          (map (partial
                                apply
                                #(vector (vector %1 coords) %2))
                               letter))))
       (into {})))

(defn traverse [grid [start-x start-y] direction]
  (let [[f static start] (match direction
                                'north [dec start-x start-y]
                                'west [dec start-y start-x]
                                'south [inc start-x start-y]
                                'east [inc start-y start-x])
        point (cond (or (= 'north direction) (= 'south direction))
                    #(vector static %)
                    true #(vector % static))
        span (take-while #(let [slot (get grid (point %))]
                            (not (or (nil? slot) (= \# slot))))
                         (iterate f start))
        end (point (last span))
        rotation (match direction
                        'north 'east
                        'east 'south
                        'south 'west
                        'west 'north)]
    (vector rotation
            (into #{} (map point span))
            (if (get grid (point (f (last span)))) end nil))))

(defn solve [grid start]
  (loop [position start
         direction 'north
         visited #{}]
    (let [[new-dir points new-pos] (traverse grid position direction)]
      (if (not new-pos)
        (union visited points)
        (recur new-pos new-dir (union visited points))))))

(defn part1 [input]
  (let [grid (parse input)
        start (first (keep #(when (= (val %) \^) (key %)) grid))]
    (count (solve grid start))))

(defn cycles [start grid]
  (loop [position start
         direction 'north
         positions #{}]
    (let [[new-dir _ new-pos] (traverse grid position direction)]
      (if (contains? positions [new-dir new-pos])
        true
        (if (not new-pos)
          false
          (recur new-pos new-dir (conj positions [new-dir new-pos])))))))

(defn part2 [input]
  (let [grid (parse input)
        start (first (keep #(when (= (val %) \^) (key %)) grid))
        path (filter #(not (= start %)) (solve grid start))]
    (->> path
         (pmap #(cycles start (assoc grid % \#)))
         (filter identity)
         (count))))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day6.txt")))
  (time (part1 (slurp "resources/2024/day6.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day6.txt")))
  (time (part2 (slurp "resources/2024/day6.txt"))))
