package model;

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
                    oos.writeObject(request);
                    oos.flush();

                    Response response = (Response) ois.readObject();
                    OutputHandler outputHandler = new OutputHandler(response);
                    outputHandler.showInformation();
                    code = response.getCode();
                }
                else{
                    System.out.println("WARNING: EMPTY COMMAND");
                    continue;
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
        if(command.equals("") || command.equals(null))
            return false;
        else
            return true;
    }
}
