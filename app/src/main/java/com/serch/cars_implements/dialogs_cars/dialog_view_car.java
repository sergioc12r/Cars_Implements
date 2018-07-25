package com.serch.cars_implements.dialogs_cars;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serch.cars_implements.R;

public class dialog_view_car {


    public dialog_view_car(final Context context, Integer id, String placa, String marca, String modelo, String tipo, Integer puertas,
                        String color_puerta, String color_llantas, String color_capo,Long precio) {

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_view_data_car);
        TextView t_id = (TextView)dialog.findViewById(R.id.txt_car_view_id);
        TextView t_placa = (TextView)dialog.findViewById(R.id.txt_car_view_placa);
        TextView t_modelo = (TextView)dialog.findViewById(R.id.txt_car_view_modelo);
        TextView t_marca = (TextView)dialog.findViewById(R.id.txt_car_view_marca);
        TextView t_tipo = (TextView)dialog.findViewById(R.id.txt_car_view_tipo);
        TextView t_puertas = (TextView)dialog.findViewById(R.id.txt_car_view_puertas);
        TextView t_color_puerta = (TextView)dialog.findViewById(R.id.txt_car_view_color_puertas);
        TextView t_color_capo = (TextView)dialog.findViewById(R.id.txt_car_view_color_capo);
        TextView t_color_llantas = (TextView)dialog.findViewById(R.id.txt_car_view_color_llantas);
        TextView t_precio = (TextView)dialog.findViewById(R.id.txt_car_view_precio);

        t_id.setText("ID: "+ String.valueOf(id));
        t_placa.setText("Placa: "+placa);
        t_modelo.setText("Modelo: "+modelo);
        t_marca.setText("Marca: "+marca);
        t_tipo.setText("Tipo Vehiculo: "+tipo);
        t_puertas.setText("# de puertas: "+String.valueOf(puertas));
        t_color_puerta.setText("Color puertas: "+color_puerta);
        t_color_capo.setText("Color Capó: "+color_capo);
        t_color_llantas.setText("Color Llantas: "+color_llantas);
        t_precio.setText("Precio: "+precio);



        Button btClose = (Button)dialog.findViewById(R.id.bt_close_view_car);


        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

}
