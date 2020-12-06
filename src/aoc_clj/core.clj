(ns aoc-clj.core
  (:require [aoc-clj.2020.day1 :as day1]
            [aoc-clj.2020.day2 :as day2]
            [aoc-clj.2020.day3 :as day3]
            [aoc-clj.2020.day4 :as day4]
            [aoc-clj.2020.day5 :as day5]
            [aoc-clj.2020.day6 :as day6]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-opts
  [["-h" "--help" "Help text"]])

(def usage "\n\tday<number> part<number> <path to challenge input>\n\n")

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-opts)]
    (cond
      (get options :help) (println usage summary)
      (== 3 (count arguments)) (let [[day part file] arguments]
                                 (case [day part]
                                   ["day1" "part1"] (println (day1/part1 file))
                                   ["day1" "part2"] (println (day1/part2 file))
                                   ["day2" "part1"] (println (day2/part1 file))
                                   ["day2" "part2"] (println (day2/part2 file))
                                   ["day3" "part1"] (println (day3/part1 file))
                                   ["day3" "part2"] (println (day3/part2 file))
                                   ["day4" "part1"] (println (day4/part1 (slurp file)))
                                   ["day4" "part2"] (println (day4/part2 (slurp file)))
                                   ["day5" "part1"] (println (day5/part1 (slurp file)))
                                   ["day5" "part2"] (println (day5/part2 (slurp file)))
                                   ["day6" "part1"] (println (day6/part1 (slurp file)))
                                   ["day6" "part2"] (println (day6/part2 (slurp file)))
                                   (println "Unimplemented.")))
      :else (println "Unknown command. Usage:\n" usage summary))))
