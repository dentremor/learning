(ns clodo.util
  (:require [clojure.core :refer [concat]])
  (:require [clojure.pprint :refer [print-table]])
  (:require [cheshire.core :refer :all]))

(defn add-task
  "Creates a task"
  [todo-list name deadline importance]
  (let [new-list (hash-map :name name :deadline deadline :importance importance :pending true)
        updated-list (conj todo-list new-list)]
    (print-table [:name :deadline :importance :pending] [new-list]) (read-line)
    updated-list))

(defn delete-task
  "Deletes a task from the todo list by index"
  [todo-list index]
  (let [new-list (concat (subvec todo-list 0 index)
                         (subvec todo-list (inc index)))]
    new-list))

(defn mark-task-as-done
  "Mark a pending task as done"
  [todo-list index]
  (assoc-in todo-list [index :pending] false))

(defn export-todos
  "Exports the todo-list into a JSON file"
  [todo-list path]
  (generate-stream todo-list (clojure.java.io/writer path) {:pretty true}))

(defn import-todos
  "Imports the todo-list from a JSON file"
  [path]
  (let [new-list (parse-stream (clojure.java.io/reader path) true)]
    new-list))