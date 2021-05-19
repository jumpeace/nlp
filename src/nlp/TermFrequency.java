package nlp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TermFrequency {
    public static void main(String[] args) {
        for (int file_i = 1; file_i <= 100; file_i++) {// 解析対象のファイル名
            String inputFilename = "./data/00" + file_i + ".txt";
            // mecab コマンドで対象ファイルを解析するコマンド文
            String[] command = { "mecab", inputFilename };

            ArrayList<TfCount> list = new ArrayList<TfCount>();
            try {
                // 解析するコマンドを実行する
                Process ps = Runtime.getRuntime().exec(command);
                // 解析した結果を表示するためのオブジェクトに変換する
                BufferedReader bReader_i = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
                // 標準出力を 1 行ずつ受け取る一時オブジェクト
                String targetLine;
                while (true) {
                    // 形態素解析結果を 1 行ずつ受け取る
                    targetLine = bReader_i.readLine();
                    if (targetLine == null) {
                        // 最終行になったら終わる
                        break;
                    } else if (targetLine.equals("EOS")) {
                        continue;
                    } else {
                        String targetArray[] = targetLine.split("[\t|,]");
                        Word wo = new Word();
                        if (targetArray.length >= 1)
                            wo.setHyousoukei(targetArray[0]);
                        if (targetArray.length >= 2)
                            wo.setHinshi(targetArray[1]);

                        int i;
                        for (i = 0; i < list.size(); i++) {
                            if (list.get(i).getWord().equals(wo)) {
                                list.get(i).setCount(list.get(i).getCount() + 1);
                                break;
                            }
                        }
                        // リストにエントリが無かったときは，新しい語としてリストに追加する
                        if (i == list.size()) {
                            list.add(new TfCount(wo, Integer.valueOf(1)));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // 今のリストのエントリをすべて表示する
                int sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    // System.out.println(list.get(i).getWord().getHyousoukei() + ":" +
                    // list.get(i).getCount());
                    sum += list.get(i).getCount();
                }
                // tf の結果をファイルに保存する
                String outputFilename = "data/00" + file_i + "tf.txt";
                FileWriter fw = new FileWriter(outputFilename);
                for (int i = 0; i < list.size(); i++) {
                    // System.out.println(fileal.get(i).word.getHyousoukei() + ":" +
                    // fileal.get(i).getCount());
                    list.get(i).tf = (double) list.get(i).getCount() / (double) sum;
                    fw.write(list.get(i).getWord().getHyousoukei() + "\t" + list.get(i).getCount() + "\t"
                            + String.format("%.10f", list.get(i).tf) + "\n");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
