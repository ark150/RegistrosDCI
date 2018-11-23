package com.lania.mca18.registrosdci;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ListadoActivity extends Fragment {
    private Spinner spinner1;
    View view;
    public ListadoActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listado,container,false);
        spinner1 = (Spinner)view.findViewById(R.id.spinner4);
        String [] opciones = {"Personas", "Equipos"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item_dci, opciones);
        spinner1.setAdapter(adapter);
        return view;
    }


}
