package redpill.engine;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static String loadResource(String fileName) throws Exception {
        String result;
        try (InputStream in = Utils.class.getResourceAsStream(fileName);
            Scanner sc = new Scanner(in, StandardCharsets.UTF_8.name())) {
               result = sc.useDelimiter("\\A").next();
        }
        return result;
    }
}
