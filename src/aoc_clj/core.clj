(ns aoc-clj.core
  (:require [clojure.tools.cli :refer [parse-opts]]
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
            [aoc-clj.2020.day16])
  (:gen-class))

(def cli-opts
  [["-h" "--help" "Help text"]
   ["-m" "--measure" "Measure function execution time (without executable startup)"]])

(def usage "\n\t<year> <day> <part> <path to challenge input>\n\n")

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-opts)]
    (if (cond
          (get options :help) (do (println usage summary) true)
          (== 4 (count arguments)) (let [[year day part file] arguments]
                                     (if-let [f (resolve (symbol (str "aoc-clj." year ".day" day "/part" part)))]
                                       (let [result (if (get options :measure)
                                                      (time (f (slurp file)))
                                                      (f (slurp file)))]
                                         ((println result) result))
                                       (println (str day " is unimplemented. Usage:\n"))))
          :else (do (println "Unknown command. Usage:\n") false))
      (System/exit 0)
      (((println usage summary) (System/exit 1))))))
