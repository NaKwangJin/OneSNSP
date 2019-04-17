package com.example.onesns;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewChatterDialog extends Dialog {
    private String name;
    private Context cont;

    private Button cancelBtn;
    private Button addChatterBtn;
    private EditText userNickInput;
    private EditText profileIDInput;

    public boolean isCancelDismiss = false;

    public String userNickname = "";
    public String profileID = "";

    public NewChatterDialog(Context cont,String name){
        super(cont);
        this.name = name;
        this.cont = cont;
    }

    private void InitComponents(){
        this.cancelBtn = (Button)findViewById(R.id.cancelAddButton);
        this.addChatterBtn = (Button)findViewById(R.id.newChatterAddButton);
        this.userNickInput = (EditText)findViewById(R.id.nicknameInput);
        this.profileIDInput = (EditText)findViewById(R.id.profileIDInput);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.newchatterdialog);

        InitComponents();

        this.addChatterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNickname = userNickInput.getText().toString();
                profileID = profileIDInput.getText().toString();
                isCancelDismiss = false;
                dismiss();
            }
        });

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelDismiss = true;
                dismiss();
            }
        });
    }
}
