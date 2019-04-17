package com.example.onesns;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText birthPicker;
    private Button regOkBtn;
    private Button regCancelBtn;
    private Context cont;

    private void InitComponent(){
        birthPicker = (EditText)findViewById(R.id.birthpicker);
        regOkBtn = (Button)findViewById(R.id.regOkButton);
        regCancelBtn = (Button)findViewById(R.id.regCancelButton);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cont = this;

        InitComponent();

        birthPicker.setFocusableInTouchMode(false);
        birthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDialog = new DatePickerDialog(cont, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthPicker.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                },1900,1,10);
                dateDialog.show();
            }
        });

        regOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RESTManager restmng = new RESTManager(cont);
                restmng.setMethod("GET");
                restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
                restmng.putArgument("mode","reg");
                restmng.putArgument("id","");
                restmng.putArgument("pw","");
                restmng.putArgument("email","");
                restmng.putArgument("bd","");
                finish();
            }
        });

        regCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
