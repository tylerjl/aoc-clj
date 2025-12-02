(ns aoc-clj.2025.day2
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn num-digits [num]
  (if (zero? num)
    1
    (loop [n num
           x 0]
      (if (zero? n)
        x
        (recur (quot n 10) (inc x))))))

(defn num-partition [num]
  (let [digits (num-digits num)]
    (if (even? digits)
      (let [middle (m/pow 10 (/ digits 2))
            part (/ num middle)
            high (int part)
            low (m/round (* (- part high) middle))]
        [high low])
      nil)))

(defn invalid? [num]
  (if-let [[high low] (num-partition num)]
    (= high low)))

(defn parse [input]
  (->> (s/split input #",")
       (map (comp vec (partial map read-string) #(s/split % #"-")))
       (map (partial apply #(range %1 (inc %2))))
       (mapcat (partial filter invalid?))
       (reduce +)))

(defn solve [input]
  (parse input))

(defn part1 [input]
  (solve input))

(comment
  (part1 (slurp "test/aoc_clj/2025/day2.txt"))
  (part1 (slurp "resources/2025/day2.txt")))
