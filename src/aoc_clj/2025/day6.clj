(ns aoc-clj.2025.day6
  (:require
   [aoc-clj.utils :refer [transpose]]
   [clojure.core.match :refer [match]]
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]))

(defn spl [re s] (s/split s re))

(defn calc [input]
  (reduce (->> input (last) (read-string) (resolve))
          (->> input (butlast) (map read-string))))

(defn part1 [input]
  (->> (s/split-lines input)
       (pmap (comp (partial spl #"\s+")
                   s/trim))
       (apply transpose)
       (map calc)
       (fold +)))

(defn partition-op [string]
  (let [[begin end] (->> string
                         (split-with #(and (not= \+ %) (not= \* %))))
        [op other] (split-at 1 end)]
    (apply str (concat begin " " other " " op))))

(defn part2 [input]
  (->> (s/split-lines input)
       (map seq)
       (apply transpose)
       (apply concat)
       (apply str)
       (spl #" {5}")
       (pmap (comp calc
                   (partial spl #"\s+")
                   s/trim
                   partition-op))
       (fold +)))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day6.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day6.txt")))
  (time (part1 (slurp "resources/2025/day6.txt")))
  (time (part2 (slurp "resources/2025/day6.txt"))))
