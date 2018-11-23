package com.lania.mca18.registrosdci;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lania.mca18.adapter.ItemsListAdapter;
import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.service.Service;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends Fragment {
    private Spinner spinner1;
    View view;

    List<Item> itemsList;
    Service service;
    Item chosenItemType;


    // Recycler
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager IManager;


    public ListadoActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        itemsList = new ArrayList<>();
        service = new Service();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listado,container,false);

        // Inicializa RecyclerView
        recycler = (RecyclerView)view.findViewById(R.id.listaItems);
        IManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(IManager);


        // Inicializa opciones de listados
        spinner1 = (Spinner)view.findViewById(R.id.spinner4);
        String [] opciones = {"Personas", "Equipos"};

        ArrayAdapter <String> spinner_adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item_dci, opciones);
        spinner1.setAdapter(spinner_adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //Toast t = Toast.makeText(getContext(), "Sel", Toast.LENGTH_SHORT);
                //t.show();

                switch (position)
                {
                    case 0:
                        chosenItemType = new Persona();
                        break;
                    case 1:
                        chosenItemType = new Equipo();
                        break;
                }

                chosenItemType.setAction(Item.LIST);
                setLoadingThread().run();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    /**
     * Crea el hilo sobre el cual correrá el proceso
     * de obtención de datos.
     * @return Thread obtención de datos para la vista "ListadoActivity"
     */
    private Thread setLoadingThread()
    {
        itemsList = new ArrayList<>();  // Limpia datos anteriores
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
                // Obtiene datos.
                try
                {
                    itemsList = service.getData(getActivity().getApplicationContext(),
                            "", chosenItemType);
                } catch (Exception ex)
                {
                    Log.d("Thread tr", "Ha ocurrido un error al intentar cargar los datos");
                    Log.e("Error Thread", ex.getMessage());
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            if(itemsList != null)
                            {
                                if(itemsList.size() == 0)
                                {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "No hay eventos resgistrados", Toast.LENGTH_SHORT).show();
                                } else
                                {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Éxito :D " + itemsList.size(),
                                            Toast.LENGTH_SHORT).show();
                                }

                                //Para mostrar en interfaz de usuario
                                adapter = new ItemsListAdapter(itemsList);
                                recycler.setAdapter(adapter);
                            }
                            else
                            {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "No se encontraron datos", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception ex)
                        {
                            String msg = "";
                            if(itemsList == null)
                                msg = "Ha habido un problema. Verifica tu conexión a internet";
                            else
                                msg = "Ha habido un problema";

                            Toast.makeText(getActivity().getApplicationContext(),
                                    msg, Toast.LENGTH_LONG).show();
                            Log.e("Mensaje de error", ex.getMessage());
                        }
                    }
                });
            }
        };

        return tr;
    }
}
