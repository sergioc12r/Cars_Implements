package com.serch.cars_implements.database_history;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "history_table")
public class History {

    @PrimaryKey(autoGenerate = true)
    private int id_history;

    @ColumnInfo(name = "history_id_number")
    private Long id_number;

    @ColumnInfo(name = "history_name")
    private String name;

    @ColumnInfo(name = "history_apel")
    private String apel;

    @ColumnInfo(name = "history_car")
    private String car;

    @ColumnInfo(name ="history_date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_history() {
        return id_history;

    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public Long getId_number() {
        return id_number;
    }

    public void setId_number(Long id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApel() {
        return apel;
    }

    public void setApel(String apel) {
        this.apel = apel;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }



}
