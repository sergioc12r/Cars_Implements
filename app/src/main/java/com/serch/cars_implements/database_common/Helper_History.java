package com.serch.cars_implements.database_common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper_History extends SQLiteOpenHelper {


    public static final String DB_Name="history";
    public static final int DB_Version =1;

    public static final String TABLE_PRUEBAS = "cars_table";
    public static final String Column_ID = "id";
    public static final String Column_Placa = "placa";
    public static final String Column_Marca = "marca";
    public static final String Column_Modelo= "modelo";
    public static final String Column_numPuerta = "numpuerta";
    public static final String Column_Vehiculo= "vehiculo";
    public static final String Column_ColorPuerta = "colorpuerta";
    public static final String Column_ColorDemas = "colordemas";
    public static final String Column_ColorLlantas = "colorllantas";

    public Helper_History(Context context) {
        super(context, DB_Name, null , DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
