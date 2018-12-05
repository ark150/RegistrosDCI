package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lania.mca18.model.Persona;

public class ModificarActivity extends Activity implements Button.OnClickListener {
    private TextInputEditText mod1;
    private TextInputEditText mod2;
    private TextInputEditText mod3;
    private TextInputEditText mod4;
    private TextInputEditText mod5;
    private TextInputEditText mod6;
    private TextInputEditText mod7;
    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        mod1 = (TextInputEditText) findViewById(R.id.mod_1);
        mod2 =(TextInputEditText)findViewById(R.id.mod_2);
        mod3 = (TextInputEditText) findViewById(R.id.mod_3);
        mod4 = (TextInputEditText) findViewById(R.id.mod_4);
        mod5 = (TextInputEditText) findViewById(R.id.mod_5);
        mod6 = (TextInputEditText) findViewById(R.id.mod_6);
        mod7= (TextInputEditText) findViewById(R.id.mod_7);

        try {
            String pstr = getIntent().getExtras().getString("gson");
            persona = new Gson().fromJson(pstr, Persona.class);
        } catch (Exception ex)
        {
            persona = new Persona();
            Toast.makeText(getApplicationContext(),
                    "Ocurrió un error al deserializar los datos",
                    Toast.LENGTH_SHORT).show();
        }

        mod1.setText(persona.getNombre());
        mod2.setText(persona.getInstitucionDeOrigen());
        mod3.setText(persona.getFacebook());
        mod4.setText(persona.getEquipo().getNombre());
        mod5.setText(persona.getComputadora().getColor());
        mod6.setText(persona.getComputadora().getModelo());
        mod7.setText(persona.getCorreo());

        Button btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(this);

        Button botonLimpiar = (Button)findViewById(R.id.btnLimpiar);
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
    }

    public void limpiar(){
        mod1.setText("");
        mod2.setText("");
        mod3.setText("");
        mod4.setText("");
        mod5.setText("");
        mod6.setText("");
        mod7.setText("");
    }

    @Override
    public void onClick(View v) {
        //metodo para modificar datos del usuario

        getModThread();

        //metodo para mandar a la pantalla principal
        Intent i= new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private Thread getModThread()
    {
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {

            }
        };

        return tr;
    }
}
