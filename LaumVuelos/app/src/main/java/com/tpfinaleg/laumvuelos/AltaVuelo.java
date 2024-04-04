package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class AltaVuelo extends AppCompatActivity {

    Button btnConfirmar;
    Button btnCancel;
    EditText etunId;
    EditText etd;
    EditText etm;
    EditText eta;
    TimePicker tp;
    ArrayList<Asiento> asientosPC;
    ArrayList<Asiento> asientosCT;

    //restricciones
    CheckBox cbrestriccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_vuelo);

        btnConfirmar= (Button) findViewById(R.id.buttonAVConfirmar);
        btnCancel= (Button) findViewById(R.id.buttonAVCancel);
        tp= (TimePicker)findViewById(R.id.timepickerVuelo);
        tp.setIs24HourView(true);

        DbHelper admin= new DbHelper(AltaVuelo.this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        //restriccioness
        cbrestriccion= (CheckBox) findViewById(R.id.checkBoxRestriccion);




        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etunId= (EditText) findViewById(R.id.editTextIdAV);
                etd= (EditText) findViewById(R.id.editTextDiaAV);
                etm= (EditText) findViewById(R.id.editTextMesAV);
                eta= (EditText) findViewById(R.id.editTextAñoAV);
                int unId= Integer.valueOf(etunId.getText().toString());
                int d=Integer.valueOf(etd.getText().toString());
                int m= Integer.valueOf(etm.getText().toString());
                int a=Integer.valueOf(eta.getText().toString());

                //DbHelper admin= new DbHelper(AltaVuelo.this, null);
                //SQLiteDatabase db= admin.getWritableDatabase();
                //boolean loEncontro = admin.buscarIdVuelo(unId);
                Fecha f= new Fecha(d, m, a);
                int h, min;
                if (Build.VERSION.SDK_INT >= 23 ){
                    h = tp.getHour();
                    min = tp.getMinute();
                }
                else{
                    h = tp.getCurrentHour();
                    min = tp.getCurrentMinute();
                }

                boolean loencontro=admin.buscarIdVuelo(unId);
                if(f.fechaCorrecta() && loencontro==false) {
                    //Vuelo v=new Vuelo(unId, d, m, a);
                    ContentValues registro = new ContentValues();
                    registro.put("id", unId);
                    registro.put("dia", d);
                    registro.put("mes", m);
                    registro.put("anio", a);
                    registro.put("hora", h);
                    registro.put("minutos", min);
                    db.insert("Vuelo", null, registro);


                    //cargamos los asientos
                    Asiento unAs;
                    ContentValues registroAsientos = new ContentValues();
                    int i;
                    int k = 1;
                    for (i = 1; i <= 25; i = i + 6) { //carga los asientos de primera clase
                        registroAsientos.put("id", i);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "A");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 1);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "B");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 2);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "C");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 3);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "D");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 4);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "E");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 5);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "F");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        k++;
                    }

                    registroAsientos.put("id", 31);
                    registroAsientos.put("fila", k);
                    registroAsientos.put("letra", "B");
                    registroAsientos.put("estado", 0);
                    registroAsientos.put("idVuelo", unId);
                    db.insert("Asiento", null, registroAsientos);
                    registroAsientos.clear();
                    registroAsientos.put("id", 32);
                    registroAsientos.put("fila", k);
                    registroAsientos.put("letra", "C");
                    registroAsientos.put("estado", 0);
                    registroAsientos.put("idVuelo", unId);
                    db.insert("Asiento", null, registroAsientos);
                    registroAsientos.clear();
                    registroAsientos.put("id", 33);
                    registroAsientos.put("fila", k);
                    registroAsientos.put("letra", "D");
                    registroAsientos.put("estado", 0);
                    registroAsientos.put("idVuelo", unId);
                    db.insert("Asiento", null, registroAsientos);
                    registroAsientos.clear();
                    registroAsientos.put("id", 34);
                    registroAsientos.put("fila", k);
                    registroAsientos.put("letra", "E");
                    registroAsientos.put("estado", 0);
                    registroAsientos.put("idVuelo", unId);
                    db.insert("Asiento", null, registroAsientos);
                    registroAsientos.clear();
                    k++;
                    for (i = 34; i <= 334; i = i + 6) { //carga los asientos de primera clase
                        registroAsientos.put("id", i + 1);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "A");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 2);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "B");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 3);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "C");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 4);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "D");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 5);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "E");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        registroAsientos.put("id", i + 6);
                        registroAsientos.put("fila", k);
                        registroAsientos.put("letra", "F");
                        registroAsientos.put("estado", 0);
                        registroAsientos.put("idVuelo", unId);
                        db.insert("Asiento", null, registroAsientos);
                        registroAsientos.clear();
                        k++;
                    }
                    db.close();

                    //restricciones
                    if (cbrestriccion.isChecked()) {
                        int j=3;
                        /*for(j=1;j<=34;j++){
                            if(j>restripc){
                                admin.ModificarAsiento(j,unId);
                            }
                        }*/
                        while(j<=27){
                            admin.ModificarAsiento(j,unId);
                            admin.ModificarAsiento(j+1,unId);
                            j=j+6;
                        }
                        admin.ModificarAsiento(32,unId);
                        admin.ModificarAsiento(33,unId);
                        int p=37;
                        //int max=34+restrict;
                        /*for(p=35;p<=340;p++){
                            if(p>max){
                                //e=ListAsientosAV.get(p).getId();
                                admin.ModificarAsiento(p,unId);
                            }
                        }*/
                        while(p<=340){
                            admin.ModificarAsiento(p,unId);
                            admin.ModificarAsiento(p+1,unId);
                            p=p+6;
                        }
                    }

                    Toast.makeText(AltaVuelo.this, "Vuelo cargado correctamente.", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    if(loencontro==true){
                        Toast.makeText(AltaVuelo.this, "Error. El id ingresado ya existe. \n Vuelve a Intentar.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(AltaVuelo.this, "Error. Fecha incorrecta. \n Vuelve a Intentar.", Toast.LENGTH_LONG).show();
                    }
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