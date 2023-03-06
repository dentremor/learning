(ns clodo.util
  (:require [clojure.core :refer [concat]])
  (:require [cheshire.core :refer :all]))

(defn add-todo
  "Creates a todo"
  [todo-list name deadline importance]
  (let [new-list (hash-map :name name :deadline deadline :importance importance :pending true)
        updated-list (conj todo-list new-list)]
    updated-list))

(defn delete-todo
  "Deletes a todo from the todo list by index"
  [todo-list index]
  (println todo-list)
  (let [new-list (into [] (concat (subvec todo-list 0 index)
                                  (subvec todo-list (inc index))))]
    new-list))

(defn complete-todo
  "Mark a pending todo as done"
  [todo-list index]
  (assoc-in todo-list [index :pending] false))

(defn export-todos
  "Exports the todo-list into a JSON file"
  [todo-list path]
  (generate-stream todo-list (clojure.java.io/writer path) {:pretty true}))

(defn import-todos
  "Imports the todo-list from a JSON file"
  [path]
  (let [new-list (into [] (parse-stream (clojure.java.io/reader path) true))]
    new-list))