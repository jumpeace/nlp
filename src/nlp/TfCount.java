package nlp;

public class TfCount extends WordCount {
    Double tf;// 計数値を全語数で割った TF 値

    TfCount(Word word, Integer count) {
        super(word, count);
    }

    TfCount(Word word, Integer count, Double tf) {
        super(word, count);
        this.tf = tf;
    }

    public Double getTf() {
        return this.tf;
    }

    public void setTf(Double tf) {
        this.tf = tf;
    }
}