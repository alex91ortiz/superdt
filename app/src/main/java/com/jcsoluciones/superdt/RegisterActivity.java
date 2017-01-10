package com.jcsoluciones.superdt;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements RegisterEmailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportFragmentManager().beginTransaction().add(R.id.register_content,new RegisterEmailFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }
}
