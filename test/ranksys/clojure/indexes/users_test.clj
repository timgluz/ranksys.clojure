(ns ranksys.clojure.indexes.users-test
  (:require [midje.sweet :refer :all])
  (:import [ranksys.clojure.indexes.users UserIndex]))

(def user1 {:id 1 :name "Rocky"})
(def user2 {:id 2 :name "Ivan Drago"})

(facts "creating index"
  (fact "possible to create empty index"
    (UserIndex.) => anything) ;anything == it didnt throw exception
  (fact "creates a new index from clojure list"
    (UserIndex. [])             => anything
    (UserIndex. [1 2 3])        => anything
    (UserIndex. [user1 user2])  => anything))

(facts "numUsers"
  (.numUsers (UserIndex.))      => 0
  (.numUsers (UserIndex. []))   => 0
  (.numUsers (UserIndex. [1]))  => 1
  (.numUsers (UserIndex. [1 2])) => 2)

(facts "containsUser"
  (let [idx (UserIndex. [user1 user2])]
    (.containsUser idx user1) => true?
    (.containsUser idx user2) => true?
    (.containsUser idx {:id 3}) => false?))

(facts "getAllUsers"
  (fact "returns correct item"
    (let [idx (UserIndex. [user1])]
      (-> idx .getAllUsers .iterator iterator-seq first) => user1)))

(facts "user2uidx"
  (fact "returns a correct index"
    (let [idx (UserIndex. [user1 user2])]
      (.user2uidx idx user1) => 0
      (.user2uidx idx user2) => 1)))

;;probably broken
(facts "uidx2user"
  (fact "returns a correct user by user"
    (let [idx (UserIndex. [user1 user2])]
      (.uidx2user idx 0) => user1
      (.uidx2user idx 1) => user2)))

