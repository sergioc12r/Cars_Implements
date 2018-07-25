package com.serch.cars_implements.database_persons;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.serch.cars_implements.database_cars.Cars;

import java.util.List;

@Dao
public interface Dao_person {


    //insert new person
    @Insert
    public void addPerson(Person person);

    //read cars
    @Query("select * from persons_table")
    public List<Person> getPersons();

    //delete specific car
    @Delete
    public void deletePerson(Person person);

    //delete person by id number
    @Query("DELETE FROM persons_table WHERE person_id_number= :id_number ")
    public void DeletePersonByIDNumber(String id_number );

    //update person by id number
    @Query("UPDATE persons_table SET person_name= :s_nombres, person_apel = :s_apellidos, person_born = :s_fecha, person_prof= :s_prof, person_married= :s_married, person_gain = :s_gain, person_car=:s_vehi WHERE person_id_number = :s_id_number")
    public void UpdatePersonByIDNumber(String s_nombres, String s_apellidos, String s_fecha, Long s_id_number, String s_prof, Boolean s_married, Double s_gain, String s_vehi);


    //delete all persons
    @Query("DELETE FROM persons_table")
    void deleteAllPersons();


}
