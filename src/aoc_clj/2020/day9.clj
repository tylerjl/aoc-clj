(ns aoc-clj.2020.day9
  (:use [clojure.math.combinatorics :only (combinations)])
  (:require [clojure.string :as str]))

(defn find-outlier
  [preamble input]
  (loop [idx preamble]
    (if (not-any? #(= (nth input idx) (+ (first %) (second %)))
                  (combinations (subvec input (- idx preamble) idx) 2))
      (nth input idx)
      (recur (inc idx)))))

(defn part1
  "Solves part 1."
  ([preamble content] (find-outlier preamble (mapv read-string (str/split-lines content))))
  ([content] (find-outlier 25 (mapv read-string (str/split-lines content)))))

(defn part2-internal
  "Solves part 2."
  [preamble content]
  (let [input (mapv read-string (str/split-lines content))
        needle (find-outlier preamble input)]
    (loop [span 2]
      (if-let [contiguous (some #(and (= needle (apply + %)) %) (partition span 1 input))]
        (+ (apply min contiguous) (apply max contiguous))
        (recur (inc span))))))

(defn part2
  ([preamble content] (part2-internal preamble content))
  ([content] (part2-internal 25 content)))
