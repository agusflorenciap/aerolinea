package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdm;
    Button btnUsu;
    Button btnSalir;
    boolean esUsuario=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdm= (Button) findViewById(R.id.buttonAdm);
        btnAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAdmEmpresa();
            }
        });

        btnUsu= (Button) findViewById(R.id.buttonUser);
        btnUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esUsuario=true;
                abrirUsuario();
            }
        });

        btnSalir= (Button) findViewById(R.id.buttonSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void abrirAdmEmpresa(){
        Intent i = new Intent(this, LoginAdmin.class);
        startActivity(i);
    }

    private void abrirUsuario(){
        Intent i= new Intent(this, GestionReserva.class);
        //i.putExtra("Eleccion", esUsuario);
        startActivity(i);
    }
}