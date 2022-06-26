import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * В данном варианте парсинг происходит "в лоб", без применения многопоточности.
 */
public class IPParser {
    public static void main(String[] args) throws IOException {
        File logFile = new File("src/main/resources/log.txt");
        Set<String> uniqueIPs = new HashSet<>();
        Set<String> uniqueLines = new HashSet<>();
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(logFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            uniqueLines.add(line);
        }

        for (String lines : uniqueLines) {
            String IPADDRESS_PATTERN =
                    "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            Matcher matcher = pattern.matcher(lines);
            if (matcher.find()) {
                uniqueIPs.add(matcher.group());
            }
        }

        System.out.println(uniqueIPs);

        System.out.println("Elapsed time: " + ((System.currentTimeMillis() - startTime) / 1000) % 60 + " seconds");
    }
}
