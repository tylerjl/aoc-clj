(ns aoc-clj.2019.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2019.day1 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= 2 (fuel-for 12)))
    (is (= 2 (fuel-for 14)))
    (is (= 654 (fuel-for 1969)))
    (is (= 33583 (fuel-for 100756)))))

(deftest part2-test
  (testing "Given example"
    (is (= 2 (backfuel 14)))
    (is (= 966 (backfuel 1969)))
    (is (= 50346 (backfuel 100756)))))
