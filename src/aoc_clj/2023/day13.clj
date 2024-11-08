(ns aoc-clj.2023.day13
  (:require [clojure.string :as s]
            [clojure.core.match :refer [match]]))

(defn str->as-bits [str]
  (-> (s/replace str "#" "1")
      (s/replace "." "0")))

(defn str->as-num [n]
  (Integer/parseInt (str->as-bits n) 2))

(defn a-prefix? [a b need-flip]
  (match [a b]
         [([] :seq) ([] :seq)] (and true (not need-flip))
         [([] :seq)         _] (and true (not need-flip))
         [_         ([] :seq)] (and true (not need-flip))
         [([ah & ar] :seq) ([bh & br] :seq)]
         (let [result (bit-xor ah bh)
               one-flipped (= 1 (Integer/bitCount result))]
           (if (not (or (zero? result)
                        (and one-flipped need-flip)))
             false
             (recur ar br (if need-flip (not one-flipped) false))))))

(defn mirror [rows need-flip]
  (loop [grow (list (first rows))
         shrink (rest rows)]
    (match [grow shrink]
           [_ ([] :seq)] false
           [a b] (if (a-prefix? a b need-flip)
                   (count a)
                   (recur (cons (first shrink) grow)
                          (rest shrink))))))

(defn reflect [lines need-flip]
  (or (mirror (map str->as-num (map (partial apply str)
                                    (apply aoc-clj.utils/transpose
                                           (map seq lines))))
              need-flip)
      (* 100 (mirror (map str->as-num lines) need-flip))))

(defn solve [input flip]
  (->> (s/split input #"\n\n")
       (pmap (comp #(reflect % flip) s/split-lines))
       (reduce +)))

(defn part1 [input] (solve input false))

(defn part2 [input] (solve input true))

(comment
  (time (part1 (slurp "test/aoc_clj/2023/day13.txt")))
  (time (part2 (slurp "resources/2023/day13.txt")))
  (time (part2 (slurp "test/aoc_clj/2023/day13.txt"))))
