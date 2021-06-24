package nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Word {
    // 単語の情報を格納
    private Map<String, String> info = new HashMap<String, String>();

    public Word() {
        this.info.put("hyousoukei", null);  // 表層形
        this.info.put("hinshi", null);      // 品詞
        this.info.put("hinshi1", null);     // 品詞細分類1
        this.info.put("hinshi2", null);     // 品詞細分類2
        this.info.put("hinshi3", null);     // 品詞細分類3
        this.info.put("katsuyoKata", null); // 活用型
        this.info.put("katsuyoKei", null);  // 活用形
        this.info.put("genkei", null);      // 原形
        this.info.put("yomi", null);        // 読み
        this.info.put("hatsuon", null);     // 発音
    }

    // 単語の情報をキーを指定して変更する
    public void infoValueSet(String key, String value) {
        this.info.put(key, value);
    }

    // 単語の情報をキーを指定して取得する
    public String infoValueGet(String key) {
        return this.info.get(key);
    }

    // 単語の情報の一覧を取得する
    public Map<String, String> getInfo() {
        return this.info;
    }

    // 指定された2つの単語の情報が完全一致するかどうかを判定
    public boolean equals(Word arg) {
        // 単語Aの情報とする
        Map<String, String> thisInfo = this.info;
        // 単語Bの情報とする
        Map<String, String> argInfo = arg.getInfo();

        Iterator<String> thisItr = thisInfo.keySet().iterator();
        Iterator<String> argItr = argInfo.keySet().iterator();

        // 単語の情報をすべて調べる
        while (thisItr.hasNext() || argItr.hasNext()) {
            // 単語Aのある情報
            String thisValue = thisInfo.get(thisItr.next());
            // 単語Bのある情報
            String argValue = argInfo.get(argItr.next());

            // どちらもnullの場合
            if (thisValue == null && argValue == null) {
                continue;
            }

            // どちらかだけnullで一致しない場合
            if (thisValue == null || argValue == null) {
                return false;
            }

            // 一致しない場合
            if (!argValue.equals(thisValue)) {
                return false;
            }
        }

        // 単語Aと単語Bの情報が完全一致する場合
        return true;
    }
}
