package com.serch.cars_implements.database_cars;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cars_table")
public class Cars {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "car_marca")
    private String marca;

    @ColumnInfo(name = "car_placa")
    private String placa;

    @ColumnInfo(name = "car_modelo")
    private String modelo;

    @ColumnInfo(name = "car_puertas")
    private Integer puertas;

    @ColumnInfo(name = "car_tipo")
    private String tipovehiculo;

    @ColumnInfo(name = "car_color_puertas")
    private String colorpuertas;

    @ColumnInfo(name = "car_color_llantas")
    private String colorllantas;

    @ColumnInfo(name = "car_color_capo")
    private String colorcapo;

    @ColumnInfo(name = "car_precio")
    private Long precio;

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public String getTipovehiculo() {
        return tipovehiculo;
    }

    public void setTipovehiculo(String tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

    public String getColorpuertas() {
        return colorpuertas;
    }

    public void setColorpuertas(String colorpuertas) {
        this.colorpuertas = colorpuertas;
    }

    public String getColorllantas() {
        return colorllantas;
    }

    public void setColorllantas(String colorllantas) {
        this.colorllantas = colorllantas;
    }

    public String getColorcapo() {
        return colorcapo;
    }

    public void setColorcapo(String colorcapo) {
        this.colorcapo = colorcapo;
    }
}
