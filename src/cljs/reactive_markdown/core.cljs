(ns reactive-markdown.core
  (:require [reagent.core :as reagent]
            [cljs-on-gh-pages.markdown :as m]))

(enable-console-print!)

(defonce app-state (reagent/atom {:text "Sndrea"}))

(defn blog-post
  []
  (m/md->reagent "## Title
                  **Writing** a blog post _with_ "))

(defn page
  []
  [:div [:div "Introduction"]
        [blog-post]])

(defn ^:export main []
  (reagent/render [page]
                  (.getElementById js/document "cljs-on-gh-pages")))
