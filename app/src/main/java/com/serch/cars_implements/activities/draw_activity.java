package com.serch.cars_implements.activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.database_cars.Cars;
import com.serch.cars_implements.database_common.DataBase_Cars;
import com.serch.cars_implements.database_common.Helper_Cars;
import com.serch.cars_implements.dialogs_cars.dialog_create_car;
import com.serch.cars_implements.dialogs_common.dialog_save_colors;
import com.serch.cars_implements.fragments.demas_fragment;
import com.serch.cars_implements.fragments.llantas_fragment;
import com.serch.cars_implements.fragments.puertas_fragment;

public class draw_activity extends AppCompatActivity implements dialog_save_colors.ResultSaveColors{

    TextView txtPlaca,txt1;
    Spinner spinner_llantas,spinner_puerta,spinner_demas;
    Context context;

    private static DataBase_Cars database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        context=this;
        ConfigSpinners();
        ItemsSpinners();
        String color_llanta, color_puerta, color_demas;

        color_demas = getIntent().getExtras().getString(Helper_Cars.Column_ColorDemas);
        color_llanta = getIntent().getExtras().getString(Helper_Cars.Column_ColorLlantas);
        color_puerta = getIntent().getExtras().getString(Helper_Cars.Column_ColorPuerta);
        txtPlaca = (TextView)findViewById(R.id.txt_draw_placa);
        txtPlaca.setText(getIntent().getExtras().getString(Helper_Cars.Column_Placa));
        txt1 = (TextView)findViewById(R.id.txt_id);
        txt1.setText(getIntent().getExtras().getString(Helper_Cars.Column_ID));
        ImageButton bt = (ImageButton) findViewById(R.id.bt_save_colors);
        int pos1 = obtenerPosicionItem(spinner_demas,color_demas);
        int pos2 = obtenerPosicionItem(spinner_llantas,color_llanta);
        int pos3 = obtenerPosicionItem(spinner_puerta,color_puerta);
        spinner_puerta.setSelection(pos3);
        spinner_demas.setSelection(pos1);
        spinner_llantas.setSelection(pos2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new dialog_save_colors(context, draw_activity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void ConfigSpinners() {

        spinner_llantas = (Spinner) findViewById(R.id.spinner_llantas);
        ArrayAdapter adapter_llantas = ArrayAdapter.createFromResource( this, R.array.Colores , android.R.layout.simple_spinner_item);
        adapter_llantas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_llantas.setAdapter(adapter_llantas);

        spinner_puerta = (Spinner) findViewById(R.id.spinner_puerta);
        ArrayAdapter adapter_puerta = ArrayAdapter.createFromResource( this, R.array.Colores , android.R.layout.simple_spinner_item);
        adapter_puerta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_puerta.setAdapter(adapter_puerta);

        spinner_demas = (Spinner) findViewById(R.id.spinner_demas);
        ArrayAdapter adapter_demas = ArrayAdapter.createFromResource( this, R.array.Colores , android.R.layout.simple_spinner_item);
        adapter_demas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_demas.setAdapter(adapter_demas);
    }

    private void ItemsSpinners(){

        spinner_puerta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                puertas_fragment fragment = (puertas_fragment) getFragmentManager().findFragmentById(R.id.fragment_puertas);
                fragment.Recept(selection);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner_llantas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                llantas_fragment fragment = (llantas_fragment) getFragmentManager().findFragmentById(R.id.fragment_llanta);
                fragment.Recept(selection);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinner_demas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                demas_fragment fragment = (demas_fragment) getFragmentManager().findFragmentById(R.id.fragment_demas);
                fragment.Recept(selection);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }

    @Override
    public void ResultSaveSend(boolean state) {
        if(state==true){
            try {
                String placa = txtPlaca.getText().toString();
                String id = txt1.getText().toString();
                String llanta = spinner_llantas.getSelectedItem().toString();
                String puerta = spinner_puerta.getSelectedItem().toString();
                String demas = spinner_demas.getSelectedItem().toString();

                database = Room.databaseBuilder(context,DataBase_Cars.class,"cars").allowMainThreadQueries().build();
                database.dao_car().updateColorsCars(Integer.parseInt(id),puerta,llanta,demas);
                database.close();

                Toast.makeText(getApplicationContext(), "Datos de pintura actualizado", Toast.LENGTH_SHORT).show();
                finish();
            }catch (Exception ew){
                Toast.makeText(getApplicationContext(), "Error con la base de datos", Toast.LENGTH_SHORT).show();
            }
        }else{

        }
    }


    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        return posicion;
    }
}
