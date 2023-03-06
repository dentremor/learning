(ns clodo.core
  (:gen-class)
  (:require [clodo.display :as display]))

(def todo-list
  [(hash-map :name "Wartering flowers" :deadline "2023-03-02" :importance 2 :pending true)
   (hash-map :name "Do dishes" :deadline "2023-03-10" :importance 1 :pending true)
   (hash-map :name "Book flight" :deadline "2023-03-09" :importance 3 :pending true)])

(defn application-loop
  "Keeps the programm alive"
  []
  (display/welcome-screen)
  (print "Input: ")
  (flush)
  (let [input (read-line)]
    (case input
      "add" (def todo-list (display/add-screen todo-list))
      "list" (display/list-screen todo-list)
      "del" (def todo-list (display/delete-screen todo-list))
      "comp" (def todo-list (display/complete-screen todo-list))
      "expo" (display/export-screen todo-list)
      "impo" (def todo-list (display/import-screen))
      "exit" (System/exit 0)
      (recur))))

(defn -main
  [& args]
  (while true (application-loop)))