(ns aoc-clj.2020.day9-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day9 :refer :all]))

(def day9-sample "35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576\n")

(deftest part1-test
  (testing "Given example"
    (is (= 127 (part1 5 day9-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 62 (part2 5 day9-sample)))))
