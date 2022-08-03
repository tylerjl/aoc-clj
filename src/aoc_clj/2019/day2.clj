(ns aoc-clj.2019.day2
  (:require [instaparse.core :as insta]))

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
  [_]
  (println "unimplemented"))
