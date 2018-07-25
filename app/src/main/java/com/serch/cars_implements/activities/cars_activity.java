package com.serch.cars_implements.activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.adapters.adapter_cars;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.dialogs_cars.dialog_create_car;
import com.serch.cars_implements.dialogs_common.dialog_secure_erase;

import java.util.ArrayList;
import java.util.List;

public class cars_activity extends AppCompatActivity implements View.OnClickListener,dialog_create_car.Result_create_Car
,dialog_secure_erase.Result_erase{

    ImageButton bt_plus,bt_clear,bt_refresh;
    ListView list_car;
    Context context;
    TextView txtprueba;

    List<Cars> ListData;
    adapter_cars adapter_list_Car;

    public static DataBase_Cars database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        context=this;
        bt_clear = (ImageButton)findViewById(R.id.im_clear_car);
        bt_plus = (ImageButton)findViewById(R.id.im_plus_car);
        bt_refresh = (ImageButton)findViewById(R.id.im_refresh_car);
        txtprueba = (TextView)findViewById(R.id.txtprueba);
        bt_clear.setOnClickListener(this);
        bt_plus.setOnClickListener(this);
        bt_refresh.setOnClickListener(this);
        list_car = (ListView)findViewById(R.id.list_cars);
        database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Cars> ListData = database.dao_car().getCars();

        adapter_list_Car = new adapter_cars(this, (ArrayList<Cars>) ListData);
        list_car.setAdapter(adapter_list_Car);
        database.close();

    }


    @Override
    protected void onResume() {
        super.onResume();
        ActualizarListCars();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_plus_car:
                new dialog_create_car(context,cars_activity.this);
                break;
            case R.id.im_clear_car:
                new dialog_secure_erase(context,cars_activity.this);
                break;
            case R.id.im_refresh_car:
                ActualizarListCars();
                break;
        }
    }


    //verificar si se cerro el dialog y en que estado
    @Override
    public void ResultDialogSend(Boolean state) {
        if(state==true){
            //actualizar listview
            ActualizarListCars();
        }
    }

    public void ActualizarListCars(){
        database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().build();
        List<Cars> ListData = database.dao_car().getCars();
        adapter_list_Car = new adapter_cars(this, (ArrayList<Cars>) ListData);
        list_car.setAdapter(adapter_list_Car);
        database.close();
        Toast.makeText(context,"Lista actualizada",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ResultDialogSendSecure(Boolean state) {
        if(state==true){
            database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().build();
            database.dao_car().deleteAllCars();
            database.close();
            ActualizarListCars();
        }
    }
}
