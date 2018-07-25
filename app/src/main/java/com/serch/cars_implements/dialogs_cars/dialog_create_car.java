package com.serch.cars_implements.dialogs_cars;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.activities.cars_activity;
import com.serch.cars_implements.database_cars.Cars;

import com.serch.cars_implements.database_common.DataBase_Cars;

public class dialog_create_car {

    public interface Result_create_Car{

        void ResultDialogSend(Boolean state);
    }

    private static DataBase_Cars database;

    private Result_create_Car send;
    String s_placa,s_modelo,s_marca,s_tipo;
    Integer s_puertas;Long s_precio;

    public dialog_create_car(final Context context, final cars_activity result){
        send=result;
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_create_car);


        database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        Button bt_guardar = (Button)dialog.findViewById(R.id.bt_save_create_car);
        Button bt_cerrar = (Button)dialog.findViewById(R.id.bt_close_create_car);
        final EditText e_placa = (EditText)dialog.findViewById(R.id.ed_placa_new_car);
        final EditText e_marca = (EditText)dialog.findViewById(R.id.ed_marca_new_car);
        final EditText e_modelo = (EditText)dialog.findViewById(R.id.ed_modelo_new_car);
        final EditText e_puertas = (EditText)dialog.findViewById(R.id.ed_puertas_new_car);
        final EditText e_tipo = (EditText)dialog.findViewById(R.id.ed_tipo_new_car);
        final EditText e_precio = (EditText)dialog.findViewById(R.id.ed_precio_new_car);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    s_placa = e_placa.getText().toString();
                    s_marca = e_marca.getText().toString();
                    s_modelo = e_modelo.getText().toString();
                    s_tipo = e_tipo.getText().toString();
                    s_puertas = Integer.parseInt(e_puertas.getText().toString());
                    s_precio = Long.decode(e_precio.getText().toString());

                    if(!s_placa.isEmpty() && !s_marca.isEmpty() && !s_modelo.isEmpty() &&
                            (s_puertas>0 && s_puertas<6) && !s_tipo.isEmpty()){

                        Cars car = new Cars();
                        car.setPlaca(s_placa);
                        car.setModelo(s_modelo);
                        car.setMarca(s_marca);
                        car.setTipovehiculo(s_tipo);
                        car.setPuertas(s_puertas);
                        car.setPrecio(s_precio);

                        database.dao_car().addCar(car);
                        database.close();
                        Toast.makeText(context,"Vehiculo creado con exito",Toast.LENGTH_SHORT).show();
                        send.ResultDialogSend(true);
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
                send.ResultDialogSend(false);
            }
        });


        dialog.show();

    }
}
