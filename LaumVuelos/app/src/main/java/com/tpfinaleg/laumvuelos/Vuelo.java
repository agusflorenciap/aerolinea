package com.tpfinaleg.laumvuelos;

import java.util.ArrayList;

public class Vuelo {
    private int id;
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minuto;
    private ArrayList<Reserva> reservas;
    private ArrayList<Asiento> asientosPC;
    private ArrayList<Asiento> asientosCT;

    /*public Vuelo (int unId, int d, int m, int a, int h, int mn){
        id=unId;
        dia=d;
        mes=m;
        anio=a;
        hora=h;
        minuto=mn;
        reservas= new ArrayList<Reserva>();
        Asiento unAs;
        int i;
        for (i=1; i<=6;i++){ //carga los asientos de primera clase
            if(i==6){
                unAs=new Asiento(i, "B", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "C", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "D", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "E", 0, unId);
                asientosPC.add(unAs);
            }
            else{
                unAs=new Asiento(i, "A", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "B", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "C", 0, unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "D", 0,unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "E", 0,unId);
                asientosPC.add(unAs);
                unAs=new Asiento(i, "F", 0, unId);
                asientosPC.add(unAs);
            }
        }

        for (i=1; i<=51;i++){
            unAs=new Asiento(i, "A", 0,unId);
            asientosCT.add(unAs);
            unAs=new Asiento(i, "B", 0, unId);
            asientosCT.add(unAs);
            unAs=new Asiento(i, "C", 0,unId);
            asientosCT.add(unAs);
            unAs=new Asiento(i, "D", 0, unId);
            asientosCT.add(unAs);
            unAs=new Asiento(i, "E", 0, unId);
            asientosCT.add(unAs);
            unAs=new Asiento(i, "F", 0, unId);
            asientosCT.add(unAs);
        }

    }*/

    public Vuelo (int unId, int d, int m, int a, int h, int mn){
        id=unId;
        dia=d;
        mes=m;
        anio=a;
        hora=h;
        minuto=mn;
        asientosPC=new ArrayList<>();
        asientosCT=new ArrayList<>();
    }



   /* public Vuelo (int unId, int d, int m, int a, ArrayList<Reserva> listreservas, ArrayList<Asiento> listasientospc, ArrayList<Asiento> listasientosct){
        id=unId;
        dia=d;
        mes=m;
        anio=a;
        reservas= (ArrayList<Reserva>)listreservas.clone();
        asientosPC= (ArrayList<Asiento>)listasientospc.clone();
        asientosCT= (ArrayList<Asiento>)listasientosct.clone();
    }*/

    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAnio() {return anio; }
    public int gerHora(){return hora;}
    public int getMin(){return minuto;}

    public int getId(){
        return id;
    }

    public void agregarAsientos(Asiento a){
        if(a.getId()<34)
            asientosPC.add(a);
        else
            asientosCT.add(a);
    }

    @Override
    public String toString(){
        return ("Id: "+id + " Fecha: "+ dia +"/"+mes+"/"+anio+ " Horario: "+hora+":"+minuto);
    }

}
