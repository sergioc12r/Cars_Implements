package com.serch.cars_implements.database_cars;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.serch.cars_implements.database_common.Helper_Cars;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Dao_Car {


    //insert new car
    @Insert
    public void addCar(Cars car);

    //read cars
    @Query("select * from cars_table")
    public  List<Cars> getCars();

    //delete specific car
    @Delete
    public void deleteCar(Cars car);

    //delete car by id
    @Query("DELETE FROM cars_table WHERE car_placa= :placa ")
    public void DeleteCarByPlaca(String placa);

    //update car by id
    @Query("UPDATE cars_table SET car_placa= :s_placa, car_modelo = :s_modelo, car_marca = :s_marca, car_tipo= :s_tipo, car_puertas= :s_puertas WHERE id = :id")
    public void UpdateCarById(Integer id, String s_placa, String s_marca, String s_modelo, String s_tipo, Integer s_puertas);

    @Query("UPDATE cars_table SET car_color_capo = :colordemas, car_color_llantas = :colorLLantas, car_color_puertas=:colorPuertas WHERE id = :id")
    public  int updateColorsCars(Integer id, String colorPuertas, String colorLLantas, String colordemas);

    //delete all cars
    @Query("DELETE FROM cars_table")
    void deleteAllCars();


}
