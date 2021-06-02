package nlp;

public class NaturalLanguageFrequency {
    public static void main(String[] args) {
        System.out.println("TF導出");
        TermFrequency[] tf = new TermFrequency[100];
        for (int i = 0; i < 1; i++) {
            tf[i] = new TermFrequency();

            String inputFilename = "data/" + String.format("%03d", i + 1) + ".txt";
            String tfOutputFilename = "data/" + String.format("%03d", i + 1) + "tf.txt";

            System.out.println(inputFilename);
            System.out.println(tfOutputFilename);

            tf[i].tf(inputFilename, tfOutputFilename);
        }

        // System.out.println("DF導出");
        // DocumentFrequency df = new DocumentFrequency(tf);
        // String idfOutputFilename = "data/df.txt";
        // System.out.println(idfOutputFilename);
        // df.df(idfOutputFilename);

        // System.out.println("TF-IDF導出");
        // TfIdf[] tfIdf = new TfIdf[100];
        // for (int i = 0; i < 100; i++) {
        //     tfIdf[i] = new TfIdf(tf[i], df);
        //     String tfIdfOutputFilename = "data/" + String.format("%03d", i + 1) + "tfIdf.txt";
        //     System.out.println(tfIdfOutputFilename);
        //     tfIdf[i].tfIdf(tfIdfOutputFilename);
        // }
    }
}
