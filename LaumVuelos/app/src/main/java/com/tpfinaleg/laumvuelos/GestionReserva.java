package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestionReserva extends AppCompatActivity {

    Button btnVolverGR;
    boolean modoUsuario; //para saber si es usuario o admin
    Button btnEfectuarReserva;
    Button btnVerReservas;
    Button btnCancelarReserva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_reserva);

        btnVolverGR= (Button) findViewById(R.id.buttonVolverGR);
        btnVolverGR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //modoUsuario=getIntent().getBooleanExtra("Eleccion", modoUsuario);

        btnEfectuarReserva= (Button) findViewById(R.id.buttonEfectuarReserva);
        btnEfectuarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirEfectuarReserva();
            }
        });

        btnVerReservas= (Button) findViewById(R.id.buttonVerReserva);
        btnVerReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVerReserva();
            }
        });
        btnCancelarReserva= (Button) findViewById(R.id.buttonCancelReserva);
        btnCancelarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCancelReserva();
            }
        });


    }

    private void abrirEfectuarReserva(){
        Intent i = new Intent(this, EfectuarReserva.class);
        startActivity(i);
    }

    private void abrirVerReserva(){
        Intent i = new Intent(this, VerReserva.class);
        startActivity(i);
    }

    private void abrirCancelReserva(){
        Intent i = new Intent(this, CancelarReserva.class);
        startActivity(i);
    }



}