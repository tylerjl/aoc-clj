(ns aoc-clj.2020.day5-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day5 :refer :all]))

(def ticket "FBFBBFFRLR")

(deftest part1-solve-test
  (testing "Given example"
    (is (= 357 (solve ticket)))))
