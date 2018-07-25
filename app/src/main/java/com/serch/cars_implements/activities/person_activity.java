package com.serch.cars_implements.activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.adapters.adapter_cars;
import com.serch.cars_implements.adapters.adapter_person;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.DataBase_Persons;
import com.serch.cars_implements.database_persons.Person;
import com.serch.cars_implements.dialogs_cars.dialog_create_car;
import com.serch.cars_implements.dialogs_common.dialog_secure_erase;
import com.serch.cars_implements.dialogs_persons.dialog_create_person;

import java.util.ArrayList;
import java.util.List;

public class person_activity extends AppCompatActivity implements View.OnClickListener,dialog_create_person.Result_create_Person{


    ImageButton bt_plus_person,bt_clear_person,bt_refresh_person;ImageButton bt;
    ListView list_person;
    Context context;
    List<Person> ListData;
    adapter_person adapter_list_Person;

    //database personas
    private static DataBase_Persons database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        context=this;
        bt_clear_person = (ImageButton)findViewById(R.id.im_clear_person);
        bt_plus_person = (ImageButton)findViewById(R.id.im_plus_person);
        bt_refresh_person = (ImageButton)findViewById(R.id.im_refresh_person);
        bt = (ImageButton) findViewById(R.id.im_history);
        bt.setOnClickListener(this);
        bt_clear_person.setOnClickListener(this);
        bt_plus_person.setOnClickListener(this);
        bt_refresh_person.setOnClickListener(this);
        list_person = (ListView)findViewById(R.id.list_person);
        database = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();
        List<Person> ListData = database.dao_person().getPersons();

        adapter_list_Person = new adapter_person(this, (ArrayList<Person>) ListData);
        list_person.setAdapter(adapter_list_Person);
        database.close();

    }


    @Override
    protected void onResume() {
        super.onResume();
        ActualizarListPersons();
    }

    public void ActualizarListPersons(){
        database = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();
        List<Person> ListData = database.dao_person().getPersons();
        adapter_list_Person = new adapter_person(this, (ArrayList<Person>) ListData);
        list_person.setAdapter(adapter_list_Person);
        database.close();
        Toast.makeText(context,"Lista actualizada",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_plus_person:
                new dialog_create_person(context,person_activity.this);
                break;
            case R.id.im_clear_person:
                //new dialog_secure_erase(context);
                break;
            case R.id.im_refresh_person:
                ActualizarListPersons();
                break;
            case R.id.im_history:
                Intent intent2 = new Intent(this, history_activity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void ResultDialogSendPerson(Boolean state) {
        if(state==true){
            ActualizarListPersons();
        }
    }
}
