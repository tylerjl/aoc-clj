(ns aoc-clj.2024.day2-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day2 :refer :all]))

(def day2-sample (slurp "test/aoc_clj/2024/day2.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 2 (part1 day2-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 4 (part2 day2-sample)))))
