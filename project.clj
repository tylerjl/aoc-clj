(defproject aoc-clj "0.1.0"
  :description "Advent of Code solutions in Clojure"
  :url "https://github.com/tylerjl/aoc-clj"
  :license {:name "GPL3"
            :url "https://choosealicense.com/licenses/gpl-3.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/core.match "1.0.0"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [org.clojure/tools.cli "1.0.194"]
                 [instaparse "1.4.12"]]
  :repl-options {:init-ns aoc-clj.core}
  :main aoc-clj.core
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :target-path "target/%s"
  :native-image {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                 :opts ["--report-unsupported-elements-at-runtime"
                        "--initialize-at-build-time"]
                 :name "aoc-clj"}
  :aot [aoc-clj.core])
