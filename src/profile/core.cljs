(ns profile.core
  (:require [reagent.core :as r]))

(enable-console-print!)

(println "This text is printed from src/profile/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:size 12
                            :color "red"
                            :update-fn #(js/alert "click!!!")}))
(def colors ["green" "blue" "red" "yellow" "purple" "pink" "brown" "orange"])

(defn reset-update-fn! [fn]
  (swap! app-state
         #(assoc % :update-fn fn)))

(defn update-size-and-color-fn [inc-or-dec-fn]
  #(swap! app-state
          (fn [{:keys [size] :as st}]
            (assoc st
                   :size (inc-or-dec-fn size)
                   :color (rand-nth colors)))))


(comment
  "Use these forms to have fun @cljs repls!!!"

  ;;; This calls the passed in fn at a given interval.
  ;;; Try updating the interval and/or fn to pass in.
  ;;; The fn is stored in an atom to allow live coding.
  ;;; That is, updating the fn to be invoked on the fly,
  ;;; without stopping the timer!!!
  (def intvl (js/setInterval #(let [fn (:update-fn @app-state)]
                                (fn)) 50))

  ;;; While the interval is being fired, try evaluating this form
  ;;; passing in inc/dec/(constantly 45) etc that updates the size of the font.
  (reset-update-fn! (update-size-and-color-fn
                     (constantly 3)))

  ;;; Use this to stop the interval that is running
  (js/clearInterval intvl))


(defn main []
  (fn []
    [:div.pure-g {:style {:font-size (str (:size @app-state) "pt")
                          :background (:color @app-state)}
                  :on-click (:update-fn @app-state)}
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
