(ns aoc-clj.2020.day6)

(require '[clojure.string :as str]
         '[clojure.set :as set])

(defn summarize
  "Accept newline-delimited lists of letters, sum intersection"
  [lines]
  (->> (str/split-lines lines)
       (map #(str/split % #""))
       (map set)
       (reduce set/intersection)))

(defn part1
  "Solves part 1."
  [content]
  (->> (str/split content #"\n\n")
       (map #(str/replace % #"\n" ""))
       (map #(str/split % #""))
       (map set)
       (map count)
       (reduce +)))

(defn part2
  "Solves part 2."
  [content]
  (->> (str/split content #"\n\n")
       (map summarize)
       (map count)
       (reduce +)))
