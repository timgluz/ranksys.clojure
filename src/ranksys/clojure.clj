(ns ranksys.clojure
  (:import [es.uam.eps.ir.ranksys.core.index]))

(comment
  (compile 'ranksys.clojure.indexes.users)
  (import '[ranksys.clojure.indexes.users UserIndex])
 
  (set! *warn-on-reflection* true) 
  
  (def u1 {:id 1 :name "Aadi"})
  (def u2 {:id 2 :name "Peter"})

  (def idx (UserIndex. [u1 u2]))
  (.numUsers idx)
  (.containsUser idx 1)
  (.containsUser idx u1)
  (.findFirst  (.getAllUsers idx))
  (.uidx2user idx (int 1))
  (.user2uidx idx u1)
  )
