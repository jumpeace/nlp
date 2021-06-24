package nlp;

class TfIdfCount {
    private Word word;
    private Double tf;
    private Double idf;
    private Double tfIdf;

    TfIdfCount(Word word, Double tf, Double idf, Double tfIdf) {
        this.word = word;
        this.tf = tf;
        this.idf = idf;
        this.tfIdf = tfIdf;
    }

    public Word getWord() {
        return this.word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Double getTf() {
        return this.tf;
    }

    public void setTf(Double tf) {
        this.tf = tf;
    }

    public Double getIdf() {
        return this.idf;
    }

    public void setIdf(Double idf) {
        this.idf = idf;
    }

    public Double getTfIdf() {
        return this.tfIdf;
    }

    public void setTfIdf(Double tfIdf) {
        this.tfIdf = tfIdf;
    }
}
