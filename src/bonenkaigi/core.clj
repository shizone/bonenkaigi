(ns bonenkaigi.core
  (:require [compojure.core :refer [routes]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as server]
            [ring.middleware.json :as middleware]
            [bonenkaigi.handler.main :refer [main-routes]]))

(defonce server (atom nil))

(def app
  (-> (handler/api main-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'app {:port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))
