(ns aoc-clj.2024.day9
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [clojure.core.reducers :refer [fold]]
            [clojure.math.combinatorics :refer [combinations]]))

(defn solve-1 [input]
  (let [disk (->> (s/trim input)
                  (map (comp read-string str))
                  (map-indexed #(if (even? %1) (repeat %2 (/ %1 2)) (repeat %2 nil)))
                  (flatten)
                  (into []))
        q (rseq disk)]
    (loop [sum 0
           idx 0
           d disk
           q q]
      (if (or (empty? d) (empty? q))
        sum
        (let [e (first d)]
          (if (nil? e)
            (let [[nils nums] (split-with (comp not identity) q)]
              (recur (+ sum (* idx (first nums))) (inc idx) (drop-last (inc (count nils)) (rest d)) (rest nums)))
            (recur (+ sum (* idx e)) (inc idx) (rest d) q)))))))

(defn part1 [input]
  (solve-1 input))

(defn solve-2 [input]
  (let [disk (->> (s/trim input)
                  (map (comp read-string str))
                  (map-indexed #(if (even? %1) (repeat %2 (/ %1 2)) (repeat %2 nil)))
                  (filter #(not (empty? %))))
        queue (into [] disk)
        fits (fn [file span] (and (>= (count span) (count file)) (nil? (first span))))
        insert (fn [file slot] (if (= (count file) (count slot))
                                 (list file)
                                 (list file (drop (count file) slot))))]
    (loop [result queue
           idx (dec (count queue))
           moved #{}]
      (if (< idx 0)
          result
          (let [file (nth result idx)]
            (if (nil? (first file))
              (recur result (dec idx) moved)
              (let [[begin end] (split-with (comp not (partial fits file)) result)]
                (if (and (not (empty? end)) (> idx (count begin)) (not (contains? moved file)))
                  (let [to-insert (insert file (first end))]
                    (recur (into [] (concat begin
                                            (let [[nils others] (split-with (partial every? nil?) end)
                                                  [ins ins-nils] (split-with (comp not nil? first) to-insert)]
                                              (concat
                                               ins
                                               (let [[before after] (split-at (- idx (+ (count begin) (count nils))) others)
                                                     [b-nils befores] (->> (split-with #(nil? (first %)) (reverse before))
                                                                           (#(vector (first %) (reverse (second %)))) )
                                                     [after-nils afters] (split-with #(nil? (first %)) (rest after))]
                                                 (concat ins-nils
                                                         befores
                                                         (flatten (concat b-nils (repeat (count file) nil) after-nils))
                                                         afters))))))
                           (- idx (count to-insert))
                           (conj moved file)))
                  (recur result (dec idx) moved)))))))))

(defn part2 [input]
  ;; (solve-2 input)
  (->> (solve-2 input)
       (flatten)
       (map-indexed #(if (nil? %2) 0 (* %1 %2)))
       (fold +))
  )

(comment
  (time (part1 (slurp "test/aoc_clj/2024/day9.txt")))
  (time (part1 (slurp "resources/2024/day9.txt")))
  (time (part2 (slurp "test/aoc_clj/2024/day9.txt")))
  (time (part2 (slurp "resources/2024/day9.txt"))))
        ;; => 6837025209112))
