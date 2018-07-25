package com.serch.cars_implements.dialogs_persons;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serch.cars_implements.R;

public class dialog_view_person {


    public dialog_view_person(final Context context, Integer id, String nombres, String apellidos, String fecha, Long cedula, String profesion,
                           boolean casado, Double ingresos, String placa_vehiculo) {

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_view_person);

        TextView t_nombres = (TextView)dialog.findViewById(R.id.txt_car_view_nombres);
        TextView t_apellidos = (TextView)dialog.findViewById(R.id.txt_car_view_apellidos);
        TextView t_fecha = (TextView)dialog.findViewById(R.id.txt_car_view_fecha);
        TextView t_cedula = (TextView)dialog.findViewById(R.id.txt_car_view_cedula);
        TextView t_profesion = (TextView)dialog.findViewById(R.id.txt_car_view_ocupacion);
        TextView t_casado = (TextView)dialog.findViewById(R.id.txt_car_view_casado);
        TextView t_ingresos = (TextView)dialog.findViewById(R.id.txt_car_view_ingresos);
        TextView t_vehiculo = (TextView)dialog.findViewById(R.id.txt_car_view_vehiculo);

        t_nombres.setText("Nombres: "+nombres );
        t_apellidos.setText("Apellidos: "+ apellidos);
        t_fecha.setText("Fecha de Nacimiento: "+ fecha);
        t_cedula.setText("Numero de identificacion: "+ String.valueOf(cedula));
        t_profesion.setText("Ocupacion o Profesion: "+ String.valueOf(profesion));
        if(casado==true) {
            t_casado.setText("Casado: " + "Si");
        }else{
            t_casado.setText("Casado: " + "No");
        }
        t_ingresos.setText("Ingresos Mensuales: " + String.valueOf(ingresos));
        t_vehiculo.setText("Vehiculo asignado: " + placa_vehiculo);




        Button btClose = (Button)dialog.findViewById(R.id.bt_close_view_person);


        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
