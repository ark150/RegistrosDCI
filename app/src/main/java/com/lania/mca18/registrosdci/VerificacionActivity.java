package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lania.mca18.adapter.ItemsListAdapter;
import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.service.Service;

import java.util.ArrayList;
import java.util.List;


public class VerificacionActivity extends Activity {
    private TextView text;
    Equipo equipo;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager IManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificacion);
        String iequipo = getIntent().getExtras().getString("idEquipo");
        //text = (TextView)  findViewById(R.id.nomEqu);
        //text.setText(equipo);

        equipo = new Equipo(Long.parseLong(iequipo));
        equipo.setAction(Item.GETDATAID);
        equipo.setDetallado(true);

        List<Item> p = Service.getData(equipo);
        try {
            equipo.setIntegrantes(java8.util.stream.StreamSupport
                    .stream(p)
                    .map(x -> (Persona)x)
                    .collect(java8.util.stream.Collectors.toList()));
            p = null;
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Ocurrió un error al cargar los datos de esta persona",
                    Toast.LENGTH_SHORT).show();
        }

        text = (TextView)  findViewById(R.id.nomEqu);
        if(equipo != null) {
            text.setText(equipo.getNombre());
        }

        recycler = (RecyclerView) findViewById(R.id.listaPerEqui);
        IManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(IManager);

        adapter = new ItemsListAdapter((java8.util.stream.StreamSupport
                .stream(equipo.getIntegrantes())
                .map(x -> (Item)x)
                .collect(java8.util.stream.Collectors.toList())));
        recycler.setAdapter(adapter);

        /*itemType=new Persona();
        itemType.setAction(Item.LIST);
        itemsList=new ArrayList<>();
        itemsList=service.getData(itemType);
        if(itemsList != null){
            //filtrado de personas
            foundItemsList = java8.util.stream.StreamSupport.stream(itemsList)
                    .filter(x -> ((Persona) x).getEquipo().getNombre()
                            .toUpperCase().equals(equipo.getNombre().toUpperCase()))
                    .collect(java8.util.stream.Collectors.toList());
            adapter = new ItemsListAdapter(foundItemsList);
            recycler.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"No se encontraron datos",Toast.LENGTH_LONG).show();
        }*/
    }

}
