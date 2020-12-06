(ns aoc-clj.2020.day6-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day6 :refer :all]))

(def sample "abc\n\na\nb\nc\n\nab\nac\n\na\na\na\na\n\nb")

(deftest part1-test
  (testing "Given example"
    (is (= 11 (part1 sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 6 (part2 sample)))))
