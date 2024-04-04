package com.tpfinaleg.laumvuelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME = "LAUM.db";
    public DbHelper(@Nullable Context context, SQLiteDatabase.CursorFactory factory ) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Tarifa(id int primary key, nombre text, precio float, impuesto float)");
        db.execSQL("create table Promocion(id int primary key, dia int, descuento int)");
        db.execSQL("create table Vuelo(id int primary key, dia int, mes int, anio int, hora int, minutos int)");
        db.execSQL("create table Asiento( id int, fila int, letra text, estado int, idVuelo int, idPasajero int)"); //1 ocupado, 0 libre, 2 Imposibilitado restricción
        // id de vuelo y persona en asiento para poder unirlos
        db.execSQL("create table Pasajero(dni int primary key, nombre text, apellido text, direccion text, tel int, correo text, idReserva int)");
        db.execSQL("create table Reserva(id int primary key, dia int, mes int, anio int, importe float, idVuelo int, idPago int)");
        db.execSQL("create table Pago(id int primary key, dia int, mes int, anio int, tipo text, total float, idReserva int, nroTarjeta int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if EXISTS Tarifa");
        db.execSQL("DROP TABLE if EXISTS Vuelo");
        db.execSQL("DROP TABLE if EXISTS Asiento");
        db.execSQL("DROP TABLE if EXISTS Pasajero");
        db.execSQL("DROP TABLE if EXISTS Reserva");
        onCreate(db);
    }

    public Cursor traerTarifas(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Tarifa", null);
        return data;
    }

    public Tarifa traerTarifa(int id){
        Tarifa t=null;
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Tarifa WHERE id = "+id+"", null);
        if(data.moveToFirst()) {
            String nomb;
            float imp, prec;
            nomb = data.getString(1);
            imp = data.getFloat(2);
            prec = data.getFloat(3);
            t = new Tarifa(id, nomb, imp, prec);
        }

        return t;
    }



    public Cursor traerPromocion(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Promocion", null);
        return data;
    }

    public Cursor traerVuelos(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Vuelo", null);
        return data;
    }
    public Cursor traerVuelosXFecha(int d, int m,int a){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Vuelo WHERE dia = "+d+" AND mes="+m+" AND anio="+a+"", null);
        return data;
    }

    public Cursor TraerPasajeros(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Pasajero", null);
        return data;
    }

    public Cursor traerAsientosDeVuelo( int id){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Asiento WHERE idVuelo = "+id+" ORDER BY id DESC", null);
        return data;
    }

    public Cursor traerAsientoDePasajero( int id){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Pasajero WHERE idPasajero = "+id+" ", null);
        return data;
    }

    public Cursor traerReservasVuelo( int unId){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Reserva WHERE idVuelo = "+unId+" ", null);
        return data;
    }

    public Cursor traerPagos( int unId){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Pago WHERE idReserva = "+unId+" ", null);
        return data;
    }

    //VUELOS ***************************************************************************
    //BUSCA QUE NO SE REPITA EL ID DEL VUELO
    public boolean buscarIdVuelo(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        boolean loEncontro=false;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT id FROM Vuelo WHERE id="+"'"+unId+"'";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            loEncontro=true;
        }
        return loEncontro;
    }

    public Vuelo traerUnVuelo(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        Vuelo v=null;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT id FROM Vuelo WHERE id="+unId+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            v=new Vuelo(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
        }
        return v;
    }

    public Vuelo traerUnVuelo2(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        Vuelo v=null;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT * FROM Vuelo WHERE id="+unId+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int id, d, m, a, h, min;
            id=cursor.getInt(0);
            d=cursor.getInt(1);
            m=cursor.getInt(2);
            a=cursor.getInt(3);
            h=cursor.getInt(4);
            min=cursor.getInt(5);
            v=new Vuelo(id,d,m,a,h,min);
        }
        return v;
    }

    //Llenar lista vuelos
    public Cursor llenarListaVuelos(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Vuelo", null);
        return data;

    }

    public void eliminarVuelo(int unid){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="SELECT idPasajero FROM Asiento WHERE idVuelo="+unid+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int idpj=cursor.getInt(0);
            db.execSQL("DELETE FROM Pasajero WHERE dni="+idpj+"");
        }
        db.execSQL("DELETE FROM Asiento WHERE idVuelo="+unid+"");
        db.execSQL("DELETE FROM Reserva WHERE idVuelo="+unid+"");
        db.execSQL("DELETE FROM Vuelo WHERE id="+unid+"");
        db.close();
    }



    public void ModificarVuelo(int unId, int d, int m, int a,int h, int min){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("UPDATE Vuelo SET dia="+d+",mes="+m+", anio="+a+", hora="+h+", minutos="+min+" WHERE id="+unId+"");
        //db.execSQL("UPDATE Vuelo SET mes="+m+", WHERE id="+"'"+unId+"'");
        //db.execSQL("UPDATE Vuelo SET anio="+a+", WHERE id="+"'"+unId+"'");
        db.close();
    }

    //PAGOS****************************************************************************
    public void eliminarPago(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="SELECT * FROM Pago WHERE id="+"'"+unId+"'";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            int nrot = cursor.getInt(7);
            db.execSQL("DELETE FROM Pago WHERE id=" + unId + "");
            db.close();

        }
    }

    //RESERVAR**************************************************************************
    public void eliminarReserva(int cod){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="SELECT * FROM Reserva WHERE id="+cod+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int idp=cursor.getInt(6);
            eliminarPago(idp);
            eliminarPasajero(cod);
            db.execSQL("DELETE FROM Reserva WHERE codigo="+cod+"");
            db.close();
        }
    }

    public Cursor traerReservas(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Reserva", null);
        return data;
    }

    public void eliminarReservas(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="SELECT * FROM Reserva WHERE idVuelo="+unId+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int idp=cursor.getInt(6);
            eliminarPago(idp);
            int cod=cursor.getInt(0);
            db.execSQL("DELETE FROM Reserva WHERE codigo="+cod+"");
            db.close();
        }
    }

    public boolean buscarIdReserva(int unId){
        SQLiteDatabase db= this.getWritableDatabase();
        boolean loEncontro=false;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT id FROM Reserva WHERE id="+"'"+unId+"'";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            loEncontro=true;
        }
        return loEncontro;
    }

    //ASIENTOS*******************************************************************
    public void ModificarAsiento(int unId, int unIdV){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("UPDATE Asiento SET estado="+2+" WHERE id="+unId+" and idVuelo="+unIdV+"");
        db.close();
    }

    public void AsientoCancelado(int idPasaj){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("UPDATE Asiento SET estado="+0+" WHERE idPasajero="+idPasaj+"");
        db.close();
    }

    public void AsientoReservado(int unId, int unIdV){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("UPDATE Asiento SET estado="+1+" WHERE id="+unId+" and idVuelo="+unIdV+"");
        db.close();
    }

    public boolean comprobarEstadoAsiento(int unId, int unIdV){
        SQLiteDatabase db= this.getWritableDatabase();
        boolean dispo=false;
        int libre=0;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT * FROM Vuelo WHERE id="+unId+" and idVuelo="+unIdV+" and estado="+libre+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            dispo=true;
        }
        return dispo;
    }

    public Cursor traerAsientoPasajero2( int dni){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT * FROM Asiento WHERE idPasajero="+dni+"", null);
        return data;
    }

    public Asiento traerAsientoPasajero(int undni){
        SQLiteDatabase db= this.getWritableDatabase();
        Asiento a=null;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT * FROM Asiento WHERE idPasajero="+undni+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int id, f, e, idV;
            String l;
            id=cursor.getInt(0);
            f=cursor.getInt(1);
            l=cursor.getString(2);
            e=cursor.getInt(3);
            idV=cursor.getInt(4);
            a= new Asiento(id, f, l, e, idV);
        }
        return a;
    }

    //PASAJEROOO********************************




    public void eliminarPasajero(int cod){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="SELECT * FROM Pasajero WHERE idReserva="+"'"+cod+"'";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            int dniPasaj=cursor.getInt(0);
            AsientoCancelado(dniPasaj);
            db.execSQL("DELETE FROM Pasajero WHERE idReserva=" +cod+ "");
            db.close();

        }
    }

    public Cursor traerPasajerosReserva( int cod){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        //String q= "SELECT * FROM puntajes ORDER BY score ASC";
        Cursor data=database.rawQuery("SELECT dni FROM Pasajero", null);
        return data;
    }

    public Pasajero traerUnPasajero (int unDni)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Pasajero p=null;
        //String q="SELECT * FROM Vuelo WHERE id="+"'"+unId+"'";
        String q="SELECT * FROM Pasajero WHERE dni="+unDni+"";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()){
            int dni,h,tel, idReserva;
            String nomb, ape, direc, correo;
            dni=cursor.getInt(0);
            nomb=cursor.getString(1);
            ape=cursor.getString(2);
            direc=cursor.getString(3);
            tel=cursor.getInt(4);
            correo=cursor.getString(5);
            p=new Pasajero(dni,nomb,ape,direc,tel,correo);
        }
        return p;
    }
    }
