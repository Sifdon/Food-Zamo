package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.LocCityCenter;
import com.user.foodzamo.OrderFood.LocMotiMahal;
import com.user.foodzamo.OrderFood.SaveAddress;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.R;

public class ShahiSubMenu extends Activity {

    //upto_here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change_this
        setContentView(R.layout.activity_shahi_sub_menu);



    }
}
