(ns aoc-clj.2024.day7-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day7 :refer :all]))

(def day7-sample (slurp "test/aoc_clj/2024/day7.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 3749 (part1 day7-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 11387 (part2 day7-sample)))))
