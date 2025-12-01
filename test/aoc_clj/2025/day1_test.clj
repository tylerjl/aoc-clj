(ns aoc-clj.2025.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2025.day1 :refer :all]))

(def day1-sample (slurp "test/aoc_clj/2025/day1.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 3 (part1 day1-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 6 (part2 day1-sample)))))
