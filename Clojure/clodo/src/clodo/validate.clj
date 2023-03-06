(ns clodo.validate
  (:require [clojure.string :as clojure.string]))

(defn string-in-list
  "Validates the input with a given list"
  [input list]
  (when (not (some #{input} list))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn date
  "Validates the input with a given date"
  [input]
  (when (not (re-matches #"\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|[1-3][0-1])" input))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn num-in-interval
  "Validates if the input lays in an interval"
  [input lower upper]
  (when (not (and (>= input lower) (<= input upper)))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn path
  "Validates if the input lays in an interval"
  [input]
  (when (not (re-matches #"^.*[^\.]\.json$" input))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)