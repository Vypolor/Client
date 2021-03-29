package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Status {
    public static String generateMessageByCode(int code) throws IOException {
        Properties properties = new Properties();
        properties.load(Status.class.getResourceAsStream("/codes.properties"));
        String message = properties.getProperty(String.valueOf(code));
        return message;
    }
}
