(ns hash-f
  (:require [clojure.walk :refer [walk]]))

(defn f* [rest]
  (apply list `f rest))

(defn r [vars level expr]
  (or (and (symbol? expr)
           (= level (count (take-while #{\$} (name expr))))
           (let [i (parse-long (subs (name expr) level))]
             ((swap! vars update i #(or % (gensym))) i)))
      (walk (partial r
                     vars
                     (+ level (count (take-while #{`f} (when (list? expr) expr)))))
            identity
            expr)))

(defmacro f [& body]
  (let [vars (atom {0 '_})
        body' (r vars 1 body)]
    (list 'fn (mapv #(@vars % '_) (range 1 (inc (apply max (keys @vars))))) body')))
