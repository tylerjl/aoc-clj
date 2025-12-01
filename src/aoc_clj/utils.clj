(ns aoc-clj.utils
  (:require
   [clj-http.client :as client]
   [clojure.java.io :as io]
   [jasentaa.monad :as m]
   [jasentaa.parser.basic :as b]
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

(defn p-sym [char sym]
  (m/do* (token (b/sat (partial = char))) (m/return sym)))

(defn transpose [& xs]
  (apply map list xs))

(defn challenge [{:keys [year day]}]
  (print "Enter session value> ")
  (flush)
  (let [session (read-line)
        path (io/file (System/getenv "PRJ_ROOT")
                      "resources" (str year) (str "day" day ".txt"))
        url (str "https://adventofcode.com/" year "/day/" day "/input")
        r (client/get url {:cookies {"session" {:value session}}})]
    (if (= (:status r) 200)
      (do
        (io/make-parents path)
        (spit path (:body r)))
      (println (str "Error getting puzzle input: " (:body r))))))
