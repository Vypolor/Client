package TransportObjects;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Response implements Serializable {
    private static Response instance;

    public static Response getInstance(){
        if(instance == null){
            instance = new Response();
        }
        return instance;
    }

    private int code;
    private Set<String> args = new HashSet<>();
    private String answer;

    public Response(){}

    public Response(int code) {
        this.code = code;
    }

    public Response(Set<String> args) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        this.args = args;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Set<String> getArgs() {
        return args;
    }

    public void setArgs(Set<String> args) {
        this.args = args;
    }

}
