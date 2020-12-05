(ns aoc-clj.2020.day3)

(require '[clojure.string :as str])

(defn step
  [y x coords]
  {:y (nth (iterate inc (get coords :y)) y) :x (nth (iterate inc (get coords :x)) x)})

(defn slope-at
  [slope coords]
  (nth (cycle (nth slope (get coords :y))) (get coords :x)))

(defn solve
  "Day 3's solution."
  [step-y step-x input]
  (loop [coords {:y 0 :x 0}
         hits 0
         slope (map (fn [x] (str/split x #"")) input)]
    (let [next (step step-y step-x coords)
          y (get next :y)]
      (cond
        ;; Have we reached the end?
        (>= y (count slope)) hits
        ;; Still have rows to process
        :else (let [row (get slope y)
                    new-coords (assoc next :x (get next :x))]
                (recur new-coords
                       (if (= (slope-at slope new-coords) "#") (inc hits) hits)
                       slope))))))

(defn part1
  "Solves part 1."
  [filepath]
  (solve 1 3 (clojure.string/split-lines (slurp filepath))))

(defn part2
  "Solves part 2."
  [filepath]
  (*
   (solve 1 1 (clojure.string/split-lines (slurp filepath)))
   (solve 1 3 (clojure.string/split-lines (slurp filepath)))
   (solve 1 5 (clojure.string/split-lines (slurp filepath)))
   (solve 1 7 (clojure.string/split-lines (slurp filepath)))
   (solve 2 1 (clojure.string/split-lines (slurp filepath))))
  )
