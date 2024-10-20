(ns aoc-clj.2023.day12-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2023.day12 :refer :all]))

(def day12-sample (slurp "test/aoc_clj/2023/day12.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 21 (part1 day12-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 525152 (part2 day12-sample)))))

