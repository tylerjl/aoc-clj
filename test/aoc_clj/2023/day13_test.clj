(ns aoc-clj.2023.day13-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2023.day13 :refer :all]))

(def day13-sample (slurp "test/aoc_clj/2023/day13.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 405 (part1 day13-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 400 (part2 day13-sample)))))
