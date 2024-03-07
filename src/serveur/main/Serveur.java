package serveur.main;

import java.net.ServerSocket;
import java.net.Socket;

import models.ReaderWriter;
import serveur.controller.ControllerJeuServeur;

public class Serveur {
    private static ServerSocket serverSocket;
    private static ControllerJeuServeur controller;

    private static void initServeur(int port, int mt) throws Exception {
        serverSocket = new ServerSocket(port);
        controller = new ControllerJeuServeur(mt);
    }
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: java Serveur <port> <maxTurn default=Integer.MAX_VALUE>");
            System.exit(1);
        } else {
            try {
                initServeur((args.length<0) ? 1234 : Integer.parseInt(args[0]), (args.length<2) ? Integer.MAX_VALUE : Integer.parseInt(args[1]));
                while(true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Joueur connecté");
                    ReaderWriter rw = new ReaderWriter(clientSocket);
                    controller.addPlayer(rw);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
