(ns precure-dcd.routes.home
  (:use compojure.core)
  (:require [precure-dcd.views.layout :as layout]
            [precure-dcd.util :as util]))

(defn home-page []
  (layout/render "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))
