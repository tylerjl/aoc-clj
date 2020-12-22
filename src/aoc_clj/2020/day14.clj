(ns aoc-clj.2020.day14
  (:require [clojure.string :as str]))

(def remask
  (fn [mask val]
    (reduce (fn [num [idx bit]]
              (case bit
                "0" (bit-clear num idx)
                "1" (bit-set num idx)
                num)) val mask)))

(def run-program
  (fn [faddr fval acc line]
    (let [[op _ arg-s] line
          [mask mem] acc]
      (cond
        (= "mask" op) (list
                       (map-indexed
                        vector
                        (reverse (str/split arg-s #""))) mem)
        :else (if-let [[_ addr-s] (str/split op #"\[")]
                (let [addr (read-string addr-s)
                      arg (read-string arg-s)]
                  (list mask
                        (reduce
                         (fn [acc k]
                           (assoc acc k (fval mask addr arg)))
                         mem
                         (faddr mask addr arg))))
                (list mask mem))))))

(def solve
  (fn [input faddr fval]
    (let [results (->> (str/split-lines input)
                       (map #(str/split % #" "))
                       (reduce
                        (fn [acc el]
                          (run-program faddr fval acc el))
                        (list nil {})))]
      (reduce + (vals (second results))))))

(def part1
  (fn [input]
    (solve input (fn [_ x _] (list x)) (fn [m _ v] (remask m v)))))

(def addrmask
  (fn [mask addresses]
    (reduce (fn [addrs [idx bit]]
              (case bit
                "0" addrs
                "1" (map #(bit-set % idx) addrs)
                "X" (mapcat #(list % (bit-flip % idx)) addrs)))
            addresses mask)))

(def part2
  (fn [input]
    (solve input (fn [m a _] (addrmask m (list a))) (fn [_ _ v] v))))

