(ns ranksys.clojure.indexes.items-test
  (:require [midje.sweet :refer :all])
  (:import [ranksys.clojure.indexes.items ItemIndex]))

(def item1 {:id 1 :name "Apple"})
(def item2 {:id 2 :name "Orange"})

(facts "creating index"
  (fact "possible to create empty index"
    (ItemIndex.) => anything)
  (fact "creates a new index from Clojure vector of items"
    (ItemIndex. [])         => anything
    (ItemIndex. [1 2 3])    => anything
    (ItemIndex. [item1 item2]) => anything))

(facts "numItems"
  (.numItems (ItemIndex.))      => 0
  (.numItems (ItemIndex. []))   => 0
  (.numItems (ItemIndex. [1]))  => 1
  (.numItems (ItemIndex. [1 2])) => 2)

(facts "containsItems"
  (let [idx (ItemIndex. [item1 item2])]
    (.containsItem idx item1)   => true
    (.containsItem idx item2)   => true
    (.containsItem idx {:id 3}) => false))

(facts "getAllUsers"
  (fact "returns correct first item"
    (let [idx (ItemIndex. [item1])]
      (-> idx .getAllItems .iterator iterator-seq first) => item1)))

(facts "item2iidx"
  (fact "returns a correct indices"
    (let [idx (ItemIndex. [item1 item2])]
      (.item2iidx idx item1) => 0
      (.item2iidx idx item2) => 1)))

(facts "iidx2item"
  (fact "returns a correct item"
    (let [idx (ItemIndex. [item1 item2])]
      (.iidx2item idx 0) => item1
      (.iidx2item idx 1) => item2)))

