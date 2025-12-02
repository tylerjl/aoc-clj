(ns aoc-clj.2025.day2
  (:require
   [clojure.math :as m]
   [clojure.core.reducers :refer [fold]]
   [clojure.string :as s]))

(defn num-digits [num]
  (if (zero? num)
    1
    (loop [n num
           x 0]
      (if (zero? n)
        x
        (recur (quot n 10) (inc x))))))

(defn num-span [num span]
  (loop [num num
         width (num-digits num)
         result (list)]
    (if (<= width 0)
      result
      (recur (quot num (m/pow 10 span))
             (- width span)
             (cons (int (mod num (m/pow 10 span))) result)))))

(defn duplicates? [nums]
  (= 1 (count (set nums))))

(defn num-spans [num]
  (let [digits (num-digits num)]
    (->> (range 1 (inc (/ digits 2)))
         (filter #(zero? (mod digits %)))
         (map (partial num-span num)))))

(defn invalid? [num f]
  (let [digits (num-digits num)]
    (and (>= digits 2)
         (some duplicates? (f num)))))

(defn solve [input f]
  (->> (s/split input #",")
       (map (comp vec (partial map read-string) #(s/split % #"-")))
       (map (partial apply #(range %1 (inc %2))))
       (mapcat (partial filter #(invalid? % f)))
       (fold +)))

(defn part1 [input]
  (solve input #(list (num-span % (/ (num-digits %) 2)))))

(defn part2 [input]
  (solve input num-spans))

(comment
  (time (part1 (slurp "test/aoc_clj/2025/day2.txt")))
  (time (part2 (slurp "test/aoc_clj/2025/day2.txt")))
  (time (part1 (slurp "resources/2025/day2.txt")))
  (time (part2 (slurp "resources/2025/day2.txt"))))
