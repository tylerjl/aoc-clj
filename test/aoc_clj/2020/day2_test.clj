(ns aoc-clj.2020.day2-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day2 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= 2 (solve "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n" policy-one)))))

(deftest part2-test
  (testing "Given example"
    (is (= 1 (solve "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n" policy-two)))))

