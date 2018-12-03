package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

public class ModificarActivity extends Activity {
    private TextInputEditText mod1;
    private TextInputEditText mod2;
    private TextInputEditText mod3;
    private TextInputEditText mod4;
    private TextInputEditText mod5;
    private TextInputEditText mod6;
    private TextInputEditText mod7;


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
        String idre = getIntent().getExtras().getString("id");
        mod1.setText(idre);
        mod2.setText(idre);
        mod3.setText(idre);
        mod4.setText(idre);
        mod5.setText(idre);
        mod6.setText(idre);
        mod7.setText(idre);

        Button botonAceptar = (Button) findViewById(R.id.btnAceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo para modificar datos del usuario

                //metodo para mandar a la pantalla principal
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        Button botonLimpiar = (Button) findViewById(R.id.btnLimpiar);
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
}
