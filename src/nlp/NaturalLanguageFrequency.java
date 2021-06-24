package nlp;

class NaturalLanguageFrequency {
    public static void main(String[] args) {
        int FILE_NUM = 100;
        System.out.println("TF導出");
        TermFrequency[] tf = new TermFrequency[FILE_NUM];
        for (int i = 0; i < FILE_NUM; i++) {
            tf[i] = new TermFrequency();

            String inputFilename = "data/" + String.format("%03d", i + 1) + ".txt";
            String tfOutputFilename = "data/" + String.format("%03d", i + 1) + "tf.txt";

            System.out.println(inputFilename);
            System.out.println(tfOutputFilename);

            tf[i].tf(inputFilename, tfOutputFilename);
        }

        System.out.println("DF導出");
        DocumentFrequency df = new DocumentFrequency(tf);
        String idfOutputFilename = "data/df.txt";
        System.out.println(idfOutputFilename);
        df.df(idfOutputFilename);

        System.out.println("TF-IDF導出");
        TfIdf[] tfIdf = new TfIdf[FILE_NUM];
        for (int i = 0; i < FILE_NUM; i++) {
            tfIdf[i] = new TfIdf(tf[i], df);
            String tfIdfOutputFilename = "data/" + String.format("%03d", i + 1) + "tfIdf.txt";
            System.out.println(tfIdfOutputFilename);
            tfIdf[i].tfIdf(tfIdfOutputFilename);
        }
    }
}
