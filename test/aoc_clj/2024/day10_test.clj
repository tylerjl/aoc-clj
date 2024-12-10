(ns aoc-clj.2024.day10-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day10 :refer :all]))

(def day10-sample (slurp "test/aoc_clj/2024/day10.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 36 (part1 day10-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 81 (part2 day10-sample)))))
