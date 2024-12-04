(ns aoc-clj.2024.day3-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day3 :refer :all]))

(def day3-sample (slurp "test/aoc_clj/2024/day3.txt"))
(def day3-2-sample (slurp "test/aoc_clj/2024/day3-2.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 161 (part1 day3-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 48 (part2 day3-2-sample)))))
