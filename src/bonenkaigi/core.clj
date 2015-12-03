(ns bonenkaigi.core
  (:require [clojure.java.io :as io])
  (:import [org.apache.poi.ss.usermodel WorkbookFactory]))

(defn read-excel [input-file]
  (with-open [in (io/input-stream input-file)]
    (let [wb (WorkbookFactory/create in)
          sheet (. wb getSheetAt 0)]
      (->> sheet
           .rowIterator    ;; 行のイテレータを取得
           iterator-seq    ;; JavaのイテレータをClojureの遅延シーケンスに変換
           (drop 1)         ;; 1行めを読み飛ばす
           (map #(.getCell % 0 ))   ;; A列を抜き出す
           (map #(if(= (.getCellType %) 0) (.getNumericCellValue %) (.getStringCellValue %) )))))) ;; 値の取得

(let [member (vec (read-excel "resources/bonenkaigi-tickets-27273.xlsx"))
      rank (vec (range 1 (inc (count member))))]
  (map (fn [x y] (vector x y)) rank (shuffle member))
  )
