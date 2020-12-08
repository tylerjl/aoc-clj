(ns aoc-clj.2020.day8-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day8 :refer :all]))

(def day8-sample
  (clojure.string/join
   "\n"
   ["nop +0"
    "acc +1"
    "jmp +4"
    "acc +3"
    "jmp -3"
    "acc -99"
    "acc +1"
    "jmp -4"
    "acc +6"]))

(deftest part1-test
  (testing "Given example"
    (is (= 5 (part1 day8-sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 8 (part2 day8-sample)))))
