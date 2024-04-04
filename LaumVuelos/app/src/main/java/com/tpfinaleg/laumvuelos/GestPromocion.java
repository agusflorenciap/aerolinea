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

public class GestPromocion extends AppCompatActivity {

    Button btnConfirmarProm;
    EditText etDias;
    EditText etDesc;
    Cursor data;
    TextView tvModProm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gest_promocion);

        btnConfirmarProm= (Button) findViewById(R.id.buttonConfirmarProm);
        etDias= (EditText) findViewById(R.id.editTextDiasProm);
        etDesc= (EditText) findViewById(R.id.editTextDescProm);
        tvModProm= (TextView) findViewById(R.id.textViewModificarPromo);

        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        data = admin.traerPromocion();  // crear getListContents
        int numRows = data.getCount();
        if (numRows != 0) {
            int i = 0;
            while (data.moveToNext()) {
                int da=data.getInt(1);
                int ds=data.getInt(2);
                etDias.setText(String.valueOf(da));
                etDesc.setText(String.valueOf(ds));
            }
        }

        tvModProm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GestPromocion.this, "Ingrese lo que desea modificar en los casilleros", Toast.LENGTH_LONG).show();
            }
        });

        btnConfirmarProm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d= Integer.parseInt(etDias.getText().toString());
                int ds=Integer.parseInt(etDesc.getText().toString());
                ContentValues registro= new ContentValues();
                registro.put("id", 0);
                registro.put("dia", d);
                registro.put("descuento", ds);
                db.insert("Promocion", null, registro);
                db.close();
                finish();
            }
        });

    }


}