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

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginUserTrueConexion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginUserTrueConexion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginUserTrueConexion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button BotonIngres;
    Activity Actividad;
    TextView OlvContraseña;
    TextView CrearCuent;
    EditText EDTCorreo;
    EditText EDTContraseña;


    private OnFragmentInteractionListener mListener;

    public LoginUserTrueConexion() {
        // Required empty public constructor
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

    public void MensajeContraseñaIncorrecta(){

        Actividad = getActivity();
        Toast ErrorConexIntern = Toast.makeText(Actividad, "Verifique sus datos ", Toast.LENGTH_SHORT);
        ErrorConexIntern.show();



    }


    public void MensajeUsuarioInexsistente(){

        Actividad = getActivity();
        Toast ErrorConexIntern = Toast.makeText(Actividad, "El usuario no existe ", Toast.LENGTH_SHORT);
        ErrorConexIntern.show();



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
     * @return A new instance of fragment LoginUserTrueConexion.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginUserTrueConexion newInstance(String param1, String param2) {
        LoginUserTrueConexion fragment = new LoginUserTrueConexion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view  = inflater.inflate(R.layout.fragment_login_user_true_conexion, container, false);

        BotonIngres = (Button)view.findViewById( R.id.buttonIngres );
        OlvContraseña = (TextView)view.findViewById( R.id.TextViewOlviContra );
        EDTCorreo = (EditText) getActivity().findViewById( R.id.editTextCorreo );
        EDTContraseña = (EditText) getActivity().findViewById( R.id.editTextContraseña );
        CrearCuent = (TextView) view.findViewById( R.id.TextViewCrerCuenta );


        CrearCuent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ProbarConex()) {

                    Intent intent = new Intent(getActivity(), Inscripcion.class);
                    startActivity(intent);

                }else{
                    MensajeNoConexion();
                    LoginUserFalseConexion Loginfalse = new LoginUserFalseConexion();
                    FragmentTransaction transicion = getFragmentManager().beginTransaction();
                    transicion.replace(R.id.Contenedor,Loginfalse);
                    transicion.commit();

                }


            }
        } );


        OlvContraseña.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ProbarConex()) {

                    Intent intent = new Intent(getActivity(), RecuperarContra.class);
                    startActivity(intent);

                }else{
                    MensajeNoConexion();
                    LoginUserFalseConexion Loginfalse = new LoginUserFalseConexion();
                    FragmentTransaction transicion = getFragmentManager().beginTransaction();
                    transicion.replace(R.id.Contenedor,Loginfalse);
                    transicion.commit();

                }

            }
        } );






        BotonIngres.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (ProbarConex()) {






                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    String URL = getString( R.string.URL );
                    URL = URL+ "/ConteitorAPP/consultarusuario.php?Correo="+EDTCorreo.getText().toString();
                    URL = URL+"&Clave=123&Contrase="+EDTContraseña.getText().toString();



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
                                    case "Correo No existe":
                                        MensajeUsuarioInexsistente();
                                        break;
                                    case "ISTRUE":
                                        Actividad = getActivity();
                                        Toast MensajeBienvenido = Toast.makeText(Actividad, "bienvenido ", Toast.LENGTH_SHORT);
                                        MensajeBienvenido.show();

                                        Intent intent = new Intent(getActivity(), CrearPIN.class);
                                        intent.putExtra( "Correo", EDTCorreo.getText().toString()  );
                                        startActivity(intent);



                                        Actividad.finish();


                                        break;
                                    case "ISFalse":
                                        MensajeContraseñaIncorrecta();
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

                            MensajeErrorServidor(2);


                        }
                    } );

                    queue.add(stringRequest);



                }else{
                    MensajeNoConexion();
                    LoginUserFalseConexion Loginfalse = new LoginUserFalseConexion();
                    FragmentTransaction transicion = getFragmentManager().beginTransaction();
                    transicion.replace(R.id.Contenedor,Loginfalse);
                    transicion.commit();

                }

            }
        } );



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
