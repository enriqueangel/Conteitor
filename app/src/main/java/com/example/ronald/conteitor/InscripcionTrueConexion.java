package com.example.ronald.conteitor;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InscripcionTrueConexion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InscripcionTrueConexion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InscripcionTrueConexion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button BotonREgistrarse;
    Activity Actividad;
    EditText EDTCorreo;
    EditText EDTNOMBRE;
    EditText EDTAPELLIDO;




    private OnFragmentInteractionListener mListener;

    public InscripcionTrueConexion() {
        // Required empty public constructor
    }



    public static boolean validateEmail(String email) {

        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);

        String  x = Boolean.toString( matcher.matches() );

        return matcher.matches();

    }


    public int ValidarDATOS(String Email,String Nombre, String Apellido){


       // Log.d("TAG",Nombre );

        if( !validateEmail( Email )){
           return 1;
        }

        if (Nombre.length() < 1){
            return 2;
        }

        if (Apellido.length() < 1){
            return 3;
        }


        return 0;
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


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InscripcionTrueConexion.
     */
    // TODO: Rename and change types and number of parameters
    public static InscripcionTrueConexion newInstance(String param1, String param2) {
        InscripcionTrueConexion fragment = new InscripcionTrueConexion();
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
        final View view  = inflater.inflate(R.layout.fragment_inscripcion_true_conexion, container, false);


        BotonREgistrarse = (Button)view.findViewById( R.id.buttonRegistrarse );
        EDTCorreo = (EditText) getActivity().findViewById( R.id.editTextCorreoregistrer );
        EDTNOMBRE = (EditText) getActivity().findViewById( R.id.editTextNombres ) ;
        EDTAPELLIDO = (EditText) getActivity().findViewById( R.id.editTextApellidos ) ;

        BotonREgistrarse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Actividad = getActivity();


                int ValidacionDATOS = ValidarDATOS( EDTCorreo.getText().toString(),EDTNOMBRE.getText().toString(),EDTAPELLIDO.getText().toString() );


                switch (ValidacionDATOS){

                    case 1:
                        Toast ErrorCorreo = Toast.makeText(Actividad, "Verifique el correo ", Toast.LENGTH_SHORT);
                        ErrorCorreo.show();
                        break;
                    case 2:
                        Toast ErrorNombre = Toast.makeText(Actividad, "Verifique el nombre", Toast.LENGTH_SHORT);
                        ErrorNombre.show();
                        break;

                    case 3:
                        Toast ErrorApellido = Toast.makeText(Actividad, "Verifique el apellido", Toast.LENGTH_SHORT);
                        ErrorApellido.show();
                        break;

                    case 0:

                        if(ProbarConex()){

                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            String URL = getString( R.string.URL );
                            URL = URL+"/ConteitorAPP/inscripcion.php?Correo="+EDTCorreo.getText().toString();
                            URL = URL+"&Clave=123&Nombres="+EDTNOMBRE.getText().toString();
                            URL = URL+"&Apellidos="+EDTAPELLIDO.getText().toString();




                            StringRequest stringRequest = new StringRequest( Request.Method.GET, URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {




                                    if(response.length() > 40){


                                        MensajeErrorServidor(1);


                                    }else {



                                        switch (response){

                                            case "Acceso invalido":
                                                MensajeAccesoinvalido(1);
                                                break;
                                            case "Correo ya existe":

                                                InscripcionRecuperarContra LoginRecuperar = new InscripcionRecuperarContra();
                                                FragmentTransaction transicion = getFragmentManager().beginTransaction();
                                                transicion.replace(R.id.ContenedorInscripcion,LoginRecuperar);
                                                transicion.commit();

                                                break;
                                            case "IStrue":

                                                InscripcionIntroducirContra inscribirContra = new InscripcionIntroducirContra();
                                                FragmentTransaction transicion1 = getFragmentManager().beginTransaction();
                                                transicion1.replace(R.id.ContenedorInscripcion,inscribirContra);
                                                transicion1.commit();
                                                break;

                                            case "Acceso invalido a los datos":
                                                MensajeAccesoinvalido(2);
                                                break;






                                        }




                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    MensajeErrorServidor(0);


                                }
                            } );

                            queue.add(stringRequest);


                        }else{
                            MensajeNoConexion();
                            InscripcionFalseConexion Loginfalse = new InscripcionFalseConexion();
                            FragmentTransaction transicion = getFragmentManager().beginTransaction();
                            transicion.replace(R.id.ContenedorInscripcion,Loginfalse);
                            transicion.commit();
                        }

                        break;


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
