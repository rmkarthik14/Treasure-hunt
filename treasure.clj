(ns treasure)
(require '[clojure.string :as str])
(use '[clojure.string :as str :only [split]])

(defn readFile []
  (def stringMap (slurp "map.txt"))
  (println "This is my challenge:\n")
  (println stringMap)
  )
(readFile)
(def linescust (str/split-lines (slurp "map.txt")))
(def line-words1 (vec (mapv #(str/split %1 #"") linescust)))
;;(println line-words1)

(def newMap (-> line-words1 ))
;;(println (str (get-in line-words1 [0 0])))
;;(def line-words1 (vec (mapv #(str/split %1 #"") linescust)))
;;(doseq [item  (line-words1)])
;; (println line-words1)
;;(print  ((line-words1 1)1) )
(def path (vector))
(def p-map (sorted-map))
(def row-cnt (count line-words1))

(def col-cnt (count (line-words1 0)))

(def row (atom 0))
(def co (atom 0))
(def flg (atom 0))


(defn valid [v-r v-c]
  (cond
    (or (and (< v-r row-cnt) (< v-c col-cnt)) (and (> v-r -1) (> v-c -1))) "true"
    :else "false"))



(defn srch [mapV r c]
  (cond
    (= (str (get-in mapV [r c])) "@") (do (swap! flg inc) (println "\nWoo hoo, I found the treasure :-)\n"))
    (= (str (get-in mapV [r c])) "-") (if (= (valid r c) "true")
                                        (do
                                          (def newMap (-> mapV (assoc-in [r c] \+)))
                                          ;;(println newMap)
                                          (cond
                                            (= (srch newMap r (inc c)) "true") ("true")
                                            (= (srch newMap (inc r) c) "true") ("true")
                                            (= (srch newMap r (dec c)) "true") ("true")
                                            (= (srch newMap (dec r) c) "true") ("true")
                                            )
                                          )
                                        )
    )
  )



(srch line-words1 0 0)
(cond
  (or (= @flg 0) (= (str (get-in newMap [0 0])) "#") ) (println "\nUh oh, I could not find the treasure :-(\n")
  )

(def l (atom 0))
(def l2 (atom 0))
(defn prnt []
  (while (< @l row-cnt)
    (do (println (str/join (newMap @l)))
        (swap! l inc))))
(prnt)


