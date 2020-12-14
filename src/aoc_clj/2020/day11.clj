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
         (into #{}))))

(def neighbors
  (memoize (fn [point]
             (let [x (first point)
                   y (second point)]
               (into #{}
                     (partition 2 (list (inc x) y (inc x) (dec y) x (dec y) (dec x) (dec y)
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
  (fn [pred myfilter unoccupied occupied]
    (reduce (partial (fn [seated [new-unocc new-occ] empty-seat]
                       (let [adjacent (count (myfilter seated empty-seat))]
                         (if (pred adjacent)
                           (list new-unocc (conj new-occ empty-seat))
                           (list (conj new-unocc empty-seat) new-occ))))
                     occupied)
            (list #{} #{}) unoccupied)))

(def step
  (fn [crowded pred unoccupied occupied]
    (let [[new-unoccupied new-occupied] (step-seating #(= 0 %) pred unoccupied occupied)
          [x y] (step-seating #(< % crowded) pred occupied occupied)]
      (list (set/union new-unoccupied x) (set/union new-occupied y)))))

(def solve
  (fn [crowded pred input]
    (loop [unoccupied (shape input)
           occupied #{}]
      (let [[new-unocc new-occ] (step crowded pred unoccupied occupied)]
        (if (and (= unoccupied new-unocc) (= occupied new-occ))
          (count occupied)
          (recur new-unocc new-occ))))))

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

(def part1
  "Solves part 1."
  (fn [input] (solve 4 #(set/intersection (neighbors %2) %1) input)))

(def part2
  "Solves part 2."
  (fn [input] (solve 5 filter-neighbors input)))
