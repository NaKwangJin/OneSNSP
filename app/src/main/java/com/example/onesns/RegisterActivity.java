package com.example.onesns;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText newIDText;
    private EditText newPWText;
    private EditText newEmailText;
    private EditText birthPicker;
    private EditText profilePhotoPicker;
    private Button regOkBtn;
    private Button regCancelBtn;
    private Context cont;

    private void InitComponent(){
        newIDText = (EditText)findViewById(R.id.newIDBox);
        newPWText = (EditText)findViewById(R.id.newPWBox);
        newEmailText = (EditText)findViewById(R.id.newEMailBox);
        birthPicker = (EditText)findViewById(R.id.birthpicker);
        profilePhotoPicker = (EditText)findViewById(R.id.profilePhotoInput);
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
                },2018,1,10);
                dateDialog.show();
            }
        });

        profilePhotoPicker.setFocusableInTouchMode(false);
        profilePhotoPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cont,FileBrowserActivity.class);
                startActivity(i);
            }
        });

        regOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RESTManager restmng = new RESTManager(cont);
                restmng.setMethod("GET");
                restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
                restmng.putArgument("mode","reg");

                if(newIDText.getText().toString().equals("") || newPWText.getText().toString().equals("") ||
                        newEmailText.getText().toString().equals("")){
                    Toast.makeText(cont, "필수정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    restmng.putArgument("id", EncryptionEncoder.encryptBase64(newIDText.getText().toString()));
                    restmng.putArgument("pw", EncryptionEncoder.encryptMD5(newPWText.getText().toString()));
                    restmng.putArgument("email", EncryptionEncoder.encryptBase64(newEmailText.getText().toString()));
                    restmng.putArgument("bd", EncryptionEncoder.encryptBase64(birthPicker.getText().toString()));

                    restmng.execute();
                    finish();
                }
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
