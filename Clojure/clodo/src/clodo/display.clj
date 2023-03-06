(ns clodo.display
  (:require [clojure.pprint :refer [print-table]])
  (:require [clodo.util :as util])
  (:require [clodo.validate :as validate]))

;; Helper functions

(defn- colorize-string
  "Print the given string in the given color"
  [string color]
  (case color
    "GREEN_BOLD" (str "\033[1;32m" string "\u001B[0m")
    "YELLOW_BOLD" (str "\033[1;33m" string "\u001B[0m")))

(defn- clear-screen
  "Clears screen and moves the cursor to the top left"
  []
  (print (str (char 27) "[2J")) ; clear screen
  (print (str (char 27) "[;H"))) ; move cursor to the top left corner of the screen

(defn- get-date
  "Handles and validate a date parameter"
  [print & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option! Please try again:"))
  (let [input
        (try
          (validate/date (read-line))
          (catch Exception e
            (let [invalid (-> e ex-data :input)]
              (get-date print invalid))))] input))

(defn- get-num-in-interval
  "Handles and validate a number parameter from a given interval"
  [print lower upper & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option! Please try again:"))
  (let [input (try (validate/num-in-interval (Integer/parseInt (read-line)) lower upper)
                   (catch Exception e
                     (let [invalid (-> e ex-data :input)]
                       (get-num-in-interval print lower upper invalid))))] input))

(defn- get-string-in-list
  "Handles and validate a string parameter from a given list"
  [print list & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option! Please try again:"))
  (let [input
        (try
          (validate/string-in-list (read-line) list)
          (catch Exception e
            (let [invalid (-> e ex-data :input)]
              (get-string-in-list print list invalid))))] input))

(defn- get-export-path
  "Handles and validate a path"
  [print & [invalid-path]]
  (if (nil? invalid-path)
    (println print)
    (println (colorize-string invalid-path "YELLOW_BOLD") "is no valid option! Please try again:"))
  (let [input
        (try
          (validate/path (read-line))
          (catch Exception e
            (let [invalid (-> e ex-data :input)]
              (get-export-path print invalid))))] input))

(defn- get-import-path
  "Handles and validate if file exists"
  [print & [invalid-path]]
  (if (nil? invalid-path)
    (println print)
    (println (colorize-string invalid-path "YELLOW_BOLD") "doesn't exist! Please try again:"))
  (let [input
        (try
          (validate/file-exists (read-line))
          (catch Exception e
            (let [invalid (-> e ex-data :input)]
              (get-import-path print invalid))))] input))

(defn- print-todos
  "Display all todos from todo-list as a table"
  [todos & [filter wait]]
  (print-table [:index :name :deadline :importance :pending]
               (map-indexed (fn [idx todo] (merge (hash-map :index idx) todo))
                            (when (not (nil? filter))
                              (sort-by (keyword filter) todos))))
  (when wait (read-line)))

;; Functions

(defn welcome-screen
  "Display the initial welcome screen"
  []
  (clear-screen)
  (println "Welcome to CloDo!\n")
  (println "  " (colorize-string "add" "GREEN_BOLD") "\t\tTo add a new todo.")
  (println "  " (colorize-string "list" "GREEN_BOLD") "\tTo list all todos.")
  (println "  " (colorize-string "del" "GREEN_BOLD") "\t\tTo delete a todo.")
  (println "  " (colorize-string "comp" "GREEN_BOLD") "\tTo mark a todo as completed.")
  (println "  " (colorize-string "expo" "GREEN_BOLD") "\tTo export the todos.")
  (println "  " (colorize-string "impo" "GREEN_BOLD") "\tTo import the todos.")
  (println "  " (colorize-string "exit" "GREEN_BOLD") "\tTo escape.\n"))

(defn add-screen
  "Display the add-screen and call relevant functions"
  [todo-list]
  (clear-screen)
  (let [name (do (println "Enter todo name: ") (read-line))
        deadline (get-date "Enter todo deadline (e.g. 2023-03-22): ")
        importance (get-num-in-interval "Enter todo importance (1-3): " 1 3)
        new-list (util/add-todo todo-list name deadline importance)]
    (print-table [:name :deadline :importance :pending] [(last new-list)]) (read-line)
    new-list))

(defn list-screen
  "Display the list-screen and call relevant functions"
  [todo-list]
  (clear-screen)
  (let [filter (get-string-in-list "Sort list by (name, deadline, importance, pending): " '("name" "deadline" "importance" "pending"))]
    (print-todos todo-list filter true)))

(defn delete-screen
  "Display the delete-screen and call relevant functions"
  [todo-list]
  (print-todos todo-list :index false)
  (let [index (get-num-in-interval "Complete Task with index: " 0 (dec (count todo-list)))
        new-list (util/delete-todo todo-list index)]
    new-list))

(defn complete-screen
  "Display the complete-screen and call relevant functions"
  [todo-list]
  (print-todos todo-list :index false)
  (let [index (get-num-in-interval "Complete Task with index: " 0 (- (count todo-list) 1))
        new-list (util/complete-todo todo-list index)]
    new-list))

(defn export-screen
  "Display the export-screen and call relevant functions"
  [todo-list]
  (clear-screen)
  (let [path (get-export-path "Enter a path for storing a json file (e.g. /tmp/foo.json): ")]
    (util/export-todos todo-list path)))

(defn import-screen
  "Display the import-screen and call relevant functions"
  [& [missing]]
  (if (nil? missing)
    (clear-screen)
    (println "File doesn't exist!"))
  (let [path (get-import-path "Enter a path for importing a json file (e.g. /tmp/foo.json): ")
        todo-list (util/import-todos path)]
    todo-list))