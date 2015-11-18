(ns reanimated-test.core
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reanimated-test.notify :as notify]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn hello-world []
  [:div
   [:button
    {:on-click
     (fn [e]
       (prn "Notifying")
       (notify/notify! "Hello"))}
    "Click me!"]
   [notify/notify-body]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
