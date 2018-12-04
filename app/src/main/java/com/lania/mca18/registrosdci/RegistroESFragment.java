package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.service.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RegistroESFragment extends Activity implements Button.OnClickListener {
    private TextInputEditText val1;
    private TextInputEditText val2;
    private TextInputEditText val3;
    private TextInputEditText val4;
    private TextInputEditText val5;
    private TextInputEditText val6;
    private TextInputEditText val7;

    Service service;
    Item chosenItemType;
    Item item;
    Persona persona;
    Button btnEntrada, btnSalida;
    String hash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_e);

        final String fr = getIntent().getExtras().getString("from");
        //final String hash;

        //final String valor = getIntent().getExtras().getString("idPersona");
        if(getIntent().getExtras().getString("hash") != null)
            hash = getQrCodeItem(getIntent().getExtras().getString("hash"), "hash");

        final String valor= getIntent().getExtras().getString("idPersona");
        // final String hash;

        val1 = (TextInputEditText) findViewById(R.id.val_1);
        val2 = (TextInputEditText) findViewById(R.id.val_2);
        val3 = (TextInputEditText) findViewById(R.id.val_3);
        val4 = (TextInputEditText) findViewById(R.id.val_4);
        val5 = (TextInputEditText) findViewById(R.id.val_5);
        val6 = (TextInputEditText) findViewById(R.id.val_6);
        val7 = (TextInputEditText) findViewById(R.id.val_7);

        if (fr.equals("lista")) {
            persona = new Persona(Long.parseLong(valor));
        } else if (fr.equals("camara"))
        {
            //final String valor = getIntent().getExtras().getString("hash");
            persona = new Persona();
            persona.setHash(hash);

            // /persona/by-hash?hash=aqui_va_el_hash
        }

        persona.setAction(Item.GETDATAID);

        List<Item> p = Service.getData(persona);
        try {
            persona = (Persona) p.get(0);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Ocurrió un error al cargar los datos de esta persona",
                    Toast.LENGTH_SHORT).show();

        }


            /*if (valor != null && valor != "") {
                persona = new Persona(Long.parseLong(valor));
            } else {
                persona = new Persona();
                persona.setHash(hash);
            }

            // /persona/by-hash?hash=aqui_va_el_hash
            persona.setAction(Item.GETDATAID);

            List<Item> p = Service.getData(persona);
            try {
                persona = (Persona) p.get(0);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Ocurrió un error al cargar los datos de esta persona",
                        Toast.LENGTH_SHORT).show();

            }*/

            if (persona != null) {
                val1.setText(persona.getNombre());
                val2.setText(persona.getInstitucionDeOrigen());
                val3.setText(persona.getFacebook());
                val4.setText(persona.getEquipo().getNombre());
                val5.setText(persona.getComputadora().getColor());
                val6.setText(persona.getComputadora().getModelo());
                val7.setText(persona.getCorreo());
            } else {
                Log.e("Error", "no se carga el objeto");
            }
            String idPer = Long.toString(persona.getId());
            ImageButton imaBot = (ImageButton) findViewById(R.id.ico_mod);

            imaBot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", idPer);
                    Intent i = new Intent(getApplicationContext(), ModificarActivity.class);
                    i.putExtra("id", idPer);
                    startActivity(i);

                }
            });

            btnEntrada = (Button) findViewById(R.id.btnRegistrarE);
            btnEntrada.setTag(true);
            btnSalida = (Button) findViewById(R.id.btnRegistrarS);
            btnSalida.setTag(false);

            btnEntrada.setOnClickListener(this);
            btnSalida.setOnClickListener(this);
        }


    @Override
    public void onClick(View view) {
        SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = sdt.format(new Date());
        //Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();

        persona.setAction(Item.REG_IO);

        // Verdadero significa que es entrada
        if(((boolean)((Button)view).getTag()))
        {
            persona.setRegist(true);
        }
        else // Falso significa que es salida
        {
            persona.setRegist(false);
        }

        loadThread().start();
    }

    private Thread loadThread()
    {
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
                // Obtiene datos.
                try {
                    String str = Service.registerIO(persona);
                    Log.d("loadThread", str);

                    if(str!= null){
                        AlertDialog.Builder builder =  new AlertDialog.Builder(getApplicationContext());
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"no se registro",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception ex) {

                }
            }
        };

        return tr;
    }

    /**
     * Obtiene el valor de un objeto JSON leído a través de un código QR.
     * @param qrc Objeto JSON del código QR en bruto.
     * @param key Llave del valor que se busca obtener
     * @return Valor del objeto en base a la llave solicitada.
     */
    private String getQrCodeItem(String qrc, String key)
    {
        JSONObject ob;
        String value = "";
        try {
            ob = new JSONObject(qrc);
            value = ob.getString(key).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }
}
