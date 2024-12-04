(ns aoc-clj.2024.day4
  (:require [clojure.string :as s]))

(defn all-directions [x y]
  "Given coordinates, return the list of directions to search"
  (list
   [[x y] [(+ x 1) y] [(+ x 2) y] [(+ x 3) y]] ;; East
   [[x y] [(- x 1) y] [(- x 2) y] [(- x 3) y]] ; West
   [[x y] [x (+ y 1)] [x (+ y 2)] [x (+ y 3)]] ; South
   [[x y] [x (- y 1)] [x (- y 2)] [x (- y 3)]] ; North
   [[x y] [(+ x 1) (- y 1)] [(+ x 2) (- y 2)] [(+ x 3) (- y 3)]] ; NE
   [[x y] [(- x 1) (- y 1)] [(- x 2) (- y 2)] [(- x 3) (- y 3)]] ; NW
   [[x y] [(- x 1) (+ y 1)] [(- x 2) (+ y 2)] [(- x 3) (+ y 3)]] ; SW
   [[x y] [(+ x 1) (+ y 1)] [(+ x 2) (+ y 2)] [(+ x 3) (+ y 3)]] ; SE
   ))

(defn x-mas [x y]
  "Given coordinates, return the crosswise paths to search"
  (list [[(+ x 1) (- y 1)] [x y] [(- x 1) (+ y 1)]] ; NE -> SW
        [[(- x 1) (- y 1)] [x y] [(+ x 1) (+ y 1)]] ; NW -> SE
        [[(- x 1) (+ y 1)] [x y] [(+ x 1) (- y 1)]] ; SW -> NE
        [[(+ x 1) (- y 1)] [x y] [(- x 1) (- y 1)]] ; SE -> NW
        ))

(def x-letters {'(\A \S \M) 2})

(defn solve [input]
  "Accept the puzzle grid and return [coordinates, grid]"
  (->> (s/split-lines input)
       (map #(map vector (range) %))
       (map vector (range))
       (mapcat (partial apply (fn [coords letter]
                                (map (partial apply #(vector (vector coords %1) %2)) letter))))
       (#(vector (map first %) (into {} %)))))

(defn part1 [input]
  (let [[letter-positions points] (solve input)]
    (->> (mapcat (comp (partial map #(map (partial get points) %))
                       #(all-directions (first %) (second %)))
                 letter-positions)
         (filter #(= '(\X \M \A \S) %))
         (count))))

(defn part2 [input]
  (let [[letter-positions points] (solve input)]
    (->> (pmap (partial apply x-mas) letter-positions)
         (pmap (partial map (partial map (partial get points))))
         (pmap frequencies)
         (filter #(or (= 2 (get % '(\S \A \M))) (= 2 (get % '(\M \A \S)))))
         (count))))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day4.txt")))
  (time (part1 (slurp "resources/2024/day4.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day4.txt")))
  (time (part2 (slurp "resources/2024/day4.txt"))))

