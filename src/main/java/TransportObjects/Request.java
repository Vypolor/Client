package TransportObjects;

import java.io.Serializable;

public class Request implements Serializable  {
    private static Request instance;
    private String command;
    private String parameter;
    private String[] args;

    public Request(String command, String parameter, String[] args) {
        this.command = command;
        this.parameter = parameter;
        this.args = args;
    }

    public static Request getInstance(){
        if (instance == null){
            instance = new Request();
        }
        return instance;
    }

    public Request(){}

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}