package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.user.foodzamo.OrderFood.FragmentOne.AndroidListAdapter;
import com.user.foodzamo.OrderFood.FragmentOne.DataCollection;
import com.user.foodzamo.OrderFood.FragmentOne.ListSeparateAdapter;
import com.user.foodzamo.R;

import java.util.ArrayList;


public class Shahi extends Activity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";

    private AndroidListAdapter list_adapter;
    private ListView lv_android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change_this
        setContentView(R.layout.activity_shahi);

    }
}



