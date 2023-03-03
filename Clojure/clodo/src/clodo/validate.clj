(ns clodo.validate)

(defn with-list
  "Validates the input with a given list"
  [input list]
  (when (not (some #{input} list)) (throw (Exception. "Invalid Input"))))

(defn date
  "Validates the input with a given date"
  [input]
  (when (not (re-matches #"\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|[1-3][0-1])" input))
    (throw (Exception. "Invalid Input"))))

(defn num-in-interval
  "Validates if the input lays in an interval"
  [input lower upper]
  (when (not (and (>= input lower) (<= input upper)))
    (throw (Exception. "Invalid Input"))))

(defn path
  "Validates if the input lays in an interval"
  [input]
  (when (not (re-matches #"\.\w+$" input))
    (throw (Exception. "Invalid Input"))))