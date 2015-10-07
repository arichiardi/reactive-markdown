(defproject reactive-markdown "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [reagent "0.5.1"]
                 [cljsjs/showdown "0.4.0-1"]]
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.0" :exclusions [cider/cider-nrepl]]  ]
  :clean-targets ^{:protect false} ["resources/public/reactive-markdown-demo/js/compiled" "target"]
  :scm {:name "git"
        :url "https://github.com/arichiardi/reactive-markdown" }
  :cljsbuild {:builds [{:id "demo"
                        :source-paths ["src/cljs" "demo-src/cljs"]
                        :figwheel {:on-jsload "reactive-markdown.demo/main"}
                        :compiler {:main reactive-markdown.demo
                                   :output-to "resources/public/reactive-markdown-demo/js/compiled/reactive-markdown-demo.js"
                                   :output-dir "resources/public/reactive-markdown-demo/js/compiled/out"
                                   :asset-path "reactive-markdown-demo/js/compiled/out"
                                   :source-map-timestamp true}}]})
