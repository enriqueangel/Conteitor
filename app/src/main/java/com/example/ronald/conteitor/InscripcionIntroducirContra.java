package com.example.ronald.conteitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InscripcionIntroducirContra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InscripcionIntroducirContra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InscripcionIntroducirContra extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText Contrase;
    EditText VerfContrase;
    Button InscribirseContr;
    Activity Actividad;
    EditText EDTCorreo;
    EditText EDTNOMBRE;
    EditText EDTAPELLIDO;
    private LoginUser cx= new LoginUser();


    private OnFragmentInteractionListener mListener;

    public InscripcionIntroducirContra() {
        // Required empty public constructor
    }

    public void MensajeNoConexion(){

        Actividad = getActivity();
        Toast ErrorConexIntern = Toast.makeText(Actividad, "No hay internet ", Toast.LENGTH_SHORT);
        ErrorConexIntern.show();



    }

    public void MensajeAccesoinvalido(int op){

        if (op == 1){

            Actividad = getActivity();
            Toast ErrorConexIntern = Toast.makeText(Actividad, "Datos invalidos. Verifique los datos ", Toast.LENGTH_SHORT);
            ErrorConexIntern.show();


        }else{

            Actividad = getActivity();
            Toast ErrorConexIntern = Toast.makeText(Actividad, "Acceso a los datos invalido ", Toast.LENGTH_SHORT);
            ErrorConexIntern.show();

        }




    }


    public void MensajeErrorServidor(int op){

        Actividad = getActivity();



        if (op == 1){
            Toast ErrorConexIntern = Toast.makeText(Actividad, "Error con la BD ", Toast.LENGTH_SHORT);
            ErrorConexIntern.show();
        }else{
            Toast ErrorConexIntern = Toast.makeText(Actividad, "Error con el servidor ", Toast.LENGTH_SHORT);
            ErrorConexIntern.show();
        }

    }


    public boolean ProbarConex(){

        Actividad = getActivity();
        ConnectivityManager connectivityManager = (ConnectivityManager)Actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }


    }


    public static boolean ValidarContra(String Contrase) {

        String PATTERN_Contra = "^[\\w-]+$";

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_Contra);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(Contrase);


        int tam = Contrase.length();

        return  tam < 40  && tam > 5 && matcher.matches();


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InscripcionIntroducirContra.
     */
    // TODO: Rename and change types and number of parameters
    public static InscripcionIntroducirContra newInstance(String param1, String param2) {
        InscripcionIntroducirContra fragment = new InscripcionIntroducirContra();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_inscripcion_introducir_contra, container, false );

        InscribirseContr = (Button) view.findViewById( R.id.ButtonInscribirsecontr ) ;
        VerfContrase = (EditText) view.findViewById( R.id.editTextContraseñainscripverif ) ;
        Contrase = (EditText) view.findViewById( R.id.editTextContraseñainscrip) ;
        EDTCorreo = (EditText) getActivity().findViewById( R.id.editTextCorreoregistrer );
        EDTNOMBRE = (EditText) getActivity().findViewById( R.id.editTextNombres ) ;
        EDTAPELLIDO = (EditText) getActivity().findViewById( R.id.editTextApellidos ) ;


        InscribirseContr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(ValidarContra(Contrase.getText().toString())){


                    if(ProbarConex()){


                        if (Contrase.getText().toString().equals( VerfContrase.getText().toString() ) ){


                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            String URL = getString( R.string.URL );
                            URL = URL+"/ConteitorAPP/inscripcion2.php?Correo="+EDTCorreo.getText().toString();
                            URL = URL+"&Clave=123&Nombres="+EDTNOMBRE.getText().toString();
                            URL = URL+"&Apellidos="+EDTAPELLIDO.getText().toString();
                            URL = URL+"&Contrase="+Contrase.getText().toString();


                            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            if(response.length() > 40){


                                                MensajeErrorServidor(1);


                                            }else {


                                                switch (response){
                                                    case "Acceso invalido":
                                                        MensajeAccesoinvalido(1);
                                                        break;

                                                    case "listo":



                                                        Intent intent = new Intent(getActivity(), CrearPIN.class);
                                                        intent.putExtra( "Correo", EDTCorreo.getText().toString()  );
                                                        startActivity(intent);

                                                        cx.fa.finish();

                                                        Actividad.finish();


                                                        break;


                                                }


                                            }


                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    MensajeErrorServidor(0);
                                }
                            });

                            queue.add(stringRequest);


                        }else{

                            Actividad = getActivity();
                            Toast ErrorConexIntern = Toast.makeText(Actividad, "Las contraseñas no coinciden ", Toast.LENGTH_SHORT);
                            ErrorConexIntern.show();



                        }






                    }else{
                        MensajeNoConexion();
                        InscripcionFalseConexion Loginfalse = new InscripcionFalseConexion();
                        FragmentTransaction transicion = getFragmentManager().beginTransaction();
                        transicion.replace(R.id.ContenedorInscripcion,Loginfalse);
                        transicion.commit();
                    }


                }else{

                    Actividad = getActivity();
                    Toast ErrorContraseña = Toast.makeText(Actividad, "La contraseña no tiene el formato adecuado ", Toast.LENGTH_SHORT);
                    ErrorContraseña.show();
                    Toast ErrorContra = Toast.makeText(Actividad, "La contraseña solo puede contener letras y numeros, debe contener entre 5 y 40 caracteres ", Toast.LENGTH_LONG);
                    ErrorContra.show();

                }




            }
        } );


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
