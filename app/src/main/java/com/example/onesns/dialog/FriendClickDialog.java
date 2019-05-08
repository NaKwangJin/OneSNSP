package com.example.onesns.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onesns.R;

public class FriendClickDialog extends Dialog {

    private Context cont;
    private String content_text;
    private Button cancel;
    private TextView content;

    public FriendClickDialog(Context context, String name, String content) {
        super(context);
        this.cont = context;
        this.content_text=content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_click_dialog);

        cancel =(Button)findViewById(R.id.cancel_btn);
        content=(TextView)findViewById(R.id.contentText);

        content.setText(content_text);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
