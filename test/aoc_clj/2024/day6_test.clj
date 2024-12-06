(ns aoc-clj.2024.day6-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day6 :refer :all]))

(def day6-sample (slurp "test/aoc_clj/2024/day6.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 41 (part1 day6-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 6 (part2 day6-sample)))))
