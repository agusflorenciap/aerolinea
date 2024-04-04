package com.tpfinaleg.laumvuelos;

public class Pasajero {

    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private String correo;

    public Pasajero (int unDni, String n, String ape, String dir, int telf, String cor){
        dni=unDni;
        nombre=n;
        apellido=ape;
        direccion=dir;
        telefono=telf;
        correo=cor;
    }


    public int getDni(){
        return dni;
    }
    public String getNombre(){ return nombre;}
    public String getApe(){ return apellido;}
    public String getDir(){ return direccion;}
    public int getTel(){
        return telefono;
    }
    public String getCorreo(){ return correo;}


}
