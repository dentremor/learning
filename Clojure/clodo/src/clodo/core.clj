(ns clodo.core
  (:gen-class)
  (:require [clodo.display :as display]))

(def todo-list [(hash-map :name "Wartering flowers" :deadline "2023-03-02" :importance 2 :pending true)
                (hash-map :name "Do dishes" :deadline "2023-03-10" :importance 1 :pending true)
                (hash-map :name "Book flight" :deadline "2023-03-09" :importance 3 :pending true)])

(defn -main
  [& args]
  (while true
    (display/display-welcome)
    (let [input (read-line)]
      (case input
        "add" (def todo-list (display/display-add-todo-screen todo-list))
        "list" (display/display-todos-handler todo-list)
        "ls" (display/ls-todos todo-list true)
        "del" (def todo-list (display/display-delete-todo todo-list))
        "comp" (def todo-list (display/display-complete-todo todo-list))
        "expo" (display/display-export todo-list)
        "impo" (def todo-list (display/display-import))
        "exit" (System/exit 0)))))