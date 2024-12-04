(ns aoc-clj.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [criterium.core :as criterium]
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
            [aoc-clj.2024.day4])
  (:gen-class))

(def cli-opts
  [["-h" "--help" "Help text"]
   ["-m" "--measure" "Measure function execution time (without executable startup)"]
   ["-b" "--bench" "Benchmark the given day/part"]])

(def usage "\n\t<year> <day> <part> <path to challenge input>\n\n")

(defn -main [& args]
  (let [{:keys [options arguments _ summary]} (parse-opts args cli-opts)]
    (cond
      ;; Help
      (get options :help)
      (do (println usage summary) true)
      ;; Asking for a day’s solution
      (== 4 (count arguments))
      (let [[year day part file] arguments]
        (if-let [f (resolve (symbol (str "aoc-clj." year ".day" day "/part" part)))]
          (let [input (slurp file)]
            (when (get options :bench)
              (criterium/quick-bench (f input)))
            (println (if (get options :measure)
                       (time (f input))
                       (f input))))
          (println (str day " is unimplemented. Usage:\n") usage)))
      ;; Otherwise, print help
      :else
      (println "Unknown command. Usage:\n" summary)))
  ;; Potential cleanup
  (shutdown-agents))
