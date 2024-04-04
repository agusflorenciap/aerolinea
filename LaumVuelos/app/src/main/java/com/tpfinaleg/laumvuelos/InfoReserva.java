package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InfoReserva extends AppCompatActivity {

    Button btnPagar;
    Button btnVolver;
    TextView tvIV;
    TextView tvFecha;
    TextView tvHora;
    TextView tvAsientos;
    ArrayList<Tarifa> tarifas;
    int idVuelo;
    int cantAsi;
    double valorAsientos;
    float impuesto;
    double descuento;
    double total;
    boolean claseElegida;



    Vuelo v;
    Tarifa t;
    TextView tvValorAsientos;
    TextView tvValorImpuesto;
    TextView tvValorPromocion;
    TextView tvValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reserva);

        btnVolver= (Button) findViewById(R.id.buttonVolverIR);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvIV = (TextView) findViewById(R.id.textViewIR2);
        tvFecha = (TextView) findViewById(R.id.textView25);
        tvHora = (TextView) findViewById(R.id.textView28);
        tvAsientos = (TextView) findViewById(R.id.textView31);
        tvValorAsientos = (TextView) findViewById(R.id.textViewIR9);
        tvValorImpuesto = (TextView) findViewById(R.id.textViewIR10);
        tvValorPromocion = (TextView) findViewById(R.id.textViewIR11);
        tvValorTotal = (TextView) findViewById(R.id.textViewIR13);

        idVuelo= getIntent().getIntExtra("Identificacion",idVuelo);
        cantAsi=getIntent().getIntExtra("cantidad2",cantAsi);
        claseElegida=getIntent().getBooleanExtra("ClaseElegida",claseElegida);
        Cursor data;
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        v=admin.traerUnVuelo2(idVuelo);

        if (v!=null)
        {
            tvIV.setText(String.valueOf(idVuelo));
            tvFecha.setText(v.getDia()+"/"+v.getMes()+"/"+v.getAnio());
            tvHora.setText(v.gerHora()+":"+v.getMin());
            tvAsientos.setText(String.valueOf(cantAsi)); //en realidad debería mostrar id asientos, no cantidad.

            if (claseElegida) // true primera clase, false turista
            {
                t = admin.traerTarifa(0);
            }
            else {
                t = admin.traerTarifa(1);
            }

            if (t != null) {
                valorAsientos = t.CalcularValorAsiento(cantAsi);
                impuesto = t.getImpuesto();
            }
        }
        else
        {
            Toast.makeText(InfoReserva.this, "No existe el vuelo seleccionado",Toast.LENGTH_LONG).show();
        }

            //FALTA CALCULAR DESCUENTO X DÍA

            int diaV =v.getDia();
            int mesV = v.getMes();
            int añoV = v.getAnio();


            String f = String.valueOf(diaV)+"/"+String.valueOf(mesV)+"/"+String.valueOf(añoV);


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha=null;
        try {
             fecha = formato.parse(f);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date hoy = new Date();
        Fecha fech = new Fecha ();

        //obtener diferencia
            long diasDiferencia = fech.getTimeDistance(hoy,fecha);


        //diasDeDiferencia


            tvValorAsientos.setText(String.valueOf(valorAsientos));
            tvValorImpuesto.setText(String.valueOf(impuesto));
            total= valorAsientos+impuesto-descuento; //poner valor en descuento

            tvValorTotal.setText(String.valueOf(total));


        btnPagar= (Button) findViewById(R.id.buttonConfirmarIR);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoReserva.this, Pagar.class);
                i.putExtra("TotalPagar",total);
                startActivity(i);
            }
        });

        }
}