(ns bonenkaigi.handler.main
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as res]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [bonenkaigi.excel :as excel]))

(defroutes main-routes
  (GET "/" [] (res/redirect "/index.html"))
  (GET "/members" []
     (response {:member (excel/members)}))
  (POST "/members" []
     (response {:member (excel/members)}))
  (route/files "/")
  (route/resources "/")
  (route/not-found "Not Found"))
