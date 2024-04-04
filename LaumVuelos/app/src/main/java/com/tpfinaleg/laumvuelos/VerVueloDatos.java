package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerVueloDatos extends AppCompatActivity {

    int unId;
    ListView lvReservasVV;
    ListView lvADPC;
    ListView lvADCT;
    ListView lvPagosVV;
    Cursor data;
    ArrayList<Reserva> ListReservasV;
    ArrayList<Asiento> ListADPCV;
    ArrayList<Asiento> ListADCTV;
    ArrayList<Asiento> ListAsientosV;
    ArrayList<Pago> ListPagoV;
    TextView tvPagadototal;
    TextView tvReservasVV;
    TextView tvPagosVV;
    TextView tvAsientospcVV;
    TextView tvAsientosctVV;
    Button btnVolverVVD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_vuelo_datos);

        unId=getIntent().getIntExtra("Identificacion",unId);
        lvReservasVV= (ListView) findViewById(R.id.listareservasVV);
        lvADPC= (ListView) findViewById(R.id.listaasientosdisppcVV);
        lvADCT= (ListView) findViewById(R.id.listaasientosdispctVV);
        lvPagosVV= (ListView) findViewById(R.id.listapagosVV);
        tvPagadototal= (TextView) findViewById(R.id.textViewPagadototal);
        tvReservasVV=(TextView) findViewById(R.id.textViewListReservasVV);
        tvAsientospcVV=(TextView) findViewById(R.id.textVievADPC);
        tvAsientosctVV=(TextView) findViewById(R.id.textViewADCT);
        tvPagosVV=(TextView) findViewById(R.id.textViewlistaPagosVV);
        tvPagadototal.setText("0");
        btnVolverVVD= (Button) findViewById(R.id.buttonVolverVVD);
        btnVolverVVD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListReservasV= new ArrayList<Reserva>();
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        data = admin.traerReservasVuelo(unId);  // crear getListContents
        Reserva unR;
        int numRows = data.getCount();
        if (numRows == 0) {
            tvReservasVV.setText("Lista de Reservas: NO HAY RESERVAS HECHAS");
            tvPagosVV.setText("Lista de Pagos: NO HAY PAGOS HECHOS");
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unR = new Reserva(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getInt(4), data.getInt(5));
                ListReservasV.add(i, unR);
            }
            if (!ListReservasV.isEmpty()) {
                ArrayAdapter<Reserva> adaptador=new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, ListReservasV);
                lvReservasVV.setAdapter(adaptador);

                ListPagoV= new ArrayList<Pago>();
                for (Reserva r: ListReservasV) {
                    data = admin.traerPagos(r.getCodigo());  // crear getListContents
                    Pago unP;
                    numRows = data.getCount();
                    if (numRows == 0) {
                        Toast.makeText(this, "No hay datos cargados", Toast.LENGTH_LONG).show();
                    }
                    else{
                        int j = 0;
                        while (data.moveToNext()) {
                            unP = new Pago(data.getInt(0), data.getInt(1), data.getInt(2), data.getInt(3), data.getString(4), data.getInt(5), data.getInt(6), data.getInt(7));
                            ListPagoV.add(j, unP);
                        }
                    }
                }
                float Ttalpagado=0;
                for (Pago p: ListPagoV) {
                    Ttalpagado+=p.getTotal();
                }
                tvPagadototal.setText(String.valueOf(Ttalpagado));

                if (!ListPagoV.isEmpty()) {
                    ArrayAdapter<Pago> adaptadorP=new ArrayAdapter<Pago>(this, android.R.layout.simple_list_item_1, ListPagoV);
                    lvPagosVV.setAdapter(adaptadorP);
                }
            }
        }


        ListAsientosV= new ArrayList<Asiento>();
        data = admin.traerAsientosDeVuelo(unId);  // crear getListContents
        Asiento unA;
        numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay asientos cargados", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unA = new Asiento(data.getInt(0), data.getInt(1), data.getString(2), data.getInt(3), data.getInt(4));
                ListAsientosV.add(i, unA);
            }
            if (!ListAsientosV.isEmpty()) {
                ArrayList<Asiento> ListAsientosAux= new ArrayList<Asiento>();
                //ArrayList<Asiento> ListAsientosAux2= new ArrayList<Asiento>();
                for(int j=0; j<340;j++) {
                    ListAsientosAux.add(j, ListAsientosV.get(j));
                }
                ListADPCV= new ArrayList<Asiento>();
                ListADCTV= new ArrayList<Asiento>();
                for (Asiento a: ListAsientosAux) {
                    if(a.getEstado()==0 && a.getId()<35){
                        ListADPCV.add(a);
                    }
                    else{
                        if(a.getEstado()==0 && a.getId()>=35){
                            ListADCTV.add(a);
                        }
                    }
                }
                if(!ListADPCV.isEmpty()){
                    ArrayAdapter<Asiento> adaptador=new ArrayAdapter<Asiento>(this, android.R.layout.simple_list_item_1, ListADPCV);
                    lvADPC.setAdapter(adaptador);
                }
                else{
                    tvAsientospcVV.setText("No hay lista de asientos disponibles para PRIMERA CLASE");
                }
                    /*ListADCTV= new ArrayList<Asiento>();
                    for (Asiento a: ListAsientosAux2) {
                        if(a.getEstado()==0){
                            ListADCTV.add(a);
                        }
                    }*/
                if(!ListADCTV.isEmpty()){
                    ArrayAdapter<Asiento> adaptador=new ArrayAdapter<Asiento>(this, android.R.layout.simple_list_item_1, ListADCTV);
                    lvADCT.setAdapter(adaptador);
                }
                else{
                    tvAsientosctVV.setText("No hay lista de asientos disponibles para CLASE TURISTA");
                }
            }
        }

    }
}