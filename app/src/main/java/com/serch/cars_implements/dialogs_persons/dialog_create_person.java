package com.serch.cars_implements.dialogs_persons;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.activities.cars_activity;
import com.serch.cars_implements.activities.person_activity;
import com.serch.cars_implements.adapters.adapter_cars;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.DataBase_History;
import com.serch.cars_implements.database_common.DataBase_Persons;
import com.serch.cars_implements.database_history.History;
import com.serch.cars_implements.database_persons.Person;
import com.serch.cars_implements.dialogs_cars.dialog_create_car;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class dialog_create_person {


    EditText e_nombres,e_apellidos,e_fecha,e_id,e_prof,e_married,e_gain,e_vehi;
    String s_nombres,s_apellidos,s_fecha,s_vehi,s_prof;
    Double s_gain; Long s_id_number;
    Boolean s_married;
    Button bt_save,bt_close;


    //databases
    private static DataBase_Cars database_cars;
    private static DataBase_Persons dataBase_persons;
    private static DataBase_History dataBase_history;

    //utilities to datepicker
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c;
    int mes;
    int dia;
    int anio;

    public interface Result_create_Person{

        void ResultDialogSendPerson(Boolean state);
    }

    private dialog_create_person.Result_create_Person send;

    public dialog_create_person(final Context context,final person_activity result) {
        send=result;
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_create_person);

        c = Calendar.getInstance();
        //Variables para obtener la fecha
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        anio = c.get(Calendar.YEAR);

        bt_save = (Button)dialog.findViewById(R.id.bt_save_create_person);
        bt_close = (Button)dialog.findViewById(R.id.bt_close_create_person);
        e_nombres = (EditText)dialog.findViewById(R.id.ed_nombre_new_person);
        e_apellidos = (EditText)dialog.findViewById(R.id.ed_apel_new_person);
        e_fecha = (EditText)dialog.findViewById(R.id.ed_date_new_person);
        e_id = (EditText)dialog.findViewById(R.id.ed_id_new_person);
        e_prof = (EditText)dialog.findViewById(R.id.ed_profe_new_person);
        e_married = (EditText)dialog.findViewById(R.id.ed_married_new_person);
        e_gain = (EditText)dialog.findViewById(R.id.ed_gain_new_person);
        e_vehi = (EditText)dialog.findViewById(R.id.ed_car_new_person);

        e_fecha.setFocusable(false);e_fecha.setClickable(true);
        e_married.setClickable(true);e_married.setFocusable(false);
        e_vehi.setClickable(true);e_vehi.setFocusable(false);

        e_married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo(context);
            }
        });

        e_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha(context);
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    s_nombres = e_nombres.getText().toString();
                    s_apellidos = e_apellidos.getText().toString();
                    s_fecha = e_fecha.getText().toString();
                    s_id_number = Long.decode(e_id.getText().toString());
                    s_prof = e_prof.getText().toString();
                    if(e_married.getText().toString()=="Si"){
                        s_married=true;
                    }else{
                        s_married=false;
                    }
                    s_gain = Double.parseDouble(e_gain.getText().toString());
                    s_vehi = String.valueOf(e_vehi.getText().toString());

                    if(!s_nombres.isEmpty() && !s_apellidos.isEmpty() && !s_fecha.isEmpty() &&
                            (s_id_number>0) && (s_gain>0) && !s_prof.isEmpty() && s_married!=null){


                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, 0);
                        c.set(Calendar.MINUTE, 0);
                        c.set(Calendar.SECOND, 0);
                        String date = String.valueOf(c.getTime());

                        History history = new History();
                        history.setId_number(s_id_number);
                        history.setName(s_nombres);
                        history.setApel(s_apellidos);
                        history.setCar(s_vehi);
                        history.setDate(date);
                        dataBase_history = Room.databaseBuilder(context,DataBase_History.class,"history").allowMainThreadQueries().build();
                        dataBase_history.dao_history().addHistory(history);
                        dataBase_history.close();

                        Person car = new Person();
                        car.setName(s_nombres);
                        car.setApel(s_apellidos);
                        car.setBorn(s_fecha);
                        car.setId_number(s_id_number);
                        car.setProf(s_prof);
                        car.setMarried(s_married);
                        car.setGain(s_gain);
                        car.setPlaca_car(s_vehi);
                        dataBase_persons = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();
                        dataBase_persons.dao_person().addPerson(car);
                        dataBase_persons.close();
                        Toast.makeText(context,"Vehiculo creado con exito",Toast.LENGTH_SHORT).show();
                        send.ResultDialogSendPerson(true);
                        dialog.dismiss();

                    }else{
                        Toast.makeText(context,"Verifique los datos",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(context,"Error, intente denuevo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        e_vehi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoCars(context);
            }
        });

    }


    private void dialogo(final Context context) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);


        final ArrayAdapter adapter = ArrayAdapter.createFromResource( context, R.array.Married_states , android.R.layout.select_dialog_singlechoice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Object strName = adapter.getItem(which);
                e_married.setText(String.valueOf(strName));
            }
        });
        builderSingle.show();



    }

    private void dialogoCars(final Context context){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        database_cars = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().build();
        List<Cars> ListData = database_cars.dao_car().getCars();
        database_cars.close();
        List<String> sistemas = new ArrayList<String>();
        for(Cars cat:ListData){
            sistemas.add(cat.getPlaca());
        }
        final ArrayAdapter<String> adapter1;
        adapter1 = new ArrayAdapter<String>(context,android.R.layout.select_dialog_singlechoice,sistemas);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(adapter1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Object strName = adapter1.getItem(which);
                e_vehi.setText(String.valueOf(strName));
            }
        });
        builderSingle.show();

    }

    private void obtenerFecha(Context context){
        DatePickerDialog recogerFecha = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                e_fecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }
        },anio, mes, dia);
        recogerFecha.show();

    }



}
