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
        deadline (do (println "Enter todo deadline (e.g. 2023-03-22): ")
                     (try
                       (validate/date (read-line))
                       (catch Exception e
                         (let [invalid (-> e ex-data :input)]
                           (add-screen todo-list invalid)))))
        importance (do (println "Enter todo importance (1-3): ")
                       (try
                         (validate/num-in-interval
                          (Integer/parseInt (read-line)) 1 3)
                         (catch Exception e
                           (let [invalid (-> e ex-data :input)]
                             (add-screen todo-list invalid)))))
        new-list (util/add-task todo-list name deadline importance)]
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
  (let [filter (do (println "Sort list by (name, deadline, importance, pending): ")
                   (try
                     (validate/with-list (read-line) '("name" "deadline" "importance" "pending"))
                     (catch Exception e
                       (let [invalid (-> e ex-data :input)]
                         (handler-show-screen todo-list invalid)))))]
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
        new-list (util/delete-task todo-list index)]
    new-list))

(defn complete-screen
  "Displays all todos and deletes one of them"
  [todo-list & [invalid]]
  (show-screen todo-list :index false)
  (let [index (do (println "Complete Task with index: ")
                  (try
                    (validate/num-in-interval
                     (Integer/parseInt (read-line)) 0 (- (count todo-list) 1))
                    (catch Exception e
                      (let [invalid (-> e ex-data :input)]
                        (complete-screen  todo-list invalid)))))
        new-list (util/mark-task-as-done todo-list index)]
    new-list))

(defn export-screen
  "List all tasks as table format"
  [todo-list & [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [path (do (println "Enter a path for storing the .json: ")
                 (try
                   (validate/path (read-line))
                   (catch Exception e
                     (let [invalid (-> e ex-data :input)]
                       (export-screen todo-list invalid)))))]
    (util/export-todos todo-list path)))

(defn import-screen
  "List all tasks as table format"
  [& [invalid]]
  (clear-screen)
  (when (not (nil? invalid))
    (println (colorize-string invalid "YELLOW_BOLD") "is no valid option. Please try again!"))
  (let [path (do (println "Enter a path for storing the .json: ")
                 (try
                   (validate/path (read-line))
                   (catch Exception e
                     (let [invalid (-> e ex-data :input)]
                       (import-screen invalid)))))
        todo-list (util/import-todos path)]
    todo-list))