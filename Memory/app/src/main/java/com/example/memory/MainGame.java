package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainGame extends AppCompatActivity
{
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<Integer> botones = new ArrayList<>();
    ArrayList<Button> botonesCarta = new ArrayList<>();
    TextView puntuacion;
    TextView nomJugador;
    TextView tiempoJuego;
    ImageView []corrazones = new ImageView[8];
    int tiempo = 0;
    int puntuacionCOorrecto = 0;
    //Intent b;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        puntuacion = findViewById(R.id.PuntuacionGame);
        nomJugador = findViewById(R.id.NombreJugador_Juego);
        nomJugador.setText(MainActivity.cogerNombre);
        tiempoJuego = findViewById(R.id.tiempo);
        ponerTiempo();
        tiempoJuego();
        //ponerCartas();
    }
    public void tiempoJuego()
    {
        CountDownTimer countDownTimer = new CountDownTimer(tiempo,1000)
        {

            @Override
            public void onTick(long l)
            {
                if(tiempo > 0)
                {
                    tiempoJuego.setText(Long.toString(l/1000));
                }

            }
            @Override
            public void onFinish()
            {

            }
        };
        countDownTimer.start();

    }
    public void puntuacion()
    {

    }
    public void ponerTiempo()
    {
        if(MainActivity.vidas == 8)
        {
            tiempo = 21000;
            quitarCorazones(corrazones.length);
            ponerCartas(corrazones.length);
        }
        else if(MainActivity.vidas == 6)
        {
            tiempo = 16000;
            quitarCorazones( corrazones.length - 2);
            ponerCartas(corrazones.length - 2);
        }
        else
            {
                tiempo = 11000;
                quitarCorazones( corrazones.length - 5);
                ponerCartas(corrazones.length - 4);
            }
    }
    public void quitarCorazones(int vidass)
    {
        corrazones = new ImageView[]{findViewById(R.id.ima1),findViewById(R.id.ima2)
                ,findViewById(R.id.ima3),findViewById(R.id.ima4),findViewById(R.id.ima5)
                ,findViewById(R.id.ima6),findViewById(R.id.ima7),findViewById(R.id.ima8)};
        for (int i = 0; i < vidass; i++)
        {
            corrazones[i].setVisibility(View.VISIBLE);
        }
    }
    public void ponerCartas(int numBotones)
    {
        images.add(R.drawable.black);
        images.add(R.drawable.white);
        images.add(R.drawable.green);
        images.add(R.drawable.red);
        images.add(R.drawable.black);
        images.add(R.drawable.white);
        images.add(R.drawable.green);
        images.add(R.drawable.red);

        botonesCarta.add(findViewById(R.id.b1));
        botonesCarta.add(findViewById(R.id.b2));
        botonesCarta.add(findViewById(R.id.b3));
        botonesCarta.add(findViewById(R.id.b4));
        botonesCarta.add(findViewById(R.id.b5));
        botonesCarta.add(findViewById(R.id.b6));
        botonesCarta.add(findViewById(R.id.b7));
        botonesCarta.add(findViewById(R.id.b8));
        for (int i = 0, j = 0; ((i  < numBotones) && (j <= (images.size() -(images.size() - numBotones)))) ; i++, j++)
        {
            /*if(numBotones == 4)
            {
                Collections.shuffle(images);
                //botonesCarta.get(j).setBackgroundResource(images.get(i));
            }
            else if (numBotones == 6)
            {
                images.add(R.drawable.black);
                images.add(R.drawable.white);
                Collections.shuffle(images);
                //botonesCarta.get(j).setBackgroundResource(images.get(i));
            }else
                {
                    images.add(R.drawable.black);
                    images.add(R.drawable.white);
                    images.add(R.drawable.green);
                    images.add(R.drawable.red);
                    Collections.shuffle(images);
                    //botonesCarta.get(j).setBackgroundResource(images.get(i));
                }*/
            botonesCarta.get(i).setBackgroundResource(images.get(j));
            Collections.shuffle(images);
            botonesCarta.get(i).setBackgroundResource(images.get(j));
        }
       //Collections.shuffle(botonesCarta);
    }
}