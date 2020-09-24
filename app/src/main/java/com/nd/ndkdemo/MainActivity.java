package com.nd.ndkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        Log.e("TAG", CipherUtil.desEncrypt2("NetDragon1", "12345678", "12345678", Charset.forName("utf-8"), "---"));
//        try {
//            String aesEnc = AESUtil.encrypt("NetDragon", "1234567812345678", "1234567812345678");
//            Log.e("TAG", aesEnc);
//            String aesDec = AESUtil.decrypt(aesEnc, "1234567812345678", "1234567812345678");
//            Log.e("TAG", aesDec);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
