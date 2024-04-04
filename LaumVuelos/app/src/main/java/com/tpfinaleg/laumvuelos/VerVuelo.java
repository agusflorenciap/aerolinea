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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerVuelo extends AppCompatActivity {

    ListView lvVuelosVV;
    ArrayList<Vuelo> VuelosVVList;
    Cursor data;
    Button btnVV;
    Button btnVolverVV;
    int pos;
    int unId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_vuelo);

        lvVuelosVV= (ListView) findViewById(R.id.listavuelosVV);
        /*
        lvReservasVV= (ListView) findViewById(R.id.listareservasVV);
        lvADPC= (ListView) findViewById(R.id.listaasientosdisppcVV);
        lvADCT= (ListView) findViewById(R.id.listaasientosdispctVV);
        lvPagosVV= (ListView) findViewById(R.id.listapagosVV);
        tvPagadototal= (TextView) findViewById(R.id.textViewPagadototal);
        tvReservasVV=(TextView) findViewById(R.id.textViewListReservasVV);
        tvAsientospcVV=(TextView) findViewById(R.id.textVievADPC);
        tvAsientosctVV=(TextView) findViewById(R.id.textViewADCT);
        tvPagosVV=(TextView) findViewById(R.id.textViewlistaPagosVV);
        tvPagadototal.setText("0");*/


        //cargar datos
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        VuelosVVList = new ArrayList<Vuelo>();

        data = admin.llenarListaVuelos();  // crear getListContents

        Vuelo unv;

        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay datos cargados", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unv = new Vuelo(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getInt(4), data.getInt(5));
                VuelosVVList.add(i, unv);
            }
            if (!VuelosVVList.isEmpty()) {
                ArrayAdapter<Vuelo> adaptador=new ArrayAdapter<Vuelo>(this, android.R.layout.simple_list_item_1, VuelosVVList);
                lvVuelosVV.setAdapter(adaptador);
            }
        }


        //SeleccionaVuelo_Click();

        pos=-1;

        lvVuelosVV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i es la posicion en la que esta en la lista
                pos=i;
            }
        });


        btnVV= (Button) findViewById(R.id.buttonVerVuelo);
        btnVV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatosVuelo();
            }
        });

        btnVolverVV= (Button) findViewById(R.id.buttonVolverVV);
        btnVolverVV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void abrirDatosVuelo(){
        if(pos!=-1){
            unId=VuelosVVList.get(pos).getId();
            Intent i = new Intent(this, VerVueloDatos.class);
            i.putExtra("Identificacion",unId);
            startActivity(i);
        }
        else{
            Toast.makeText(VerVuelo.this, "Debe seleccionar un vuelo de la lista.", Toast.LENGTH_LONG).show();
        }
    }

}