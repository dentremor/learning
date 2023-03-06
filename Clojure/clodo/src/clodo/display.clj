(ns clodo.display
  (:require [clojure.pprint :refer [print-table]])
  (:require [clodo.util :as util])
  (:require [clodo.validate :as validate]))

;; Helper functions

(defn- colorize-string
  "Print the given string in the given color"
  [string & [color]]
  (case color
    "GREEN" (str "\u001B[32m" string "\u001B[0m")
    "YELLOW" (str "\u001B[33m" string "\u001B[0m")
    "GRAY" (str "\u001B[90m" string "\u001B[0m")
    "GREEN_BOLD" (str "\033[1;32m" string "\u001B[0m")
    "YELLOW_BOLD" (str "\033[1;33m" string "\u001B[0m")
    "GRAY_BOLD" (str "\033[1;90m" string "\u001B[0m")
    (str string)))

(defn- clear-screen
  "Clears screen and moves the cursor to the top left"
  []
  (print (str (char 27) "[2J")) ; clear screen
  (print (str (char 27) "[;H"))) ; move cursor to the top left corner of the screen

(defn- get-date
  "TODO"
  [print & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again:"))
  (let [input (read-line)]
    (try
      (validate/date input)
      (catch Exception e
        (let [invalid (-> e ex-data :input)]
          (get-date print invalid)))) input))

(defn- get-num-in-interval
  "TODO"
  [print lower upper & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again:"))
  (let [input (Integer/parseInt (read-line))]
    (try
      (validate/num-in-interval input lower upper)
      (catch Exception e
        (let [invalid (-> e ex-data :input)]
          (get-num-in-interval print lower upper invalid)))) input))

(defn- get-string-in-list
  "TODO"
  [print list & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again:"))
  (let [input (read-line)]
    (try
      (validate/string-in-list input list)
      (catch Exception e
        (let [invalid (-> e ex-data :input)]
          (get-string-in-list print list invalid)))) input))

(defn- get-path
  "TODO"
  [print & [invalid]]
  (if (nil? invalid)
    (println print)
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again:"))
  (let [input (read-line)]
    (try
      (validate/path input)
      (catch Exception e
        (let [invalid (-> e ex-data :input)]
          (get-path print invalid)))) input))

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
  (println "  " (colorize-string "expo" "GREEN_BOLD") "\tTo export the todo.")
  (println "  " (colorize-string "impo" "GREEN_BOLD") "\tTo import the todo.")
  (println "  " (colorize-string "exit" "GREEN_BOLD") "\tTo escape.\n"))

(defn add-screen
  "Display the add todo and handles parameter"
  [todo-list & [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [name (do (println "Enter todo name: ") (read-line))
        deadline (get-date "Enter todo deadline (e.g. 2023-03-22): ")
        importance (get-num-in-interval "Enter todo importance (1-3): " 1 3)
        new-list (util/add-todo todo-list name deadline importance)]
    (print-table [:name :deadline :importance :pending] [(last new-list)]) (read-line)
    new-list))

(defn show-screen
  "Display all tasks as table format"
  [todos & [filter flag]]
  (print-table [:index :name :deadline :importance :pending]
               (map-indexed (fn [idx todo] (merge (hash-map :index idx) todo))
                            (when (not (nil? filter))
                              (sort-by (keyword filter) todos))))
  (when flag (read-line)))

(defn handler-show-screen
  "Display the add todo and handles parameter"
  [todo-list & [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [filter (get-string-in-list "Sort list by (name, deadline, importance, pending): " '("name" "deadline" "importance" "pending"))]
    (show-screen todo-list filter true)))

(defn delete-screen
  "Displays all todos and deletes one of them"
  [todo-list & [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (show-screen todo-list :index false)
  (let [index (do (println "Delete Task with index: ")
                  (try
                    (validate/num-in-interval
                     (Integer/parseInt (read-line)) 0 (dec (count todo-list)))
                    (catch Exception e
                      (let [invalid (-> e ex-data :input)]
                        (delete-screen todo-list invalid)))))
        new-list (util/delete-todo todo-list index)]
    new-list))

(defn complete-screen
  "Displays all todos and deletes one of them"
  [todo-list & [invalid]]
  (show-screen todo-list :index false)
  (let [index (get-num-in-interval "Complete Task with index: " 0 (- (count todo-list) 1))
        new-list (util/complete-todo todo-list index)]
    new-list))

(defn export-screen
  "List all tasks as table format"
  [todo-list & [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [path (get-path "Enter a path for storing a json file (e.g. /tmp/foo.json): ")]
    (util/export-todos todo-list path)))

(defn import-screen
  "List all tasks as table format"
  [& [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [path (get-path "Enter a path for storing a json file (e.g. /tmp/foo.json): ")
        todo-list (util/import-todos path)]
    todo-list))