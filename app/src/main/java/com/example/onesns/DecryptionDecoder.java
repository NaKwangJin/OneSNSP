package com.example.onesns;

import android.util.Base64;

public class DecryptionDecoder {
    public static String decryptBase64( String message ){
        return new String(Base64.decode( message, 0 )); 
    }
}
