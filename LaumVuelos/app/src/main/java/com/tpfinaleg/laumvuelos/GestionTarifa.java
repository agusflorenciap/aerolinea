package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GestionTarifa extends AppCompatActivity {

    TextView tvModTarifa;
    Button btnConfirmar;
    EditText precPrim;
    EditText precTur;
    EditText impPrim;
    EditText impTur;
    ArrayList<Tarifa> tarifas;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_tarifa);

        tvModTarifa= (TextView) findViewById(R.id.textViewModificarTarifa);
        tvModTarifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GestionTarifa.this, "Ingrese lo que desea modificar en los casilleros", Toast.LENGTH_LONG).show();
            }
        });
        precPrim= (EditText) findViewById(R.id.editTextTextPrimPrecio);
        precTur= (EditText) findViewById(R.id.editTextTextTurPrecio);
        impPrim= (EditText) findViewById(R.id.editTextTextImp3);
        impTur= (EditText) findViewById(R.id.editTextTextImpTur);

        precPrim.setText("No hay datos cargados");
        precTur.setText("No hay datos cargados");
        impPrim.setText("No hay datos cargados");
        impTur.setText("No hay datos cargados");

        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        tarifas = new ArrayList<>();
        data = admin.traerTarifas();  // crear getListContents

        int numRows = data.getCount();
        if (numRows != 0) {
            int i = 0;
            while (data.moveToNext()) {
                Tarifa t = new Tarifa(data.getInt(0), data.getString(1), data.getFloat(2), data.getFloat(3));
                tarifas.add(i, t);
                if (data.getInt(0) == 0) {
                    precPrim.setText(String.valueOf(tarifas.get(i).getPrecio()));
                    impPrim.setText(String.valueOf(tarifas.get(i).getImpuesto()));
                } else {
                    precTur.setText(String.valueOf(tarifas.get(i).getPrecio()));
                    impTur.setText(String.valueOf(tarifas.get(i).getImpuesto()));
                }
            }
        }

        //precPrim.setText();

        //HACER FUNCION PARA QUE EN LOS EDITS TEXTS ESTEN LO CUANTO VALE CADA UNO EN EL ULTIMO INGRESO

        btnConfirmar= (Button) findViewById(R.id.buttonTarifConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hacer funcion para que guarde las modificaciones que realizo o no
                float prec= Float.parseFloat(precPrim.getText().toString());
                float imp=Float.parseFloat(impPrim.getText().toString());
                ContentValues registro= new ContentValues();
                registro.put("id", 0);
                registro.put("nombre", "Primera Clase");
                registro.put("precio", prec);
                registro.put("impuesto", imp);
                db.insert("Tarifa", null, registro);
                prec=Float.parseFloat(precTur.getText().toString());
                imp=Float.parseFloat(impTur.getText().toString());
                ContentValues registro2= new ContentValues();
                registro2.put("id", 1);
                registro2.put("nombre", "Clase Turista");
                registro2.put("precio",prec);
                registro2.put("impuesto",imp);
                db.insert("Tarifa", null, registro2);

                db.close();


                finish();
            }
        });
    }
}