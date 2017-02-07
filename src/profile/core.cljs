(ns profile.core
  (:require [reagent.core :as r]))

(enable-console-print!)

(println "This text is printed from src/profile/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn main []
  (let [state (r/atom 12)]
    (fn []
      [:div.pure-g {:style {:font-size (str @state "pt")}
                    :on-click #(swap! state inc)}
       [:div.pure-u-1-3 "okokok"]
       [:div.pure-u-2-3
        [:div "pooppp"]
        [:div "pooppp"]
        [:div "pooppp"]]
       #_   [:div.pure-u-1-3 "jsjsjsjs"]])))

(defn mount []
  (r/render-component [main]
                      (.getElementById js/document "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (mount)
  )
