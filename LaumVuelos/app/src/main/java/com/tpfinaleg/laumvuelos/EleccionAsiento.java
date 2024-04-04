package com.tpfinaleg.laumvuelos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EleccionAsiento extends AppCompatActivity {


    Button btnCancelEA;
    Button btnSigEA;
    boolean claselegida;
    int idVuelo;
    int[] asientos = new int[306];
    Integer[] botones;
    ArrayList<Asiento> ListADPCEA;
    ArrayList<Asiento> ListADCTEA;
    ArrayList<Asiento> ListAsientosEA;
    Button[] button = new Button[306];
    int[] asientosSelec;
    int cant;
    int cantAux;
    List<Integer> ListasientosS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_asiento);

        btnCancelEA = (Button) findViewById(R.id.buttonEACancel);
        btnCancelEA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        claselegida = getIntent().getBooleanExtra("ClaseElegida", claselegida);
        idVuelo = getIntent().getIntExtra("Identificacion", idVuelo);
        cant = getIntent().getIntExtra("cantidad2", cant);
        cantAux=cant;

        btnSigEA = (Button) findViewById(R.id.buttonEAsig);
        btnSigEA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EleccionAsiento.this, InfoReserva.class);
                i.putExtra("Identificacion",idVuelo);
                i.putExtra("cantidad2",cantAux);
                i.putExtra("ClaseElegida",claselegida);
                startActivity(i);
            }
        });


        botones = new Integer[]{R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13,
                R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button19, R.id.button20,
                R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27,
                R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34,
                R.id.button35, R.id.button36, R.id.button37, R.id.button38, R.id.button39, R.id.button40, R.id.button41,
                R.id.button42, R.id.button43, R.id.button44, R.id.button45, R.id.button46, R.id.button47, R.id.button48,
                R.id.button49, R.id.button50, R.id.button51, R.id.button52, R.id.button53, R.id.button54, R.id.button55,
                R.id.button56, R.id.button57, R.id.button58, R.id.button59,
                R.id.button60, R.id.button61, R.id.button62, R.id.button63, R.id.button64, R.id.button65, R.id.button66, R.id.button67,
                R.id.button68, R.id.button69, R.id.button70, R.id.button71, R.id.button72, R.id.button73, R.id.button74,
                R.id.button75, R.id.button76, R.id.button77, R.id.button78, R.id.button79, R.id.button80, R.id.button81,
                R.id.button82, R.id.button83, R.id.button84, R.id.button85, R.id.button86, R.id.button87, R.id.button88,
                R.id.button89, R.id.button90, R.id.button91, R.id.button92, R.id.button93, R.id.button94, R.id.button95,
                R.id.button96, R.id.button97, R.id.button98, R.id.button99, R.id.button100, R.id.button101, R.id.button102,
                R.id.button103, R.id.button104, R.id.button105, R.id.button106,
                R.id.button107, R.id.button108, R.id.button109, R.id.button110, R.id.button111, R.id.button112, R.id.button113,
                R.id.button114, R.id.button115, R.id.button116, R.id.button117, R.id.button118, R.id.button119, R.id.button120,
                R.id.button121, R.id.button122, R.id.button123, R.id.button124, R.id.button125, R.id.button126, R.id.button127,
                R.id.button128, R.id.button129, R.id.button130, R.id.button131, R.id.button132, R.id.button133, R.id.button134,
                R.id.button135, R.id.button136, R.id.button137, R.id.button138, R.id.button139, R.id.button140, R.id.button141,
                R.id.button142, R.id.button143, R.id.button144, R.id.button145, R.id.button146, R.id.button147, R.id.button148,
                R.id.button149, R.id.button150, R.id.button151, R.id.button152, R.id.button153, R.id.button154, R.id.button155,
                R.id.button156, R.id.button157, R.id.button158, R.id.button159,
                R.id.button160, R.id.button161, R.id.button162, R.id.button163, R.id.button164, R.id.button165, R.id.button166, R.id.button167,
                R.id.button168, R.id.button169, R.id.button170, R.id.button171, R.id.button172, R.id.button173, R.id.button174,
                R.id.button175, R.id.button176, R.id.button177, R.id.button178, R.id.button179, R.id.button180, R.id.button181,
                R.id.button182, R.id.button183, R.id.button184, R.id.button185, R.id.button186, R.id.button187, R.id.button188,
                R.id.button189, R.id.button190, R.id.button191, R.id.button192, R.id.button193, R.id.button194, R.id.button195,
                R.id.button196, R.id.button197, R.id.button198, R.id.button199, R.id.button200, R.id.button201, R.id.button202,
                R.id.button203, R.id.button204, R.id.button205, R.id.button206,
                R.id.button207, R.id.button208, R.id.button209, R.id.button210, R.id.button211, R.id.button212, R.id.button213,
                R.id.button214, R.id.button215, R.id.button216, R.id.button217, R.id.button218, R.id.button219, R.id.button220,
                R.id.button221, R.id.button222, R.id.button223, R.id.button224, R.id.button225, R.id.button226, R.id.button227,
                R.id.button228, R.id.button229, R.id.button230, R.id.button231, R.id.button232, R.id.button233, R.id.button234,
                R.id.button235, R.id.button236, R.id.button237, R.id.button238, R.id.button239, R.id.button240, R.id.button241,
                R.id.button242, R.id.button243, R.id.button244, R.id.button245, R.id.button246, R.id.button247, R.id.button248,
                R.id.button249, R.id.button250, R.id.button251, R.id.button252, R.id.button253, R.id.button254, R.id.button255,
                R.id.button256, R.id.button257, R.id.button258, R.id.button259,
                R.id.button260, R.id.button261, R.id.button262, R.id.button263, R.id.button264, R.id.button265, R.id.button266, R.id.button267,
                R.id.button268, R.id.button269, R.id.button270, R.id.button271, R.id.button272, R.id.button273, R.id.button274,
                R.id.button275, R.id.button276, R.id.button277, R.id.button278, R.id.button279, R.id.button280, R.id.button281,
                R.id.button282, R.id.button283, R.id.button284, R.id.button285, R.id.button286, R.id.button287, R.id.button288,
                R.id.button289, R.id.button290, R.id.button291, R.id.button292, R.id.button293, R.id.button294, R.id.button295,
                R.id.button296, R.id.button297, R.id.button298, R.id.button299, R.id.button300, R.id.button301, R.id.button302,
                R.id.button303, R.id.button304, R.id.button305, R.id.button306,
        };

        //cargar asientoss

        DbHelper admin = new DbHelper(this, null);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor data;

        ListAsientosEA = new ArrayList<Asiento>();
        data = admin.traerAsientosDeVuelo(idVuelo);  // crear getListContents
        Asiento unA;
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(this, "No hay asientos cargados", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                unA = new Asiento(data.getInt(0), data.getInt(1), data.getString(2), data.getInt(3), data.getInt(4));
                ListAsientosEA.add(i, unA);
            }
            if (!ListAsientosEA.isEmpty()) {
                ArrayList<Asiento> ListAsientosAux = new ArrayList<Asiento>();
                //ArrayList<Asiento> ListAsientosAux2= new ArrayList<Asiento>();
                for (int j = 0; j < 340; j++) {
                    ListAsientosAux.add(j, ListAsientosEA.get(j));
                }
                ListADPCEA = new ArrayList<Asiento>();
                ListADCTEA = new ArrayList<Asiento>();
                for (Asiento a : ListAsientosAux) {
                    if (a.getId() < 35) {
                        ListADPCEA.add(a);
                    } else {
                        if (a.getId() >= 35) {
                            ListADCTEA.add(a);
                        }
                    }
                }
            }
        }

        /*Button btnAD;
        for(int j=0;j<306;j++){
            if(ListAsientosEA.get(j).getEstado()==0){
                btnAD= (Button) findViewById(botones[j]);
                btnAD.setBackgroundResource(R.drawable.as_disp);
            }
            else{
                if(ListAsientosEA.get(j).getEstado()==1){
                    btnAD= (Button) findViewById(botones[j]);
                    btnAD.setBackgroundResource(R.drawable.as_reservado);
                }
                else{
                    btnAD= (Button) findViewById(botones[j]);
                    btnAD.setBackgroundResource(R.drawable.as_restricto);
                }
            }
        }*/

        int id;
        for (int i = 1; i <= 306; i++) {
            id = getResources().getIdentifier("button" + i, "id", getPackageName());
            button[i - 1] = (Button) findViewById(id);
        }

        int unIDB;
        if (claselegida == true) {
            String idb;
            Button btnAD;
            for (int i = 34; i <= 305; i++) {
                /*unIDB=botones[i];
                btnAD= (Button) findViewById(botones[i]);
                btnAD.setVisibility(View.GONE);*/
                button[i].setVisibility(View.GONE);
            }
            for (int j = 0; j < 34; j++) {
                if (ListAsientosEA.get(j).getEstado() == 0) {
                    /*btnAD= (Button) findViewById(botones[j]);
                    btnAD.setBackgroundResource(R.drawable.as_disp);*/
                    button[j].setBackgroundResource(R.drawable.as_disp);
                } else {
                    if (ListAsientosEA.get(j).getEstado() == 1) {
                        /*btnAD= (Button) findViewById(botones[j]);
                        btnAD.setBackgroundResource(R.drawable.as_reservado);*/
                        button[j].setBackgroundResource(R.drawable.as_reservado);
                    } else {
                        /*btnAD= (Button) findViewById(botones[j]);
                        btnAD.setBackgroundResource(R.drawable.as_restricto);*/
                        button[j].setBackgroundResource(R.drawable.as_restricto);
                    }
                }
            }
        } else {
            Button btnAD;
            int p;
            for (int j = 0; j < 306; j++) {
                p = j + 34;
                if (ListAsientosEA.get(p).getEstado() == 0) {
                    /*btnAD= (Button) findViewById(botones[j]);
                    btnAD.setBackgroundResource(R.drawable.as_disp);*/
                    button[j].setBackgroundResource(R.drawable.as_disp);
                } else {
                    if (ListAsientosEA.get(p).getEstado() == 1) {
                        /*btnAD= (Button) findViewById(botones[j]);
                        btnAD.setBackgroundResource(R.drawable.as_reservado);*/
                        button[j].setBackgroundResource(R.drawable.as_reservado);
                    } else {
                        /*btnAD= (Button) findViewById(botones[j]);
                        btnAD.setBackgroundResource(R.drawable.as_restricto);*/
                        button[j].setBackgroundResource(R.drawable.as_restricto);
                    }
                }
            }
        }

        //asientosSelec=new int[cantAsientos];
        ListasientosS = new ArrayList<Integer>();
    }

    public void marcarAsiento(View v) {
        //int numBoton= Arrays.asList(botones).indexOf(v.getId());
        //v.setBackgroundResource(R.drawable.as_seleccionado);
        int numBoton = Arrays.asList(botones).indexOf(v.getId());
        //int numboton2;
        if (claselegida == false) {
            numBoton = numBoton + 34;
        }

        int idAs = ListAsientosEA.get(numBoton).getId();
        int est = ListAsientosEA.get(numBoton).getEstado();
        DbHelper admin = new DbHelper(com.tpfinaleg.laumvuelos.EleccionAsiento.this, null);
        if (cant != 0 && est == 0) {
            v.setBackgroundResource(R.drawable.as_seleccionado);
            ListasientosS.add(numBoton);
            cant--;
        } else {
            if (cant == 0) {
                Toast.makeText(this, "Ya seleccionó todos sus asientos", Toast.LENGTH_LONG).show();
                for (Integer unInt : ListasientosS) {
                    admin.AsientoReservado(unInt + 1, idVuelo);
                }
            }
        }

    }


}
