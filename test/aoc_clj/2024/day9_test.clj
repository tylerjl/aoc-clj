(ns aoc-clj.2024.day9-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2024.day9 :refer :all]))

(def day9-sample (slurp "test/aoc_clj/2024/day9.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 1928 (part1 day9-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 2858 (part2 day9-sample)))))
