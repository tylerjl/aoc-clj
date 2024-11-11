(ns aoc-clj.2023.day14-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2023.day14 :refer :all]))

(def day14-sample (slurp "test/aoc_clj/2023/day14.txt"))

(deftest part1-test
  (testing "Given example"
    (is (= 136 (part1 day14-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 64 (part2 day14-sample)))))
