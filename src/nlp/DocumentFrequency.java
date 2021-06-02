package nlp;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class DocumentFrequency {
    // DFをカウントするためのデータ格納領域の定義
    ArrayList<DfCount> list = new ArrayList<DfCount>();

    // DFの元になるTFを受ける
    TermFrequency tf[];

    DocumentFrequency(TermFrequency[] tf) {
        this.tf = tf;
    }

    public void df(String outputFilename) {
        // TF100ファイル分について繰り返す
        for (int i = 0; i < 100; i++) {
            // TF1件分に含まれる語の分だけ繰り返し
            for (int j = 0; j < this.tf[i].list.size(); j++) {
                Word wo = this.tf[i].list.get(j).getWord();
                // DFのリストの中にエントリがあるか調べる
                int k;
                for (k = 0; k < list.size(); k++) {
                    if (list.get(k).getWord().equals(wo)) {
                        list.get(k).setCount(list.get(k).getCount() + 1);
                        break;
                    }
                }
                // リストにエントリが無かったときは，新しい語としてリストに追加する
                if (k == list.size()) {
                    list.add(new DfCount(wo, Integer.valueOf(1)));
                }
            }
        }
        // ソートする
        list.sort(new WordCompare());
        try {
            // df, idfの結果をファイルに保存する
            FileWriter fw = new FileWriter(outputFilename);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIdf(Math.log10((double) 100 / (double) list.get(i).getCount()) + 1.0);
                fw.write(list.get(i).getWord().infoValueGet("hyousoukei") + "\t" + list.get(i).getCount() + "\t"
                        + String.format("%.10f", list.get(i).getIdf()) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
