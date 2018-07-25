package com.serch.cars_implements.dialogs_persons;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.adapters.adapter_person;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.DataBase_History;
import com.serch.cars_implements.database_common.DataBase_Persons;
import com.serch.cars_implements.database_history.History;
import com.serch.cars_implements.database_persons.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class dialog_edit_person {

    public interface Result_edit_Person{

        void ResultStateEditPerson(Boolean state);
    }

    //databases
    private static DataBase_Cars database_cars;
    private static DataBase_Persons dataBase_persons;
    private static DataBase_History dataBase_history;

    private dialog_edit_person.Result_edit_Person send;

    EditText e_nombres,e_apellidos,e_fecha,e_cedula,e_profesion,e_casado,e_ingresos,e_vehiculos;

    //utilities to datepicker
    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public Calendar c;
    int mes;
    int dia;
    int anio;

    public dialog_edit_person(final Context context, adapter_person result, Integer id, String name, String apel, String born, Long id_number, String profession, Boolean married, Double gains, String vehiculo) {

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_edit_person);

        dataBase_persons = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();

        c = Calendar.getInstance();
        //Variables para obtener la fecha
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        anio = c.get(Calendar.YEAR);
        
        Button bt_guardar = (Button)dialog.findViewById(R.id.bt_save_edit_person);
        Button bt_cerrar = (Button)dialog.findViewById(R.id.bt_close_edit_person);

         e_nombres = (EditText)dialog.findViewById(R.id.ed_nombre_edit_person);
         e_apellidos = (EditText)dialog.findViewById(R.id.ed_apel_edit_person);
         e_fecha = (EditText)dialog.findViewById(R.id.ed_date_edit_person);
         e_cedula = (EditText)dialog.findViewById(R.id.ed_id_edit_person);
         e_profesion = (EditText)dialog.findViewById(R.id.ed_profe_edit_person);
         e_casado = (EditText)dialog.findViewById(R.id.ed_married_edit_person);
         e_ingresos = (EditText)dialog.findViewById(R.id.ed_gain_edit_person);
         e_vehiculos = (EditText)dialog.findViewById(R.id.ed_car_edit_person);

        e_fecha.setFocusable(false);e_fecha.setClickable(true);
        e_casado.setClickable(true);e_casado.setFocusable(false);
        e_vehiculos.setClickable(true);e_vehiculos.setFocusable(false);

        //carga datos previos
        e_nombres.setText(name);
        e_apellidos.setText(apel);
        e_fecha.setText(born);
        e_cedula.setText(String.valueOf(id_number));
        e_profesion.setText(profession);
        if(married==true) {
            e_casado.setText("Si");
        }else {
            e_casado.setText("No");
        }
        e_ingresos.setText(String.valueOf(gains));

        if(vehiculo!=null || vehiculo!=""){
            e_vehiculos.setText(vehiculo);
        }

        e_casado.setOnClickListener(new View.OnClickListener() {
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

        bt_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        e_vehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoCars(context);
            }
        });


        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String s_nombres = e_nombres.getText().toString();
                    String s_apellidos = e_apellidos.getText().toString();
                    String s_fecha = e_fecha.getText().toString();
                    Long s_id_number = Long.decode(e_cedula.getText().toString());
                    String s_prof = e_profesion.getText().toString();
                    Boolean s_married=null;
                    if(e_casado.getText().toString()=="Si"){
                        s_married=true;
                    }else{
                        s_married=false;
                    }
                    Double s_gain = Double.parseDouble(e_ingresos.getText().toString());
                    String s_vehi = String.valueOf(e_vehiculos.getText().toString());

                    if(!s_nombres.isEmpty() && !s_apellidos.isEmpty() && !s_fecha.isEmpty() &&
                            (s_id_number>0) && (s_gain>0) && !s_prof.isEmpty() && s_married!=null){


                        dataBase_persons = Room.databaseBuilder(context,DataBase_Persons.class,"persons").allowMainThreadQueries().build();
                        dataBase_persons.dao_person().UpdatePersonByIDNumber(s_nombres,s_apellidos,s_fecha,s_id_number,s_prof,s_married,s_gain,s_vehi);
                        dataBase_persons.close();


                        dataBase_history = Room.databaseBuilder(context,DataBase_History.class,"history").allowMainThreadQueries().build();

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


                        dataBase_history.close();

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
                e_casado.setText(String.valueOf(strName));
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
                e_vehiculos.setText(String.valueOf(strName));
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
