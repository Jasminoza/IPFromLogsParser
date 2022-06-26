import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * В данном варианте парсинг происходит "в лоб", без применения многопоточности.
 */
public class IPParser {
    private static final File logFile = new File("src/main/resources/log.txt");
    private static Set<String> uniqueLines = new HashSet<>();
    private static final Set<String> uniqueIPs = new HashSet<>();
    private static final String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
    private static Matcher matcher;
    private static final long startTime = System.currentTimeMillis();

    public static void main(String[] args) throws IOException {
        readUniqueLinesFromFile();
        makeParcing();
        printParcingResults();
    }


    public static void makeParcing() {
        for (String line : uniqueLines) {
            matcher = pattern.matcher(line);
            if (matcher.find()) uniqueIPs.add(matcher.group());
        }
    }

    public static void readUniqueLinesFromFile() throws IOException {
        FileInputStream fis = new FileInputStream(logFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            uniqueLines.add(line);
        }
    }

    public static void printParcingResults() {
        long elapsedTime = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        System.out.println("Выделены уникальные IP-адреса: ");
        uniqueIPs.stream().sorted().forEach(System.out::println);
        System.out.println("\nЗатраченное время: " + elapsedTime + " секунд");
    }
}
