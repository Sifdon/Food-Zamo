package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.OrderFood.FragmentOne.AndroidListAdapter;
import com.user.foodzamo.OrderFood.FragmentOne.DataCollection;
import com.user.foodzamo.OrderFood.FragmentOne.ListSeparateAdapter;
import com.user.foodzamo.R;

import java.util.ArrayList;


public class South extends Activity implements AdapterView.OnItemClickListener {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";

    private AndroidListAdapter list_adapter;
    private ListView lv_android;
    private FirebaseAuth firebaseAuth;
    ListView listView;

    String message;
    String data="";
    private DatabaseReference db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change_this
        setContentView(R.layout.activity_south);


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
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

       /* if (sharedpreferences.contains(Name)) {
            //name.setText(sharedpreferences.getString(Name, ""));
            Toast.makeText(getApplicationContext(),sharedpreferences.getString(Name,"")+"\n"+
                    sharedpreferences.getString(price,""),Toast.LENGTH_LONG).show();
        }*/

//add_data();
        snacks();





        lv_android=(ListView)findViewById(R.id.lv_android);

        list_adapter=new AndroidListAdapter(South.this, R.layout.list_item, DataCollection.data_arr,sharedpreferences,editor);


        ListSeparateAdapter<DataCollection> listsectionAdapter = new ListSeparateAdapter<DataCollection>(South.this,
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
        //change_this
        startActivity(new Intent(getApplicationContext(), SouthSubMenu.class));
    }


   public void snacks()
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

       if(message.equals("0")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Restaurant Special", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("1")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Snacks", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("2")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Indian Veg Curry", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("3")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Indian NonVeg Curry", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("4")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Tandoori Veg", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("5")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Tandoori NonVeg", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("6")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Indian NonVeg Starters", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("7")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Chinese Veg Starters", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("8")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Chinese NonVeg Starters", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("9")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Biryani", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("10")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Chinese Veg Fried Rice", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("11")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Chinese NonVeg Fried Rice", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("12")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Noodles", R.drawable.south_express_cover, rate[i]));
       }
       if(message.equals("13")) {
           for (int i = 0; i < size; i++)
               DataCollection.data_arr.add(new DataCollection(item[i], "Roti", R.drawable.south_express_cover, rate[i]));
       }
               //Toast.makeText(getApplicationContext(),rate[0],Toast.LENGTH_LONG).show();
               //Toast.makeText(getApplicationContext(),full_item,Toast.LENGTH_LONG).show();
               //Toast.makeText(getApplicationContext(),full_cost,Toast.LENGTH_LONG).show();





   }


}


