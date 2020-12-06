(ns aoc-clj.2020.day5)

(require '[clojure.string :as str]
         '[clojure.set :as set])

(defn instruction-position
  [character]
  (cond
    (or (= "F" character) (= "L" character)) first
    (or (= "B" character) (= "R" character)) second))

(defn translate
  [seats instruction]
  (let [next ((instruction-position instruction) (split-at (/ (count seats) 2) seats))]
    (if (== 1 (count next)) (first next) next)))

(defn solve
  "Accept a string like 'FBFBBFFRLR' and turn it into the solution number."
  [partitioning]
  (let [instructions (str/split partitioning #"")
        [rows seats] (split-at 7 instructions)]
    (+ (* (reduce translate (range 0 128) rows) 8)
       (reduce translate (range 0 8) seats))))

(defn part1
  "Solves part 1."
  [content]
  (->> (str/split-lines content)
       (map solve)
       (apply max)))

(defn part2
  "Solves part 2."
  [content]
  (let [passengers (sort (->> (str/split-lines content)
                         (map solve)))
        seats (range (first passengers) (last passengers))]
    (set/difference (set seats) (set passengers))))
