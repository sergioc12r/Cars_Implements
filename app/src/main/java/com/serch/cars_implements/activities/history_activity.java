package com.serch.cars_implements.activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.serch.cars_implements.R;
import com.serch.cars_implements.adapters.adapter_cars;
import com.serch.cars_implements.adapters.adapter_history;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.DataBase_History;
import com.serch.cars_implements.database_common.DataBase_Persons;
import com.serch.cars_implements.database_history.History;

import java.util.ArrayList;
import java.util.List;

public class history_activity extends AppCompatActivity {

    List<History> ListData;
    adapter_history adapter_list_Car;
    public static DataBase_History database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Context context = getApplicationContext();
        database = Room.databaseBuilder(context,DataBase_History.class,"history").allowMainThreadQueries().build();
        List<History> ListData = database.dao_history().getHistory();
        ListView list_car = (ListView)findViewById(R.id.list_history);
        adapter_list_Car = new adapter_history(this, (ArrayList<History>) ListData);
        list_car.setAdapter(adapter_list_Car);
        database.close();
    }
}
