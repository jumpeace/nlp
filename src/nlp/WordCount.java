package nlp;

class WordCount {
    private Word word;// 形態素解析した語の結果を格納する
    private Integer count;// 出現回数を計数するためのカウンタ

    WordCount(Word word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public Word getWord() {
        return this.word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
