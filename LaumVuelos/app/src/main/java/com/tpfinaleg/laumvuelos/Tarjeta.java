package com.tpfinaleg.laumvuelos;

public class Tarjeta {

    private int numero;
    private String nombre;
    private String tipo;
    private int diaV;
    private int mesV;
    private int anioV;
    private int codSeg;

    public Tarjeta( int nro, String n, String tip, int d, int m, int a, int cs){
        numero=nro;
        nombre=n;
        tipo=tip;
        diaV=d;
        mesV=m;
        anioV=a;
        codSeg=cs;
    }
}
