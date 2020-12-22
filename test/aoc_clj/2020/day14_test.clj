(ns aoc-clj.2020.day14-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day14 :refer :all]))

(def day14-sample
  (clojure.string/join
   "\n"
   ["mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
    "mem[8] = 11"
    "mem[7] = 101"
    "mem[8] = 0"]))

(def day14-part2-sample
  (clojure.string/join
   "\n"
   ["mask = 000000000000000000000000000000X1001X"
    "mem[42] = 100"
    "mask = 00000000000000000000000000000000X0XX"
    "mem[26] = 1"]))

(deftest part1-test
  (testing "Given example"
    (is (= 165 (part1 day14-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 208 (part2 day14-part2-sample)))))
