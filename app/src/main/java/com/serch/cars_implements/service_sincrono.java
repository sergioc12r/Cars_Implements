package com.serch.cars_implements;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.serch.cars_implements.database_common.DataBase_Persons;
import com.serch.cars_implements.database_persons.Person;
import com.serch.cars_implements.retrofit.iData;
import com.serch.cars_implements.retrofit.response;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class service_sincrono extends IntentService {

    private static final String url = "http://192.168.1.104/practicas/cars.php/";

    //database personas
    private static DataBase_Persons database;

    public service_sincrono() {
        super("service_sincrono");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            //sincroniza con retrofit
            OkHttpClient okHttpClient = new OkHttpClient();
            Retrofit.Builder retrofitbuilder = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient);
            Retrofit retrofit = retrofitbuilder.build();
            iData service = retrofit.create(iData.class);
            database = Room.databaseBuilder(getApplicationContext(),DataBase_Persons.class,"persons").allowMainThreadQueries().build();
            List<Person> ListData = database.dao_person().getPersons();
            for(int i=0;i<ListData.size();i++){

                Person person = ListData.get(i);
                response resp = new response(person.getName(), person.getApel(), String.valueOf(person.getId_number()), person.getPlaca_car());
                Log.e("post",resp.toString());
                Call<ResponseBody> call = service.respuesta(resp);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.e("response:", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


                Log.e("Servicio", "sincronizacion data");
            }
        }
    }


}
