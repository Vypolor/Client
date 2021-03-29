package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class GetInfoFromFile {

    public static String getInfo(String path) throws IOException {
        String xml = Files.lines(Paths.get(path)).collect(Collectors.joining("\n"));
        return xml;
    }
}
