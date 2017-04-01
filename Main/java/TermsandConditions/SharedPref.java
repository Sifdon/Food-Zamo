package com.user.foodzamo.TermsandConditions;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.user.foodzamo.R;

public class SharedPref extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "saveaddress";
    public static final String name = "name";
    public static final String address= "address";
    public static final String number = "phone_number";
    public static final String area= "area";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(name)) {
            //name.setText(sharedpreferences.getString(Name, ""));
            Toast.makeText(getApplicationContext(),
                    sharedpreferences.getString(name,"")+sharedpreferences.getString(address,"")+
                            sharedpreferences.getString(number,"")+
                            sharedpreferences.getString(area,"")
                    ,Toast.LENGTH_SHORT).show();
        }
    }
}
