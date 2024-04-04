package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GestionVuelo extends AppCompatActivity {

    Button btnVolverGV;
    Button btnGestTarifas;
    Button btnModV;
    Button btnCancelV;
    boolean opcMod;
    Button btnnewVuelo;
    Button btnVerVuelos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_vuelo);

        btnVolverGV= (Button)  findViewById(R.id.buttonVolverGV);
        btnVolverGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGestTarifas= (Button) findViewById(R.id.buttonGestionarTarifa);
        btnGestTarifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGestionarTarifas();
            }
        });

        btnModV= (Button) findViewById(R.id.buttonModVuelo);
        btnModV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcMod=true;
                abrirModCanVuelo();
            }
        });

        btnCancelV= (Button) findViewById(R.id.buttonCancelarVuelo);
        btnCancelV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcMod=false;
                abrirModCanVuelo();
            }
        });

        btnnewVuelo= (Button) findViewById(R.id.buttonNewVuelo);
        btnnewVuelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAltaVuelo();
            }
        });

        btnVerVuelos= (Button) findViewById(R.id.buttonVerVuelos);
        btnVerVuelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVerVuelos();
            }
        });
    }

    private void abrirGestionarTarifas(){
        Intent i = new Intent(this, GestionTarifa.class);
        startActivity(i);
    }

    private void abrirVerVuelos(){
        Intent i = new Intent(this, VerVuelo.class);
        startActivity(i);
    }

    private void abrirAltaVuelo(){
        Intent i = new Intent(this, AltaVuelo.class);
        startActivity(i);
    }

    private void abrirModCanVuelo(){
        Intent i = new Intent(this, ModCanVuelo.class);
        i.putExtra("Eleccion",opcMod);
        startActivity(i);

    }
}