package com.example.ronald.conteitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Inscripcion extends AppCompatActivity implements InscripcionFalseConexion.OnFragmentInteractionListener , InscripcionTrueConexion.OnFragmentInteractionListener, InscripcionRecuperarContra.OnFragmentInteractionListener , InscripcionIntroducirContra.OnFragmentInteractionListener  {


    public void VerifConexion(){

        Toast ErrorConexIntern = Toast.makeText(this, "No hay internet ", Toast.LENGTH_SHORT);


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            InscripcionTrueConexion LoginTrue = new InscripcionTrueConexion();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.ContenedorInscripcion,LoginTrue);
            transicion.commit();
        }else{
            ErrorConexIntern.show();
            InscripcionFalseConexion Loginfalse = new InscripcionFalseConexion();
            FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();
            transicion.replace(R.id.ContenedorInscripcion,Loginfalse);
            transicion.commit();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_inscripcion );

        VerifConexion();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
