(ns aoc-clj.2023.day12
  (:require [clojure.string :refer [split-lines]]
            [jasentaa.monad :as m]
            [jasentaa.position :refer :all]
            [jasentaa.parser :as p]
            [jasentaa.parser.basic :as b]
            [jasentaa.parser.combinators :refer :all]
            [clojure.core.match :refer [match]]))

(declare parse-12)

(defn p-sym [char sym]
  (m/do* (token (b/sat (partial = char))) (m/return sym)))

(def p-spring
  (any-of (p-sym \? :unknown) (p-sym \. :operational) (p-sym \# :broken)))

(def parse-12
  (m/do*
    (springs <- (plus p-spring))
    space
    (slots <- (separated-by aoc-clj.utils/parse-number (b/match ",")))
    (m/return [springs slots])))

(def expand
  (memoize (fn [expansion springs slots]
             (match springs
                    ;; No other springs to build with
                    ([] :seq) (if (empty? slots) 1 0)
                    ;; Next spring is a broken one, and
                    ([:broken spring & others] :seq)
                    (match spring
                           ;; Following is also broken
                           :broken (expand (inc expansion) (rest springs) slots)
                           ;; Following is an operational spring:
                           ;; this is the one case in which we've
                           ;; completed the termination of a broken
                           ;; spring "run"
                           :operational (if (= (inc expansion) (first slots))
                                          (expand 0 (rest springs) (rest slots))
                                          0)
                           ;; Following is a wildcard, split it
                           :unknown
                           (+ (expand expansion (cons :broken (cons :broken others)) slots)
                              (expand expansion (cons :broken (cons :operational others)) slots)))
                    ;; Next is a wildcard
                    ([:unknown & others] :seq)
                    (+ (expand expansion (cons :broken others) slots)
                       (expand expansion (cons :operational others) slots))
                    ;; Next is operational
                    ([:operational & others] :seq)
                    (expand expansion others slots)))))

(defn solve [springs slots]
  (expand 0 (apply list (conj (vec springs) :operational)) slots))

(defn part1 [input]
  (->> (split-lines input)
       (pmap (partial p/parse-all parse-12))
       (pmap (partial apply solve))
       (clojure.core.reducers/fold +)))

(defn unfold [springs slots]
  [(flatten (interpose :unknown (repeat 5 springs)))
   (flatten (repeat 5 slots))])

(defn part2 [input]
  (->> (split-lines input)
       (map (partial p/parse-all parse-12))
       (map (partial apply unfold))
       (map (partial apply solve))
       (clojure.core.reducers/fold +)))

(comment
 (time (part2 "????.######..#####. 1,6,5"))

 (let [input (slurp "resources/2023/day12.txt")]
   (time (part2 input)))

 (time (part1 "?###???????? 3,2,1")))
