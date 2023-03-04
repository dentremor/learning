(ns clodo.validate)
(defn with-list
  "Validates the input with a given list"
  [input list]
  (when (not (some #{input} list))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)

(defn date
  "Validates the input with a given date"
  [input]
  (when (not (re-matches #"\d{4}-\d{2}-\d{2}" input))
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
  (when (not (re-matches #"\.\w+$" input))
    (throw (ex-info "Invalide Input" {:input input}))
    (+ input 1)) input)