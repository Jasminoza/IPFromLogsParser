package withThreads;

public class RangesForThreads {

    public static int[] rangesForThreads;

    public static void setRangesForThreads(int uniqueLinesSize, int numberOfThreads) {
        int[] arr = new int[numberOfThreads];
        for (int i = 0; i < arr.length; i++) {
            uniqueLinesSize -= arr[i] = (uniqueLinesSize + numberOfThreads - i + 1) / (numberOfThreads - i);
        }
        rangesForThreads = arr;
    }
}
