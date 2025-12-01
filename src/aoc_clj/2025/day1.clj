(ns aoc-clj.2025.day1
  (:require
   [aoc-clj.utils :as u]
   [clojure.core.match :refer [match]]
   [clojure.string :as s]
   [jasentaa.monad :as m]
   [jasentaa.parser :as p]
   [jasentaa.parser.combinators :refer :all]))

(declare parse-1)

(def parse-1
  (m/do*
    (direction <- (any-of (u/p-sym \L -) (u/p-sym \R +)))
    (number <- u/parse-number)
    (m/return {:op direction :n number})))

(def dial (concat (range 100) (range 100) (range 100)))

(defn rotate [{:keys [p z-end z-click]} {:keys [op n]}]
  (let [turn (op p (mod n 100))
        position (nth dial (+ 100 turn))
        full-turns (quot n 100)
        click (if (and (or (neg? turn) (> turn 100))
                       (not (zero? position))
                       (not (zero? p)))
                (inc z-click)
                z-click)]
    {:p position
     :z-end (if (zero? position) (inc z-end) z-end)
     :z-click (if (zero? full-turns) click (+ full-turns click))}))

(defn solve [input]
  (->> input
       (s/split-lines)
       (map (partial p/parse-all parse-1))
       (reduce rotate {:p 50 :z-end 0 :z-click 0})))

(defn part1 [input] (:z-end (solve input)))

(defn part2 [input]
  (let [solution (solve input)]
    (+ (:z-end solution)
       (:z-click solution))))

(comment
  (part1 (slurp "test/aoc_clj/2025/day1.txt"))
  (part1 (slurp "resources/2025/day1.txt"))
  (part2 (slurp "resources/2025/day1.txt")))
