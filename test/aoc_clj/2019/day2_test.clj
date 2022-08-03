(ns aoc-clj.2019.day2-test
  (:require [clojure.test :refer :all]
            [aoc-clj.2019.day2 :refer :all]))

(deftest part1-test
  (testing "Given example"
    (is (= (vec [2 0 0 0 99]) (solve (parse "1,0,0,0,99"))))
    (is (= (vec [2 3 0 6 99]) (solve (parse "2,3,0,3,99"))))
    (is (= (vec [2 4 4 5 99 9801]) (solve (parse "2,4,4,5,99,0")))
    (is (= (vec [30 1 1 4 2 5 6 0 99]) (solve (parse "1,1,1,4,99,5,6,0,99")))))))
