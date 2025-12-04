(ns aoc-clj.utils
  (:require
   [clj-http.client :as client]
   [clojure.java.io :as io]
   [clojure.string :as s]
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

(defn into-grid [input]
  (->> (s/split-lines input)
       (map #(map vector (range) %))
       (map vector (range))
       (mapcat (partial apply
                        (fn [coords letter]
                          (map (partial
                                apply
                                #(vector (vector %1 coords) %2))
                               letter))))
       (into {})))

(defn neighbors [point]
  (let [x (first point)
        y (second point)]
    (set (partition
          2 (list (inc x) y (inc x) (dec y) x (dec y) (dec x) (dec y)
                  (dec x) y (dec x) (inc y) x (inc y) (inc x) (inc y))))))

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
