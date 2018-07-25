package com.serch.cars_implements.dialogs_common;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serch.cars_implements.R;
import com.serch.cars_implements.activities.cars_activity;


public class dialog_secure_erase {

    public interface Result_erase{

        void ResultDialogSendSecure(Boolean state);
    }

    private dialog_secure_erase.Result_erase send;


    public dialog_secure_erase(final Context context, cars_activity result){
        send=result;
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Autenticacion");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_erase_question);

        Button btAcept = (Button)dialog.findViewById(R.id.bt_accept);
        Button btCancel = (Button)dialog.findViewById(R.id.bt_reject);


        btAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.ResultDialogSendSecure(true);
                dialog.dismiss();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.ResultDialogSendSecure(false);
                dialog.dismiss();
            }
        });




        dialog.show();

    }
}
