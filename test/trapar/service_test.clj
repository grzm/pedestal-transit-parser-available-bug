(ns trapar.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [trapar.service :as service]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet service/service)))

(deftest transit-test
  (let [{:keys [body]
         :as response} (response-for service
                                     :get "/transit"
                                     :headers {"Content-Type" "application/transit+json"}
                                     :body "[\"^ \",\"~:b\",2,\"~:a\",1]")]
    (is (= "{:b 2, :a 1}" body))))
