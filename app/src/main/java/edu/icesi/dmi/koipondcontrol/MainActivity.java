package edu.icesi.dmi.koipondcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import envios.Mensaje;

public class MainActivity extends AppCompatActivity implements Observer {

    private ComunicacionCliente cc;
    private Button botonArriba, botonAbajo, botonIzquierda, botonDerecha, conectar;

    //HOLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("App Iniciada");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cc = ComunicacionCliente.getReference();
        //new Thread(cc).start();
        cc.addObserver(this);
        //cc.enviarMensaje("arriba");
        conectar = (Button) findViewById(R.id.conectar);
        botonArriba = (Button) findViewById(R.id.botonArriba);
        botonAbajo = (Button) findViewById(R.id.botonAbajo);
        botonDerecha = (Button) findViewById(R.id.botonDerecha);
        botonIzquierda = (Button) findViewById(R.id.botonIzquierda);

        conectar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("Mi indice es: " + cc.getIndice());
                Mensaje m = new Mensaje("nada", cc.getIndice());
                cc.enviarMensaje(m);
                conectar.setEnabled(false);
            }
        });


        botonArriba.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("DOWN");
                    Mensaje m = new Mensaje("arriba", cc.getIndice());
                    cc.enviarMensaje(m);
                } else {
                    System.out.println("UP");
                    Mensaje m = new Mensaje("quieto", cc.getIndice());
                    cc.enviarMensaje(m);
                }

                return false;
            }
        });

        botonDerecha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("DOWN");
                    Mensaje m = new Mensaje("derecha", cc.getIndice());
                    cc.enviarMensaje(m);
                } else {
                    System.out.println("UP");
                    Mensaje m = new Mensaje("quieto", cc.getIndice());
                    cc.enviarMensaje(m);
                }

                return false;
            }
        });

        botonIzquierda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("DOWN");
                    Mensaje m = new Mensaje("izquierda", cc.getIndice());
                    cc.enviarMensaje(m);
                } else {
                    System.out.println("UP");
                    Mensaje m = new Mensaje("quieto", cc.getIndice());
                    cc.enviarMensaje(m);
                }

                return false;
            }
        });

        botonAbajo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("DOWN");
                    Mensaje m = new Mensaje("abajo", cc.getIndice());
                    cc.enviarMensaje(m);
                } else {
                    Mensaje m = new Mensaje("quieto", cc.getIndice());
                    cc.enviarMensaje(m);
                }

                return false;
            }
        });
//
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ComunicacionCliente) {
            if (o instanceof Mensaje) {
                Mensaje mensaje = (Mensaje) o;
                //  if(mensaje.getIndice() == 6){
                //mensaje.setIndice();
                // }
            }
        }
        //------FINAL UPDATE-------//
    }

    //----------FINAL DE LA CLASE--------//
}