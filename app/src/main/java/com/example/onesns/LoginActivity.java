package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button gotoRegisterBtn;
    private Button gotoLoginBtn;
    private Context cont;

    private void InitComponents(){
        gotoRegisterBtn = (Button)findViewById(R.id.regbtn);
        gotoLoginBtn = (Button)findViewById(R.id.loginbtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cont = this;

        InitComponents();

        gotoRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cont,RegisterActivity.class);
                startActivity(i);
            }
        });

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cont,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
