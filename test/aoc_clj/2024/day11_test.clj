(ns aoc-clj.2024.day11-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day11 :refer :all]))

(def day11-sample (slurp "test/aoc_clj/2024/day11.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 55312 (part1 day11-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 65601038650482 (part2 day11-sample)))))
