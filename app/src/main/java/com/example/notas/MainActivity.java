package com.example.notas;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Nota> listaNotas = new ArrayList<>();
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        listView = findViewById(R.id.listView);

        NotaAdapter notaAdapter = new NotaAdapter(
                getApplicationContext(),
                R.layout.nota,
                listaNotas
        );

        listView.setAdapter(notaAdapter);

        bd = openOrCreateDatabase("bd", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR);");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                builder.setTitle("Digite sua nova nota: ");
                final EditText input = new EditText(MainActivity.this);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String novaNota = input.getText().toString();
                        SalvarNota(novaNota);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    public void SalvarNota(String novaNota){
        Nota nota = new Nota(novaNota);
        listaNotas.add(nota);
        ContentValues notaValue = new ContentValues();
        notaValue.put("",novaNota);
        bd.insert("notas",null, notaValue);
    }

    protected void onStart() {
        super.onStart();
        Cursor dataset = bd.rawQuery("SELECT * FROM notas", null);

        dataset.moveToFirst();

        while (!dataset.isAfterLast()){
            listaNotas.add(new Nota(dataset.getString(0)));
        }
    }
}
