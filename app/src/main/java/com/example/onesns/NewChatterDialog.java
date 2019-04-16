package com.example.onesns;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewChatterDialog extends Dialog {
    private String name;
    private Context cont;

    private Button cancelBtn;

    public NewChatterDialog(Context cont,String name){
        super(cont);
        this.name = name;
        this.cont = cont;
    }

    private void InitComponents(){
        this.cancelBtn = (Button)findViewById(R.id.cancelAddButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.newchatterdialog);

        InitComponents();

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
