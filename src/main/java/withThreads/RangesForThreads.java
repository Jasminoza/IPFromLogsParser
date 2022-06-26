package withThreads;

import java.util.ArrayList;

import static withThreads.IPParser10Threads.uniqueLines;
import static withThreads.IPParser10Threads.uniqueLinesList;

public class RangesForThreads {

    public static int[] rangesForThreads;

    public static void setRangesForThreads(int uniqueLinesSize, int numberOfThreads) {
        rangesForThreads = new int[numberOfThreads];
        for (int i = 0; i < rangesForThreads.length; i++) {
            uniqueLinesSize -= rangesForThreads[i] = (uniqueLinesSize + numberOfThreads - i + 1) / (numberOfThreads - i);
        }
        uniqueLinesList = new ArrayList<>(uniqueLines);
    }
}
