(defproject reactive-markdown "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [reagent "0.5.1"]
                 [markdown-clj "0.9.75"]
                 [cljsjs/showdown "0.4.0-1"]]
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.1" :exclusions [cider/cider-nrepl]]  ]
  :clean-targets ^{:protect false} ["resources/public/reactive-markdown-demo/js/compiled"
                                    "resources/public/js/compiled"
                                    "resources/phantomjs/js/compiled"
                                    "target"]
  :scm {:name "git"
        :url "https://github.com/arichiardi/reactive-markdown" }

  :cljsbuild {:builds [{:id "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :compiler {:output-to "resources/phantomjs/js/compiled/cljs-browser-repl.js"
                                   :pretty-print false
                                   :optimizations :whitespace
                                   :source-map-timestamp true}}
                       {:id "demo"
                        :source-paths ["src/cljs" "test/cljs" "demo-src/cljs"]
                        :figwheel {:on-jsload "reactive-markdown.demo/main"
                                   :css-dirs ["resources/public/demo/styles"]}
                        :compiler {:main reactive-markdown.demo
                                   :output-to "resources/public/demo/js/compiled/reactive-markdown-demo.js"
                                   :output-dir "resources/public/demo/js/compiled/out"
                                   :asset-path "demo/js/compiled/out"
                                   :optimizations :none
                                   :source-map-timestamp true}}]
              :test-commands {"unit" ["phantomjs"
                                      "resources/phantomjs/test.js"
                                      "resources/phantomjs/test.html"]}}

  :aliases {"fig-demo" ["do" "clean" ["figwheel" "demo"]]
            "minify" ^{:doc "Clean and compile sources minified for production."} ["do" "clean" ["cljsbuild" "once" "min"]]
            ;; Nested vectors are supported for the "do" task
            "deploy" ^{:doc "Clean, compile (minified) sources, test and then deploy."} ["do" "clean" ["test" ":integration"] ["deploy" "clojars"]]
            "unit-test" ^{:doc "Executes unit tests."} ["do" "clean" ["cljsbuild" "test" "unit"]]}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.5"]
                                  [org.clojure/tools.nrepl "0.2.11"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-port 5088
                              ;; Load CIDER, refactor-nrepl and piggieback middleware
                              :nrepl-middleware ["cider.nrepl/cider-middleware"
                                                 "refactor-nrepl.middleware/wrap-refactor"
                                                 "cemerick.piggieback/wrap-cljs-repl"]}}})
