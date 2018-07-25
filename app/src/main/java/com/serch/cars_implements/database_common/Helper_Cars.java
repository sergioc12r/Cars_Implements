package com.serch.cars_implements.database_common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper_Cars extends SQLiteOpenHelper {


    private static final String LOGTAG="LOGTAG";

    public static final String DB_Name="cars";
    public static final int DB_Version =2;

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

    // SQL CREAR TABLA BASE DE DATOS
    public static final String Table_Create =
            "CREATE TABLE "+TABLE_PRUEBAS+" ("+
                    Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    Column_Placa+" TEXT,"+
                    Column_Marca+" TEXT,"+
                    Column_Modelo+" TEXT,"+
                    Column_Vehiculo+" TEXT,"+
                    Column_ColorDemas+" TEXT,"+
                    Column_ColorPuerta+" TEXT,"+
                    Column_ColorLlantas+" TEXT,"+
                    Column_numPuerta+" INT"+
                    ")";


    public Helper_Cars(Context context) {
        super(context, DB_Name, null , DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " +TABLE_PRUEBAS);
        //onCreate(db);
    }


}
