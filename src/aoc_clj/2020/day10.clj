(ns aoc-clj.2020.day10
  (:use [clojure.math.combinatorics :only (combinations)])
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn shape
  [input]
  (->> (str/split-lines input)
       (map read-string)
       (sort)
       (#(concat (cons 0 %) (list (+ 3 (last %)))))))

(defn part1
  "Solves part 1."
  [input]
  (->> (shape input)
       (partition 2 1)
       (map #(Math/abs (apply - %)))
       (filter #(or (= 1 %) (= 3 %)))
       (frequencies)
       (vals)
       (apply *)))

(def bag-lookup (memoize valid-connections))

(defn valid-connections
  [bag index]
  (cond
    (<= index 0) 1
    :else (let [connections (set/intersection bag (set (range (- index 3) index)))]
            (apply + (map #(bag-lookup bag %) connections)))))

(defn part2
  [input]
  (let [plugs (shape input)]
    (bag-lookup (set (butlast plugs)) (last plugs))))
