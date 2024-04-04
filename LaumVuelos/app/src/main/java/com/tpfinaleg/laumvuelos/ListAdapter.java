package com.tpfinaleg.laumvuelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tpfinaleg.laumvuelos.R;
import com.tpfinaleg.laumvuelos.Vuelo;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Vuelo> {

    private LayoutInflater inflater;
    private ArrayList<Vuelo> vuelos;
    private int viewResourceID;
    TextView hora;
    TextView fecha;
    private int count=0;
    public ListAdapter(Context context, int textViewResourceID, ArrayList<Vuelo> vuelos ){
        super(context, textViewResourceID, vuelos);
        this.vuelos=vuelos;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceID=textViewResourceID;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView=inflater.inflate(viewResourceID, null);
        Vuelo v=vuelos.get(position);
        if(v!=null){
            View finalConvertView = convertView;
            fecha = (TextView) finalConvertView.findViewById(R.id.textViewFechaV2);
            fecha.setText(v.getDia()+"/"+v.getMes()+"/"+v.getAnio());
            hora=(TextView)finalConvertView.findViewById(R.id.textViewHoraV2) ;
            hora.setText(v.gerHora()+" : "+v.getMin());

        }
        return convertView;
    }
}