package com.example.notas;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Notas nota;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = findViewById(R.id.editText);

        nota = new Notas(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nota salva com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                nota.salvaNota(editText.getText().toString());
            }
        });
    }

    protected void onStart() {
        super.onStart();
        editText.setText(nota.recuperaNota());
    }

    protected void onPause() {
        super.onPause();
        nota.salvaNota(editText.getText().toString());
    }
}
