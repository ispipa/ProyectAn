package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity {

    TextView texto;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> puntuaciones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        listView = findViewById(R.id.listView);
        texto = findViewById(R.id.textView);
        Intent i = getIntent();

        String usuario = i.getStringExtra("usuario");
        int punt = i.getIntExtra("puntuacionConseguida", 0);
        texto.setText("¡Enhorabuena " + usuario + "!\n¡Has conseguido " + punt + " puntos!");
        puntuaciones.add(String.valueOf(punt));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, puntuaciones);
        listView.setAdapter(adapter);

    }
}