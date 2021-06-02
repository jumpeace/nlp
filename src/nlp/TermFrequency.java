package nlp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

public class TermFrequency {
    ArrayList<TfCount> list = new ArrayList<TfCount>();

    // tf値の本来の意味にそぐわない単語を取り除く
    private boolean doesSkipWord(Word word) {
        String hyousoukei = word.infoValueGet("hyousoukei");
        String hinshi = word.infoValueGet("hinshi");
        String hinshi1 = word.infoValueGet("hinshi1");
        String genkei = word.infoValueGet("genkei");

        // 0文字の単語をスキップする
        if (hyousoukei.length() == 0)
            return true;

        // アルファベット1文字をスキップする
        if (hyousoukei.matches("[a-zA-Z]{1}"))
            return true;

        // 品詞によってスキップする
        String[] skipHinshi = { "助詞", "助動詞", "接続詞", "記号" };
        for (int i = 0; i < skipHinshi.length; i++) {
            if (hinshi == null)
                return true;
            if (hinshi.equals(skipHinshi[i]))
                return true;
        }

        // 名詞の品詞細分類1によってスキップする
        if (hinshi.equals("名詞")) {
            String[] skipHinshi1Meishi = { "数" };
            for (int i = 0; i < skipHinshi1Meishi.length; i++) {
                if (hinshi1 == null)
                    continue;
                if (hinshi1.equals(skipHinshi1Meishi[i]))
                    return true;
            }
        }

        // 名詞でサ変接続でも原形がない(原型が*)場合は記号であるのでスキップする
        if ((hinshi.equals("名詞") && hinshi1.equals("サ変接続")) && genkei.equals("*"))
            return true;

        return false;
    }

    public void tf(String inputFilename, String outputFilename) {
        // mecab コマンドで対象ファイルを解析するコマンド文
        String[] command = { "mecab", inputFilename };

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
                        wo.infoValueSet("hyousoukei", targetArray[0]);
                    if (targetArray.length >= 2)
                        wo.infoValueSet("hinshi", targetArray[1]);
                    if (targetArray.length >= 3)
                        wo.infoValueSet("hinshi1", targetArray[2]);
                    if (targetArray.length >= 8)
                        wo.infoValueSet("genkei", targetArray[7]);

                    if (this.doesSkipWord(wo))
                        continue;

                    boolean doesExist = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getWord().equals(wo)) {
                            list.get(i).setCount(list.get(i).getCount() + 1);
                            doesExist = true;
                            break;
                        }
                    }
                    // リストにエントリが無かったときは，新しい語としてリストに追加する
                    if (!doesExist) {
                        list.add(new TfCount(wo, Integer.valueOf(1)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 今のリストのエントリをすべて表示する
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getCount();
        }
        list.sort(new WordCompare());
        try {
            FileWriter fw = new FileWriter(outputFilename);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).tf = (double) list.get(i).getCount() / (double) sum;
                fw.write(list.get(i).getWord().infoValueGet("hyousoukei") + "\t" + list.get(i).getCount() + "\t"
                        + String.format("%.10f", list.get(i).tf) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
