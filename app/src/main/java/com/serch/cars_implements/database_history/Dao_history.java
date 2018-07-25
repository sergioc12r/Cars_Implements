package com.serch.cars_implements.database_history;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.serch.cars_implements.database_persons.Person;

import java.util.List;

@Dao
public interface Dao_history {

    //insert new record
    @Insert
    public void addHistory(History history);

    //Leer historial
    @Query("select * from history_table")
    public List<History> getHistory();



}
