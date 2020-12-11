(ns aoc-clj.2020.day10-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day10 :refer :all]))

(def day10-sample "16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4")

(def day10-big "28\n33\n18\n42\n31\n14\n46\n20\n48\n47\n24\n23\n49\n45\n19\n38\n39\n11\n1\n32\n25\n35\n8\n17\n7\n9\n4\n2\n34\n10\n3\n")

(deftest part1-test
  (testing "Given example"
    (is (= 35 (part1 day10-sample)))
    (is (= 220 (part1 day10-big)))))

(deftest part2-test
  (testing "Given example"
    (is (= 8 (part2 day10-sample)))
    (is (= 19208 (part2 day10-big)))))
