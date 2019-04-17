package com.example.onesns;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalenderDialog extends Dialog {
    private String name;
    private Context cont;

    private Button cancelBtn;

    public CalenderDialog(Context cont,String name){
        super(cont);
        this.name = name;
        this.cont = cont;
    }

    private void InitComponents(){
        this.cancelBtn = (Button)findViewById(R.id.calendarCancle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.calenderdialog);

        InitComponents();

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
