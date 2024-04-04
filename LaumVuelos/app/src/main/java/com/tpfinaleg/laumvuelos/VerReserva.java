package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VerReserva extends AppCompatActivity {

    ListView lvVR;
    ArrayList<Reserva> ReservaList;
    Cursor data;
    TextView tvDatosR;
    TextView tvListAsientos;
    ListView lvAsientos;
    Button btnVR;
    Button btnVolverVR;
    int pos;
    ArrayList<Asiento> AsientoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reserva);

        tvDatosR= (TextView) findViewById(R.id.textViewDatosReserva);
        tvListAsientos= (TextView) findViewById(R.id.textViewListaAsientos);
        lvAsientos= (ListView) findViewById(R.id.listaVRAsientos);
        tvDatosR.setVisibility(View.INVISIBLE);
        tvListAsientos.setVisibility(View.INVISIBLE);
        lvAsientos.setVisibility(View.INVISIBLE);

        btnVolverVR= (Button) findViewById(R.id.buttonVolverVR);
        btnVolverVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnVR= (Button) findViewById(R.id.buttonVerReserva);

        lvVR= (ListView) findViewById(R.id.listaVR);
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        ReservaList = new ArrayList<Reserva>();

        data = admin.traerReservas();  // crear getListContents

        Reserva unR;

        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay datos cargados", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unR = new Reserva(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getInt(4), data.getInt(5));
                ReservaList.add(i, unR);
            }
            if (!ReservaList.isEmpty()) {
                ArrayAdapter<Reserva> adaptador=new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, ReservaList);
                lvVR.setAdapter(adaptador);
            }
        }

        pos=-1;

        lvVR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i es la posicion en la que esta en la lista
                pos=i;
            }
        });

        btnVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos!=-1){
                    tvDatosR.setVisibility(View.VISIBLE);
                    tvListAsientos.setVisibility(View.VISIBLE);
                    lvAsientos.setVisibility(View.VISIBLE);

                    tvDatosR.setText(ReservaList.get(pos).toString());
                    int unCod=ReservaList.get(pos).getCodigo();

                    ArrayList<Integer> listadnis=new ArrayList<Integer>();

                    data = admin.traerPasajerosReserva(unCod);
                    int numRows = data.getCount();
                    if (numRows == 0) {
                        Toast.makeText(VerReserva.this, "No hay datos cargados", Toast.LENGTH_LONG).show();
                    } else {
                        int i = 0;
                        while (data.moveToNext()) {
                            listadnis.add(i, data.getInt(0));
                        }
                        if (!listadnis.isEmpty()) {
                            for (int unc: listadnis){
                                AsientoList.add(admin.traerAsientoPasajero(unc));
                                ArrayAdapter<Asiento> adapt=new ArrayAdapter<Asiento>(VerReserva.this, android.R.layout.simple_list_item_1,AsientoList);
                                lvAsientos.setAdapter(adapt);
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(VerReserva.this, "Debe seleccionar un vuelo de la lista.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}