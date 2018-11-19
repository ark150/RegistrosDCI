package com.lania.mca18.registrosdci;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lania.mca18.model.Computadora;
import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.service.DataAsync;
import com.lania.mca18.service.Service;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> list;
    Thread tr;
    private Button btnRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRepeat = findViewById(R.id.btnRepeat);
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                click();
            }
        });

        tr = setLoadingThread();
        tr.start();
    }

    private Thread setLoadingThread()
    {
        DataAsync da = new DataAsync();
        final Computadora pc = new Computadora();
        //persona.setNombre("Dell");
        pc.setModelo("Lenovo");
        pc.setAction("list");

        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    //events = service.getData(getActivity().getApplicationContext(), null, eventType);
                    list = Service.getData(getApplicationContext(), "3", pc);
                    Log.d("Thread tr", "Tamaño:" + String.valueOf(list.size()));
                    //assertTrue(list.size() > 0);
                    //Log.d("Eventos encontrados", String.valueOf(events.size()));
                }
                catch (Exception ex)
                {
                    //Log.d("Thread tr", "Ha ocurrido un error al intentar cargar los datos");
                    //Log.e("Error Thread", ex.getMessage());
                }
            }
        };

        //tr.start();
        //tr.run();
        //assertTrue(list.size() > 0);

        return tr;
    }

    private void click() {
        //tr.start();
        Toast toast = Toast.makeText(this, "Click", Toast.LENGTH_SHORT);
        toast.show();
    }
}
