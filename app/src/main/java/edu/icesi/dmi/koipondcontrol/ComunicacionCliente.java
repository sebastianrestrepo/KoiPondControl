package edu.icesi.dmi.koipondcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by sebastianrestrepo on 12/09/17.
 */

public class ComunicacionCliente extends Observable implements Runnable{

    private Socket s;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean conectado;

    public ComunicacionCliente(){
        conectado = false;

        Thread t = new Thread(this);
        t.start();


    }

    @Override
    public void run() {
        while (true){
            if(s == null) {

                try {
                    s = new Socket(InetAddress.getByName("192.168.0.6"), 9090);
                    System.out.println("Conectado");
                    salida = new ObjectOutputStream(s.getOutputStream());
                    entrada = new ObjectInputStream(s.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                recibirMensaje();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void recibirMensaje(){
        try {
            Object atraparObjecto = entrada.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(final Object obj){

        final Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    salida.writeObject(obj);
                    salida.flush();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

        });
        t.start();
    }


    //------------FINAL DE LA CLASE------------//
}
