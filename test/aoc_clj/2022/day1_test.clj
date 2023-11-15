(ns aoc-clj.2022.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2022.day1 :refer :all]))

(def day1-sample (slurp "test/aoc_clj/2022/day1.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 24000 (part1 day1-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 45000 (part2 day1-sample)))))
