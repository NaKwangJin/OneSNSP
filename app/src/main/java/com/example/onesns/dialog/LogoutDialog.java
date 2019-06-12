package com.example.onesns.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onesns.LoadingActivity;
import com.example.onesns.LoginActivity;
import com.example.onesns.R;

public class LogoutDialog extends Dialog {

    private String name;
    private Context cont;

    private Button yes;
    private Button no;

    public LogoutDialog(Context cont,String name){
        super(cont);
        this.name = name;
        this.cont = cont;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_logout_dialog);

        yes = (Button)findViewById(R.id.yesBtn);
        no = (Button)findViewById(R.id.noBtn);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cont, LoadingActivity.class);
                cont.startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });


    }
}
