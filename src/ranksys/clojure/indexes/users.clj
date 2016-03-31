(ns ranksys.clojure.indexes.users
  (:import [es.uam.eps.ir.ranksys.fast.index FastUserIndex]
           [es.uam.eps.ir.ranksys.fast.utils IdxIndex]
           [java.util Arrays]))

(gen-class
  :name ranksys.clojure.indexes.users.UserIndex
  :implements [es.uam.eps.ir.ranksys.fast.index.FastUserIndex]
  :state "umap"
  :init "init"
  :constructors {[] []
                 [clojure.lang.Seqable] []}
  :prefix "uv-")

(defn uv-init
  ([] (uv-init []))
  ([users]
    (let [idx (IdxIndex.)]
      (doall
        (doseq [u users] (.add idx u)))
      ;;send data to constructors
      [[] idx])))

(defn uv-containsUser
  [this user]
  (.containsId (.umap this) user))

(defn uv-getAllUsers [this]
  (.getIds (.umap this)))

(defn uv-uidx2user [this ^Long uidx]
  (.get (.umap this) (int uidx)))

(defn uv-user2uidx [this user]
  (.get (.umap this) user))

(defn uv-numUsers [this]
  (.size (.umap this)))
