package TTL.utils;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FileURLDecoder {
    public static String getPathToResource(String fileName) {
        URL url = FileURLDecoder.class.getResource("/"  + fileName);
        String path = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
        return path;
    }
}
