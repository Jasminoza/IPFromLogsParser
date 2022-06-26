package withThreads;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static withThreads.RangesForThreads.setRangesForThreads;

public class IPParser10Threads {
    private static final File logFile = new File("src/main/resources/log.txt");
    protected static Set<String> uniqueLines = new HashSet<>();
    protected static List<String> uniqueLinesList;
    private static final Set<String> uniqueIPs = new HashSet<>();
    private static final String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
    private static Matcher matcher;
    private static AtomicBoolean allLinesAreReadFromTheFile = new AtomicBoolean(false);
    private static final int numberOfThreads = 10; // TODO:: задаем количество потоков
    private static final long startTime = System.currentTimeMillis();

    public static void main(String[] args) throws IOException {
        readUniqueLinesFromFile();
        setRangesForThreads(uniqueLines.size(), numberOfThreads);
        makeParcing();
        printParcingResults();
    }




public static void makeParcing() {
    for (String line : uniqueLines) {
        matcher = pattern.matcher(line);
        if (matcher.find()) uniqueIPs.add(matcher.group());
    }
}
    public static void printParcingResults() {
        long elapsedTime = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        System.out.println("Выделены уникальные IP-адреса: ");
        uniqueIPs.stream().sorted().forEach(System.out::println);
        System.out.println("\nЗатраченное время: " + elapsedTime + " секунд");
    }
    public static AtomicBoolean getAllLinesAreReadFromTheFile() {
        return allLinesAreReadFromTheFile;
    }

    public static void setAllLinesAreReadFromTheFile() {
        allLinesAreReadFromTheFile = new AtomicBoolean(true);
    }

    public static void readUniqueLinesFromFile() throws IOException {
        FileInputStream fis = new FileInputStream(logFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            uniqueLines.add(line);
        }
    }
}
