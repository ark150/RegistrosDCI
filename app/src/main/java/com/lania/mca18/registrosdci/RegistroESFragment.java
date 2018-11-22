package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class RegistroESFragment extends Activity {
    private TextInputEditText val1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_e);
        String valor=getIntent().getExtras().getString("code_qr");
        val1 = (TextInputEditText) findViewById(R.id.val_1);
        val1.setText(valor);

    }


}
