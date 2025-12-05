(ns aoc-clj.2025.day5-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2025.day5 :refer :all]))

(def day5-sample (slurp "test/aoc_clj/2025/day5.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 3 (part1 day5-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 14 (part2 day5-sample)))))
