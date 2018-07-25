package com.serch.cars_implements.dialogs_common;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.DataBase_Persons;

public class dialog_erase_individual_person {

    private static DataBase_Persons database;


    public dialog_erase_individual_person(final Context context, final Long cedula){
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_erase_individual_car);


        database = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();

        Button bt_guardar = (Button)dialog.findViewById(R.id.bt_accept_individual_car);
        Button bt_cerrar = (Button)dialog.findViewById(R.id.bt_reject_individual_car);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    database.dao_person().DeletePersonByIDNumber(String.valueOf(cedula));
                    database.close();
                    dialog.dismiss();
                    Toast.makeText(context,"Operacion exitosa, favor actualice la lista de vehiculos",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(context,"Error al realizar la operacion",Toast.LENGTH_SHORT).show();
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
