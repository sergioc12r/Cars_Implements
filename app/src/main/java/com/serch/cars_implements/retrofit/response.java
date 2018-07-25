package com.serch.cars_implements.retrofit;

public class response {
    public String names;
    public String aples;
    public String id_number;
    public String vehiculo;

    public response(String names,String aples,String id_number, String vehiculo){
        this.names = names;
        this.aples = aples;
        this.id_number = id_number;
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString(){
        return "names: "+names+", aples: "+aples+",id_number: "+id_number+",vehiculo:"+vehiculo;
    }
}
