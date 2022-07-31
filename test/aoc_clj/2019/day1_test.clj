(ns aoc-clj.2019.day1-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2019.day1 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= 2 (solve 12)))
    (is (= 2 (solve 14)))
    (is (= 654 (solve 1969)))
    (is (= 33583 (solve 100756)))))
