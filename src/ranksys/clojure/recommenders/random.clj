(ns ranksys.clojure.recommenders.random
  (:import [java.util Arrays]
           [es.uam.eps.ir.ranksys.rec.fast.basic RandomRecommender]
           [es.uam.eps.ir.ranksys.rec Recommender]
           [es.uam.eps.ir.ranksys.fast FastRecommendation]))

(defprotocol IResultFormatter
  (to-result [this idx-double]))

(defprotocol IRecommender
  (recommend [this uidx max-length]))

(defn to-result-map
  "turns RankSys result of recommendation into Clojure hash-map"
  [^FastRecommendation idx-double]
  (doall
    (for [item (.getIidxs idx-double)]
      {:id  (.getKey item)
       :score (.getValue item)})))

(deftype CljRecommender [recommender]
  IRecommender
  (recommend [this uidx item-ids]
    (to-result-map
      (.getRecommendation recommender
                          (int uidx)
                          (-> item-ids int-array Arrays/stream))
     )))
  

(defn create
  [user-index item-index]
  (->CljRecommender
    (RandomRecommender. user-index item-index)))

(comment
  (import '[java.util Arrays]) 
  (import '[es.uam.eps.ir.ranksys.rec.fast.basic RandomRecommender])
  (import '[ranksys.clojure.indexes.users UserIndex])
  (import '[ranksys.clojure.indexes.items ItemIndex])
 
  (def users
    [{:id 1 :name "Aadi"}
     {:id 2 :name "Peter"}])
  (def uidx (UserIndex. users))
  (.numUsers uidx)

  (def items [
    {:id 10 :name "Item Robot"}
    {:id 20 :name "Item Drone"}
    {:id 30 :name "Item Pony"}])
  (def iidx (ItemIndex. items))
  (.getAllIidx iidx)

  (def shaman (RandomRecommender. uidx iidx))
  (def res (.getRecommendation shaman (int 0) (.getAllIidx iidx)))
  (map
    (fn [r] [(.getKey r) (.getValue r)])
    (.getIidxs res))

  (def arr (int-array [1 2 4]))
  (Arrays/stream arr)

  (require '[ranksys.clojure.recommenders.random :as random-rec] :reload)
  (def rec (random-rec/create uidx iidx))
  (def res (random-rec/recommend rec 0 [0 1]))
  
  )
