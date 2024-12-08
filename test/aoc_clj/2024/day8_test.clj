(ns aoc-clj.2024.day8-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day8 :refer :all]))

(def day8-sample (slurp "test/aoc_clj/2024/day8.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 14 (part1 day8-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 34 (part2 day8-sample)))))
