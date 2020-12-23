(ns aoc-clj.2020.day15-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day15 :refer :all]))

(def day15-sample "0,3,6")

(deftest part1-test
  (testing "Given example"
    (is (= 436 (part1 day15-sample)))))
