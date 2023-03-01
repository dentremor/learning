(ns clodo.display
  (:require [clojure.pprint :refer [print-table]])
  (:require [clodo.util :as util]))

(defn clear-screen
  "Clears screen and moves the cursor to the top left"
  []
  (print (str (char 27) "[2J")) ; clear screen
  (print (str (char 27) "[;H"))) ; move cursor to the top left corner of the screen

(defn display-todos
  "Display all tasks as table format"
  [todos & [filter flag]]
  (print-table [:index :name :deadline :importance :pending]
               (map-indexed (fn [idx todo] (merge (hash-map :index idx) todo))
                            (when (not (nil? filter))
                              (sort-by (keyword filter) todos))))
  (when flag (read-line)))

(defn display-todos-handler
  "Display the add todo and handles parameter"
  [todo-list]
  (let [filter (do (println "Sort list by (name, deadline, importance, pending): ") (read-line))]
    (display-todos todo-list filter true)))

(defn display-welcome
  "Display the initial welcome screen"
  []
  (clear-screen)
  (println "Welcome to CloDo!\n")
  (println "  add\tTo add a new todo")
  (println "  list\tTo list all todos")
  (println "  del\tTo delete a todo")
  (println "  comp\tTo mark a todo as completed")
  (println "  expo\tTo export the todo")
  (println "  impo\tTo import the todo")
  (println "  exit\tTo escape")
  (print "\nInput:")
  (flush)
  (clear-screen))

(defn display-add-todo-screen
  "Display the add todo and handles parameter"
  [todo-list]
  (let [name (do (println "Enter todo name: ") (read-line))
        deadline (do (println "Enter todo deadline (e.g. 2023-03-22): ") (read-line))
        importance (do (println "Enter todo importance (1-3): ") (Integer/parseInt (read-line)))
        new-list (util/add-task todo-list name deadline importance)]
    new-list))

(defn display-delete-todo
  "Displays all todos and deletes one of them"
  [todo-list]
  (display-todos-handler todo-list)
  (let [index (do (println "Delete Task with index: ") (Integer/parseInt (read-line)))
        new-list (util/delete-task todo-list index)]
    new-list))

(defn display-complete-todo
  "Displays all todos and deletes one of them"
  [todo-list]
  (display-todos todo-list :name false)
  (let [index (do (println "Complete Task with index: ") (Integer/parseInt (read-line)))
        new-list (util/mark-task-as-done todo-list index)]
    new-list))

(defn ls-todos
  "List all tasks as table format"
  [todos flag]
  (println todos)
  (if flag (read-line) nil))

(defn display-export
  "List all tasks as table format"
  [todo-list]
  (let [path (do (println "Enter a path for storing the .json: ") (read-line))]
    (util/export-todos todo-list path)))

(defn display-import
  "List all tasks as table format"
  []
  (let [path (do (println "Enter a path for storing the .json: ") (read-line))
        todo-list (util/import-todos path)]
    todo-list))