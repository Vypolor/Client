package sample;

import TransportObjects.*;
import util.GetInfoFromFile;
import util.RequestParser;
import util.Status;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 8000)){

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            socket.getOutputStream());

            ObjectInputStream ois =
                    new ObjectInputStream(
                            socket.getInputStream());

            Scanner in = new Scanner(System.in);
            int code = 0;

            do {

                System.out.println("=====================");
                System.out.println("Enter the command: ");
                String request = in.nextLine();

                Request req = RequestParser.parseCommand(request);
                if(request.equals("") || request.equals(null))
                    continue;
                else{
                    oos.writeObject(req);
                    oos.flush();

                    Response response = (Response) ois.readObject();
                    String answer = response.getAnswer();
                    code = response.getCode();
                    for(String str : response.getArgs())
                        if(str!= null)
                            System.out.println(str);
                    if (answer != null)
                        System.out.println(answer);
                    else
                        System.out.println("operation status: " + Status.generateMessageByCode(response.getCode()));
                }

            }while (code != 700);

            oos.close();
            ois.close();
            in.close();

            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("\nError: Server Disconnected!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
