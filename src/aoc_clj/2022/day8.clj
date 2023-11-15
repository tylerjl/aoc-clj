(ns aoc-clj.2022.day8
  (:require [clojure.string :as str]
            [clojure.core.matrix :as m]))

(defn part1
  "Solve part 1"
  [content]
  (->> (str/split-lines content)
       (map #(str/split % #""))
       (map #(map read-string %))))
