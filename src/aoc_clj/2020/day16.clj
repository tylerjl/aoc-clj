(ns aoc-clj.2020.day16
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def range-set
  (fn [limits]
    (let [[beg end] (str/split limits #"-")]
      (into #{} (range (read-string beg) (inc (read-string end)))))))

(def into-n-set
  (fn [line]
    (let [[a _ b] (str/split (second (str/split line #": ")) #" ")]
      (set/union (range-set a) (range-set b)))))

(def to-tickets
  (fn [line]
    (map read-string (str/split line #","))))

(def invalid-fields
  (fn [valids ticket]
    (remove #(contains? valids %) ticket)))

(def part1
  (fn [input]
    (let [[ranges mine raw-tickets] (str/split input #"\n\n")
          valids (reduce set/union
                         (map into-n-set (str/split-lines ranges)))
          tickets (map to-tickets
                       (drop 1 (str/split-lines raw-tickets)))]
      (->> tickets
           (map #(invalid-fields valids %))
           (map #(apply + %))
           (apply +)))))
