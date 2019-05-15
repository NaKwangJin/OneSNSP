package com.example.onesns;

import android.util.Base64;

public class DecryptionDecoder {
    public static String decryptBase64( String base64str ){
        return new String(Base64.decode( base64str,0 ));
    }
}
