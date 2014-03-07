(ns precure-dcd.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]
            [korma.db :refer [defdb]]))

(def db-store "site.db")

(defdb db-spec (postgres {:db "precure_dcd"
                          :user "precure_dcd"
                          :password "precure_dcd"
                          :port 5432}))
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-users-table
  []
  (sql/with-connection db-spec
    (sql/create-table
      :users
      [:id "varchar(20) PRIMARY KEY"]
      [:first_name "varchar(30)"]
      [:last_name "varchar(30)"]
      [:email "varchar(30)"]
      [:admin :boolean]
      [:last_login :time]
      [:is_active :boolean]
      [:pass "varchar(100)"])))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-users-table))
