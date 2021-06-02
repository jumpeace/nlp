package nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Word {
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

    public void infoValueSet(String key, String value) {
        this.info.put(key, value);
    }

    public String infoValueGet(String key) {
        return this.info.get(key);
    }

    public Map<String, String> getInfo() {
        return this.info;
    }

    public boolean equals(Word arg) {
        Map<String, String> thisInfo = this.info;
        Map<String, String> argInfo = arg.getInfo();

        Iterator<String> thisItr = thisInfo.keySet().iterator();
        Iterator<String> argItr = argInfo.keySet().iterator();
        while (thisItr.hasNext() || argItr.hasNext()) {
            String thisValue = thisInfo.get(thisItr.next());
            String argValue = argInfo.get(argItr.next());
            if (thisValue == null && argValue == null) {
                continue;
            }
            if (thisValue == null || argValue == null) {
                return false;
            }
            if (!argValue.equals(thisValue)) {
                return false;
            }
        }
        return true;
    }
}
