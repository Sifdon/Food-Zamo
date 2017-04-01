package com.user.foodzamo.OrderFood.RestuarantsMenus;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.user.foodzamo.OrderFood.FragmentOne.AndroidListAdapter;
import com.user.foodzamo.OrderFood.FragmentOne.DataCollection;
import com.user.foodzamo.OrderFood.FragmentOne.ListSeparateAdapter;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.R;


public class PunjabiChilli extends Activity implements OnItemClickListener {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";

    private AndroidListAdapter list_adapter;
    private ListView lv_android;
    //newly_added
    String message;
    String data="";
    //upto_here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punjabi_chilli);

        //newly_added
        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");

        char second=message.charAt(1);
        for(int i=1;i<message.length();i++)
        {
            char c=message.charAt(i);
            data=data+c;
        }
        message= String.valueOf(message.charAt(0));
        if(second=='1'||second=='2'||second=='3'||second=='4'||
                second=='5'||second=='6'||second=='7'||second=='8'||
                second=='9'||second=='0')
        {
            char z=second;
            message=message+z;
        }
        //upto_here
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(price,sharedpreferences.getString(price,""));
        editor.commit();

       /* if (sharedpreferences.contains(Name)) {
            //name.setText(sharedpreferences.getString(Name, ""));
            Toast.makeText(getApplicationContext(),sharedpreferences.getString(Name,"")+"\n"+
                    sharedpreferences.getString(price,""),Toast.LENGTH_LONG).show();
        }*/

//add_data();
        //newly_added
        fetch_data();


        lv_android=(ListView)findViewById(R.id.lv_android);

        list_adapter=new AndroidListAdapter(PunjabiChilli.this, R.layout.list_item,DataCollection.data_arr,sharedpreferences,editor);


        ListSeparateAdapter<DataCollection> listsectionAdapter = new ListSeparateAdapter<DataCollection>(PunjabiChilli.this,
                list_adapter, R.layout.enroll_section_list, R.id.tv_section);

        lv_android.setAdapter(listsectionAdapter);
        lv_android.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub

        DataCollection data_obj=(DataCollection) parent.getAdapter().getItem(position);
        if (data_obj!=null) {

            //Toast.makeText(ListSectionActivity.this, "You have Selected "+data_obj.name, Toast.LENGTH_LONG).show();

        }


    }


    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), PunjabiChilliSubMenu.class));
    }

    //newly_added
    public void fetch_data()
    {



        String s=data;
        String full_item="",full_cost="";
        String[] item=new String[1000];
        String[] rate=new String[1000];

        int star_index=0;
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c=='*')
            {
                star_index=i;
                break;
            }
        }
        for(int i=0;i<star_index;i++)
        {
            char c=s.charAt(i);
            full_item=full_item+c;
        }
        for(int i=star_index+1;i<s.length();i++)
        {
            char c=s.charAt(i);
            full_cost=full_cost+c;
        }

        //fetching items from server
        int[] ind=new int[1000];
        int x=0;
        for(int i=0;i<full_item.length();i++)
        {
            char c=full_item.charAt(i);
            if(c=='$')
            {
                ind[x++]=i;
            }
        }
        int size=0;
        for(int i=0;i<x-1;i++)
        {
            String z="";
            for(int k=ind[i]+1;k<ind[i+1];k++)
            {
                char c=full_item.charAt(k);
                z=z+c;
            }
            item[size++]=z;
        }

//fetching costs from the server
        int[] ind2=new int[1000];
        int x2=0;
        for(int i=0;i<full_cost.length();i++)
        {
            char c=full_cost.charAt(i);
            if(c=='$')
            {
                ind2[x2++]=i;
            }
        }
        int size2=0;
        for(int i=0;i<x2-1;i++)
        {
            String z="";
            for(int k=ind2[i]+1;k<ind2[i+1];k++)
            {
                char c=full_cost.charAt(k);
                z=z+c;
            }
            rate[size2++]=z;
        }

        DataCollection.data_arr=new ArrayList<DataCollection>();

        //"Soups(Veg)",
        if(message.equals("0")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Restaurant Special", R.drawable.punjabi_chilli, rate[i]));
        }

        //"Soups(Veg)",
        if(message.equals("1")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Soups(Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Starters(Veg)",
        if(message.equals("2")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Starters(Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Veg Wraps",
        if(message.equals("3")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Veg Wraps", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Burgers",
        if(message.equals("4")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Burgers", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Sandwiches",
        if(message.equals("5")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Sandwiches", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Momos",
        if(message.equals("6")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Momos", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Noodles",
        if(message.equals("7")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Noodles", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Pizza",
        if(message.equals("8")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Pizza", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Pasta&Macroni",
        if(message.equals("9")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Pasta&Macroni", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Chole Bhature",
        if(message.equals("10")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Chole Bhature", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Platters",
        if(message.equals("11")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Platters", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Combo",
        if(message.equals("12")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i],  "Combo", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Khande De Naal-Naal",
        if(message.equals("13")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Khande De Naal-Naal", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Rice",
        if(message.equals("14")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Rice", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Biryani(Veg)",
        if(message.equals("15")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Biryani(Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"South Indian",
        if(message.equals("16")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "South Indian", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Main Course(Veg)",
        if(message.equals("17")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Main Course(Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Roti",
        if(message.equals("18")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Roti", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Paranthe Di Gali",
        if(message.equals("19")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Paranthe Di Gali", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Pocket Parantha",
        if(message.equals("20")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i],  "Pocket Parantha", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Soups(Non-Veg)",
        if(message.equals("21")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Soups(Non-Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Starters(Non-Veg)",
        if(message.equals("22")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Starters(Non-Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Chopsey",
        if(message.equals("23")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Chopsey", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Burgers(Non-Veg)",
        if(message.equals("24")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Burgers(Non-Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Italian",
        if(message.equals("25")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Italian", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Main Course(Non-Veg)",
        if(message.equals("26")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Main Course(Non-Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
                //"Biryani(Non-Veg)",
        if(message.equals("27")) {
            for (int i = 0; i < size; i++)
                DataCollection.data_arr.add(new DataCollection(item[i], "Biryani(Non-Veg)", R.drawable.punjabi_chilli, rate[i]));
        }
      
        
        
        //Toast.makeText(getApplicationContext(),rate[0],Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),full_item,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),full_cost,Toast.LENGTH_LONG).show();






    }
}

