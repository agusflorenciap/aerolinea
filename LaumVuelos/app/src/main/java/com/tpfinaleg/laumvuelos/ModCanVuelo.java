package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;


import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;

public class ModCanVuelo extends AppCompatActivity  {

    Button btnAcept;
    Button btnCancel;
    ListView lvVuelos;
    Cursor data;
    ArrayList<Vuelo> VuelosList;
    boolean opcMod; //true=Modificar, false=Cancelar
    EditText etDia;
    EditText etMes;
    EditText etAnio;
    EditText etHorario;
    TextView tvdia;
    TextView tvmes;
    TextView tvanio;
    TextView tvdatos;
    TextView tvhymmc;
    TimePicker tpmc;
    Button reloj;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_can_vuelo);



        etDia= (EditText) findViewById(R.id.editTextDiaMCV);
        etMes= (EditText) findViewById(R.id.editTextMesV);
        etAnio=(EditText) findViewById(R.id.editTextAnioV);
        opcMod=getIntent().getBooleanExtra("Eleccion",opcMod);
        tpmc= (TimePicker)findViewById(R.id.timepickerVueloMod);
        tpmc.setIs24HourView(true);

        if(opcMod==false){
            tvdia= (TextView) findViewById(R.id.textViewDiaV);
            tvmes= (TextView) findViewById(R.id.textViewMesV);
            tvanio= (TextView) findViewById(R.id.textViewAnioV);
            tvdatos= (TextView) findViewById(R.id.textViewDatosVuelo);
            tvhymmc= (TextView) findViewById(R.id.textViewHyMMC);
            reloj= (Button) findViewById(R.id.elegir_Horamc);
            tvdia.setVisibility(View.INVISIBLE);
            tvmes.setVisibility(View.INVISIBLE);
            tvanio.setVisibility(View.INVISIBLE);
            tvdatos.setVisibility(View.INVISIBLE);
            tvhymmc.setVisibility(View.INVISIBLE);
            etDia.setVisibility(View.INVISIBLE);
            etMes.setVisibility(View.INVISIBLE);
            etAnio.setVisibility(View.INVISIBLE);
            tpmc.setVisibility(View.INVISIBLE);
            reloj.setVisibility(View.INVISIBLE);


        }
        lvVuelos= (ListView) findViewById(R.id.listVuelos);

        //cargar datos
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        VuelosList = new ArrayList<Vuelo>();

        data = admin.llenarListaVuelos();  // crear getListContents

        Vuelo unv;

        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay datos cargados", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unv = new Vuelo(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getInt(4), data.getInt(5));
                VuelosList.add(i, unv);
            }
            if (!VuelosList.isEmpty()) {
                ArrayAdapter<Vuelo> adaptador=new ArrayAdapter<Vuelo>(this, android.R.layout.simple_list_item_1, VuelosList);
                lvVuelos.setAdapter(adaptador);

            }
        }
        pos=-1;

        lvVuelos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i es la posicion en la que esta en la lista
                pos=i;
            }
        });

        btnAcept= (Button) findViewById(R.id.buttonAceptMCV);
        btnCancel= (Button) findViewById(R.id.buttonCancelMCV);

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos!=-1){
                    int unId=VuelosList.get(pos).getId();
                    if(opcMod==true){
                        int d=Integer.valueOf(etDia.getText().toString());
                        int m=Integer.valueOf(etMes.getText().toString());
                        int a=Integer.valueOf(etAnio.getText().toString());
                        int h, min;
                        if (Build.VERSION.SDK_INT >= 23 ){
                            h = tpmc.getHour();
                            min = tpmc.getMinute();
                        }
                        else{
                            h = tpmc.getCurrentHour();
                            min = tpmc.getCurrentMinute();
                        }
                        Fecha f= new Fecha(d, m, a);
                        if(f.fechaCorrecta()){
                            admin.ModificarVuelo(unId,d, m, a, h, min);
                            Toast.makeText(ModCanVuelo.this, "Vuelo modificado correctamente.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(ModCanVuelo.this, "Error. Fecha incorrecta. \n Vuelve a Intentar.", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        //eliminamos pago de la reserva
                        //si tiene tarjeta tambien se elimina
                        //se elimina al pasajeros '?? HELPPPP!!!******
                        admin.eliminarReservas(unId);
                        //y por ult eliminamos el vuelo
                        admin.eliminarVuelo(unId);
                        Toast.makeText(ModCanVuelo.this, "Vuelo cancelado correctamente.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else{
                    Toast.makeText(ModCanVuelo.this, "Debe seleccionar un vuelo de la lista.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}