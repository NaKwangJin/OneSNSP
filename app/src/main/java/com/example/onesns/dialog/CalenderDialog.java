package com.example.onesns.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onesns.EncryptionEncoder;
import com.example.onesns.MainContentFragment;
import com.example.onesns.R;
import com.example.onesns.RESTManager;

import com.example.onesns.calendarContentsList.CalenderContents;

public class CalenderDialog extends Dialog {
    private String name;
    private Context cont;
    Intent intent;

    private Button calcAddBtn;
    private EditText calcAddText;
    private Button calclook;
    private String passedDate;
    private Button cancelBtn;



    public CalenderDialog(Context cont, String name, String date) {
        super(cont);
        this.name = name;
        this.cont = cont;
        this.passedDate = date;
    }

    private void InitComponents() {
        this.calcAddBtn = (Button) findViewById(R.id.calcAddBtn);
        this.calcAddText = (EditText) findViewById(R.id.calcAddText);
        this.calclook = (Button) findViewById(R.id.calendarCancle);
        this.cancelBtn = (Button) findViewById(R.id.cancel_btn);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calenderdialog);

        InitComponents();

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cont, "cancelClick", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        this.calclook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cont, "일정보기", Toast.LENGTH_SHORT).show();
                intent = new Intent(cont, CalenderContents.class);
                cont.startActivity(intent);


            }
        });
        this.calcAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calcText = calcAddText.getText().toString();

                RESTManager manager = new RESTManager(cont);
                manager.setURL("http://fght7100.dothome.co.kr/profile.php");
                manager.setMethod("GET");
                manager.putArgument("mode", "addcal");
                manager.putArgument("id", EncryptionEncoder.encryptBase64("rdt419"));
                manager.putArgument("cdate", passedDate);
                manager.putArgument("ctodo", calcText);

                manager.execute();
                Toast.makeText(cont, "일정추가", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });


    }

}
