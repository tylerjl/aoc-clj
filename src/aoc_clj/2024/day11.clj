(ns aoc-clj.2024.day11
  (:require [clojure.string :as s]))

(def blink
  (memoize
   (fn [steps stone]
     (cond
       (= steps 0) 1
       (= 0 stone) (blink (dec steps) 1)
       (even? (count (str stone))) (let [s (str stone)
                                         [a b] (split-at (/ (count s) 2) s)]
                                     (+ (blink (dec steps) (->> (apply str a) (Integer/parseInt)))
                                        (blink (dec steps) (->> (apply str b) (Integer/parseInt)))))
       true (blink (dec steps) (* stone 2024))))))

(defn solve [input steps]
  (->> (s/split (s/trim input) #" ")
       (map read-string)
       (pmap (partial blink steps))
       (reduce +)))

(defn part1 [input]
  (solve input 25))

(defn part2 [input]
  (solve input 75))

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day11.txt")))
  (time (part1 (slurp "resources/2024/day11.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day11.txt")))
  (time (part2 (slurp "resources/2024/day11.txt"))))
