package com.jcsoluciones.superdt.AuthAndRegister;

import android.content.Context;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.jcsoluciones.superdt.R;
import com.jcsoluciones.superdt.DataModel.*;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterEmailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterEmailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private OnFragmentInteractionListener mListener;
    private DataAccessByHttp dataAccessHttp;

    public RegisterEmailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterEmailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterEmailFragment newInstance(String param1, String param2) {
        RegisterEmailFragment fragment = new RegisterEmailFragment();
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
        // Set Data Access
        dataAccessHttp= new DataAccessByHttp();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_email, container, false);
        mUsernameView = (EditText) view.findViewById(R.id.username);
        mEmailView = (EditText) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);

        Button mButton = (Button) view.findViewById(R.id.email_sign_up_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFlag();
            }
        });
        return view;
    }

    public void makeFlag(){
        mUsernameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String mUsername = mUsernameView.getText().toString();
        String mEmail = mEmailView.getText().toString();
        String mPassword = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;



        if(TextUtils.isEmpty(mEmail)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!mEmail.contains("@")) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if(TextUtils.isEmpty(mUsername)){
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(mPassword) && !(mPassword.length()>4)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else {

        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            JSONObject token = dataAccessHttp.apiAuth(mPassword,mEmail);

            if(token!=null)
                return true;
            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }

        @Override
        protected void onCancelled() {

        }
    }
}
