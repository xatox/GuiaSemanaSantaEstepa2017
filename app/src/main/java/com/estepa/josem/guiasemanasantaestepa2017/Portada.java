package com.estepa.josem.guiasemanasantaestepa2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.estepa.josem.guiasemanasantaestepa2017.tareas.TareaGetHdades;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        TareaGetHdades tareaGetHdades = new TareaGetHdades(this);
        tareaGetHdades.execute();

        Intent mainAntivityIntent = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(mainAntivityIntent);

    }
}
