package com.serch.cars_implements.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.serch.cars_implements.R;
import com.serch.cars_implements.activities.draw_activity;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.Helper_Cars;
import com.serch.cars_implements.database_persons.Person;
import com.serch.cars_implements.dialogs_cars.dialog_edit_car;
import com.serch.cars_implements.dialogs_cars.dialog_view_car;
import com.serch.cars_implements.dialogs_common.dialog_erase_individual_car;
import com.serch.cars_implements.dialogs_common.dialog_erase_individual_person;
import com.serch.cars_implements.dialogs_persons.dialog_edit_person;
import com.serch.cars_implements.dialogs_persons.dialog_view_person;

import java.util.ArrayList;

public class adapter_person extends BaseAdapter implements dialog_edit_person.Result_edit_Person {

    private Context context;
    private ArrayList<Person> list;

    public adapter_person(Context context, ArrayList<Person> list) {
        this.context = context;
        this.list = list;
    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {


        final Person item = (Person) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_list_person,null);
        TextView txt_name = (TextView)view.findViewById(R.id.txt_name_item_person);
        TextView txt_apel = (TextView)view.findViewById(R.id.txt_apel_item_person);

        ImageButton bt_clear = (ImageButton)view.findViewById(R.id.im_clear_item_person);
        ImageButton bt_search = (ImageButton)view.findViewById(R.id.im_search_item_person);
        ImageButton bt_edit = (ImageButton)view.findViewById(R.id.im_ajust_item_person);

        txt_name.setText(item.getName());
        txt_apel.setText(item.getApel());

        //boton ver datos del vehiculo
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = item.getId_person();
                String name = item.getName();
                String apel = item.getApel();
                String born = item.getBorn();
                Long id_number = item.getId_number();
                String profession = item.getProf();
                Boolean married = item.getMarried();
                Double gains = item.getGain();
                String vehiculo = item.getPlaca_car();
                new dialog_view_person(context,id,name,apel,born,id_number,profession,married,gains,vehiculo);
            }
        });


        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = item.getId_person();
                String name = item.getName();
                String apel = item.getApel();
                String born = item.getBorn();
                Long id_number = item.getId_number();
                String profession = item.getProf();
                Boolean married = item.getMarried();
                Double gains = item.getGain();
                String vehiculo = item.getPlaca_car();
                new dialog_edit_person(context,adapter_person.this,id,name,apel,born,id_number,profession,married,gains,vehiculo);
            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id_number = item.getId_number();
                new dialog_erase_individual_person(context,id_number);
            }
        });





        return view;
    }

    @Override
    public void ResultStateEditPerson(Boolean state) {
    }
}
