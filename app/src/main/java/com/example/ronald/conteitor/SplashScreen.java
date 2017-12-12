package com.example.ronald.conteitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {







                SharedPreferences SP = getSharedPreferences("PIN",MODE_PRIVATE);

                SharedPreferences.Editor editor = SP.edit();

                //editor.clear();

                // editor.remove("Correo");
                // editor.putBoolean("User",true);
                //editor.putString("Correo","ronald-1524@hotmail.com");
                //editor.apply();

                boolean HayUsuario = SP.getBoolean("User",false);

                if (HayUsuario){
                    Intent intent = new Intent(SplashScreen.this, login_pin.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashScreen.this, LoginUser.class);
                    startActivity(intent);
                }


                finish();
            }
        },4000);
    }
}
