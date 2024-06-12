(ns main
  (:require [org.httpkit.server :refer [run-server]]))

(def PORT 8080)

(defonce server-atom (atom nil))

(defn server-started?
  []
  (boolean (deref server-atom)))

(defn start!
  []
  (if (server-started?)
    "Already cooking"
    (let [stop-server-fn (run-server (fn [request]
                                       {:status 200
                                        :headers {"Content-Type" "text/html"}
                                        :body (str "<h1> the square hole?<br/>" (apply str (repeat 10000 "&#x1f440;")) "</h1>")})
                                     {:port PORT})]
      (reset! server-atom stop-server-fn)
      (println (str "Cooking on localhost:" PORT "/")))))

(defn stop!
  []
  (if-not (server-started?)
    "Already not cooking"
    (let [stop-server-fn (deref server-atom)]
      (stop-server-fn :timeout 100)
      (reset! server-atom nil)
      "Stopped cooking")))

(comment
  (start!)
  (stop!)
  (server-started?))


