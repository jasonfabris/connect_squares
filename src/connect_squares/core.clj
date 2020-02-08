(ns connect_squares.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(def grid-size-x 3) ;; number of cells wide
(def grid-size-y 3) ;; number of cells high
(def prop-area 0.9)  ;; how much of the cell does the box fill?

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb 360 100 100 1)
  (q/background 240)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw-state [state]
      
  (q/fill (:color state) 255 255)
  
  (let [cell-width (/ (q/width) grid-size-x)
        cell-height (/ (q/width) grid-size-y)
        rect-width (* cell-width prop-area)
        rect-height (* cell-height prop-area)]
    
      
    (q/with-translation [cell-width cell-height]
      
      (q/rect 0 0 cell-width cell-height)
      (q/fill 306 50 10 0.8)
      (q/rect 0 0 rect-width rect-height))))


(q/defsketch connect_squares
  :title "Connected Squares"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
