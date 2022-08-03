(ns aoc-clj.2019.day2
  (:require [instaparse.core :as insta]
            [clojure.math.combinatorics :as combo]))

(def parser
  (insta/parser
   "<S> := INT (<','> INT)* <\"\n\">?
    INT := #'[0-9]+'"))

(def parse-tree
  {:INT read-string})

(defn parse
  [s]
  (vec (flatten
        (insta/transform parse-tree (parser s)))))

(defn solve
  [instructions]
  (loop [inst instructions
         p 0]
    (if (or (>= p (count inst)) (= 99 (nth inst p)))
      inst
      (let [a (nth inst (nth inst (+ 1 p)))
            b (nth inst (nth inst (+ 2 p)))
            r (case (nth inst p) 1 (+ a b) 2 (* a b))
            new-inst (assoc inst (nth inst (+ 3 p)) r)]
        (recur new-inst (+ 4 p))))))

(defn part1
  "Solves part 1."
  [input]
  (let [ins (parse input)
        patched (assoc (assoc ins 1 12) 2 2)]
    (solve patched)))

(defn part2
  "Solves part 2."
  [input]
  (let [orig (parse input)
        nouns (range 0 99)
        verbs (range 0 99)
        prod (combo/cartesian-product nouns verbs)]
    (loop [[n v] (first prod)
           r (rest prod)]
      (let [patched (assoc (assoc orig 1 n) 2 v)
            s (nth (solve patched) 0)]
        (if (= s 19690720)
          (+ v (* 100 n))
          (recur (first r) (rest r)))))))
