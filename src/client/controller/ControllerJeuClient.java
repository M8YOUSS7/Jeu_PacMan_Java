package client.controller;

import java.net.Socket;
import javax.naming.directory.InvalidAttributesException;
import models.ReaderWriter;

public class ControllerJeuClient extends controllers.ControleurPacmanGame {
    private Socket socket;
    private ReaderWriter readerWriter;

    public ControllerJeuClient(int mt, String adresse, int port) throws Exception {
        super(mt);
        this.socket = new Socket(adresse, port);
        this.readerWriter = new ReaderWriter(socket);
    }
    
    public Socket getSocket() throws InvalidAttributesException {
        if (socket != null) {
            return socket;
        } else {
            throw new InvalidAttributesException("Instance null");
        }
    }
}
