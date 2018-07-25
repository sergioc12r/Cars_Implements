package com.serch.cars_implements.database_common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.serch.cars_implements.database_cars.Dao_Car;
import com.serch.cars_implements.database_history.Dao_history;
import com.serch.cars_implements.database_history.History;

@Database(entities = {History.class},version = Helper_History.DB_Version)
public abstract class DataBase_History extends RoomDatabase {
    public abstract Dao_history dao_history();
}
