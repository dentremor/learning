(ns clodo.util-test
  (:require [clojure.test :refer :all]
            [clodo.util :refer :all]
            [clodo.util :as util]))

(deftest add-todo-test
  (testing "Testing add-todo with valid args"
    (is (= [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)
            (hash-map :name "b" :deadline "2023-03-10" :importance 1 :pending true)]
           (util/add-todo [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)] "b" "2023-03-10" 1)))))

(deftest delete-todo-test
  (testing "Testing delete-todo with valid args"
    (is (= [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)]
           (util/delete-todo [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true) (hash-map :name "b" :deadline "2023-03-10" :importance 1 :pending true)] 1)))))

(deftest complete-todo-test
  (testing "Testing complete-todo with valid args"
    (is (= [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending false)]
           (util/complete-todo [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)] 0)))))

(deftest export-import-todos-test
  (testing "Testing export-todos and import-todos with valid args"
    (util/export-todos [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)] "/tmp/foo")
    (is (= [(hash-map :name "a" :deadline "2023-03-02" :importance 2 :pending true)]
           (util/import-todos "/tmp/foo")))))