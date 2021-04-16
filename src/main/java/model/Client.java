package model;

import org.apache.commons.lang3.StringUtils;
import transport.*;
import util.RequestParser;
import view.OutputHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Scanner scanner;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(
                        socket.getOutputStream());

        ois = new ObjectInputStream(
                        socket.getInputStream());
        scanner = new Scanner(System.in);
    }

    public void startClient(){
        try {
            int code = 0;
            System.out.println("ENTER /help TO VIEW THE COMMANDS");
            do{
                String userCommand = getUserCommand();
                if(checkCommandValidity(userCommand)){
                    Request request = RequestParser.parseCommand(userCommand);
                    if(checkArgsValidity(request.getArgs()))
                    {
                        oos.writeObject(request);
                        oos.flush();

                        Response response = (Response) ois.readObject();
                        OutputHandler outputHandler = new OutputHandler(response);
                        outputHandler.showInformation();
                        code = response.getCode();
                    }
                    else {
                        System.out.println("WARNING: ONE OF THE ARGUMENTS CONSISTS ONLY OF SPACES");
                    }
                }
                else{
                    System.out.println("WARNING: EMPTY COMMAND");
                }
            }while (code!= 700);

            oos.close();
            ois.close();
            scanner.close();

            System.exit(0);
        } catch (IOException e) {
            System.err.println("\nError: Server Disconnected!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getUserCommand(){
        System.out.println("=====================");
        System.out.println("Enter the command: ");
        String userCommand = scanner.nextLine();
        return userCommand;
    }

    private boolean checkCommandValidity(String command){
        if(command.equals(""))
            return false;
        else
            return true;
    }

    private boolean checkArgsValidity(String[] args){
        int argLength = 0;

        for(String arg : args){
            if(arg != null){
                if(StringUtils.isWhitespace(arg)){
                    return false;
                }
            }
        }
        return true;
    }
}
