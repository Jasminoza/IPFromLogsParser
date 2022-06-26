import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static withThreads.RangesForThreads.setRangesForThreads;

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

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        //читаем уникальные строки из файла
        readUniqueLinesFromFile();

        //парсим
        for (String line : uniqueLines) {
            matcher = pattern.matcher(line);
            if (matcher.find()) uniqueIPs.add(matcher.group());
        }

        long elapsedTime = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        System.out.println("Выделены уникальные IP-адреса: ");
        uniqueIPs.stream().sorted().forEach(System.out::println);
        System.out.println("\nЗатраченное время: " + elapsedTime + " секунд");
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
