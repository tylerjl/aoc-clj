(ns aoc-clj.2020.day11-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day11 :refer :all]))

(def day11-sample
  (clojure.string/join
   "\n"
   ["L.LL.LL.LL"
    "LLLLLLL.LL"
    "L.L.L..L.."
    "LLLL.LL.LL"
    "L.LL.LL.LL"
    "L.LLLLL.LL"
    "..L.L....."
    "LLLLLLLLLL"
    "L.LLLLLL.L"
    "L.LLLLL.LL"]))

(deftest part1-test
  (testing "Given example"
    (is (= 37 (part1 day11-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 26 (part2 day11-sample)))))
