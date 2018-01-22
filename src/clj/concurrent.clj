(ns core.concurrent)

;;ref: https://www.braveclojure.com/concurrency/

;; futures
(let [result (future (Thread/sleep 3000)
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))

(deref (future (Thread/sleep 1000) 0) 100 5) ;; return 5 if compuattion takjes more than 100ms

(realized? (future (Thread/sleep 1000)))

;; promises
(def my-promise (promise))
(deliver my-promise (+ 1 2))
(deref my-promise) ;; block until delivered, otherwise fetch cached value without blocking 

;;delays
(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there"]
           (println "First deref:" message)
           message)))
(force jackson-5-delay)  ;;causing a task to start
(deref jackson-5-delay)  ;; return cached result         

;; atom - mutable, reactive and synchronized variable
(def state (atom {:a 1 :b 2}))

(deref state)

(swap! state (fn [current-state]
               (merge-with + current-state {:a 9 :b 8})))  ;; can be attempted multiple times, hence should not have side effects

(reset! state {:a 1 :b 2})

(add-watch state :a (fn [key watched old-state new-state]
                      (println (cond
                                 (> (:a new-state) 10) "Hello"
                                 (<= (:a new-state) 10) "Ola"))))
(swap! state update-in [:a] + 15)

(swap! state update-in [:a] - 15)

(def state (atom {:a 1 :b 2} :validator (fn [{:keys [a]}]
                                          (or (< a 20)
                                              (throw (IllegalStateException. "Cant be grater than 20"))))))

(swap! state update-in [:a] + 100)
(deref state)

;; ref 
(def counter (ref 0))

(deref counter)

(do
  (future
    (dosync
     (alter counter inc)
     (println @counter)
     (Thread/sleep 500)
     (alter counter inc)
     (println @counter)))
  (Thread/sleep 250)
  (println @counter)
  (Thread/sleep 250))

;; agents
(def counter (agent 0))
(send counter inc)

;; pmap
(partition-all 3 (range 100))

(time
 (map inc (range 1000)))

(time
 (pmap inc (range 1000)))

(time
 (pmap
  #(doall (map inc %))
  (partition-all 3 (range 1000))))