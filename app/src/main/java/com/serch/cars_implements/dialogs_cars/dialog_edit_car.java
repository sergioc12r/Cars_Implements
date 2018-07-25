package com.serch.cars_implements.dialogs_cars;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.adapters.adapter_cars;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;

public class dialog_edit_car {

    public interface Result_edit_Car{

        void ResultStateEditCar(Boolean state);
    }

    private static DataBase_Cars database;
    private dialog_edit_car.Result_edit_Car send;
    String s_placa,s_modelo,s_marca,s_tipo;
    Integer s_puertas;

    public dialog_edit_car(final Context context, adapter_cars result, final Integer id, String placa, String marca, String modelo, String tipo, Integer puerta){
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_edit_car);


        database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        Button bt_guardar = (Button)dialog.findViewById(R.id.bt_save_edit_car);
        Button bt_cerrar = (Button)dialog.findViewById(R.id.bt_close_edit_car);
        final EditText e_placa_ = (EditText)dialog.findViewById(R.id.ed_placa_edit_car);
        final EditText e_marca_ = (EditText)dialog.findViewById(R.id.ed_marca_edit_car);
        final EditText e_modelo_ = (EditText)dialog.findViewById(R.id.ed_modelo_edit_car);
        final EditText e_puertas_ = (EditText)dialog.findViewById(R.id.ed_puertas_edit_car);
        final EditText e_tipo_ = (EditText)dialog.findViewById(R.id.ed_tipo_edit_car);
        e_placa_.setText(placa);
        e_marca_.setText(marca);
        e_modelo_.setText(modelo);
        e_puertas_.setText(String.valueOf(puerta));
        e_tipo_.setText(tipo);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    s_placa = e_placa_.getText().toString();
                    s_marca = e_marca_.getText().toString();
                    s_modelo = e_modelo_.getText().toString();
                    s_tipo = e_tipo_.getText().toString();
                    s_puertas = Integer.parseInt(e_puertas_.getText().toString());

                    if(!s_placa.isEmpty() && !s_marca.isEmpty() && !s_modelo.isEmpty() &&
                            (s_puertas>0 && s_puertas<6) && !s_tipo.isEmpty()){

                        database.dao_car().UpdateCarById(id,s_placa,s_marca,s_modelo,s_tipo,s_puertas);
                        database.close();
                        Toast.makeText(context,"Actualizacion realizada con exito",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context,"Verifique los datos",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(context,"Error, intente denuevo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        dialog.show();

    }



}
