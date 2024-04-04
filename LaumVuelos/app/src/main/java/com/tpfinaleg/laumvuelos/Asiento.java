package com.tpfinaleg.laumvuelos;

public class Asiento {
    private int id;
    private int fila;
    private String letra;
    private int estado;
    private int idvuelo;
    private int idpasajero;

    public Asiento ( int unid, int f, String l, int e, int idv){
        id=unid;
        fila=f;
        letra=l;
        estado=e;
        idvuelo=idv;
    }


    public void setIdpasajero(int unIdPasajero){
        idpasajero=unIdPasajero;
    }

    public int getEstado(){
        return estado;
    }
    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        String disp;
        if(estado==0){
            disp="Libre";
        }
        else{
            disp="Ocupado";
        }
        return ("Fila: "+fila + " Letra: "+ letra + " Estado: "+disp);
    }
}
