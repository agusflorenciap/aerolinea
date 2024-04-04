package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CancelarReserva extends AppCompatActivity {

    Button btnVolverCR;
    Button btnCR;
    Cursor data;
    ListView lvCR;
    ArrayList<Reserva> ListReservasCR;
    EditText etReservaId;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_reserva);

        btnVolverCR= (Button) findViewById(R.id.buttonVolverCR);
        btnVolverCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etReservaId= (EditText) findViewById(R.id.editTextIdCR);

        lvCR= (ListView) findViewById(R.id.listaCR);
        ListReservasCR= new ArrayList<Reserva>();
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        data = admin.traerReservas();

        Reserva unR;
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay datos cargados", Toast.LENGTH_LONG).show();
        }
        else{
            int i = 0;
            while (data.moveToNext()) {
                unR = new Reserva(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getInt(4), data.getInt(5));
                ListReservasCR.add(i, unR);
            }
            if (!ListReservasCR.isEmpty()){
                ArrayAdapter<Reserva> adaptador=new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, ListReservasCR);
                lvCR.setAdapter(adaptador);
            }
        }

        pos=-1;

        lvCR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i es la posicion en la que esta en la lista
                pos=i;
            }
        });

        btnCR= (Button) findViewById(R.id.buttonCR);
        btnCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iden=etReservaId.getText().toString();
                int unIdReserva;
                int idV;
                Reserva unRV;
                if(pos!=-1 && !iden.isEmpty()){
                    if(pos!=-1){
                        unIdReserva=ListReservasCR.get(pos).getCodigo();
                        admin.eliminarReserva(unIdReserva);

                    }else{
                        int unIdR=Integer.valueOf(etReservaId.getText().toString());
                        if(admin.buscarIdReserva(unIdR)){
                            admin.eliminarReserva(unIdR);
                        }
                        else{
                            Toast.makeText(CancelarReserva.this, "Error.No existe el id ingresado.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(CancelarReserva.this, "Debe seleccionar un vuelo de la lista o ingresar en el casillero.", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}