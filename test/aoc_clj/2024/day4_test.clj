(ns aoc-clj.2024.day4-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day4 :refer :all]))

(def day4-sample (slurp "test/aoc_clj/2024/day4.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 18 (part1 day4-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 9 (part2 day4-sample)))))
