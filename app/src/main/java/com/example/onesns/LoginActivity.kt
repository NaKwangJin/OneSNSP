package com.example.onesns

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.kakao.auth.AuthType
import com.kakao.auth.Session

import java.util.Arrays

class LoginActivity : AppCompatActivity() {
    private var gotoRegisterBtn: Button? = null
    private var gotoLoginBtn: Button? = null
    private var loginIDBox: EditText? = null
    private var loginPWBox: EditText? = null
    private var cont: Context? = null
    private var kakaoLoginBtn: ImageButton? = null
    private var facebookLoginBtn: LoginButton? = null
    private var autoLoginCheckBox: CheckBox? = null

    private var facebookLoginCallback: FacebookLoginCallback? = null
    private var facebookCallbackManager: CallbackManager? = null

    private fun InitComponents() {
        gotoRegisterBtn = findViewById<View>(R.id.regbtn) as Button
        gotoLoginBtn = findViewById<View>(R.id.loginbtn) as Button
        loginIDBox = findViewById<View>(R.id.LoginIDBox) as EditText
        loginPWBox = findViewById<View>(R.id.LoginPWBox) as EditText
        kakaoLoginBtn = findViewById<View>(R.id.btn_kakao_login) as ImageButton
        facebookLoginBtn = findViewById<View>(R.id.facebookLoginButton) as LoginButton
        autoLoginCheckBox = findViewById<View>(R.id.autoLoginCheck) as CheckBox
    }

    private fun InitLoginAPIs() {
        facebookCallbackManager = CallbackManager.Factory.create()
        facebookLoginCallback = FacebookLoginCallback(cont)
        facebookLoginBtn!!.setReadPermissions(Arrays.asList("public_profile", "email"))
        facebookLoginBtn!!.registerCallback(facebookCallbackManager, facebookLoginCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cont = this

        Log.e("DebugKeyHash", EncryptionEncoder.getHashKey(cont))

        InitComponents()
        InitLoginAPIs()

        val pref = cont!!.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE)
        autoLoginCheckBox!!.isChecked = pref.getBoolean("isAutoLoginChecked", false)

        autoLoginCheckBox!!.setOnCheckedChangeListener { buttonView, isChecked ->
            val pref = cont!!.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE)
            val prefEdit = pref.edit()

            prefEdit.putBoolean("isAutoLoginChecked", isChecked)
            prefEdit.commit()
        }

        gotoRegisterBtn!!.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

        gotoLoginBtn!!.setOnClickListener {
            // Using REST API Manager For Using User Login DB//
            val restmng = RESTManager(cont)
            restmng.setURL("http://fght7100.dothome.co.kr/profile.php")
            restmng.setMethod("GET")
            restmng.putArgument("mode", "login")
            //restmng.putArgument("id",loginIDBox.getText().toString());
            restmng.putArgument("id", EncryptionEncoder.encryptBase64(loginIDBox!!.text.toString()))
            //restmng.putArgument("pw",loginPWBox.getText().toString());
            restmng.putArgument("pw", EncryptionEncoder.encryptMD5(loginPWBox!!.text.toString()))
            restmng.execute()
            //                Intent i = new Intent(cont,MainActivity.class);
            //                startActivity(i);
            //                finish();
        }

        kakaoLoginBtn!!.setOnClickListener {
            val session = com.kakao.auth.Session.getCurrentSession()
            session.addCallback(KakaoSessionCallback(cont))
            session.open(AuthType.KAKAO_LOGIN_ALL, this@LoginActivity)
        }

        if (autoLoginCheckBox!!.isChecked) {
            // Get UserSessionInfo //
            val sessionpref = cont!!.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            // Using REST API Manager For Using User Login DB//
            val restmng = RESTManager(cont)
            restmng.setURL("http://fght7100.dothome.co.kr/profile.php")
            restmng.setMethod("GET")
            restmng.putArgument("mode", "login")
            //restmng.putArgument("id",loginIDBox.getText().toString());
            restmng.putArgument("id", EncryptionEncoder.encryptBase64(sessionpref.getString("UID", "[NULLID]")!!))
            //restmng.putArgument("pw",loginPWBox.getText().toString());
            restmng.putArgument("pw", sessionpref.getString("UPW", "[NULLPW]"))
            restmng.execute()
        }

    }
}
