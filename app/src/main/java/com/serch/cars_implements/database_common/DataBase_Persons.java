package com.serch.cars_implements.database_common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.serch.cars_implements.database_cars.Dao_Car;
import com.serch.cars_implements.database_persons.Dao_person;
import com.serch.cars_implements.database_persons.Person;

@Database(entities = {Person.class},version = Helper_Persons.DB_Version)
public abstract class DataBase_Persons extends RoomDatabase {

    public abstract Dao_person dao_person();
}
