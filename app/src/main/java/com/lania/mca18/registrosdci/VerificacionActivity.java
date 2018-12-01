package com.lania.mca18.registrosdci;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class VerificacionActivity extends Fragment  {
    View view;
    ImageButton imgBQR;
    private TextInputEditText val1;
    public VerificacionActivity(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.verificacion,container,false);
        imgBQR=(ImageButton) view.findViewById(R.id.botonQRV);
        imgBQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),CamaraActivity.class);
                i.putExtra("pantalla","ver");
                getActivity().startActivity(i);
            }
        });
        //String valor=getArguments().getString("code_qr");
        //val1 = (TextInputEditText) view.findViewById(R.id.val_1);
        //val1.setText(valor);

        return view;
    }

}
