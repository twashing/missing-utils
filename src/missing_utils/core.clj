(ns missing-utils.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]

            [clojure.reflect :as r]
            [clojure.pprint :refer [print-table]]))


(defn generate-id
  "generate system wide unique ID"
  []
  (.. (java.rmi.dgc.VMID.) toString (replaceAll ":" "") (replaceAll "-" "")))

(defn fns-in-ns [input-ns]
  (require input-ns)
  (keys (ns-publics input-ns)))

(defn load-edn [fname]
  (let [pbreader (java.io.PushbackReader. (io/reader (io/resource fname)))]
    (edn/read pbreader)))

(defn java-methods [^Object obj]
  (sort-by :name
           (filter :exception-types (:members (r/reflect client)))))

(defn java-methods-table [^Object obj]
  (clojure.pprint/print-table (java-methods obj)))
