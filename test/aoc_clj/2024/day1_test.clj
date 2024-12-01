(ns aoc-clj.2024.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day1 :refer :all]))

(def day1-sample (slurp "test/aoc_clj/2024/day1.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 11 (part1 day1-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 31 (part2 day1-sample)))))
