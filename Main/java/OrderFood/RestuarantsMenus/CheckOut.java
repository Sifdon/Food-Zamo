package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.GetCashback.MessageActivity;
import com.user.foodzamo.MyAccount.MyWallet;
import com.user.foodzamo.OrderFood.FragmentOne.AndroidListAdapter;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.Global;
import com.user.foodzamo.OrderFood.LocCityCenter;
import com.user.foodzamo.OrderFood.LocCooks;
import com.user.foodzamo.OrderFood.LocDDMall;
import com.user.foodzamo.OrderFood.LocFoodzInn;
import com.user.foodzamo.OrderFood.LocGKM;
import com.user.foodzamo.OrderFood.LocMotiMahal;
import com.user.foodzamo.OrderFood.LocParams;
import com.user.foodzamo.OrderFood.LocSpice;
import com.user.foodzamo.OrderFood.LocVolga;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.HomeDeliv;
import com.user.foodzamo.Register.User;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.RestuarantsAbout.ListRestuarants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

public class CheckOut extends AppCompatActivity {
TextView textView;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
String[] data= data=new String[10000];
    String item="";
    TextView item_name;
    TextView text_quantity;
    TextView text_price;
    TextView total_amount,delivery_charges,delivery_address;
Button place_order;
    SharedPreferences sharedpreferences_address;
    public static final String mypreference_address = "saveaddress";
    public static final String name = "name";
    public static final String address= "address";
    public static final String number = "phone_number";
    public static final String area= "area";
//data from server
SharedPreferences sharedpreferences_available;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text="text";
//add_this
int extra_charges=50;
    LocCityCenter locCityCenter;
    LocCooks locCooks;
    LocMotiMahal locMotiMahal;
    LocFoodzInn locFoodzInn;
    LocDDMall locDDMall;
    LocVolga locVolga;
    LocSpice locSpice;
    LocParams locParams;
    LocGKM locGKM;
    String area_details;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    String message;
    final Context context = this;
    //int f=50;
    int count=0;
    String fin="";
    ImageView edit_address;
    Global global;
    String finalMy_address;
    String alert_phone_number="";
    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        locCityCenter=new LocCityCenter();
        locCooks=new LocCooks();
        locMotiMahal=new LocMotiMahal();
        locFoodzInn=new LocFoodzInn();
        locDDMall=new LocDDMall();
        locVolga=new LocVolga();
        locSpice=new LocSpice();
        locParams=new LocParams();
        locGKM=new LocGKM();

        Bundle b = getIntent().getExtras();
       message = b.getString("key", "");

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences_address = getSharedPreferences(mypreference_address,
                Context.MODE_PRIVATE);
        sharedpreferences_available=getSharedPreferences(mypreference_available,Context.MODE_PRIVATE);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        global=new Global();
//above_five();


        //web view
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setVisibility(View.INVISIBLE);
        wv1.setWebViewClient(new MyBrowser());


