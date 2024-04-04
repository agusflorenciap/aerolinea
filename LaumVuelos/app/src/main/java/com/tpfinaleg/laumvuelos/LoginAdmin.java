package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginAdmin extends AppCompatActivity {

    private String usuario="admin";
    private String contraseña="123";

    EditText userText;
    EditText contraText;
    Button btnIngresar;
    Button btnVol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        btnIngresar= (Button) findViewById(R.id.buttonIngresar);
        userText= (EditText) findViewById(R.id.editTextUsuario);
        contraText= (EditText) findViewById(R.id.editTextContra);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String usuIngresado=userText.getText().toString();
                String contraIngresada=contraText.getText().toString();

                validarIngreso(usuIngresado,contraIngresada);
            }
        });


        btnVol= (Button) findViewById(R.id.buttonVolver);
        btnVol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void validarIngreso (String usuIngresado, String contraIngresada)
    {

        if ((!usuIngresado.isEmpty()) && (!contraIngresada.isEmpty()))
        {
            if ((usuIngresado.equals(usuario)) && (contraIngresada.equals(contraseña)))
            {
                Intent i = new Intent(this, GestionEmpresa.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
            }

        }
        else{
        Toast.makeText(this, "Debe completar los campos",Toast.LENGTH_SHORT).show();
    }
    }
}