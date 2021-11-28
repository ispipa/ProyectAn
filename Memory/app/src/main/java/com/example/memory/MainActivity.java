package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText nomJugador;
    RadioGroup dificultad;
    Button play;
    static String cogerNombre;
    Intent mainGame;
    static int vidas = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomJugador = findViewById(R.id.NombreJugador);
        dificultad = findViewById(R.id.dificultad);
        play = findViewById(R.id.jugar);
        play.setEnabled(false);
        dificultad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if(i == R.id.facil)
                {
                    vidas = 8;

                }
                else if(i == R.id.medio)
                {
                    vidas = 6;

                }
                else
                {
                    vidas = 3;

                }
                //cogerNombre();
            }
        });
        nomJugador.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!nomJugador.getText().toString().equals(""))
            {
                play.setEnabled(true);
                cogerNombre();
            }
                else
                    {
                        play.setEnabled(false);
                    }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        //cogerNombre();
    }
    public void cogerNombre()
    {
        if(!nomJugador.getText().toString().equals(""))
        {
            cogerNombre = nomJugador.getText().toString();
            onClick();
        }
        else
            {
                Toast.makeText(getApplicationContext(),"Introduzca su nombre",Toast.LENGTH_SHORT).show();

            }
    }
    public void onClick()
    {
        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            mainGame = new Intent(getApplicationContext(),MainGame.class);
           // mainGame.putExtra("nombreJugador",cogerNombre);
            startActivity(mainGame);
            }
        });
    }
}