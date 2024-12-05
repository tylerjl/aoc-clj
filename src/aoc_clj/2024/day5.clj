(ns aoc-clj.2024.day5
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [clojure.core.reducers :refer [fold]]))

(defn valid [rules update]
  (->> update
       (map-indexed #(every? (partial contains? (get rules %2))
                             (take %1 update)))
       (every? identity)))

(defn parse [input]
  (let [[raw-rules raw-updates] (s/split input #"\n\n")
        rules (->> (s/split-lines raw-rules)
                   (map #(s/split % #"\|"))
                   (map #(map read-string %))
                   (map #(vector (second %) (set (vector (first %)))))
                   (map #(into {} (vector %)))
                   (reduce (partial merge-with union) {}))
        updates (->> (s/split-lines raw-updates)
                     (map (comp #(map read-string %) #(s/split % #","))))]
    (vector rules updates)))

(defn summarize [updates]
  (->> updates
       (map #(nth % (/ (count %) 2)))
       (fold +)))

(defn part1 [input]
  (let [[rules updates] (parse input)]
    (->> (filter (partial valid rules) updates)
         (summarize))))

(defn part2 [input]
  (let [[rules updates] (parse input)
        wrong (filter #(not (valid rules %)) updates)
        sorter (fn [a b] (if-let [befores (get rules a)]
                           (not (contains? befores b))
                           (> a b)))]
    (->> (map (partial sort-by identity sorter) wrong)
         (summarize))))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day5.txt")))
  (time (part1 (slurp "resources/2024/day5.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day5.txt")))
  (time (part2 (slurp "resources/2024/day5.txt"))))

