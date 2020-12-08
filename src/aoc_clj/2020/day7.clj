(ns aoc-clj.2020.day7)

(require '[clojure.string :as str])

(def needle "shiny gold")

(defn as-map
  [child]
  (if-let [[_match bags color] (re-matches #"^(\d+) ([a-z\s]+) bags?" child)]
    {color (read-string bags)} {}))

(defn transform
  "Turn a line specifying bag containing capabilities into the right data structure"
  [line]
  (let [[_str bag contents] (re-matches #"^([a-z\s]+) bags contain ([^.]+)$" (str/replace line #"\." ""))]
    {bag (into {} (map as-map (str/split contents #", ")))}))

(defn holds-needle
  [all bag-color]
  (if-let [spec (get all bag-color)]
    (or (contains? spec needle) (some #(holds-needle all %) (keys spec)))
    false))

(defn shape
  [input]
  (->> (str/split input #"\n")
       (map transform)
       (into {})))

(defn part1
  "Solves part 1."
  [content]
  (let [all-bags (shape content)]
    (count (filter #(holds-needle all-bags %) (keys all-bags)))))

(defn contains-bags
  [all pivot]
  (let [contents (get all pivot)]
    (reduce-kv #(+ %1 (* %3 (contains-bags all %2))) 1 contents)))

(defn part2
  "Solves part 2."
  [content]
  ; Subtract one, otherwise our function counts the gold bag as well.
  (- (contains-bags (shape content) "shiny gold") 1))
