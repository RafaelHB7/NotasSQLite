package com.example.notas;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Notas {
    SharedPreferences preferencias;
    SharedPreferences.Editor editorPreferencias;
    Context contexto;
    private static final String PREFERENCIAS_FILE ="Notas";

    public Notas(Context c){
        this.contexto = c;
        preferencias = this.contexto.getSharedPreferences(PREFERENCIAS_FILE, Context.MODE_PRIVATE);
        editorPreferencias = preferencias.edit();
    }

    public void salvaNota(String s){
        editorPreferencias.putString("Notas",s);
        editorPreferencias.commit();
    }

    public String recuperaNota(){
        if (this.preferencias.contains("Notas")){
            return this.preferencias.getString("Notas","");
        } else{
            return "";
        }
    }
}
