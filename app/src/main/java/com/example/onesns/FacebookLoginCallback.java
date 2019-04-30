package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {
    private Context cont;

    public FacebookLoginCallback( Context cont ){
        this.cont = cont;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        requestMe(loginResult.getAccessToken());
    }

    @Override
    public void onCancel() {
        Toast.makeText(this.cont,"로그인이 취소 되었습니다.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this.cont,"로그인 중 오류가 발생하였습니다.",Toast.LENGTH_SHORT).show();
    }

    public void requestMe(AccessToken token){
        GraphRequest graphRequest = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Intent i = new Intent(cont,MainActivity.class);
                cont.startActivity(i);
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
}
