(ns aoc-clj.2023.day1
  (:require [clojure.string :refer [split-lines]]
            [clojure.core.reducers :refer [fold]]
            [instaparse.core :as insta]))

(def a
  (insta/parser
   "<S> := ( <LETTER> | DIGIT )*
    LETTER := #'[a-z]'
    DIGIT := #'[1-9]'"))

(def b
  (insta/parser
   "<S> := ( SPELLED / DIGIT / <LETTER> )*
    SPELLED := ( ONE | TWO | THREE | FOUR | FIVE | SIX | SEVEN | EIGHT | NINE ) <LETTER>
    ONE := &\"one\"
    TWO := &\"two\"
    THREE := &\"three\"
    FOUR := &\"four\"
    FIVE := &\"five\"
    SIX := &\"six\"
    SEVEN := &\"seven\"
    EIGHT := &\"eight\"
    NINE := &\"nine\"
    LETTER := #'[a-z]'
    DIGIT := #'[1-9]'"))

(def parse-tree
  {:DIGIT read-string,
   :SPELLED (fn [num] (case (first num)
                        :ONE 1
                        :TWO 2
                        :THREE 3
                        :FOUR 4
                        :FIVE 5
                        :SIX 6
                        :SEVEN 7
                        :EIGHT 8
                        :NINE 9
                        _ 0))})

(defn parse [p s]
  (insta/transform parse-tree (p s)))

(defn to-nums ([l c]
  (cond
    (= \1 c) (cons 1 l)
    (= \2 c) (cons 2 l)
    (= \3 c) (cons 3 l)
    (= \4 c) (cons 4 l)
    (= \5 c) (cons 5 l)
    (= \6 c) (cons 6 l)
    (= \7 c) (cons 7 l)
    (= \8 c) (cons 8 l)
    (= \9 c) (cons 9 l)
    :else l))
  ([] '()))

(defn line->num
  ([parser line]
   (let [digits (parse parser line)]
     (+ (* 10 (first digits)) (last digits))))
  ([line]
   (let [digits (fold to-nums (seq line))]
     (+ (* 10 (last digits)) (first digits)))))

(defn solve [proc input]
  (->> (split-lines input) (pmap proc) (fold +)))

(defn part1 [input] (solve #(line->num a %) input))
(defn part2 [input] (solve #(line->num b %) input))
