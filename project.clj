(defproject aoc-clj "0.1.0-SNAPSHOT"
  :description "Advent of Code solutions in Clojure"
  :url "https://github.com/tylerjl/aoc-clj"
  :license {:name "GPL3"
            :url "https://choosealicense.com/licenses/gpl-3.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [org.clojure/tools.cli "1.0.194"]]
  :repl-options {:init-ns aoc-clj.core}
  :main aoc-clj.core
  :aot [aoc-clj.core])
