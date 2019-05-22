package com.example.onesns.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onesns.EncryptionEncoder;
import com.example.onesns.R;
import com.example.onesns.RESTManager;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalenderDialog extends Dialog {
    private String name;
    private Context cont;

    private Button calcAddBtn;
    private EditText calcAddText;
    private Button cancelBtn;
    private String passedDate;

    public CalenderDialog(Context cont, String name,String date){
        super(cont);
        this.name = name;
        this.cont = cont;
        this.passedDate = date;
    }

    private void InitComponents(){
        this.calcAddBtn = (Button)findViewById(R.id.calcAddBtn);
        this.calcAddText = (EditText)findViewById(R.id.calcAddText);
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
        this.calcAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calcText = calcAddText.getText().toString();

                RESTManager manager = new RESTManager(cont);
                manager.setURL("http://fght7100.dothome.co.kr/profile.php");
                manager.setMethod("GET");
                manager.putArgument("mode","addcal");
                manager.putArgument("id", EncryptionEncoder.encryptBase64("rdt419"));
                manager.putArgument("cdate",passedDate);
                manager.putArgument("ctodo",calcText);

                manager.execute();

                dismiss();
            }
        });
    }
}
