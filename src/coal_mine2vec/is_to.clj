(ns coal-mine2vec.is-to
  (:require [clojure-word2vec.core :refer [create-input-format get-matches
                                           get-relations word2vec]]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn num->filename [n]
  (try
    (slurp (.getFile (io/resource (str "coal_mine/problem_" n ".cljc"))))
    (catch Exception e
      nil)))

(defn extract [str-data]
  (->> str-data
    str/split-lines
    (mapv #(str/split % #" |\(|\)"))
    (mapv #(filter (fn [x] (not= "" x)) %))))

(defn get-corpi [n]
  (let [prep (mapv num->filename (range 1 n))
        text (filterv #(not (nil? %)) prep)
        corpus (mapv extract text)]
    (->> corpus (apply concat) (filter not-empty))))

(def d (get-corpi 170))
(def m (word2vec d))

(defn is-to [from-this from-that to-this]
  (get-relations m from-this from-that to-this))

(defn is-to-message [args res]
  (apply str "\"" (first args) "\" is to \"" (second args) "\" as \""
         (last args) "\" is probably to \"" (first res)
         "\"\n ... but may also be one of these: "
         (interpose " " (map #(str "\"" % "\"") (rest res)))))

(defn -main [& args]
  (let [res (apply is-to args)]
    (println (is-to-message args res))))
