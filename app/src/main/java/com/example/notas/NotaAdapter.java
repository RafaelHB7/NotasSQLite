package com.example.notas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NotaAdapter extends ArrayAdapter<Nota> {
    Context mContext;
    int mResource;

    public NotaAdapter(Context context, int resource, ArrayList<Nota> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);

        TextView texto = convertView.findViewById(R.id.textView);

        Nota nota = getItem(position);

        texto.setText(nota.getTexto());

        return convertView;
    }
}
