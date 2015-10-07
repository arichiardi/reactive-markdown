(ns reactive-markdown.markdown
  "A small utility that renders Markdown to react/reagent
  components. Most of the code has been borrowed from devcards, courtesy
  of Bruce Hauman."
  (:require [reactive-markdown.impl.core :as impl]))

(defn md->react
  "Interprets the input string(s) as Markdown, yielding a react
  component. It wraps the generated HTML in <div
  class=\"<container-class>\"> or <div
  class=\"reactive-markdown-block\"> if container-class is not given as
  first parameter. In case of failure a message is logged to console and
  an error <div> is added to the DOM."
  ([& strs]
   (if (every? string? strs)
     (let [blocks (mapcat impl/parse-out-blocks strs)]
       [:div {:class "reactive-markdown-block"}
        (map impl/markdown-block->react blocks)])
     (let [message "Error: The input should be a seq of strings containing less-sensitive markdown."]
       (try (.error js/console message))
       [:div {:style {:color "#a94442"}} message]))))
