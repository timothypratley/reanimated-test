(ns reanimated-test.notify
  (:require
   [reagent.core :as r]
   [timothypratley.reanimated.core :as anim]))


(def notify-type-classes {:success "alert-success"
                          :info "alert-info"
                          :warning "alert-warning"
                          :danger "alert-danger"})

(def show-notify? (r/atom false))

(def notify-content (r/atom [:div "Initial state should not be visible"]))

(def current-notify-type (r/atom :success))

(defn notify-body
  "Basic notify message"
  []
  [:div {:style {:top 0
                 :right 0
                 :position "fixed"
                 :margin 5
                 :z-index 1050}}
   [anim/pop-when @show-notify?
    [:div {:class (str "alert "(get notify-type-classes @current-notify-type "alert-success"))}
     [anim/timeout #(reset! show-notify? false) 10000]
     [:span {:style {:display "inline-block"}} @notify-content]
     " "
     [:span
      {:on-click #(reset! show-notify? false)
       :style {:cursor "pointer"}}
      "x"]]]])

(defn notify!
  "Content can be a string or a Reagent component.
  Optional argument 'notify-type' can be one of the following:
  - :success
  - :info
  - :warning
  - :danger"
  ([content] (notify! content nil))
  ([content notify-type]
   (reset! notify-content content)
   (reset! current-notify-type notify-type)
   (reset! show-notify? true)))
