(defproject clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [org.clojure/core.async "0.4.474"]
                 [org.clojure/core.logic "0.8.11"]
                 [com.taoensso/nippy "2.13.0"]
                 [org.clojure/data.csv "0.1.4"]]
  :main ^:skip-aot clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
