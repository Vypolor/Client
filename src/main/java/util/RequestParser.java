package util;

import TransportObjects.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    public static Request parseCommand(String requestStr) {
        Request request = new Request();
        String[] args = new String[4];

        Pattern cmdNamePattern = Pattern.compile("(/[a-z]+)");
        Matcher cmdNameMatcher = cmdNamePattern.matcher(requestStr);

        Pattern keyPattern = Pattern.compile("(-[a-z]+)");
        Matcher keyMatcher = keyPattern.matcher(requestStr);

        Pattern argsPattern = Pattern.compile("\"([a-zA-Zа-яА-ЯёЁ0-9/:*.:?\\s]+)\"");
        Matcher argsMatcher = argsPattern.matcher(requestStr);

        if (cmdNameMatcher.find())
            request.setCommand(cmdNameMatcher.group(1));

        if (keyMatcher.find())
            request.setParameter(keyMatcher.group(1));

        for (int i = 0; i < 4; i++) {
            if (argsMatcher.find()) {
                if(argsMatcher.group(1).equals(request.getCommand())) {
                    --i;
                    continue;
                }
                args[i] = argsMatcher.group(1);
            }
        }
        request.setArgs(args);
        return request;
    }

    public static long parseLength(String length) {
        //length pattern hh:mm:ss
        String[] splitted = length.split(":");

        return Long.parseLong(splitted[0]) * 3600000
                + Long.parseLong(splitted[1]) * 60000
                + Long.parseLong(splitted[2]) * 1000;
    }

}
