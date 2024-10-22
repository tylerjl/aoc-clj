(ns aoc-clj.2023.day13
  (:require [clojure.string :as s]
            [clojure.core.match :refer [match]]))

(defn str->as-bits [str]
  (-> (s/replace str "#" "1")
      (s/replace "." "0")))

(defn str->as-num [n]
  (Integer/parseInt (str->as-bits n) 2))

(defn transpose [& xs]
  (apply map list xs))

(defn a-prefix? [a b]
  (match [a b]
         [([] :seq) ([] :seq)] true
         [([] :seq)         _] true
         [_         ([] :seq)] true
         [([ah & ar] :seq) ([bh & br] :seq)]
         (if (not (= ah bh)) false (recur ar br))))

(defn mirror [rows]
  (loop [grow (list (first rows))
         shrink (rest rows)]
    (match [grow shrink]
           [_ ([] :seq)] false
           [a b] (if (a-prefix? a b)
                   (count a)
                   (recur (cons (first shrink) grow)
                          (rest shrink))))))

(defn solve [lines]
  (or (mirror (map str->as-num (map (partial apply str)
                                    (apply transpose (map seq lines)))))
      (* 100 (mirror (map str->as-num lines)))))

(defn part1 [input]
  (->> (s/split input #"\n\n")
       (map (comp solve s/split-lines))
       (reduce +)))

(comment
  (part1 (slurp "test/aoc_clj/2023/day13.txt")))
