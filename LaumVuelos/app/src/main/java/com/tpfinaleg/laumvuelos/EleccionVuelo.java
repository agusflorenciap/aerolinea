package com.tpfinaleg.laumvuelos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EleccionVuelo extends AppCompatActivity {

    Button btnVolver;
    Button btnEleccion;
    ArrayList<Vuelo> vuelos;
    Cursor data;
    TextView seleccione;
    TextView noHayVuelos;
    Vuelo vuelo;
    Asiento asiento;
    int dia, mes, anio, cant;
    int checked;
    int pos, unId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_vuelo);
        cant=getIntent().getIntExtra("cantidad2", cant);
        checked=getIntent().getIntExtra("checked", checked);

        seleccione=(TextView) findViewById(R.id.textViewEleccionVuelo);
        noHayVuelos=(TextView) findViewById(R.id.textViewNoHayVuelos) ;

        ListView lv = (ListView) findViewById(R.id.listaVuelosAElegir);
        btnVolver= (Button) findViewById(R.id.buttonVolverR);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        vuelos=new ArrayList<>();
        DbHelper admin= new DbHelper(this, null);
        vuelos = new ArrayList<>();
        if(checked==0){
            dia=getIntent().getIntExtra("dia", dia);
            mes=getIntent().getIntExtra("mes", mes);
            anio=getIntent().getIntExtra("anio", anio);
            data = admin.traerVuelosXFecha(dia,mes,anio); //traer vuelos por fecha filtra en el select
        }else{
            data=admin.traerVuelos();
        }

        int numRows = data.getCount();
        if (numRows == 0) {
            seleccione.setVisibility(View.INVISIBLE);
            //Toast.makeText(this, "No hay vuelos cargados", Toast.LENGTH_LONG).show();
        } else {
            noHayVuelos.setVisibility(View.INVISIBLE);
            int i = 0;
            while (data.moveToNext()) {
                vuelo=new Vuelo(data.getInt(0),data.getInt(1),data.getInt(2),data.getInt(3),data.getInt(4),data.getInt(5));
                vuelos.add(i,vuelo);
                Cursor cursorA= admin.traerAsientosDeVuelo(data.getInt(0));
                ArrayList<Asiento> asientos;
                int numRowsAsientos = cursorA.getCount();
                if (numRowsAsientos == 0) {
                    Toast.makeText(this, "No hay asientos cargados", Toast.LENGTH_LONG).show();
                } else {
                    if(numRowsAsientos>=cant){
                        while(cursorA.moveToNext()){
                            asiento= new Asiento(cursorA.getInt(0), cursorA.getInt(1), cursorA.getString(2),cursorA.getInt(3), cursorA.getInt(4));
                            vuelo.agregarAsientos(asiento);
                            if (!vuelos.isEmpty()) {
                                ArrayAdapter<Vuelo> adaptador=new ArrayAdapter<Vuelo>(this, android.R.layout.simple_list_item_1, vuelos);
                                lv.setAdapter(adaptador);
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "No hay suficientes asientos para la cantidad de pasajeros elegida", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        pos=-1;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i es la posicion en la que esta en la lista
                pos=i;
            }
        });

        btnEleccion=(Button) findViewById(R.id.buttonConfirmarSeleccion);
        btnEleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirVueloElegido();
            }
        });
    }

    public void abrirVueloElegido ()
    {
        if(pos!=-1){
            unId=vuelos.get(pos).getId();
            Intent i = new Intent(this, VerVueloSeleccionado.class);
            i.putExtra("Identificacion",unId);
            i.putExtra("cantidad2",cant);
            startActivity(i);
        }
        else{
            Toast.makeText(EleccionVuelo.this, "Debe seleccionar un vuelo de la lista.", Toast.LENGTH_LONG).show();
        }
    }



}