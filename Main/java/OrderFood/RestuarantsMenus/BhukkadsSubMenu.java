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
import com.user.foodzamo.OrderFood.SaveAddress;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.R;

public class BhukkadsSubMenu extends Activity {
    ListView list;
    String[] web = {
            "Soups",
            "Starters & Appetizers",
            "Salad & Raita",
            "Quick Bites",
            "Chat Bar",
            "Asian",
            "Indian Main Course",
            "Tawa & Tandoor",
            "Rice",
            "South Indian",
            "Some Healthy Options",
            "Western"
    } ;
    TextView amount;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
    Button proceed_button;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change_this
        setContentView(R.layout.activity_bhukkads_sub_menu);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        amount=(TextView)findViewById(R.id.total_amount);
        proceed_button=(Button)findViewById(R.id.proceed_button);
        CustomList adapter = new
                CustomList(BhukkadsSubMenu.this, web);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //if(position==0)
                // startActivity(new Intent(getApplicationContext(),PunjabiChilli.class));
                //Toast.makeText(getApplicationContext(), "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                //change_this
                Intent i = new Intent(BhukkadsSubMenu.this, Bhukkads.class);
                String s=String.valueOf(position);
                i.putExtra("key", s);
                startActivity(i);
            }
        });

        if (sharedpreferences.contains(Name)) {
            //name.setText(sharedpreferences.getString(Name, ""));
            amount.setText("Total Amount: Rs. "+sharedpreferences.getString(price,"")+"/-");
        }
        else
        {
            editor.putString(Name,"");
            editor.putString(price,"0");
            editor.commit();
        }

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=sharedpreferences.getString(price,"");
                int c= Integer.parseInt(x);
                if(c==0)
                {
                    Toast.makeText(getApplicationContext(),"Your Cart is Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(c<300)
                {
                    Toast.makeText(getApplicationContext(),"Minimum order should be Rs.300/-",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(BhukkadsSubMenu.this,CheckOut.class);
                    //change_this
                    i.putExtra("key", "Bhukkads");
                    startActivity(i);
                    //startActivity(new Intent(getApplicationContext(),CheckOut.class));
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (sharedpreferences.getString(price, "").equals("0")) {
            finish();
            startActivity(new Intent(getApplicationContext(), SelectRestuarant.class));
        }
        else {
            new AlertDialog.Builder(this).setTitle("").setMessage("If you go back your current cart items data will be lost. Do you want to go back?").setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), SelectRestuarant.class));
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }
}
