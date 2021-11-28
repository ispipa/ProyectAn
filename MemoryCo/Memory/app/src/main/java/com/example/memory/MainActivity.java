package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup buttons;
    EditText editText;
    Button playButton;
    int difficult = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = findViewById(R.id.radioGroup);
        editText = findViewById(R.id.editText);
        playButton = findViewById(R.id.button);

        buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton)
                {
                    difficult = 1;
                }
                else if(i == R.id.radioButton2)
                {
                    difficult = 2;
                }
                else
                {
                    difficult = 3;
                }
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editText.getText().toString();
                if(usuario.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
                else if(difficult == 0)
                {
                    Toast.makeText(MainActivity.this, "Por favor, seleccione una dificultad", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, MainGame.class);
                    i.putExtra("dificultad", difficult);
                    i.putExtra("usuario", usuario);
                    startActivity(i);
                }
            }
        });

    }
}
