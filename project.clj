(defproject ranksys.clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.ranksys/RankSys "0.4.2"]]
  :profiles {:test {:dependencies [[midje "1.8.3"]]
                    :plugins [[lein-midje "3.2"]]}}
  :repositories [["jitpack.io" "https://jitpack.io"]])
