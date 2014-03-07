(ns precure-dcd.views.layout
  (:require [selmer.parser :as parser]
            [clojure.string :as s]
            [compojure.response :refer [Renderable]]
            [noir.session :as session]))

(def template-path "precure_dcd/views/templates/")

(deftype
  RenderableTemplate
  [template params]
  Renderable
  (render
    [_ request]
    {:status 200
     :headers {"Content-Type" "text/html; charset=utf-8"}
     :body (parser/render-file
             (str template-path template)
             (assoc
               params
               (keyword (s/replace template #".html" "-selected"))
               "active"
               :servlet-context
               (:context request)
               :user-id
               (session/get :user-id)))}))

(defn render [template & [params]]
  (RenderableTemplate. template params))

