(ns aoc-clj.utils
  (:require [jasentaa.monad :as m]
            [jasentaa.position :refer :all]
            [jasentaa.parser.basic :refer :all]
            [jasentaa.parser.combinators :refer :all]))

(defn call
  [func & args]
  (apply (resolve (symbol func)) args))

(defn digit? [^Character c]
  (Character/isDigit c))

(def parse-digit
  (m/do*
    (x <- (token (sat digit?)))
    (m/return (- (byte (strip-location x)) (byte \0)))))

(def parse-number
  (m/do*
    (x <- (plus (token (sat digit?))))
    (m/return (read-string (strip-location x)))))
