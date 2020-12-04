(ns aoc-clj.2020.day3-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2020.day3 :refer :all]))

(def sample [ "..##......."
              "#...#...#.."
              ".#....#..#."
              "..#.#...#.#"
              ".#...##..#."
              "..#.##....."
              ".#.#.#....#"
              ".#........#"
              "#.##...#..."
              "#...##....#"
              ".#..#...#.#" ])

(deftest part1-test
  (testing "Given example"
    (is (= 7 (solve 1 3 sample)))))

(deftest part2-test
  (testing "Given example"
    (is (= 336 (*
                (solve 1 1 sample)
                (solve 1 3 sample)
                (solve 1 5 sample)
                (solve 1 7 sample)
                (solve 2 1 sample)
                )))))
