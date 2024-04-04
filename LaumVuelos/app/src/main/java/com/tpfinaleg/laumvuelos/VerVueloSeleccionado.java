package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class VerVueloSeleccionado extends AppCompatActivity {

        int idVuelo;
        Vuelo v;
        Button btnConfirmar;
        Button btnVolver;
        TextView fecha;
        TextView hora;
        TextView cod;
        boolean claselegida;
        CheckBox cbclaselegida;
        int cant;
        int dia, mes, anio;
        int checked;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ver_vuelo_seleccionado);

            idVuelo=getIntent().getIntExtra("Identificacion",idVuelo);
            cant=getIntent().getIntExtra("cantidad2",cant);

            dia=getIntent().getIntExtra("dia", dia);
            mes=getIntent().getIntExtra("mes", mes);
            anio=getIntent().getIntExtra("anio", anio);
            checked=getIntent().getIntExtra("checked", checked);

            cod= (TextView) findViewById(R.id.textView27);
            fecha=(TextView) findViewById(R.id.textView25);
            hora=(TextView) findViewById(R.id.textView28);

            cbclaselegida= (CheckBox) findViewById(R.id.checkBoxCE);

            DbHelper admin= new DbHelper(this, null);
            v=admin.traerUnVuelo2(idVuelo);


            cod.setText(String.valueOf(idVuelo));
            fecha.setText(v.getDia()+"/"+v.getMes()+"/"+v.getAnio());
            hora.setText(v.gerHora()+":"+v.getMin());

            claselegida=false;
            btnConfirmar=(Button) findViewById(R.id.buttonConfirmarVVS);
            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(com.tpfinaleg.laumvuelos.VerVueloSeleccionado.this, EleccionAsiento.class);
                    i.putExtra("Identificacion",idVuelo);
                    if (cbclaselegida.isChecked()){
                        claselegida=true;
                    }
                    i.putExtra("ClaseElegida",claselegida);
                    i.putExtra("cantidad2",cant);
                    startActivity(i);
                }
            });

            btnVolver=(Button) findViewById(R.id.buttonVolverVVS);
            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(com.tpfinaleg.laumvuelos.VerVueloSeleccionado.this, EfectuarReserva.class);
                    i.putExtra("Identificacion",idVuelo);
                    if(checked==0){
                        i.putExtra("dia", dia);
                        i.putExtra("mes", mes);
                        i.putExtra("anio", anio);
                        i.putExtra("checked", 0);
                        //traer vuelos por fecha filtra en el select
                    }else{
                        i.putExtra("checked", 1);
                    }
                    startActivity(i);
                }
            });
        }
    }