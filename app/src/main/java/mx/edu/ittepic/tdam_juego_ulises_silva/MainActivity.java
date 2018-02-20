package mx.edu.ittepic.tdam_juego_ulises_silva;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button comenzar;
    Thread thread;
    int sumador = 1;
    TextView texto;
    boolean ejecutar, pausa;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comenzar=findViewById(R.id.comenzar);
        texto=findViewById(R.id.text);
        ejecutar=true;
        pausa =false;


        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x=(int) (Math.random()*3);

                texto.setText(""+x);
                ejecutar=true;


                if(sumador==x){

                   final AlertDialog.Builder mensaje= new AlertDialog.Builder(MainActivity.this);
                   mensaje.setMessage("Felicidades has ganado! :)").
                           setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                               }
                           });
                   mensaje.show();
                    ejecutar=false;


                }else {
                    Toast.makeText(MainActivity.this, "casi lo logras!:(", Toast.LENGTH_SHORT).show();
                    if(pausa ==false){
                        try {
                            thread = new Thread() {
                                public void run() {
                                    while (ejecutar) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                DecimalFormat df = new DecimalFormat("#"); String twoDigitNum = df.format(sumador);
                                                comenzar.setText(twoDigitNum);

                                            }
                                        });
                                        try {
                                            sleep(400);
                                        } catch (InterruptedException e) {
                                        }
                                        sumador += 1.0;
                                        if(sumador >=7){
                                            sumador = 0;
                                        }
                                    }
                                }
                            };
                            thread.start();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error ya se preciono dos veces", Toast.LENGTH_SHORT).show();
                        }
                        pausa =true;
                    }

                }

            }


        });
    }
}
