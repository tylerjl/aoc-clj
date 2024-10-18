(ns aoc-clj.2023.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2023.day1 :refer :all]))

(def day1-part1-sample (slurp "test/aoc_clj/2023/day1-part1.txt"))
(def day1-part2-sample (slurp "test/aoc_clj/2023/day1-part2.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 142 (part1 day1-part1-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 281 (part2 day1-part2-sample)))))
