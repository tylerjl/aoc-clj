(ns aoc-clj.2025.day7
  (:require
   [clojure.core.reducers :refer [fold]]
   [clojure.core.match :refer [match]]
   [clojure.set :as set]
   [clojure.string :as s]))

(defn unzip
  "Unzips a sequence of sequences (or vectors) into separate sequences,
  where each resulting sequence contains elements from a specific index."
  [coll]
  (apply map vector coll))

(defn parse [input]
  (let [rows (s/split-lines input)]
    [(rest rows) (s/index-of (first rows) "S")]))

(defn cascade [[splits tachyons] row]
  (let [[new-splits beams]
        (->> tachyons
             (pmap (fn [n]
                     (match (nth row n)
                            \. [0 #{n}]
                            \^ [1 #{(inc n) (dec n)}])))
             (unzip))]
    [(+ splits (apply + new-splits))
     (apply set/union beams)]))

(defn part1 [input]
  (let [[rows tachyons] (parse input)]
    (->> (reduce cascade [0 #{tachyons}] rows)
         (first))))

(defn timelines [tachyons row]
  (->> tachyons
       (pmap (comp (partial into {})
                   (fn [[idx n]]
                     (match (nth row idx)
                            \. [[idx n]]
                            \^ [[(inc idx) n] [(dec idx) n]]))))
       (apply merge-with +)))

(defn part2 [input]
  (let [[rows tachyons] (parse input)]
    (->> (reduce timelines {tachyons 1} rows)
         (vals)
         (fold +))))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day7.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day7.txt")))
  (time (part1 (slurp "resources/2025/day7.txt")))
  (time (part2 (slurp "resources/2025/day7.txt"))))
