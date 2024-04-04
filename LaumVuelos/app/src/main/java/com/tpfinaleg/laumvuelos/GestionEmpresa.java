package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestionEmpresa extends AppCompatActivity {

    Button btnGEVolver;
    Button btnGR;
    Button btnGV;
    Button btnGP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_empresa);

        btnGEVolver= (Button) findViewById(R.id.buttonVolverGE);
        btnGEVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGR= (Button) findViewById(R.id.buttonGestReservas);
        btnGR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGestionReservas();
            }
        });

        btnGV= (Button) findViewById(R.id.buttonGestVuelos);
        btnGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGestionVuelos();
            }
        });

        btnGP= (Button) findViewById(R.id.buttonGestProm);
        btnGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGestionarPromocion();
            }
        });


    }

    private void abrirGestionReservas(){
        Intent i = new Intent(this, GestionReserva.class);
        startActivity(i);
    }

    private void abrirGestionVuelos(){
        Intent i = new Intent(this, GestionVuelo.class);
        startActivity(i);
    }

    private void abrirGestionarPromocion(){
        Intent i = new Intent(this, GestPromocion.class);
        startActivity(i);
    }

}