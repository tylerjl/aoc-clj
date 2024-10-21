(defproject aoc-clj "0.1.0"
  :description "Advent of Code solutions in Clojure"
  :url "https://github.com/tylerjl/aoc-clj"
  :license {:name "GPL3"
            :url "https://choosealicense.com/licenses/gpl-3.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/core.match "1.1.0"]
                 [org.clojure/math.combinatorics "0.3.0"]
                 [org.clojure/tools.cli "1.1.230"]
                 [net.mikera/core.matrix "0.63.0"]
                 [rm-hull/jasentaa "0.2.5"]
                 [criterium "0.4.6"]
                 [instaparse "1.5.0"]]
  :repl-options {:init-ns aoc-clj.core}
  :main aoc-clj.core
  :jvm-opts ["-Xss512M"]
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :target-path "target/%s"
  :native-image {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                 :opts ["--report-unsupported-elements-at-runtime"
                        "--initialize-at-build-time"]
                 :name "aoc-clj"}
  :aot [aoc-clj.core])
