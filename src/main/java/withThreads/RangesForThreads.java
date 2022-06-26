package withThreads;

public class RangesForThreads {

    public static int[] rangesForThreads;

    public static void setRangesForThreads(int uniqueLinesSize, int numberOfThreads) {
        rangesForThreads = new int[numberOfThreads];
        for (int i = 0; i < rangesForThreads.length; i++) {
            uniqueLinesSize -= rangesForThreads[i] = (uniqueLinesSize + numberOfThreads - i + 1) / (numberOfThreads - i);
        }
    }
}
