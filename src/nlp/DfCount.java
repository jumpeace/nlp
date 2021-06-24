package nlp;

class DfCount extends WordCount {
    private Double idf;
    
    DfCount(Word word, Integer count) {
        super(word, count);
    }

    public Double getIdf() {
        return this.idf;
    }

    public void setIdf(Double idf) {
        this.idf = idf;
    }
}
