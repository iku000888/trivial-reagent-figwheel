(ns profile.core
  (:require [reagent.core :as r]))

(enable-console-print!)

(println "This text is printed from src/profile/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:size 12
                            :color "red"}))
(def colors ["green" "blue" "red" "yellow" "purple" "pink" "brown" "orange"])

(def update-fn
  #(swap! app-state
          (fn [{:keys [size] :as st}]
            (assoc st
                   :size (inc size)
                   :color (rand-nth colors)))))

(defn main []
  (fn []
    [:div.pure-g {:style {:font-size (str (:size @app-state) "pt")
                          :background (:color @app-state)}
                  :on-click update-fn}
     [:div.pure-u-1-3 (:color @app-state)]
     [:div.pure-u-2-3
      [:div (:color @app-state)]
      [:div (:color @app-state)]
      [:div (:color @app-state)]]]))

(defn mount []
  (r/render-component [main]
                      (.getElementById js/document "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (mount))

(mount)
