(ns aoc-clj.2020.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day1 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= 514579 (solve 2 [1721 979 366 299 675 1456])))))

(deftest part2-test
  (testing "Given example"
    (is (= 241861950 (solve 3 [1721 979 366 299 675 1456])))))

