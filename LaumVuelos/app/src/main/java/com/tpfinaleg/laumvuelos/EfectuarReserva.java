package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
public class EfectuarReserva extends AppCompatActivity {

    Button btnCancelar;
    Button btnBuscarViaje;
    Button btnCant;
    //private TextView cant;
    CheckBox checkBox;
    int cant2=0;
    int cant=0;
    EditText editTextDia, editTextMes,editTextanio, cantP;
    int dia=0, mes=0, anio=0, canti=0;
    int checked=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efectuar_reserva);

        editTextDia= (EditText) findViewById(R.id.editTextDiaReserva);
        editTextMes= (EditText) findViewById(R.id.editTextMesReserva);
        editTextanio= (EditText) findViewById(R.id.editTextAnioReserva);

        canti=getIntent().getIntExtra("cantidad2",canti);
        dia=getIntent().getIntExtra("dia", dia);
        mes=getIntent().getIntExtra("mes", mes);
        anio=getIntent().getIntExtra("anio", anio);
        checked=getIntent().getIntExtra("checked", checked);

        if(canti!=0 && dia!=0 && mes!=0 && anio!=0 && checked!=-1){  //para cuando toca el boton Volver en ver vuelo
            cantP.setText(""+ canti);
            editTextMes.setText(""+ mes);
            editTextDia.setText(""+ dia);
            editTextanio.setText(""+ anio);
        }

        cantP=(EditText) findViewById(R.id.editTextCant);
        btnCant=(Button) findViewById(R.id.buttonCant);
        btnCant.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerDialog();
            }


        }));

        btnCancelar= (Button) findViewById(R.id.buttonVolver);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        checkBox= (CheckBox) findViewById(R.id.checkBoxReserva);


        btnBuscarViaje= (Button) findViewById(R.id.buttonBuscarViaje);
        btnBuscarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cant=Integer.parseInt(cantP.getText().toString());
                if (!checkBox.isChecked()) //si no está marcado validamos q estén completos los otros campos
                {


                    String dia=editTextDia.getText().toString();

                    String mes=editTextMes.getText().toString();

                    String anio=editTextanio.getText().toString();

                    if((dia.isEmpty()) || (mes.isEmpty()) || (anio.isEmpty()) || cant==0)
                    {
                        Toast.makeText(EfectuarReserva.this, "Debe completar todos los campos",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        int d=Integer.valueOf(editTextDia.getText().toString());
                        int m=Integer.valueOf(editTextMes.getText().toString());
                        int a=Integer.valueOf(editTextanio.getText().toString());
                        Fecha f= new Fecha(d, m, a);
                        if(!f.fechaCorrecta()){
                            Toast.makeText(EfectuarReserva.this, "Error. Fecha incorrecta. \n Vuelva a Intentar.", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            //manda fecha a la otra activity y filtra la lista de vuelos según la fecha
                            //manda tambien la cantidad de pasajeros
                            Intent i = new Intent(EfectuarReserva.this, EleccionVuelo.class);
                            i.putExtra("dia", d);
                            i.putExtra("mes", m);
                            i.putExtra("anio", a);
                            i.putExtra("cantidad2",cant);
                            i.putExtra("checked",0);
                            startActivity(i);
                        }
                    }
                }
                else
                {
                    //abre la activity y filtra vuelos sin fecha
                    Intent i = new Intent(EfectuarReserva.this, EleccionVuelo.class);
                    i.putExtra("cantidad2",cant);
                    i.putExtra("checked",1);
                    startActivity(i);
                }

            }

        });



    }

    private void numberPickerDialog(){
        NumberPicker myNumberPicker= new NumberPicker(this);
        myNumberPicker.setMaxValue(340);
        myNumberPicker.setMinValue(1);
        NumberPicker.OnValueChangeListener myValChangeListener= new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cantP.setText(""+ newVal);
                cant2=newVal;
            }
        };
        myNumberPicker.setOnValueChangedListener(myValChangeListener);
        AlertDialog.Builder builder= new AlertDialog.Builder(this).setView(myNumberPicker);
        builder.setTitle("Cantidad");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}