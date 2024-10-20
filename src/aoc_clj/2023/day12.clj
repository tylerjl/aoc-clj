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

(def solve
  (memoize (fn [solution springs slots]
             (match [springs slots]
                    ;; No more springs to process - at the end of the row - and
                    ;; we've fulfilled all slots
                    [([] :seq) ([] :seq)] 1
                    ;; No more springs to process but remaining unfulfilled
                    ;; slots
                    [([] :seq) _] 0
                    [([:broken & _] :seq) ([] :seq)] 0
                    ;; Next is a broken followed by a blank space; check and
                    ;; mark the span
                    [([:broken spring & rest-springs] :seq) ([slot & rest-slots] :seq)]
                    (match spring
                           :broken (solve (inc solution) (rest springs) slots)
                           :operational (if (= (inc solution) slot)
                                          (solve 0 (rest springs) rest-slots)
                                          0)
                           :unknown
                           (+ (solve solution (cons :broken (cons :broken rest-springs)) slots)
                              (solve solution (cons :broken (cons :operational rest-springs)) slots)))
                    ;; Unknown token
                    [([:unknown & rest-springs] :seq) _]
                    (+ (solve solution (cons :broken rest-springs) slots)
                       (solve solution (cons :operational rest-springs) slots))
                    ;; Next position is operational?
                    [([:operational & rest-springs] :seq) _]
                    (solve 0 rest-springs slots)))))

(defn arrangement [springs slots]
  (solve 0 (concat springs '(:operational)) slots))

(defn part1 [input]
  (->> (split-lines input)
       (pmap (partial p/parse-all parse-12))
       (pmap (partial apply arrangement))
       (clojure.core.reducers/fold +)))

(defn unfold [springs slots]
  [(flatten (interpose '(:unknown) (repeat 5 springs)))
   (flatten (repeat 5 slots))])

(defn part2 [input]
  (->> (split-lines input)
       (map (partial p/parse-all parse-12))
       (map (partial apply unfold))
       (map (partial apply arrangement))
       (reduce +)))

(time (part2 (slurp "resources/2023/day12.txt")))
