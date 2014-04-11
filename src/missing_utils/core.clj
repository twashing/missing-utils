(ns missing-utils.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))


(defn generate-id 
  "generate system wide unique ID"
  []  
  (.. (java.rmi.dgc.VMID.) toString (replaceAll ":" "") (replaceAll "-" "")))

(defn list-fns-in-ns [input-ns]
  (require input-ns)
  (keys (ns-publics input-ns)))

(defn load-edn [fname]
  (let [pbreader (java.io.PushbackReader. (io/reader (io/resource fname)))]
    (edn/read pbreader)))


