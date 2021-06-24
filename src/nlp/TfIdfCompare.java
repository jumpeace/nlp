package nlp;

import java.util.Comparator;

class TfIdfCompare implements Comparator<TfIdfCount> {
    @Override
    public int compare(TfIdfCount tic1, TfIdfCount tic2) {
        if (tic1.getTfIdf() < tic2.getTfIdf())
            return 1;
        if (tic1.getTfIdf() == tic2.getTfIdf())
            return 0;
        if (tic1.getTfIdf() > tic2.getTfIdf())
            return -1;
        return 0;
    }
}
