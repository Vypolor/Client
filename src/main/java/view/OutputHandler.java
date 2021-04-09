package view;

import transport.Response;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class OutputHandler {
    private Response response;

    public OutputHandler(Response response){
        this.response = response;
    }

    public void showInformation() throws IOException {
        showArgs(response.getArgs());
        showAnswer(response.getAnswer(), response.getCode());
    }

    private void showArgs(Set<String> arguments){
        for(String argument: arguments)
        {
            if(argument != null){
                System.out.println(argument);
            }
        }
    }

    private void showAnswer(String answer, int operationStatus) throws IOException {
        if(answer != null){
            System.out.println(answer);
        }
        else {
            Properties properties = new Properties();
            properties.load(OutputHandler.class.getResourceAsStream("/codes.properties"));
            String message = properties.getProperty(String.valueOf(operationStatus));
            System.out.println(message);
        }
    }


}
