package client.main;

import client.controller.ControllerJeuClient;

public class Client {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: java Client <adresse> <port> <maxTurn default=Integer.MAX_VALUE>");
            System.exit(1);
        } else {
            try {
                ControllerJeuClient gestionaire = new ControllerJeuClient((args.length<3) ? Integer.MAX_VALUE : Integer.parseInt(args[2]), args[0], Integer.parseInt(args[1]));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
