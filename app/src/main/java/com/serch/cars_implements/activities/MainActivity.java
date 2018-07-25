package com.serch.cars_implements.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.dialogs_cars.dialog_create_car;
import com.serch.cars_implements.dialogs_persons.dialog_create_person;
import com.serch.cars_implements.service_sincrono;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView cardpeople,cardcars;
    Context context;
    EditText edit;ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardcars = (CardView)findViewById(R.id.cardCars);
        cardcars.setOnClickListener(this);
        cardpeople = (CardView)findViewById(R.id.cardPerson);
        cardpeople.setOnClickListener(this);
        context = this;
        edit = (EditText)findViewById(R.id.ed_number);
        button= (ImageButton)findViewById(R.id.img_time);
        button.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardCars:
                //new dialog_create_car(context,MainActivity.this);
                Intent intent = new Intent(this, cars_activity.class);
                startActivity(intent);
                break;
            case R.id.cardPerson:
                //new dialog_create_person(context,MainActivity.this);
                Intent intent2 = new Intent(this, person_activity.class);
                startActivity(intent2);
                break;
            case R.id.img_time:
                int minutos=0;
                try {
                    minutos = Integer.parseInt(edit.getText().toString());
                }catch (Exception e){
                    minutos=5;
                }
                Toast.makeText(getApplicationContext(),"Iniciando Servicio de Sincronizacion",Toast.LENGTH_SHORT).show();
                edit.setText("");

                /* ALARMA PARA LA SERVICIO */
                Intent intent_hola = new Intent(context,service_sincrono.class);
                PendingIntent pendingIntent = PendingIntent.getService(context,0,intent_hola,0);
                AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC,System.currentTimeMillis(),minutos*1000*60,pendingIntent);

        }
    }

    public static class Persons extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_persons);
        }
    }
}
