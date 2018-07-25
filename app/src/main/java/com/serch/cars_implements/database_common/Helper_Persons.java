package com.serch.cars_implements.database_common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper_Persons extends SQLiteOpenHelper {

    public static final String DB_Name="persons";
    public static final int DB_Version =1;

    public static final String TABLE_PRUEBAS = "persons_table";
    public static final String Column_ID = "id_person";
    public static final String Column_Name = "person_name";
    public static final String Column_Apel = "person_apel";
    public static final String Column_Born= "person_born";
    public static final String Column_Id_Number = "person_id_number";
    public static final String Column_Prof= "person_prof";
    public static final String Column_Married = "person_married";
    public static final String Column_Gain = "person_gain";
    public static final String Column_Car_Placa = "person_car";

    public Helper_Persons(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
