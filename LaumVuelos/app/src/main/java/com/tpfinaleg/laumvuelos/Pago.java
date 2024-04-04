package com.tpfinaleg.laumvuelos;

public class Pago {

    private int id;
    private int dia;
    private int mes;
    private int anio;
    private String tipo;
    private float total;
    private int reservaid;
    private int nrotarjeta;

    public Pago (int unId, int d, int m, int a, String tip, float tol, int rid, int unT){
        id=unId;
        dia=d;
        mes=m;
        anio=a;
        tipo=tip;
        total=tol;
        reservaid=rid;
        nrotarjeta=unT;
    }

    public void setTarjeta (int unT){
        nrotarjeta=unT;
    }

    public float getTotal(){
        return total;
    }
}
