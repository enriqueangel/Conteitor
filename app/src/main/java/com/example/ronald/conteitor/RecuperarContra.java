package com.example.ronald.conteitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RecuperarContra extends AppCompatActivity implements RecuperarContraTrueConex.OnFragmentInteractionListener, RecuperarContraFalseConex.OnFragmentInteractionListener {

    public void VerifConexion(){

        Toast ErrorConexIntern = Toast.makeText(this, "No hay internet ", Toast.LENGTH_SHORT);


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            RecuperarContraTrueConex LoginTrue = new RecuperarContraTrueConex();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.ContenedorRecuperarContra,LoginTrue);
            transicion.commit();
        }else{
            ErrorConexIntern.show();
            RecuperarContraFalseConex Loginfalse = new RecuperarContraFalseConex();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.ContenedorRecuperarContra,Loginfalse);
            transicion.commit();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recuperar_contra );

        VerifConexion();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
