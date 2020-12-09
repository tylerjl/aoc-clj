(ns aoc-clj.utils)

(defn call
  [func & args]
  (apply (resolve (symbol func)) args))
