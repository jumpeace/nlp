package nlp;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

class TfIdf {
    ArrayList<TfIdfCount> list = new ArrayList<TfIdfCount>();

    TermFrequency tf;
    DocumentFrequency df;

    TfIdf(TermFrequency tf, DocumentFrequency df) {
        this.tf = tf;
        this.df = df;
    }

    public void tfIdf(String outputFilename) {
        for (int i = 0; i < this.tf.list.size(); i++) {
            double tf = this.tf.list.get(i).getTf();
            for (int j = 0; j < this.df.list.size(); j++) {
                Word tfWord = this.tf.list.get(i).getWord();
                Word dfWord = this.df.list.get(j).getWord();
                if (tfWord.equals(dfWord)) {
                    double idf = this.df.list.get(j).getIdf();
                    double tfIdf = tf * idf;
                    list.add(new TfIdfCount(this.tf.list.get(i).getWord(), tf, idf, tfIdf));
                    break;
                }
            }

        }
        // ソートする
        list.sort(new TfIdfCompare());
        try {
            FileWriter fw = new FileWriter(outputFilename);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).getWord().infoValueGet("hyousoukei") + "\t"
                        + String.format("%.10f", list.get(i).getTf()) + "\t"
                        + String.format("%.10f", list.get(i).getIdf()) + "\t"
                        + String.format("%.10f", list.get(i).getTfIdf()) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}