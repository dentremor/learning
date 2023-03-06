(ns clodo.validate-test
  (:require [clojure.test :refer :all]
            [clodo.util :refer :all]
            [clodo.validate :as validate]))

(deftest with-list-test []
  (let [valid-input 1
        invalid-input 0
        test-list [1 2 3]]
    (is (= valid-input (validate/string-in-list valid-input test-list)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/string-in-list invalid-input test-list)))))

(deftest date-test []
  (let [valid-input "2022-12-31"
        invalid-input "2022-31-12"]
    (is (= valid-input (validate/date valid-input)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/date invalid-input)))))

(deftest num-in-interval-test []
  (let [valid-input 5
        invalid-input 10
        lower-limit 1
        upper-limit 7]
    (is (= valid-input (validate/num-in-interval valid-input lower-limit upper-limit)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/num-in-interval invalid-input lower-limit upper-limit)))))

(deftest path-test []
  (let [valid-input "file.json"
        valid-input1 "/tmp/file.json"
        invalid-input "path/to/file"
        invalid-input2 "file."
        invalid-input3 "file.json.c"
        invalid-input4 "file..json"]
    (is (= valid-input (validate/path valid-input)))
    (is (= valid-input1 (validate/path valid-input)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input2)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input3)))
    (is (thrown? clojure.lang.ExceptionInfo (validate/path invalid-input4)))))


