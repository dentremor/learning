(ns clodo.validate-test
  (:require [clojure.test :refer :all]
            [clodo.util :refer :all]
            [clodo.validate :as validate]))

(deftest with-list-test []
  (testing "Testing with-list with valid and invalid args"
    (let [valid-input 1
          invalid-input 0
          test-list [1 2 3]]
      (is (= valid-input (validate/string-in-list valid-input test-list)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/string-in-list invalid-input test-list))))))

(deftest date-test []
  (testing "Testing date with valid and invalid args"
    (let [valid-input "2022-12-31"
          invalid-input "2022-31-12"]
      (is (= valid-input (validate/date valid-input)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/date invalid-input))))))

(deftest num-in-interval-test []
  (testing "Testing num-in-interval with valid and invalid args"
    (let [valid-input 5
          invalid-input 10
          lower-limit 1
          upper-limit 7]
      (is (= valid-input (validate/num-in-interval valid-input lower-limit upper-limit)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/num-in-interval invalid-input lower-limit upper-limit))))))

(deftest path-test []
  (testing "Testing path with valid and invalid args"
    (let [valid-input "/home/user/file"
          invalid-input "usr/bin"
          invalid-input2 "/home/user/file with spaces"
          invalid-input3 "/usr//bin"
          invalid-input4 "/var/log/messages/"]
      (is (= valid-input (validate/path valid-input)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input2)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input3)))
      (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input4))))))