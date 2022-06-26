import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogCreator {
    public static void main(String[] args) throws IOException {
        File logFile = new File("src/main/resources/log.txt");
        FileWriter fileWriter = new FileWriter(logFile);
        for (int i = 0; i < 1_000_000; i++) {
            fileWriter.append("gwnrt;og'a'werfksdf 127.0.0.1 kw;erjgnwlrtngert\n");
            fileWriter.append("gafgsdfhsdfgadcf 168.168.168.0 oirmdwihn^@^%Dkw;erjgnwlrtngert\n");
            fileWriter.append("0.0.0.0 jgnwlrtngert\n");
            fileWriter.append("ggnsgsdfgsdghsrtht \n");
            fileWriter.append("thkidf 158.89.57.4\n");
            fileWriter.append("thkidf 158.49.67.5 \n");
            fileWriter.append("thkidf 300.468.657.4 \n");
        }
    }
}
