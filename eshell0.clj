;; 不要在项目里面编程而是大脑流单文件(Repl+受限环境), 顺着兴趣的中心线而下, 安卓,Spark, React, 后端,同时混编 => 最后组合兴趣

(def partition2-list
  (partition
   2
   (rest
    (rest (clojure.string/split 
           (slurp "吊床驱动编程.txt") #"\n")))))


(defn diff [s1 s2]
  (mapcat
   (fn [[x n]] (repeat n x))
   (apply merge-with - (map frequencies [s1 s2]))))

;; (two-list-minus-merge (list "正如我昨天所说，我换了 茎 " "正如我昨天所说，我换了 茎 ,所以这是更哲学的"))
;; => "正如我昨天所说，我换了 茎 ，所以这是更哲学的"
(defn two-list-minus-merge
  [lis]
  (str (first lis) "，"
       (first (apply diff (map #(clojure.string/split % #",|，") lis)))))

(defn uniq-merge-list
  [lis]
  (clojure.string/join
   ","
   (distinct
    (flatten
     (map #(clojure.string/split % #",|，")  lis)))))

;; (map uniq-merge-list partition2-list) ;;不行!  => ("正如我昨天所说,我换了 茎 ,所以这是更哲学的" "所以这是更哲学的,也谈谈我不能 的头衔 , 决定其中一些是半胡思乱想的 " ... )

(clojure.string/join
 ","
 (distinct
  (flatten
   (map #(clojure.string/split % #",|，") partition2-list))))
