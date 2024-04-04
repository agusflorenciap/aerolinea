package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pagar extends AppCompatActivity {

    TextView warning;
    TextView tvnomb;
    TextView tvpe;
    TextView tvdirec;
    TextView tvtel;
    TextView tvcorreo;
    TextView tvtarjeta;
    TextView tvPagar;
    TextView tvMonto;
    Button btnBuscar;
    Button btnPagar;
    EditText etD;
    int unDni;
    Pasajero p;
    double totalPagar;

    EditText etNombre;
    EditText etApe;
    EditText etDir;
    EditText etTel;
    EditText etCorreo;
    EditText etNroTarjeta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);

        tvnomb = (TextView) findViewById(R.id.textViewP);
        tvpe = (TextView) findViewById(R.id.textViewP2);
        tvdirec = (TextView) findViewById(R.id.textViewP3);
        tvtel = (TextView) findViewById(R.id.textViewP4);
        tvcorreo = (TextView) findViewById(R.id.textViewP5);
        tvtarjeta = (TextView) findViewById(R.id.textViewP6);
        tvPagar = (TextView) findViewById(R.id.textViewP7);
        tvMonto = (TextView) findViewById(R.id.textViewP8);

        etD = (EditText) findViewById(R.id.editTextDni);
        etNombre = (EditText) findViewById(R.id.editTextNomP);
        etApe = (EditText) findViewById(R.id.editTextApeP);
        etDir = (EditText) findViewById(R.id.editTextDirP);
        etTel = (EditText) findViewById(R.id.editTextTelP);
        etCorreo = (EditText) findViewById(R.id.editTextCorreoP);
        etNroTarjeta = (EditText) findViewById(R.id.editTextNroTarP);

        btnBuscar=(Button) findViewById(R.id.buttonBuscarDNI);
        btnPagar= (Button) findViewById(R.id.buttonConfirmarP);


        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unDni= Integer.parseInt(etD.getText().toString());
                String CantDni= Integer.toString(unDni);

                totalPagar=getIntent().getDoubleExtra("TotalPagar",totalPagar);

                if(CantDni.length()==8){
                    
                    /*tvnomb.setVisibility(View.VISIBLE);
                    tvpe.setVisibility(View.VISIBLE);
                    tvdirec.setVisibility(View.VISIBLE);
                    tvtel.setVisibility(View.VISIBLE);
                    tvcorreo.setVisibility(View.VISIBLE);
                    tvtarjeta.setVisibility(View.VISIBLE);
                    tvPagar.setVisibility(View.VISIBLE);
                    tvMonto.setText(String.valueOf(totalPagar));
                    tvMonto.setVisibility(View.VISIBLE);

                    etNombre.setVisibility(View.VISIBLE);
                    etApe.setVisibility(View.VISIBLE);
                    etDir.setVisibility(View.VISIBLE);
                    etTel.setVisibility(View.VISIBLE);
                    etCorreo.setVisibility(View.VISIBLE);
                    etNroTarjeta.setVisibility(View.VISIBLE);
                    */

                    p= admin.traerUnPasajero(unDni);
                    if (p!=null) //está registrado
                    {
                        etNombre.setText(p.getNombre());
                        etNombre.setEnabled(false);
                        etApe.setText(p.getApe());
                        etApe.setEnabled(false);
                        etDir.setText(p.getDir());
                        etDir.setEnabled(false);
                        etTel.setText(String.valueOf(p.getTel()));
                        etTel.setEnabled(false);
                        etCorreo.setText(p.getCorreo());
                        etCorreo.setEnabled(false);
                    }
                    else
                    {
                        Toast.makeText(Pagar.this, "El dni ingresado no está registrado. \n Complete el formulario.", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(Pagar.this, "Error al ingresar DNI. Intente Nuevamente",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int idReserva = (int) (Math.random() * 5000 +1); //el id de reserva generado será un número random del 1 al 5000
                if (p==null) //no estaba registrado. cargo en bd
                {
                    int dni = Integer.parseInt(etD.getText().toString());
                    String nomb= etNombre.getText().toString();
                    String ape= etApe.getText().toString();
                    String direc=etDir.getText().toString();
                    int tel = Integer.parseInt(etTel.getText().toString());
                    String correo=etCorreo.getText().toString();

                    ContentValues registro = new ContentValues();
                    registro.put("dni", dni);
                    registro.put("nombre", nomb);
                    registro.put("apellido", ape);
                    registro.put("direccion", direc);
                    registro.put("tel", tel);
                    registro.put("correo", correo);
                    registro.put("idReserva", idReserva);
                    db.insert("Pasajero", null, registro);
                    db.close();
                }

                //para generar el pago

                int idPago = (int) (Math.random() * 5000 +1); //el id será un número random del 1 al 5000
                int nroTar = Integer.parseInt(etNroTarjeta.getText().toString());
                //DIA MES AÑO ES HOY. TAMBIÉN FALTA CALCULAR EL TOTAL SEGÚN DESCUENTO
                //genera el pago
                //genera reserva
            }
        });

    }
}