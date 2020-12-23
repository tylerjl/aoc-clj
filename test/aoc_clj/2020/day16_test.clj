(ns aoc-clj.2020.day16-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day16 :refer :all]))

(def day16-sample
  (clojure.string/join
   "\n"
   ["class: 1-3 or 5-7"
    "row: 6-11 or 33-44"
    "seat: 13-40 or 45-50"
    ""
    "your ticket:"
    "7,1,14"
    ""
    "nearby tickets:"
    "7,3,47"
    "40,4,50"
    "55,2,20"
    "38,6,12"]))

(deftest part1-test
  (testing "Given example"
    (is (= 71 (part1 day16-sample)))))
