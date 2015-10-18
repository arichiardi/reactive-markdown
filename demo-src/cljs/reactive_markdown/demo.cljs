(ns reactive-markdown.demo
  (:require [reagent.core :as reagent]
            [reactive-markdown.markdown :as markdown]))

(enable-console-print!)

(defn blog-post []
  (markdown/md->react
   "## Why would you want that?

    Easy, in order to **write** a _blog post_ using:

    1. [Reagent](https://github.com/reagent-project/reagent)
    2. [Markdown](https://daringfireball.net/projects/markdown/)"))

(defn ^:export main []
  (reagent/render [blog-post]
                  (.getElementById js/document "demo-app")))
