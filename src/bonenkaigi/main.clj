(ns bonenkaigi.main
  (:require [bonenkaigi.core :as core])
  (:gen-class))

(defn -main [& {:as args}]
  (core/start-server))
