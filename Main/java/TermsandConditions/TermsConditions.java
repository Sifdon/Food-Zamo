package com.user.foodzamo.TermsandConditions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.user.foodzamo.R;

public class TermsConditions extends AppCompatActivity {
Button button;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

       // Toast.makeText(TermsConditions.this,"Terms and Conditions",Toast.LENGTH_SHORT).show();
    }
}
