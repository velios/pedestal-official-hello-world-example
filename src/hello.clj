(ns hello
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defonce server nil)

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})


(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]}))

(defn create-server []
  (http/create-server
   {::http/routes routes
    ::http/type :jetty
    ::http/join? false
    ::http/port 8890}))

(defn start []
  (alter-var-root #'server (constantly (create-server)))
  (http/start server))

(defn stop []
  (http/stop server)
  (alter-var-root #'server (constantly nil)))


(comment
  ;; Start your server
  (start)

  ;; check it is working
  ;; curl -i http://127.0.0.1:8890/greet

  ;; Stop your server
  (stop)

  ;; Add instrumentation (with reload)

  ;; Start your server again
  (start)

  ;; You need to re-create your server so it uses the new (instrumented) functions
  )
