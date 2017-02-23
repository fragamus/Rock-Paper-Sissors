(defproject untangled/demo "1.0.0"
  :description "Untangled Cookbook Recipe"
  :url ""
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}
  :min-lein-version "2.6.1"
  :verbose true
  :uberjar-name "uberjar.jar"








  :dependencies [

                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.omcljs/om "1.0.0-alpha46"]
                 [navis/untangled-client "0.6.1"]
                 [navis/untangled-server "0.6.2" :exclusions [io.aviso/pretty org.clojure/clojurescript]]
                 [navis/untangled-datomic "0.4.11" :exclusions [org.clojure/tools.cli]]
                 [com.datomic/datomic-free "0.9.5359" :exclusions [com.google.guava/guava]]
                 [secretary "1.2.3" :exclusions [com.cemerick/clojurescript.test]]
                 [joda-time "2.9.3"]
                 [clj-time "0.11.0"]
                 [lein-doo "0.1.7" :scope "test" :exclusions [org.clojure/tools.reader]]
                 [org.clojure/tools.namespace "0.2.11"]
                 [commons-codec "1.10"]
                 [com.taoensso/timbre "4.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [navis/untangled-spec "0.3.9" :scope "test"]
                 [navis/untangled-websockets "0.3.1"]
                 [binaryage/devtools "0.5.2"]
                 [figwheel-sidecar "0.5.3" :exclusions [ring/ring-core joda-time org.clojure/tools.reader]]
                 [com.cemerick/piggieback "0.2.1"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [juxt/dirwatch "0.2.3"]
                 [com.google.protobuf/protobuf-java "2.5.0" :exclusions [com.google.guava/guava]]

                 ]

  :plugins [[lein-cljsbuild "1.1.3"]]

  :source-paths ["dev/server" "src/server"]

  :jvm-opts ["-server" "-Xmx1024m" "-Xms512m" "-XX:-OmitStackTraceInFastThrow"]

  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :cljsbuild {:builds
              [



              {:id           "dev"
                :source-paths ["src/client" "dev/client"]
                :figwheel     true
                :compiler     {:main                 cljs.user
                               :asset-path           "js/compiled/dev"
                               :output-to            "resources/public/js/compiled/app.js"
                               :output-dir           "resources/public/js/compiled/dev"
                               :optimizations        :none
                               :parallel-build       false
                               :verbose              false
                               :recompile-dependents true
                               :source-map-timestamp true}}

              {:id           "production"
                        :source-paths ["src/client"]
                        :compiler     {:verbose         true
                                       :output-to       "resources/public/js/compiled/app.min.js"
                                       :output-dir      "resources/public/js/compiled"
                                       :pretty-print    false
                                       :closure-defines {goog.DEBUG false}
                                       :source-map      "resources/public/js/compiled/app.min.js.map"
                                       :elide-asserts   true
                                       :optimizations   :advanced}}



                               ]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {

             :uberjar {:main      app.core
                       :aot       :all
                       :prep-tasks ["compile"
                                    ["cljsbuild" "once" "production"]]
                      }

            }


  :repl-options {:init-ns          user
                 :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
)
