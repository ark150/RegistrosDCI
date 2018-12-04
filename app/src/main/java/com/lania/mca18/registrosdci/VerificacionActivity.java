package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lania.mca18.adapter.ItemsListAdapter;
import com.lania.mca18.service.Service;

import java.util.ArrayList;


public class VerificacionActivity extends Activity {
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificacion);
        String equipo = getIntent().getExtras().getString("idEquipo");
        text = (TextView)  findViewById(R.id.nomEqu);
        text.setText(equipo);

    }

}
