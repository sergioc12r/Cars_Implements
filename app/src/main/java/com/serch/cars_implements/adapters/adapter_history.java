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
import com.serch.cars_implements.database_history.History;
import com.serch.cars_implements.dialogs_cars.dialog_edit_car;
import com.serch.cars_implements.dialogs_cars.dialog_view_car;
import com.serch.cars_implements.dialogs_common.dialog_erase_individual_car;

import java.util.ArrayList;

public class adapter_history extends BaseAdapter{

    private Context context;
    private ArrayList<History> list;

    public adapter_history(Context context, ArrayList<History> list) {
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

        final History item = (History) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_history,null);

        TextView txt1 = (TextView)view.findViewById(R.id.txt1);
        TextView txt2 = (TextView)view.findViewById(R.id.txt2);
        TextView txt3 = (TextView)view.findViewById(R.id.txt3);
        TextView txt4 = (TextView)view.findViewById(R.id.txt4);

        txt1.setText(item.getName());
        txt2.setText(item.getApel());
        txt3.setText(item.getCar());
        txt4.setText(item.getDate());

        return view;
    }


}
