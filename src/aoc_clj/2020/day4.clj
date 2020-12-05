(ns aoc-clj.2020.day4)

(require '[clojure.string :as str]
         '[clojure.set :as set])

(def required #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn valid?
  [field value]
  (case field
    "byr" (and (re-find #"^[0-9]{4}$" value)
               (let [i (read-string value)]
                 (and (>= i 1920) (<= i 2002))))
    "iyr" (and (re-find #"^[0-9]{4}" value)
               (let [i (read-string value)]
                 (and (>= i 2010) (<= i 2020))))
    "eyr" (and (re-find #"^[0-9]{4}$" value)
               (let [i (read-string value)]
                 (and (>= i 2020) (<= i 2030))))
    "hgt" (if-let [[s m u] (re-matches #"^([0-9]+)(cm|in)$" value)]
            (let [n (read-string m)]
              (case u
                "in" (and (>= n 59) (<= n 76))
                "cm" (and (>= n 150) (<= n 193))
                false))
            false)
    "hcl" (re-matches #"^#[0-9a-f]{6}$" value)
    "ecl" (re-matches #"^(amb|blu|brn|gry|grn|hzl|oth)$" value)
    "pid" (re-matches #"^[0-9]{9}$" value)
    "cid" true
    false
    ))

(defn shape
  [input]
  (->> (str/split input #"\n\n")
       (map (fn [x] (str/replace x #"\n" " ")))
       (map (fn [x] (str/split x #" ")))
       (map (fn [x] (into {} (map (fn [y] (str/split y #":")) x))))))

(defn fields-provided?
  [passport]
  (empty? (set/difference required (set (keys passport)))))

(defn is-valid?
  [pair]
  (valid? (first pair) (second pair)))

(defn part1
  "Solves part 1."
  [content]
  (->> (shape content)
       (filter fields-provided?)
       (count)))

(defn part2
  "Solves part 2."
  [content]
  (->> (shape content)
       (filter (every-pred fields-provided? #(every? is-valid? %)))
       (count)))