        item_name=(TextView)findViewById(R.id.item_name);
        text_quantity=(TextView)findViewById(R.id.text_quantity);
        text_price=(TextView)findViewById(R.id.text_price);
        total_amount=(TextView)findViewById(R.id.total_amount);
        delivery_charges=(TextView)findViewById(R.id.delivery_charges);
        delivery_address=(TextView)findViewById(R.id.delivery_address);
        place_order=(Button)findViewById(R.id.place_order);
        edit_address=(ImageView)findViewById(R.id.change_address);
        edit_address.setVisibility(View.INVISIBLE);

        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());

        /*edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alert_dialog);
                dialog.setTitle("Change Address!");
                dialog.show();

                final EditText edit_name=(EditText) dialog.findViewById(R.id.name);
                final   EditText edit_address=(EditText) dialog.findViewById(R.id.address);
                final   EditText edit_number=(EditText) dialog.findViewById(R.id.phone_number);

                //Creating the instance of ArrayAdapter containing list of language names
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getApplicationContext(),R.layout.list_autocomplete,global.products);
                final AutoCompleteTextView autocomplete=(AutoCompleteTextView)dialog.findViewById(R.id.area);

                Resources res = getResources();
                int color = res.getColor(android.R.color.black);
                autocomplete.setText("");
                //autocomplete.setThreshold(20);//will start working from first character
                autocomplete.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

                Button save_address=(Button)dialog.findViewById(R.id.save_address);

                edit_name.setText(sharedpreferences_address.getString(name,""));
                edit_address.setText(sharedpreferences_address.getString(address,""));
                edit_number.setText(sharedpreferences_address.getString(number,""));


                save_address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(autocomplete.length()==0)
                        {
                            Toast.makeText(getApplicationContext(),"Please Enter Your Area",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String check=autocomplete.getText().toString();
                        int flag=0;
                        for(int i=0;i<global.products.length;i++)
                        {
                            if(check.equals(global.products[i]))
                            {
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            Toast.makeText(getApplicationContext(),"Area mismatched with available ones!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SharedPreferences.Editor editor = sharedpreferences_address.edit();
                        editor.putString(name, edit_name.getText().toString());
                        editor.putString(address, edit_address.getText().toString());
                        editor.putString(number, edit_number.getText().toString());
                        editor.putString(area, autocomplete.getText().toString());

                        editor.commit();
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Address Saved",Toast.LENGTH_SHORT).show();

                        String id ="";
                        String t=firebaseAuth.getCurrentUser().getUid();
                        for(int i=0;i<5;i++)
                        {
                            char z=t.charAt(i);
                            id=id+z;
                        }
                        String my_address="Customer Id:  "+id+"\nCustomer Details:\n\n";
                        my_address=my_address+"Name: "+sharedpreferences_address.getString(name,"")+"\n";
                        my_address=my_address+"Address: "+sharedpreferences_address.getString(address,"")+"\n";
                        my_address=my_address+"Phone Number: "+sharedpreferences_address.getString(number,"")+"\n";
                        my_address=my_address+"Area, "+sharedpreferences_address.getString(area,"")+", Gwalior"+"\n";
                        finalMy_address = my_address;
                        my_address=my_address+"Delivery from: "+message+"\n";
                        delivery_address.setText(my_address);

                    }
                });
            }
        });*/




        //add_this
        area_details=sharedpreferences_address.getString(area,"");
        if(message.equals("Albaek")||message.equals("Chawla Square")||message.equals("Chopal Chawla")||
                message.equals("FoodFactory")||message.equals("IndianMeal")||message.equals("Mejbaan")||
                message.equals("Thadka")||message.equals("Pavitra")
                )
        {
            for(int i=0;i<locCityCenter.below_five.length;i++)
            {
                if(area_details.equals(locCityCenter.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("FoodDessert")||message.equals("Uzbekk")||message.equals("BBQHut"))
        {
            for(int i=0;i<locParams.below_five.length;i++)
            {
                if(area_details.equals(locParams.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("Cooks FastFood"))
        {
            for(int i=0;i<locCooks.below_five.length;i++)
            {
                if(area_details.equals(locCooks.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("Bawarchi Xpress")||message.equals("SaiBhoj")||message.equals("MakkhanBhoj")||message.equals("SaiFruitSake"))
        {
            for(int i=0;i<locMotiMahal.below_five.length;i++)
            {
                if(area_details.equals(locMotiMahal.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("LuckyChicken")||message.equals("Shri South Express"))
        {
            for(int i=0;i<locGKM.below_five.length;i++)
            {
                if(area_details.equals(locGKM.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("Moti Mahal"))
        {
            for(int i=0;i<locMotiMahal.below_five.length;i++)
            {
                if(area_details.equals(locMotiMahal.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("Foodz Inn"))
        {
            for(int i=0;i<locFoodzInn.below_five.length;i++)
            {
                if(area_details.equals(locFoodzInn.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("7 Spice")||message.equals("New Mazbaan")||message.equals("MughalDarbar"))
        {
            for(int i=0;i<locSpice.below_five.length;i++)
            {
                if(area_details.equals(locSpice.below_five[i]))
                   below_five();
            }
        }
        if(message.equals("Pari FoodZone")||message.equals("Zayka"))
        {
            for(int i=0;i<locDDMall.below_five.length;i++)
            {
                if(area_details.equals(locDDMall.below_five[i]))
                    below_five();
            }
        }
        if(message.equals("Virasat")||message.equals("The Punjabi Chilli")||message.equals("Volga"))
        {
            for(int i=0;i<locVolga.below_five.length;i++)
            {
                if(area_details.equals(locVolga.below_five[i]))
                    below_five();
            }
        }
if(message.equals("Hakeems"))
        {
            int service=0;
            service= Integer.parseInt(sharedpreferences.getString(price,""));
            extra_charges=service*15;
            extra_charges=extra_charges/100;
            delivery_charges.setText("Service Tax+ Vat Tax: Rs. "+extra_charges+"/-");
            extra_charges= extra_charges+Integer.parseInt(sharedpreferences.getString(price,""));
        }
        else if(message.equals("RoyalView"))
{
    int service=0;
    service= Integer.parseInt(sharedpreferences.getString(price,""));
    extra_charges=service*11;
    extra_charges=extra_charges/100;
    delivery_charges.setText("Service Tax+ Vat Tax: Rs. "+extra_charges+"/-");
    extra_charges= extra_charges+Integer.parseInt(sharedpreferences.getString(price,""));
}
        else{
    delivery_charges.setText("Delivery Charges(depends on distance): Rs. "+extra_charges+"/-");
    extra_charges= extra_charges+Integer.parseInt(sharedpreferences.getString(price,""));
        }

        total_amount.setText("Total Amount: Rs. "+extra_charges+"/-");

        for(int i=0;i<10000;i++)
        {
            data[i]="";
        }
        String s=sharedpreferences.getString(Name,"");
s=s+"$";
        //textView=(TextView)findViewById(R.id.order_list);
int p=0;
        for(int i=1;i<s.length();)
        {
            int x=0;
            if(i>=s.length())
                break;
            if(s.charAt(i)=='$')
            {
                i++;
            }
            for(int j=i;j<s.length();j++)
            {
                char c=s.charAt(j);
                if(c=='$') {
                    i=j;
                    p++;
                    break;
                }
               data[p]=data[p]+c;
            }
        }
        //for(int i=0;i<p;i++)
        //Toast.makeText(getApplicationContext(),data[i],Toast.LENGTH_LONG).show();
        //textView.setText(sharedpreferences.getString(Name,""));
        final String[] items=new String[1000];
        String[] cost=new String[1000];
        final String[] quantity=new String[1000];
        for(int i=0;i<1000;i++)
        {
            items[i]="";
            cost[i]="";
            quantity[i]="";
        }
        //Arrays.sort(data);
        ArrayList<String> names = new ArrayList<String>();
        for(int i=0;i<p;i++)
            names.add(data[i]);

        //sorted arrays
        Collections.sort(names);

for(int i=0;i<p;i++)
    data[i]=names.get(i);

        //for(int i=0;i<p;i++)
           //item=item+data[i]+"\n";
        //item_name.setText(item);

        String x="";
        int c=1;
        for(int i=0;i<p-1;i++)
        {
            if(data[i].equals(data[i+1]))
            {
                c++;
            }
            else if(!data[i].equals(data[i+1]))
            {
              //x=x+data[i]+String.valueOf(c);
                items[count]=data[i];
                quantity[count]= String.valueOf(c);
                count++;
                c=1;
            }
        }

       //x=x+data[p-1]+String.valueOf(c);
        /*if(data[p-2].equals(data[p-1]))
        {
            c++;
        }*/
        items[count]=data[p-1];
        quantity[count]= String.valueOf(c);
        count++;
String s1="",s2="";

        for(int i=0;i<count;i++)
        {
          // s1=s1+items[i]+"\n";
            s2=s2+"   "+quantity[i]+"\n";
        }

        //item_name.setText(s1);
       text_quantity.setText(s2);

        for(int i=0;i<count;i++)
        {
            for(int j=0;j<items[i].length();j++)
            {
                if(j>=items[i].length())
                    break;
                if(items[i].charAt(j)=='#')
                {
                    j=j+1;
                    while (items[i].charAt(j)!='*') {
                        cost[i] = cost[i] + items[i].charAt(j);
                        j++;
                    }
                    break;
                }
            }
        }
        s1="";
        for(int i=0;i<count;i++)
        {
            s1=s1+quantity[i]+" x "+cost[i]+"\n";
            //s2=s2+"   "+quantity[i]+"\n";
        }
        text_price.setText(s1);
        final String[] arrays=new String[1000];
        for(int i=0;i<1000;i++)
            arrays[i]=" ";
        for(int i=0;i<count;i++)
        {
            for(int j=0;j<items[i].length();j++)
            {
                if(items[i].charAt(j)=='#')
                {

                    break;
                }
                arrays[i]=arrays[i]+items[i].charAt(j);

            }
        }
        s1="";
        for(int i=0;i<count;i++)
            s1=s1+arrays[i]+"\n";

        item_name.setText(s1);

        String id ="";
        String t=firebaseAuth.getCurrentUser().getUid();
        for(int i=0;i<5;i++)
        {
            char z=t.charAt(i);
            id=id+z;
        }
        String my_address="Customer Id:  "+id+"\nCustomer Details:\n\n";
        my_address=my_address+"Name: "+sharedpreferences_address.getString(name,"")+"\n";
        my_address=my_address+"Address: "+sharedpreferences_address.getString(address,"")+"\n";
        my_address=my_address+"Phone Number: "+sharedpreferences_address.getString(number,"")+"\n";
        my_address=my_address+"Area: "+sharedpreferences_address.getString(area,"")+", Gwalior"+"\n";
        finalMy_address = my_address;
        my_address=my_address+"Delivery from: "+message+"\n\n(To change delivery address details please go to My Account--->My Profile)";
        delivery_address.setText(my_address);



        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkconnection() == 0) {
                    Toast.makeText(CheckOut.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(getApplicationContext(),"Permission is granting!",Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(CheckOut.this, new String[]{Manifest.permission.SEND_SMS}, 123);
                    //button.setEnabled(false);
                } else {
                    //Toast.makeText(getApplicationContext(),"Permission granted!",Toast.LENGTH_SHORT).show();


                    //registerUser();
                    progressDialog.setMessage("Placing your order... Please wait!");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    String item_details = "";
                    for (int i = 0; i < count; i++) {
                        item_details = item_details + arrays[i] + " Quantity " + quantity[i] + "\n";
                    }
                    item_details = item_details + "\nTotal Amount =" + String.valueOf(extra_charges) + "\n Order Timings: \n"+Date+"\n\n-------------------------------------------------------------------------\n\n";
                    fin = finalMy_address + "Order Details:\n" + item_details;
                    //write time here
                    progressDialog.setMessage("Placing your order... Please wait!");
                    progressDialog.show();

                    //start here

                    if (message.equals("Shri South Express")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Shri South Express/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("The Punjabi Chilli")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The Punjabi Chilli/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Moti Mahal")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Moti Mahal/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Cooks FastFood")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Cooks FastFood/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Volga")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Volga/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Virasat")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Virasat/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("7 Spice")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/7 Spice/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Bhukkads")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Bhukkads/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Pari FoodZone")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Pari FoodZone/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Foodz Inn")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Foodz Inn/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    }
                    if (message.equals("Zayka")) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Zayka/address");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });

                    } else if (message.equals("Mejbaan") || message.equals("New Mazbaan") || message.equals("Chawla Square") || message.equals("Chopal Chawla") ||
                            message.equals("Bawarchi Xpress") || message.equals("FoodDessert") || message.equals("FoodFactory") ||
                            message.equals("BBQHut") || message.equals("IndianMeal") || message.equals("RoyalView") || message.equals("Uzbekk") ||
                            message.equals("Albaek") || message.equals("Thadka") || message.equals("Hakeems") || message.equals("WildChefHouse") ||
                            message.equals("MughalDarbar") || message.equals("Heavens") || message.equals("ShahiSwad") || message.equals("LuckyChicken") ||
                            message.equals("Demo") || message.equals("SaiBhoj") || message.equals("MakkhanBhoj") || message.equals("Pavitra") || message.equals("Ajanta") || message.equals("SaiFruitSake")) {
                        DatabaseReference ref = null;
                        if (message.equals("Mejbaan")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Mejbaan/address");
                        } else if (message.equals("New Mazbaan")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/New Mazbaan/address");
                        } else if (message.equals("Chawla Square")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chawla Square/address");
                        } else if (message.equals("Chopal Chawla")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chopal Chawla/address");
                        } else if (message.equals("Bawarchi Xpress")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Bawarchi Xpress/address");
                        } else if (message.equals("FoodDessert")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodDessert/address");
                        } else if (message.equals("FoodFactory")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodFactory/address");
                        } else if (message.equals("BBQHut")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/BBQHut/address");
                        } else if (message.equals("IndianMeal")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/IndianMeal/address");
                        } else if (message.equals("RoyalView")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/RoyalView/address");
                        } else if (message.equals("Uzbekk")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Uzbekk/address");
                        } else if (message.equals("Albaek")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Albaek/address");
                        } else if (message.equals("Thadka")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Thadka/address");
                        } else if (message.equals("Hakeems")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Hakeems/address");
                        } else if (message.equals("WildChefHouse")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/WildChefHouse/address");
                        } else if (message.equals("LuckyChicken")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/LuckyChicken/address");
                        } else if (message.equals("SaiBhoj")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiBhoj/address");
                        } else if (message.equals("SaiFruitSake")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiFruitSake/address");
                        } else if (message.equals("MakkhanBhoj")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/MakkhanBhoj/address");
                        } else if (message.equals("Ajanta")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Ajanta/address");
                        } else if (message.equals("Pavitra")) {
                            ref = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Pavitra/address");
                        }


                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = (String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin = fin + s;
                                writeNewUser(fin);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });
                    }
                    //DatabaseReference current_data = db.child(s);

                }
            }
        });


    }
    private void writeNewUser(String name) {
        HomeDeliv user = new HomeDeliv(name);

        if(message.equals("The ManSingh")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("The ManSingh");
        }
        if(message.equals("The City FoodCourt")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("The City FoodCourt");
        }
        if(message.equals("Shri South Express")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Shri South Express");
        }
        if(message.equals("The Punjabi Chilli")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("The Punjabi Chilli");
        }
        if(message.equals("The Yellow Chilli")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("The Yellow Chilli");
        }
        if(message.equals("Moti Mahal")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Moti Mahal");
        }
        if(message.equals("Cooks FastFood")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Cooks FastFood");
        }
        if(message.equals("Volga")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Volga");
        }
        if(message.equals("Bamboo")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Bamboo");
        }
        if(message.equals("Virasat")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Virasat");
        }
        if(message.equals("7 Spice")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("7 Spice");
        }
        if(message.equals("Bhukkads")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Bhukkads");
        }
        if(message.equals("Pari FoodZone")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Pari FoodZone");
        }
        if(message.equals("Foodz Inn")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Foodz Inn");
        }
        if(message.equals("Zayka")) {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Zayka");
        }
        if(message.equals("Mejbaan"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Mejbaan");
        }
        else if(message.equals("New Mazbaan")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("New Mazbaan");
        }
        else if(message.equals("Chawla Square")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Chawla Square");
        }
        else if(message.equals("Chopal Chawla")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Chopal Chawla");
        }
        else if(message.equals("Bawarchi Xpress")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Bawarchi Xpress");
        }
        else if(message.equals("FoodDessert")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("FoodDessert");
        }
        else if(message.equals("FoodFactory")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("FoodFactory");
        }
        else if(message.equals("BBQHut")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("BBQHut");
        }
        else if(message.equals("IndianMeal")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("IndianMeal");
        }
        else if(message.equals("RoyalView")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("RoyalView");
        }
        else if(message.equals("Uzbekk")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Uzbekk");
        }
        else if(message.equals("Albaek")){
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Albaek");
        }
        else if(message.equals("Thadka"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Thadka");

        }
        else if(message.equals("Hakeems"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Hakeems");
        }
        else if(message.equals("WildChefHouse"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("WildChefHouse");
        }
        else if(message.equals("MughalDarbar"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("MughalDarbar");
        }
        else if(message.equals("Heavens"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Heavens");
        }
        else if(message.equals("ShahiSwad"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("ShahiSwad");
        }
        else if(message.equals("LuckyChicken"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("LuckyChicken");
        }
        else if(message.equals("Demo"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Demo");
        }
        else if(message.equals("SaiBhoj"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("SaiBhoj");
        }
        else if(message.equals("SaiFruitSake"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("SaiFruitSake");
        }
        else if(message.equals("MakkhanBhoj"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("MakkhanBhoj");
        }
        else if(message.equals("Ajanta"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Ajanta");
        }
        else if(message.equals("Pavitra"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("HomeDelivery").child("Pavitra");
        }

        db.setValue(user);
        //Toast.makeText(CheckOut.this,"Success",Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        sendSms();

        //startActivity(new Intent(CheckOut.this,OrderMessage.class));

    }

    public int checkconnection()
    {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
            return 1;
            //tvstatus.setText("Network Available");
        }
        else
        {
            // Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
            return 0;
            //tvstatus.setText("Network Not Available");
        }
    }
    public void sendSms()
    {
        //Uri uri = Uri.parse("smsto:" + edt_phoneNo);
        //Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

String num=sharedpreferences_available.getString(text,"");
        String decode_num="";
        for(int i=0;i<10;i++)
        {
            char c=num.charAt(i);
            decode_num=decode_num+c;
        }
        /*SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(
                decode_num,
                null,
                "Order has been Placed to "+message+" !",
                null,
                null);*/

        //textView.setText("SMS sent");
        String url = "http://api.infoskysolution.com/SendSMS/sendmsg.php?uname=FDZAMOT&pass=thisisas&send=FDZAMO&dest=919479675817,91"+decode_num+",919425725423&msg=Order has been placed from "+message+". Regards Food Zamo.";

        //wv1.getSettings().setLoadsImagesAutomatically(true);
        // wv1.getSettings().setJavaScriptEnabled(true);
        //wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);
        Intent i = new Intent(getApplicationContext(),OrderMessage.class);
        i.putExtra("key", message);
        startActivity(i);


    }
    public  void above_five()
    {
        int front=0,back=0;
        String msg=sharedpreferences_available.getString(text,"");
        for(int i=0;i<msg.length();i++)
        {
            char c=msg.charAt(i);
            if(c=='%')
            {
                front=i+1;
                break;
            }
        }
        for(int i=msg.length()-1;i>=0;i--)
        {
            char c=msg.charAt(i);
            if(c=='%')
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
        extra_charges= Integer.parseInt(val);
    }
    public  void below_five()
    {
        int front=0,back=0;
        String msg=sharedpreferences_available.getString(text,"");
        for(int i=0;i<msg.length();i++)
        {
            char c=msg.charAt(i);
            if(c=='*')
            {
                front=i+1;
                break;
            }
        }
        for(int i=msg.length()-1;i>=0;i--)
        {
            char c=msg.charAt(i);
            if(c=='*')
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
       extra_charges= Integer.parseInt(val);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(getApplicationContext(),"Permission has been granted!",Toast.LENGTH_SHORT).show();
                //textView.setText("You can send SMS!");
                //button.setEnabled(true);
            } else {
                finish();
                startActivity(new Intent(getApplicationContext(), SelectRestuarant.class));
                // Toast.makeText(getApplicationContext(),"Request Cancelled!",Toast.LENGTH_SHORT).show();
                //textView.setText("You can not send SMS!");
                //button.setEnabled(false);
            }
        }
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
