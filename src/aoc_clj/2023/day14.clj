(ns aoc-clj.2023.day14
  (:require [aoc-clj.utils :refer [transpose]]
            [clojure.string :as s]
            [clojure.core.match :refer [match]]
            [clojure.core.reducers :refer [fold]]))

(defn step [sum [idx slot]]
  (match slot
         \O (+ sum (inc idx))
         :else sum))

(defn solve [input]
  (->> input
       (pmap (comp (partial reduce step 0)
                  (partial map-indexed vector)))
       (fold +)))

(defn transform [input]
  (->> input
       s/split-lines
       (map seq)))

(defn tilt [rows]
  (map (comp flatten
             (partial map sort)
             (partial partition-by #(= \# %)))
       rows))

(defn render [rows]
  (->> rows
       (map (partial apply str))
       (s/join "\n")))

(defn clockwise [rows]
  (->> rows
       (apply transpose)
       (map reverse)))

(defn spin [rows]
  (->> rows
       clockwise
       tilt
       clockwise
       tilt
       clockwise
       tilt
       clockwise
       tilt))

(defn cycle [n rows]
  (loop [[state & states] (iterate spin rows)
         cache (transient {})
         idx 0]
    (cond (= n idx) state
          (cache state)
          (cycle (mod (- n (cache state)) (- idx (cache state))) state)
          :else
          (recur states (conj! cache [state idx]) (inc idx)))))

(defn part1 [input]
  (->> input
       transform
       clockwise
       tilt
       solve))

(defn part2 [input]
  (->> input
       transform
       (cycle 1000000000)
       clockwise
       solve))

(comment
  (time (part1 (slurp "test/aoc_clj/2023/day14.txt")))
  (time (part2 (slurp "test/aoc_clj/2023/day14.txt"))))
