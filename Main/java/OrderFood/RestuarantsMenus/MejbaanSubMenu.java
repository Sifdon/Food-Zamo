package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.LocCityCenter;
import com.user.foodzamo.OrderFood.SaveAddress;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.R;

public class MejbaanSubMenu extends Activity {
    ListView list;
    String[] web = {
            "Restaurant Special",
            "Roti",
            "Rice & Noodles(Veg)",
            "Non-Veg Delights",
            "Lamb",
            "Rice & Noodles(Non-Veg)",
            "South Indian",
            "Sandwiches",
            "Snacks",
            "Vegetarian Soups",
            "Non Vegetarian Soups",
            "Tandoori Khazana",
            "Veg Starters",
            "Non-Veg Starters",
            "Veg Curries",
            "Non-Veg Curries",
            "Dal",
            "Raita",
            "Rice",
            "Papad",
            "Ala-Carte Breakfast",
            "Continental Flavours"
    } ;
    TextView amount;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
    Button proceed_button;
    //add_this
    LocCityCenter locCityCenter;
    SharedPreferences sharedpreferences_address;
    public static final String mypreference_address = "saveaddress";
    public static final String name = "name";
    public static final String address= "address";
    public static final String number = "phone_number";
    public static final String area= "area";
    int minimum_cost=500;
    String area_details;
    int x;
    //upto_here
    //newly_added
    int position_item;
    ProgressDialog progressDialog;
    //upto_here
    //adding_new
    SharedPreferences sharedpreferences_available;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text="text";
    //upto_here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change_this
        setContentView(R.layout.activity_mejbaan_sub_menu);

//newly_added
        progressDialog=new ProgressDialog(this);
        //add_this
        locCityCenter=new LocCityCenter();

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        sharedpreferences_address = getSharedPreferences(mypreference_address,
                Context.MODE_PRIVATE);

        area_details=sharedpreferences_address.getString(area,"");
        //newly_added
        sharedpreferences_available=getSharedPreferences(mypreference_available,Context.MODE_PRIVATE);
        above_five();
        for(int i=0;i<locCityCenter.below_five.length;i++)
        {
            if(area_details.equals(locCityCenter.below_five[i]))
                below_five();
        }
//upto_here

        amount=(TextView)findViewById(R.id.total_amount);
        proceed_button=(Button)findViewById(R.id.proceed_button);
        CustomList adapter = new
                CustomList(MejbaanSubMenu.this, web);
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
                //newly_added
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"Internet Connection Lost!",Toast.LENGTH_SHORT).show();
                    return;
                }
                position_item=position;
                progressDialog.setMessage("Items are loading...Please wait!");
                progressDialog.show();
                progressDialog.setCancelable(false);

                String[] url={
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/AARestaurantSpecial",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Roti",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/RiceNoodles",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/NonVegetarianDelights",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Lamb",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/RiceNoodlesNonVeg",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/SouthIndian",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Sandwiches",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Snacks",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/VegetarianSoups",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/NonVegetarianSoups",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/TandooriKhazana",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/VegetarianStarters",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/NonVegetarianStarters",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/VegetarianCurries",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/NonVegCurries",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Dal",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Raita",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Rice",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/Papad",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/AlaCarteBreakfast",
                        "https://foodzamo-80ed4.firebaseio.com/Menus/Mejbaan/ContinentalFlavours",

                };
                DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                        getReferenceFromUrl(url[position]);
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String s=(String) dataSnapshot.getValue();
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                        //textView.setText(s);
                        progressDialog.dismiss();
                        if((s.length()<5))
                        {
                            get_alert();
                        }
                        else {
                            Intent i = new Intent(MejbaanSubMenu.this, Mejbaan.class);
                            //String s=String.valueOf(position);
                            s = String.valueOf(position_item) + s;
                            i.putExtra("key", s);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // textView.setText("Some error occured please try again!");
                    }
                });

                //upto_here
            }
        });

        //add_this
        if (sharedpreferences.contains(Name)) {
            //name.setText(sharedpreferences.getString(Name, ""));
            amount.setText("Total Amount: Rs. "+sharedpreferences.getString(price,"")+"/-\nMinimum Order must be Rs."+minimum_cost);
        }
        else
        {
            editor.putString(Name,"");
            editor.putString(price,"0");
            editor.commit();
            amount.setText("Total Amount: Rs. "+sharedpreferences.getString(price,"")+"/-\nMinimum Order must be Rs."+minimum_cost);
        }
//upto_here

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=sharedpreferences.getString(price,"");
                int c= Integer.parseInt(x);
                if(c==0)
                {
                    Toast.makeText(getApplicationContext(),"Your Cart is Empty!",Toast.LENGTH_SHORT).show();
                }
                //add_this
                else if(c<minimum_cost)
                {
                    Toast.makeText(getApplicationContext(),"Minimum order should be Rs."+minimum_cost+"/-",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MejbaanSubMenu.this,CheckOut.class);
                    //change_this
                    i.putExtra("key", "Mejbaan");
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
    public final int isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return 1;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return 0;
        }
        return 0;
    }
    public  void above_five()
    {
        int front=0,back=0;
        String msg=sharedpreferences_available.getString(text,"");
        for(int i=0;i<msg.length();i++)
        {
            char c=msg.charAt(i);
            if(c=='#')
            {
                front=i+1;
                break;
            }
        }
        for(int i=msg.length()-1;i>=0;i--)
        {
            char c=msg.charAt(i);
            if(c=='#')
            {
                back=i-1;
                break;
            }
        }
        String val="";
        for(int i=front;i<=back;i++)
        {
            char c=msg.charAt(i);
            val=val+c;
        }
        minimum_cost= Integer.parseInt(val);
    }
    public  void below_five()
    {
        int front=0,back=0;
        String msg=sharedpreferences_available.getString(text,"");
        for(int i=0;i<msg.length();i++)
        {
            char c=msg.charAt(i);
            if(c=='$')
            {
                front=i+1;
                break;
            }
        }
        for(int i=msg.length()-1;i>=0;i--)
        {
            char c=msg.charAt(i);
            if(c=='$')
            {
                back=i-1;
                break;
            }
        }
        String val="";
        for(int i=front;i<=back;i++)
        {
            char c=msg.charAt(i);
            val=val+c;
        }
        minimum_cost= Integer.parseInt(val);
    }
    public void get_alert()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                MejbaanSubMenu.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("These category items are not available today!Please check for other items.");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.not_available);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}


