package com.tpfinaleg.laumvuelos;

import java.util.ArrayList;

public class Reserva {
    private int codigo;
    //private ArrayList<Pasajero> pasajeros;
    private int dia;
    private int mes;
    private int anio;
    private float importe;
    private int idvuelo;
    private int idpago;

    public Reserva( int c,int d, int m, int a, float ct, int unV){
        codigo=c;
        //pasajeros=(ArrayList<Pasajero>) listpasajeros.clone();
        dia=d;
        mes=m;
        anio=a;
        importe=ct;
        idvuelo=unV;

    }

    public Reserva( int c,int d, int m, int a, float ct, int unV, int idP){
        codigo=c;
        //pasajeros=(ArrayList<Pasajero>) listpasajeros.clone();
        dia=d;
        mes=m;
        anio=a;
        importe=ct;
        idvuelo=unV;
        idpago=idP;

    }

    public void setPago( int unP){
        idpago=unP;
    }

    public int getPago(){
        return (idpago);
    }

    public int getCodigo(){
        return (codigo);
    }

    public int getIdvuelo(){
        return (idvuelo);
    }

    @Override
    public String toString(){
        return ("Codigo: "+codigo + " Fecha: "+ dia +"/"+mes+"/"+anio+ " Importe: "+importe+ "$");
    }

}
