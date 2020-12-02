(ns aoc-clj.2020.day2)

(defn parse [input] (re-find #"(\d+)-(\d+) ([a-z]): ([a-z]+)" input))

(defn solve
  "Day 2's solution."
  [input pred]
  (->> (clojure.string/split-lines input)
       (map (fn [x] (parse x)))
       (filter (fn [match]
                 (let [[_ minS maxS reqS pw] match]
                   (let [min (read-string minS)
                         max (read-string maxS)]
                     (pred min max reqS pw)))))
       (count))
  )

(defn policy-one
  [min max req-string pw]
  (let [freq (frequencies pw)
        req (first (char-array req-string))]
    (and (clojure.string/includes? pw req-string)
         (<= min (get freq req))
         (>= max (get freq req)))))

(defn policy-two
  [fst snd req-string pw]
  (let [req (first (char-array req-string))]
    (let [alpha (= (nth pw (dec fst)) req)
          beta (= (nth pw (dec snd)) req)]
      (or (and alpha (not beta)) (and (not alpha) beta)))))

(defn part1
  "Solves part 1."
  [filepath]
  (solve (slurp filepath) policy-one))

(defn part2
  "Solves part 2."
  [filepath]
  (solve (slurp filepath) policy-two))

(part1 "resources/day2")

(part2 "resources/day2")

(solve "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n" policy-one)

(solve "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n" policy-two)
