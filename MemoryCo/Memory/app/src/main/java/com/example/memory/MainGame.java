package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainGame extends AppCompatActivity {

    CountDownTimer timer;
    Intent i;
    TextView playerName, timerText, scoreText;
    ImageView[] cartasImg = new ImageView[8];
    ArrayList<Integer> cartasConDrawable = new ArrayList<>();
    int[] drawables = {R.drawable.black, R.drawable.green, R.drawable.red, R.drawable.white};
    int imgReverso;
    ImageView[] corazones = new ImageView[8];
    int totalTimer;
    int puntActual, puntAcierto;
    int vidasIniciales, vidasActuales;
    ImageView anteriorClickada = null;
    ImageView actualClickada = null;
    int parejasDescubiertas = 0;
    int cartasMostradas;
    boolean comparando, acierto;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        inicializacion();
        prepararCartas();
        barajarCartas();
        prepararNivel();
        manejarTimer();
    }

    void inicializacion()
    {
        i = getIntent();
        playerName = findViewById(R.id.playerName);
        usuario = i.getStringExtra("usuario");
        playerName.setText("Jugador: " + usuario);

        timerText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        corazones[0] = findViewById(R.id.heart0);
        corazones[1]  = findViewById(R.id.heart1);
        corazones[2] = findViewById(R.id.heart2);
        corazones[3]  = findViewById(R.id.heart3);
        corazones[4] = findViewById(R.id.heart4);
        corazones[5]  = findViewById(R.id.heart5);
        corazones[6]  = findViewById(R.id.heart6);
        corazones[7]  = findViewById(R.id.heart7);

        cartasImg[0] = findViewById(R.id.card0);
        cartasImg[1]  = findViewById(R.id.card1);
        cartasImg[2] = findViewById(R.id.card2);
        cartasImg[3]  = findViewById(R.id.card3);
        cartasImg[4] = findViewById(R.id.card4);
        cartasImg[5]  = findViewById(R.id.card5);
        cartasImg[6]  = findViewById(R.id.card6);
        cartasImg[7]  = findViewById(R.id.card7);

        imgReverso = R.drawable.magic;

    }
    void prepararCartas()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                cartasConDrawable.add(drawables[j]);
            }
        }

    }
    void barajarCartas()
    {
        Collections.shuffle(cartasConDrawable);
    }
    void prepararNivel()
    {
        LinearLayout layoutCorazones = findViewById(R.id.linearLayoutCorazones);
        int dificultad;
        dificultad = i.getIntExtra("dificultad", 0);
        if(dificultad == 1)
        {
            totalTimer = 20;
            puntAcierto = 25;
            vidasIniciales = 8;
        }
        else if(dificultad == 2)
        {
            totalTimer = 15;
            puntAcierto = 50;
            for (int i = 6; i < 8; i++) {
                ((ViewGroup)corazones[i].getParent()).removeView(corazones[i]);
            }
            vidasIniciales = 6;
            layoutCorazones.setWeightSum(vidasIniciales);
        }
        else
        {
            totalTimer = 10;
            puntAcierto = 100;
            for (int i = 3; i < 8; i++) {
                ((ViewGroup)corazones[i].getParent()).removeView(corazones[i]);
            }
            vidasIniciales = 3;
            layoutCorazones.setWeightSum(vidasIniciales);
        }

        vidasActuales = vidasIniciales;

    }

    void manejarTimer()
    {
        timer = new CountDownTimer(totalTimer * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timerText.setText(Long.toString(millisUntilFinished /1000));
            }

            @Override
            public void onFinish()
            {
                if(vidasActuales > 1)
                {
                    vidasActuales--;
                    ((ViewGroup)corazones[vidasActuales].getParent()).removeView(corazones[vidasActuales]);
                    timer.cancel();
                    timer.start();
                }
                else
                {
                    Intent i = new Intent(MainGame.this, FinishActivity.class);
                    i.putExtra("puntuacionConseguida", puntActual);
                    i.putExtra("usuario", usuario);
                    startActivity(i);
                }
            }
        }.start();
    }

    public void onClickCard (View view)
    {
        actualClickada = (ImageView) view;
        int numClickado = Integer.parseInt(view.getTag().toString());;

        if(comparando)
            return;


        actualClickada.setImageResource(cartasConDrawable.get(numClickado));
        cartasMostradas++;

        if(cartasMostradas == 2) //Se están mostrando dos cartas
        {
            comparando = true;
            //Si ambos drawables son iguales...
            if(anteriorClickada.getDrawable().getConstantState().equals(actualClickada.getDrawable().getConstantState()))
            {
                Toast.makeText(this, "Acierto!", Toast.LENGTH_SHORT).show();
                parejasDescubiertas++;
                acierto = true;
            }
            else
            {
                Toast.makeText(this, "Fallo!", Toast.LENGTH_SHORT).show();
                acierto = false;
            }
            verJugada(acierto);
        }
        else
        {
            anteriorClickada = actualClickada;
        }





    }
    void verJugada(boolean acierto)
    {
        final boolean thisAcierto = acierto;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                cartasMostradas = 0;
                comparando = false;
                if(!thisAcierto)
                {
                    anteriorClickada.setImageResource(imgReverso);
                    actualClickada.setImageResource(imgReverso);
                }
                else if(parejasDescubiertas == 4)
                {
                    nuevaRonda();
                }
            }
        };
        handler.postDelayed(runnable, 2000);
    }
    void nuevaRonda()
    {
        puntActual += puntAcierto;
        scoreText.setText("Puntuación: " + puntActual);
        Toast.makeText(getApplicationContext(), "New Round!", Toast.LENGTH_SHORT).show();
        for(ImageView carta : cartasImg)
        {
            carta.setImageResource(imgReverso);
        }
        parejasDescubiertas = 0;
        timer.cancel();
        timer.start();
        barajarCartas();
    }


}
