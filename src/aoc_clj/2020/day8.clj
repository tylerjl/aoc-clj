(ns aoc-clj.2020.day8)

(require '[clojure.string :as str])

(defn transform
  [line]
  (let [[ex op] (str/split line #" ")] (vec [ex (read-string op)])))

(defn step
  [idx acc instruction]
  (let [[cmd operand] instruction]
    (case cmd
      "acc" (vec [(inc idx) (+ operand acc)])
      "jmp" (vec [(+ idx operand) acc])
      (vec [(inc idx) acc])))
  )

(defn run
  [pred program history [idx acc]]
  (let [updated-history (update history idx (fnil inc 0))
        visits (get updated-history idx)]
    (if (pred visits program idx)
      (vec [acc idx])
      (recur pred program updated-history (step idx acc (nth program idx))))))

(defn part1
  "Solves part 1."
  [content]
  (let [program (->> (str/split content #"\n")
                     (map transform))]
    (first (run (fn [v _p _i] (> v 1)) program {} [0 0]))))

(defn swap
  [[ex op]]
  (vec [(case ex
           "jmp" '"nop"
           "nop" '"jmp"
           ex) op]))

(defn try-swap
  [program index]
  (run (fn [v p i] (or (> v 1) (= i (count p)))) (assoc program index (swap (nth program index))) {} [0 0]))

(defn part2
  "Solves part 2."
  [content]
  (let [program (->> (str/split content #"\n")
                     (mapv transform))]
    (first (first (filter (fn [[acc idx]] (= idx (count program)))
                          (map #(try-swap program %1) (range 0 (count program))))))))
