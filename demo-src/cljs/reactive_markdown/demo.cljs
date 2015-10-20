(ns reactive-markdown.demo
  (:require [reagent.core :as reagent]
            [markdown.core :as mclj :refer [md->html]]
            [reactive-markdown.markdown :as mine :refer [md->react]]))

(enable-console-print!)

(def state (reagent/atom {:bold false
                          :italic false}))

;; from bhauman/devcards
(defn react-raw [raw-html-str]
  "A React component that renders raw html."
  (.div (.-DOM js/React)
        (clj->js { :key (str (hash raw-html-str))
                  :dangerouslySetInnerHTML
                  { :__html
                   raw-html-str}})))

(defn blog-post
  []
  (react-raw
   (mclj/md->html
    (str "## Using `markdown-clj`\n"
         "Easy, in order to "
         (if (:bold @state) "**write**" "write")
         " an interactive "
         (if (:italic @state) "_blog post_" "blog post")
         " using:\n\n"
         "* [Reagent](https://github.com/reagent-project/reagent)\n"
         "* [Markdown](https://daringfireball.net/projects/markdown/)"))))

(defn blog-post2 []
  (mine/md->react (str "## Using `devcards`\n"
                  "Easy, in order to "
                  (if (:bold @state) "**write**" "write")
                  " a "
                  (if (:italic @state) "_blog post_" "blog post")
                  " using:\n\n"
                  "* [Reagent](https://github.com/reagent-project/reagent)\n"
                  "* [Markdown](https://daringfireball.net/projects/markdown/)")))

(defn toggle-bold [] (swap! state update-in [:bold] not))
(defn toggle-italic [] (swap! state update-in [:italic] not))

(defn console []
  (fn [{:keys [bold italic]}]
    [:div.view
     [:input.toggle {:type "checkbox" :checked bold
                     :on-change #(toggle-bold)}]
     [:label (if (:bold @state) "Bold on" "Bold off")]
     [:input.toggle {:type "checkbox" :checked italic
                     :on-change #(toggle-italic)}]
     [:label (if (:italic @state) "Italic on" "Italic off")]]))

(defn page
  []
  [:div.page [blog-post]
             [blog-post2]
             [console]])

(defn ^:export main []
  (reagent/render [page]
                  (.getElementById js/document "demo-app")))
