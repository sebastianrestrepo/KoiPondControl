package edu.icesi.dmi.koipondcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ComunicacionCliente cc;
    private Button botonArriba, botonAbajo, botonIzquierda, botonDerecha;

    //HOLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cc = new ComunicacionCliente();
        //new Thread(cc).start();
        cc.addObserver(this);
        //cc.enviarMensaje("arriba");
        botonArriba = (Button) findViewById(R.id.botonArriba);
        botonAbajo = (Button) findViewById(R.id.botonAbajo);
        botonDerecha = (Button) findViewById(R.id.botonDerecha);
        botonIzquierda = (Button) findViewById(R.id.botonIzquierda);

        botonArriba.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    System.out.println("DOWN");
                    cc.enviarMensaje("arriba");
                } else {
                    System.out.println("UP");
                    cc.enviarMensaje("quieto");
                }

                return false;
            }
        });

        botonDerecha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    System.out.println("DOWN");
                    cc.enviarMensaje("derecha");
                } else {
                    System.out.println("UP");
                    cc.enviarMensaje("quieto");
                }

                return false;
            }
        });

        botonIzquierda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    System.out.println("DOWN");
                    cc.enviarMensaje("izquierda");
                } else {
                    System.out.println("UP");
                    cc.enviarMensaje("quieto");
                }

                return false;
            }
        });

        botonAbajo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    System.out.println("DOWN");
                    cc.enviarMensaje("abajo");
                } else {
                    System.out.println("UP");
                    cc.enviarMensaje("quieto");
                }

                return false;
            }
        });
//
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    //----------FINAL DE LA CLASE--------//
}
