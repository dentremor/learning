(ns clodo.validate
  (:require [clojure.java.io :as io]))

(defn string-in-list
  "Validates the input with a list"
  [input list]
  (when (not (some #{input} list))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn date
  "Validates if input matches date format"
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
  "Validates if input matches a unix path"
  [input]
  (when (not (re-matches #"^(/[^/ ]+)+$" input))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn file-exists
  "Validates if file exists"
  [input]
  (when (not (.exists (io/file input)))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)