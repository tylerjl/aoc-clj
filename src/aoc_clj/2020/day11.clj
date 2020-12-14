(ns aoc-clj.2020.day11
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def shape
  "Turn puzzle input into set of points"
  (fn [input]
    (->> (str/split-lines input)
         (map #(str/split % #""))
         (map-indexed
          (fn [i cs]
            (map #(list i (first %))
                 (filter #(= "L" (second %))
                         (map-indexed vector cs)))))
         (flatten)
         (partition 2)
         (#(zipmap % (repeat false))))))

(def neighbors
  (memoize
   (fn [point]
     (let [x (first point)
           y (second point)]
       (set (partition
             2 (list (inc x) y (inc x) (dec y) x (dec y) (dec x) (dec y)
                     (dec x) y (dec x) (inc y) x (inc y) (inc x) (inc y))))))))

(def visible-neighbors
  (fn [observer candidate]
    (let [x (first observer)
          y (second observer)]
      (or
       (= x (first candidate))
       (= y (second candidate))
       (= (- x (first candidate)) (- y (second candidate)))))))

(def step-seating
  (fn [crowded f seats]
    (into {}
          (map
           (fn [seat]
             (let [adjacent (f (remove #(= (first seat) (first %)) seats) seat)]
               (cond
                 (= 0 (count adjacent)) (vector (first seat) true)
                 (>= (count adjacent) crowded) (vector (first seat) false)
                 :else seat)))
              seats))))

(def solve
  (fn [crowded pred input]
    (loop [seating (shape input)]
      (let [new-seating (step-seating crowded pred seating)]
        (if (= seating new-seating)
          (count (filter second seating))
          (recur new-seating))))))

(def part1-pred
  (fn [coll seat]
    (let [occupied-seats (set (map first (filter second coll)))]
      (set/intersection occupied-seats (neighbors (first seat))))))

(def part1
  "Solves part 1."
  (fn [input] (solve 4 part1-pred input)))

(def pcenter
  (fn [origin point]
    (list (- (first point) (first origin)) (- (second point) (second origin)))))

(def cardinal
  (fn [point]
    (let [dx (compare (first point) 0)
          dy (compare (second point) 0)]
      (cond
        (and (= 1 dx) (= 0 dy)) 'east
        (and (= 1 dx) (= -1 dy)) 'southeast
        (and (= 0 dx) (= -1 dy)) 'south
        (and (= -1 dx) (= -1 dy)) 'southwest
        (and (= -1 dx) (= 0 dy)) 'west
        (and (= -1 dx) (= 1 dy)) 'northwest
        (and (= 0 dx) (= 1 dy)) 'north
        (and (= 1 dx) (= 1 dy)) 'northeast))))

(def filter-neighbors
  (fn [coll point]
    (keys
     (group-by cardinal
               (map #(pcenter point %)
                    (filter (partial
                             (fn [pt candidate]
                               (visible-neighbors pt candidate)) point) coll))))))

(def part2
  "Solves part 2."
  (fn [input] (solve 5 filter-neighbors input)))
