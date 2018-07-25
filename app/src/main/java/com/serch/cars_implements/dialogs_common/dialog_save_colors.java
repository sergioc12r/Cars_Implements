package com.serch.cars_implements.dialogs_common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.serch.cars_implements.R;
import com.serch.cars_implements.activities.draw_activity;

public class dialog_save_colors {

    public interface ResultSaveColors{

        void ResultSaveSend(boolean state);
    }

    private dialog_save_colors.ResultSaveColors send;


    public dialog_save_colors(final Context context, draw_activity result){
        send=result;
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Guardar Pintura");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_save_colors);

        Button btGuardar = (Button)dialog.findViewById(R.id.bt_color_acept);
        Button btClose = (Button)dialog.findViewById(R.id.bt_color_reject);



        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.ResultSaveSend(true);
                dialog.dismiss();
            }
        });

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.ResultSaveSend(false);
                dialog.dismiss();
            }
        });




        dialog.show();

    }
}
