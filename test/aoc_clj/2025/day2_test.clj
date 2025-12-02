(ns aoc-clj.2025.day2-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2025.day2 :refer :all]))

(def day2-sample (slurp "test/aoc_clj/2025/day2.txt"))

(deftest num-digits-test
  (testing "Basic numbers"
    (is (= 1 (num-digits 1)))
    (is (= 1 (num-digits 0)))
    (is (= 2 (num-digits 55)))
    (is (= 3 (num-digits 123)))))

(deftest part1-test
  (testing "Given example"
    (is (= 1227775554 (part1 day2-sample)))))
