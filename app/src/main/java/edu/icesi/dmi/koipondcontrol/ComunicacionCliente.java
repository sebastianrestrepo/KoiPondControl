package edu.icesi.dmi.koipondcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

import envios.Mensaje;

/**
 * Created by sebastianrestrepo on 12/09/17.
 */

public class ComunicacionCliente extends Observable implements Runnable{

    private Socket s;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean conectado;
    private int indice;
    private static ComunicacionCliente ref;

    public ComunicacionCliente(){
        conectado = false;
/*
        Thread t = new Thread(this);
        t.start();
*/
        indice = 6;

    }

    public static ComunicacionCliente getReference() {
        if(ref == null){
            ref = new ComunicacionCliente();
            new Thread(ref).start();
        }
        return ref;
    }

    @Override
    public void run() {
        while (true) {
            if (s == null) {

                try {
                    System.out.println("Conexión iniciada");
                    s = new Socket(InetAddress.getByName("10.0.2.2"), 8080);
                    salida = new ObjectOutputStream(s.getOutputStream());
                    entrada = new ObjectInputStream(s.getInputStream());
                    System.out.println("Flujos enlazados");
                    conectado = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            while (true) {
                try {
                    recibirMensaje();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void recibirMensaje(){
        try {
            Mensaje m = (Mensaje) entrada.readObject();
            if(indice == 6){
                indice = m.getIndice();
                System.out.println("Se agrega el indice: " +indice);
                setChanged();
                notifyObservers("Indice asignado: " + indice);
                clearChanged();
           }
            setChanged();
            notifyObservers(m);
            clearChanged();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(final Object obj){

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Mensaje m = (Mensaje) obj;
                    if(conectado) {
                        if (s.isConnected()) {
                            salida.writeObject(m);
                            salida.flush();
                            System.out.println("Mensaje enviado: " + m);
                        }
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

        });
        t.start();
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    //------------FINAL DE LA CLASE------------//
}
