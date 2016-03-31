(ns ranksys.clojure.indexes.items
  (:import [es.uam.eps.ir.ranksys.fast.index FastItemIndex]
           [es.uam.eps.ir.ranksys.fast.utils IdxIndex]
           [java.util Arrays]))

(gen-class
  :name ranksys.clojure.indexes.items.ItemIndex
  :implements [es.uam.eps.ir.ranksys.fast.index.FastItemIndex]
  :state "imap"
  :init "init"
  :constructors {[] []
                 [clojure.lang.Seqable] []}
  :prefix "ix-")

(defn ix-init
  ([] (ix-init []))
  ([items]
    (let [idx (IdxIndex.)]
      (doall
        (doseq [i items] (.add idx i)))
      ;;send data to constructors
      [[] idx])))

(defn ix-containsItem
  [this item]
  (.containsId (.imap this) item))

(defn ix-getAllItems [this]
  (.getIds (.imap this)))

(defn ix-iidx2item
  [this ^Long iidx]
  (.get (.imap this) (int iidx)))

(defn ix-item2iidx
  [this item]
  (.get (.imap this) item))

(defn ix-numItems [this]
  (.size (.imap this)))
