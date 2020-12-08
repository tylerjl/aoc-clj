(ns aoc-clj.2020.day7-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day7 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= 4 (part1 (clojure.string/join
                     "\n"
                     ["light red bags contain 1 bright white bag, 2 muted yellow bags."
                      "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
                      "bright white bags contain 1 shiny gold bag."
                      "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
                      "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
                      "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
                      "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."]))))))

(deftest part2-test
  (testing "Given example"
    (is (= 126 (part2
                (clojure.string/join
                 "\n"
                 ["shiny gold bags contain 2 dark red bags."
                  "dark red bags contain 2 dark orange bags."
                  "dark orange bags contain 2 dark yellow bags."
                  "dark yellow bags contain 2 dark green bags."
                  "dark green bags contain 2 dark blue bags."
                  "dark blue bags contain 2 dark violet bags."
                  "dark violet bags contain no other bags."]))))))
