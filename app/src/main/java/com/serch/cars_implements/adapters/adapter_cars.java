package com.serch.cars_implements.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.serch.cars_implements.dialogs_cars.dialog_edit_car;
import com.serch.cars_implements.dialogs_cars.dialog_view_car;
import com.serch.cars_implements.dialogs_common.dialog_erase_individual_car;
import com.serch.cars_implements.dialogs_common.dialog_secure_erase;

import java.util.ArrayList;

public class adapter_cars extends BaseAdapter implements dialog_edit_car.Result_edit_Car {

    private Context context;
    private ArrayList<Cars> list;

    public adapter_cars(Context context, ArrayList<Cars> list) {
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

        final Cars item = (Cars)getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_list_car,null);
        TextView txt_placa = (TextView)view.findViewById(R.id.txt_item_placa);
        ImageButton bt_colors = (ImageButton)view.findViewById(R.id.im_color_item_car);
        ImageButton bt_clear = (ImageButton)view.findViewById(R.id.im_clear_item_car);
        ImageButton bt_search = (ImageButton)view.findViewById(R.id.im_search_item_car);
        ImageButton bt_edit = (ImageButton)view.findViewById(R.id.im_ajust_item_car);

        txt_placa.setText(item.getPlaca());


        //boton ver datos del vehiculo
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = item.getId();
                String placa = item.getPlaca();
                String marca = item.getMarca();
                String modelo = item.getModelo();
                String tipo = item.getTipovehiculo();
                Integer puerta = item.getPuertas();
                String color_puerta = item.getColorpuertas();
                String color_llantas = item.getColorllantas();
                String color_demas = item.getColorcapo();
                Long precio = item.getPrecio();
                new dialog_view_car(context,id,placa,marca,modelo,tipo,puerta,color_puerta,color_llantas,color_demas,precio);
            }
        });


        bt_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer id = item.getId();
                String placa = item.getPlaca();
                String marca = item.getMarca();
                String modelo = item.getModelo();
                String tipo = item.getTipovehiculo();
                Integer puertas = item.getPuertas();
                String color_puertas = item.getColorpuertas();
                String color_demas = item.getColorcapo();
                String color_llantas = item.getColorllantas();

                Intent intent = new Intent(context,draw_activity.class);
                intent.putExtra(Helper_Cars.Column_ID,String.valueOf(id));
                intent.putExtra(Helper_Cars.Column_Placa,placa);
                intent.putExtra(Helper_Cars.Column_Marca,marca);
                intent.putExtra(Helper_Cars.Column_Modelo,modelo);
                intent.putExtra(Helper_Cars.Column_Vehiculo,tipo);
                intent.putExtra(Helper_Cars.Column_numPuerta,puertas);
                intent.putExtra(Helper_Cars.Column_ColorPuerta,color_puertas);
                intent.putExtra(Helper_Cars.Column_ColorDemas,color_demas);
                intent.putExtra(Helper_Cars.Column_ColorLlantas,color_llantas);
                context.startActivity(intent);

            }
        });
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = item.getId();
                String placa = item.getPlaca();
                String marca = item.getMarca();
                String modelo = item.getModelo();
                String tipo = item.getTipovehiculo();
                Integer puerta = item.getPuertas();
                new dialog_edit_car(context,adapter_cars.this,id,placa,marca,modelo,tipo,puerta);
            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = item.getId();
                String placa = item.getPlaca();
                new dialog_erase_individual_car(context,placa);
            }
        });



        return view;
    }

    @Override
    public void ResultStateEditCar(Boolean state) {

    }
}
