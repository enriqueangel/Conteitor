package com.example.ronald.conteitor;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.security.AccessController.getContext;


public class LoginUser extends AppCompatActivity implements LoginUserTrueConexion.OnFragmentInteractionListener ,LoginUserFalseConexion.OnFragmentInteractionListener {

    public static Activity fa;

    public boolean VerifConexion(){

        Toast ErrorConexIntern = Toast.makeText(this, "No hay internet ", Toast.LENGTH_SHORT);


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            LoginUserTrueConexion LoginTrue = new LoginUserTrueConexion();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.Contenedor,LoginTrue);
            transicion.commit();
        }else{
            ErrorConexIntern.show();
            LoginUserFalseConexion Loginfalse = new LoginUserFalseConexion();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.Contenedor,Loginfalse);
            transicion.commit();

        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        fa=this;



        VerifConexion();



        /*
        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobile = connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mWifi.isConnected()) {
                tipoConexion1 = true;
            }
            if (mMobile.isConnected()) {
                tipoConexion2 = true;
            }

            if (tipoConexion1 == true || tipoConexion2 == true) {
                ErrorConexIntern.show();
               }
        }
        else {
        No estas conectado a internet
        }*/




       // ConnectivityManager conectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);


       // NetworkInfo info_wifi = conectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //NetworkInfo info_datos = conectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
