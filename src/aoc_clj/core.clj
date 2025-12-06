(ns aoc-clj.core
  (:require [cli-matic.core :refer [run-cmd]]
            [clojure.string :as s]
            [criterium.core :as criterium]
            [aoc-clj.utils :refer [challenge]]
            [aoc-clj.2015.day1]
            [aoc-clj.2019.day1]
            [aoc-clj.2019.day2]
            [aoc-clj.2020.day1]
            [aoc-clj.2020.day2]
            [aoc-clj.2020.day3]
            [aoc-clj.2020.day4]
            [aoc-clj.2020.day5]
            [aoc-clj.2020.day6]
            [aoc-clj.2020.day7]
            [aoc-clj.2020.day8]
            [aoc-clj.2020.day9]
            [aoc-clj.2020.day10]
            [aoc-clj.2020.day11]
            [aoc-clj.2020.day14]
            [aoc-clj.2020.day15]
            [aoc-clj.2020.day16]
            [aoc-clj.2022.day1]
            [aoc-clj.2022.day8]
            [aoc-clj.2023.day1]
            [aoc-clj.2023.day12]
            [aoc-clj.2023.day13]
            [aoc-clj.2023.day14]
            [aoc-clj.2024.day1]
            [aoc-clj.2024.day2]
            [aoc-clj.2024.day3]
            [aoc-clj.2024.day4]
            [aoc-clj.2024.day5]
            [aoc-clj.2024.day6]
            [aoc-clj.2024.day7]
            [aoc-clj.2024.day10]
            [aoc-clj.2024.day11]
            [aoc-clj.2024.day12]
            [aoc-clj.2024.day23]
            [aoc-clj.2025.day1]
            [aoc-clj.2025.day2]
            [aoc-clj.2025.day3]
            [aoc-clj.2025.day4]
            [aoc-clj.2025.day5]
            [aoc-clj.2025.day6])
  (:gen-class))

(defn solve [{:keys [measure bench year day part input]}]
  (if-let [f (resolve (symbol (str "aoc-clj." year ".day" day "/part" part)))]
    (if bench
      (criterium/quick-bench (f input))
      (println (if measure (time (f input)) (f input))))
    (println (str day " is unimplemented.")))
  ;; Potential cleanup
  (shutdown-agents))

(def CONFIGURATION
  {:app {:command "aoc-clj"
         :version "1.0.0"
         :description "Advent of Code solutions in Clojure"}
   :commands [{:command "solve" :short "s"
               :description "Solve a puzzle"
               :opts [{:option "measure" :short "m" :type :with-flag
                       :as  "Measure function execution time (without executable startup)"}
                      {:option "bench" :short "b" :type :with-flag
                       :as "Benchmark the given day/part"}
                      {:option "year" :short 0 :type :int :default :present
                       :as "Year of challenge to solve"}
                      {:option "day" :short 1 :type :int :default :present
                       :as "Day of challenge to solve"}
                      {:option "part" :short 2 :type :int :default :present
                       :as "Part 1 or 2 of solution"}
                      {:option "input" :short 3 :type :slurp :default :present
                       :as "Path to file containing puzzle input"}]
               :runs solve}
              {:command "download" :short "d"
               :description "Download a day puzzle input"
               :opts [{:option "year" :short 0 :type :int :default :present
                       :as "Year of challenge to retrieve"}
                      {:option "day" :short 1 :type :int :default :present
                       :as "Day of challenge to solve"}]
               :runs challenge}]})

(defn -main [& args]
  (run-cmd args CONFIGURATION))
