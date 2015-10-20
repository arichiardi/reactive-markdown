# reactive-markdown (abandoned)

This project is only useful for explaining how to create a Markdown to raw html
component for React. It used to be a library, but it eventually is not
necessary, now that I have discovered
[```markdown-clj```](https://github.com/yogthos/markdown-clj).

Therefore, after importing the correct dependencies (js/React should be
visible) you will be able to do:

 ``` clojure

(ns reactive-markdown.demo
  (:require [reagent.core :as reagent]
            [markdown.core :as mclj :refer [md->html]]))

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

```

See ```demo.cljs``` for details (the old code is left there as comparison.  I
am willing to deploy this library if there is a need, but I don't see any at
the moment.

---

<del>This tiny library renders Markdown strings to React components.

<del>The code is borrowed from Bruce Hauman's amazing
[devcards](https://github.com/bhauman/devcards) with his blessing.</del>

<del>The employed Markdown->HTML converter in both this library and ```devcards```
is [showdownjs](https://github.com/showdownjs/showdown).</del>

<del>There are is one function only in the ```reactive-markdown.markdown```
namespace that you can use: ```md->react```. It is a variadic function that
accepts strings and returns a React component. Easy peasy.</del>

