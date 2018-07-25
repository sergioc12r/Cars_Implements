package com.serch.cars_implements.database_persons;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "persons_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id_person;

    @ColumnInfo(name = "person_name")
    private String name;

    @ColumnInfo(name = "person_apel")
    private String apel;

    @ColumnInfo(name = "person_born")
    private String born;

    @ColumnInfo(name = "person_id_number")
    private Long id_number;

    @ColumnInfo(name = "person_prof")
    private String prof;

    @ColumnInfo(name = "person_married")
    private Boolean married;

    @ColumnInfo(name = "person_gain")
    private Double gain;

    @ColumnInfo(name = "person_car")
    private String placa_car;



    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
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

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public Long getId_number() {
        return id_number;
    }

    public void setId_number(Long id_number) {
        this.id_number = id_number;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public String getPlaca_car() {
        return placa_car;
    }

    public void setPlaca_car(String placa_car) {
        this.placa_car = placa_car;
    }




}
