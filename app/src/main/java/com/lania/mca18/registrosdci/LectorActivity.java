package com.lania.mca18.registrosdci;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Console;

public class LectorActivity extends Fragment {
    View view;
    ImageButton imageButton;

    public LectorActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lectorqr,container,false);
        imageButton=(ImageButton)view.findViewById(R.id.botonQR);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //RegistroESFragment fr = new RegistroESFragment();
                    //CamaraActivity camaraActivity = new CamaraActivity();
                    //pasarF(camaraActivity);
                    Intent i = new Intent(getContext(),CamaraActivity.class);
                    i.putExtra("pantalla","reg");
                    getActivity().startActivity(i);


                }catch (Exception e){
                    Log.e("Error/lania",e.getMessage());
                }
                //fragmentTransaction.replace(R.id.fragmentLector,fr);
                //Toast.makeText(getActivity(),"funcion boton qr",Toast.LENGTH_LONG).show();
                //CamaraActivity camaraActivity = new CamaraActivity();
                //Log.e("error","erro");
                //camaraActivity.onResume
                //getFragmentManager().beginTransaction().replace(R.id.fragmentLector,fr).commit();
                //fr.onStart();
                System.out.println("Boton error");

            }
        });
        return view;
    }

    public void pasarF(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLector, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        getActivity().getSupportFragmentManager().executePendingTransactions();
    }

}






