package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

public class KakaoSessionCallback implements ISessionCallback {
    private Context cont;
    public KakaoSessionCallback(Context cont){
        this.cont = cont;
    }
    @Override
    public void onSessionOpened() {
        requestMe();
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e("SessionCallback::",exception.getMessage());
    }

    public void requestMe(){
        UserManagement.getInstance().requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("SessionCallback::",errorResult.getErrorMessage());
            }

            // If user not kakao member //
            @Override
            public void onNotSignedUp() {

            }
            // On User successed to Kakao login //
            @Override
            public void onSuccess(UserProfile result) {
                // Move to Main Activity //
                Intent i = new Intent(cont,MainActivity.class);
                cont.startActivity(i);
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                super.onFailure(errorResult);
            }
        });
    }
}
