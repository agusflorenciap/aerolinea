package com.tpfinaleg.laumvuelos;

public class Tarifa {
    private int id;
    private String nombre;
    private float impuesto;
    private float precio;

    public Tarifa(int unId, String n, float p, float imp){
        id=unId;
        nombre=n;
        impuesto=imp;
        precio=p;
    }

    public float getPrecio(){
        return precio;
    }

    public float getImpuesto(){
        return impuesto;
    }

    public double CalcularValorAsiento(int cantAsi)
    {
        double p = precio*cantAsi;
        return (p);
    }


}
