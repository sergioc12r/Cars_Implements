package com.serch.cars_implements.database_common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_cars.Dao_Car;

@Database(entities = {Cars.class},version = Helper_Cars.DB_Version)
public abstract class DataBase_Cars extends RoomDatabase {

    public abstract Dao_Car dao_car();



}

