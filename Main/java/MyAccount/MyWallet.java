package com.user.foodzamo.MyAccount;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.JoinUs.JoinNetwork;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.Join;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.WriteComplaint.WriteCompl;

import java.util.Random;


/*
* Created By AndroidBash on 30/07/2016
*/

public class MyWallet extends Activity {
private ImageView imageView;
    String alert_string;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "couponid";
    public static final String couponamount= "couponamount";
    public static final String restuarantname= "restuarantname";
    public static final String number = "number";
   private  TextView textView;
   private TextView available_amount;
    private DatabaseReference db;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Button get_coupon;
    int amt=0;
    EditText amount;
    String message;
    SharedPreferences sharedpreferences_coupon;
    public static final String mypreference_flag_coupon = "setflag";
    public static final String flagcoupon = "0";
    public static final String restname= "restname";
    String coupon_type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

alert_string="Dear customer, please check if there are any old coupons in your account. This coupon will replaces the old one(if exists any)! We are not responsible" +
        " in case if coupons are replaced! If there are no coupons you can freely proceed.";
        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences_coupon = getSharedPreferences(mypreference_flag_coupon,
                Context.MODE_PRIVATE);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Users");
        progressDialog.setMessage("Verifying Your Account Balance...");
        progressDialog.show();
        progressDialog.setCancelable(false);
imageView=(ImageView)findViewById(R.id.cashback_restuarant);
        textView=(TextView)findViewById(R.id.restuarant_name);
        available_amount=(TextView)findViewById(R.id.available_cash);
        get_coupon=(Button)findViewById(R.id.proceed_button);
        amount=(EditText)findViewById(R.id.redeem_cash);





String uid="";
        String x=firebaseAuth.getCurrentUser().getUid();
        for(int i=0;i<5;i++)
        {
            char c=x.charAt(i);
            uid=uid+c;
        }
        if(message.equals("1"))
        {

           imageView.setImageResource(R.drawable.man_singh_cover);
            textView.setText("The ManSingh");
            final DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/The Mansingh Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);
                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });

            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    coupon("The ManSingh","The Mansingh Cashback");
                }
            });
        }
        else if(message.equals("0"))
        {
            imageView.setImageResource(R.drawable.foodcourt_cover);
            textView.setText("The City FoodCourt");
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/FoodCourt Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });

            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("The City FoodCourt","FoodCourt Cashback");
                }
            });
        }
        else if(message.equals("3"))
        {
            imageView.setImageResource(R.drawable.south_express_cover);
            textView.setText("Shri SouthExpress");
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Shri SouthExpress Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);
                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Shri SouthExpress","Shri SouthExpress Cashback");
                }
            });
        }
        else if(message.equals("6"))
        {
            imageView.setImageResource(R.drawable.punjabi_chilli);
            textView.setText("The Punjabi Chilli Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Punjabi Chilli Restuarant Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("The Punjabi Chilli","Punjabi Chilli Restuarant Cashback");
                }
            });
        }
         else if(message.equals("12")){
            imageView.setImageResource(R.drawable.cooks_cover_pic);
            textView.setText("Cooks FastFood and Bakery");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Cooks FastFood and Bakery");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    amt= Integer.parseInt(s);

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Cooks FastFood and Bakery","Cooks FastFood and Bakery");
                }
            });
        }
        else if(message.equals("7")) {
            imageView.setImageResource(R.drawable.volga_cover);
            textView.setText("Volga Restaurant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Volga Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Volga Restuarant","Volga Cashback");
                }
            });
        }
        else if(message.equals("4")) {
            imageView.setImageResource(R.drawable.bamboo_cover_pic);
            textView.setText("Bamboo Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Bamboo Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Bamboo Restuarant","Bamboo Cashback");
                }
            });
        }
        else if(message.equals("9")) {
            imageView.setImageResource(R.drawable.bawarchi_cover_pic);
            textView.setText("Bawarchi Xpress");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Bawarchi Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Bawarchi Xpress","Bawarchi Cashback");
                }
            });
        }
        else if(message.equals("8")) {
            imageView.setImageResource(R.drawable.virasat_cover_pic);
            textView.setText("Virasat");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Virasat Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Virasat Restuarant","Virasat Cashback");
                }
            });
        }
        else if(message.equals("10")) {
            imageView.setImageResource(R.drawable.spice_cover_pic);
            textView.setText("7 Spice Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/7Spice Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("7 Spice Restuarant","7Spice Cashback");
                }
            });
        }
        else if(message.equals("13")) {
            imageView.setImageResource(R.drawable.bhukkads_cover_pic);
            textView.setText("Shree Bhukkads");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Bhukkads Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Shri Bhukkads","Bhukkads Cashback");
                }
            });
        }
        else if(message.equals("11")) {
            imageView.setImageResource(R.drawable.parifoodzone_cover_pic);
            textView.setText("Pari FoodZone & Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/PariFoodZone Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Pari FoodZone & Restuarant","PariFoodZone Cashback");
                }
            });
        }
        else if(message.equals("14")) {
            imageView.setImageResource(R.drawable.foodzin_cover_pic);
            textView.setText("Foodz Inn Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/FoodzInn Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Foodz Inn Restuarant","FoodzInn Cashback");
                }
            });
        }
        else if(message.equals("18")) {
            imageView.setImageResource(R.drawable.zayka_cover_pic);
            textView.setText("Zayka Restuarant");
            textView.setTextSize(20);
            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Zayka Cashback");
            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
            get_coupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkconnection()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    coupon("Zayka Restuarant","Zayka Cashback");
                }
            });
        }
        else
        {
            DatabaseReference databaseReference1=null;
            if(message.equals("15"))
            {
                imageView.setImageResource(R.drawable.south_express_cover);
                textView.setText("Mejbaan Restuarant");
                textView.setTextSize(20);
             databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Mejbaan");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Mejbaan Restuarant","Mejbaan");
                    }
                });
            }
            if(message.equals("16"))
            {
                imageView.setImageResource(R.drawable.chawla_square);
                textView.setText("Chawla Square Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Chawla Square");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Chawla Square Restuarant","Chawla Square");
                    }
                });
            }
            if(message.equals("17"))
            {
                imageView.setImageResource(R.drawable.south_express_cover);
                textView.setText("New Mazbaan Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/New Mazbaan");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("New Mazbaan Restuarant","New Mazbaan");
                    }
                });

            }
            if(message.equals("19"))
            {
                imageView.setImageResource(R.drawable.food_dessert);
                textView.setText("Food & Dessert Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/FoodDessert");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Food and Dessert Restuarant","FoodDessert");
                    }
                });
            }
            if(message.equals("5"))
            {
                imageView.setImageResource(R.drawable.food_factory_cover_pic);
                textView.setText("The Food Factory");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/FoodFactory");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("The Food Factory","FoodFactory");
                    }
                });
            }
            if(message.equals("20"))
            {
                imageView.setImageResource(R.drawable.indian_meal_cover_pic);
                textView.setText("Indian Meal Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/IndianMeal");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Indian Meal Restuarant","IndianMeal");
                    }
                });
            }
            if(message.equals("21"))
            {
                imageView.setImageResource(R.drawable.royalview_cover_pic);
                textView.setText("Royal View Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/RoyalView");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Royal View Restuarant","RoyalView");
                    }
                });
            }
            if(message.equals("2"))
            {
                imageView.setImageResource(R.drawable.hakeems);
                textView.setText("Hakeems Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Hakeems");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Hakeems Restuarant","Hakeems");
                    }
                });
            }
            if(message.equals("22"))
            {
                imageView.setImageResource(R.drawable.pavitra_cover_pic);
                textView.setText("Pavitra Restuarant");
                textView.setTextSize(20);
                databaseReference1=FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/Pavitra");
                get_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection()==0)
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        coupon("Pavitra Restuarant","Pavitra");
                    }
                });
            }


            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String s=(String) dataSnapshot.getValue();
                    amt= Integer.parseInt(s);

                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                    available_amount.setText("Available Amount: Rs. "+s+"/-");
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.setText("Some error occured please try again!");
                }
            });
        }


    }
    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                MyWallet.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Congratulations!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("You have received a foodcoupon. Please check 'My Account->Coupon Codes' to use the Coupon");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                startActivity(new Intent(getApplicationContext(),RestuarantsList.class));
            }
        });

        // Showing Alert Message
        alertDialog.show();
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
public  void coupon(final String rest,final String path)
{


            if(TextUtils.isEmpty(amount.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Enter some amount before you Proceed",Toast.LENGTH_SHORT).show();
                return;

            }
            final String f=amount.getText().toString();
            final int l= Integer.parseInt(f);
            if(l<=0)
            {
                Toast.makeText(getApplicationContext(),"Enter Valid Amount!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(amt>=300)
            {
                if(checkconnection()==0)
                {
                    Toast.makeText(getApplicationContext(),"Enter some amount before you Proceed",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(amt>=l)
                {
                    new AlertDialog.Builder(MyWallet.this).setTitle("").setMessage(alert_string).setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //finish();
                                    //startActivity(new Intent(getApplicationContext(), SelectRestuarant.class));
                                    DatabaseReference referenceFromUrl=null;
                                    progressDialog.setMessage("Generating coupon please wait!...Please wait...");
                                    progressDialog.show();

                                    if(message.equals("0"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences00 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor00 = app_preferences00.edit();

                                        isFirstTime = app_preferences00.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor00.putBoolean("isFirstTime", false);
                                            editor00.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/FoodCourt");
                                    }
                                    if(message.equals("1"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences11 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor11 = app_preferences11.edit();

                                        isFirstTime = app_preferences11.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor11.putBoolean("isFirstTime", false);
                                            editor11.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Mansingh");
                                    }
                                    if(message.equals("2"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences22 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor22 = app_preferences22.edit();

                                        isFirstTime = app_preferences22.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor22.putBoolean("isFirstTime", false);
                                            editor22.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Hakeems");
                                    }
                                    if(message.equals("3"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences33 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor33 = app_preferences33.edit();

                                        isFirstTime = app_preferences33.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor33.putBoolean("isFirstTime", false);
                                            editor33.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/SouthExpress");
                                    }
                                    if(message.equals("4"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences44 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor44 = app_preferences44.edit();

                                        isFirstTime = app_preferences44.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor44.putBoolean("isFirstTime", false);
                                            editor44.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Bamboo");
                                    }
                                    if(message.equals("5"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences55 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor55 = app_preferences55.edit();

                                        isFirstTime = app_preferences55.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor55.putBoolean("isFirstTime", false);
                                            editor55.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/FoodFactory");
                                    }
                                    if(message.equals("6"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences66 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor66 = app_preferences66.edit();

                                        isFirstTime = app_preferences66.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor66.putBoolean("isFirstTime", false);
                                            editor66.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/PunjabiChilli");
                                    }
                                    if(message.equals("7"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences77 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor77 = app_preferences77.edit();

                                        isFirstTime = app_preferences77.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor77.putBoolean("isFirstTime", false);
                                            editor77.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Volga");
                                    }
                                    if(message.equals("8"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences88 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences88.edit();

                                        isFirstTime = app_preferences88.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Virasat");
                                    }
                                    if(message.equals("9"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences99 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences99.edit();

                                        isFirstTime = app_preferences99.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Bawarchi");
                                    }
                                    if(message.equals("10"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences10 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences10.edit();

                                        isFirstTime = app_preferences10.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/7Spice");
                                    }
                                    if(message.equals("11"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences111 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences111.edit();

                                        isFirstTime = app_preferences111.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/PariFoodZone");
                                    }
                                    if(message.equals("12"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences12 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences12.edit();

                                        isFirstTime = app_preferences12.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Cooks");
                                    }
                                    if(message.equals("13"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences13 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences13.edit();

                                        isFirstTime = app_preferences13.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Bhukkads");
                                    }
                                    if(message.equals("14"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences14 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences14.edit();

                                        isFirstTime = app_preferences14.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/FoodzInn");
                                    }
                                    if(message.equals("15"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences15 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences15.edit();

                                        isFirstTime = app_preferences15.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Mejbaan");
                                    }
                                    if(message.equals("16"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences16 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences16.edit();

                                        isFirstTime = app_preferences16.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/ChawlaSquare");
                                    }
                                    if(message.equals("17"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences17 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences17.edit();

                                        isFirstTime = app_preferences17.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/NewMazbaan");
                                    }
                                    if(message.equals("18"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences18 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences18.edit();

                                        isFirstTime = app_preferences18.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Zayka");
                                    }
                                    if(message.equals("19"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences19 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences19.edit();

                                        isFirstTime = app_preferences19.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/FoodDessert");
                                    }
                                    if(message.equals("20"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences20 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences20.edit();

                                        isFirstTime = app_preferences20.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/IndianMeal");
                                    }
                                    if(message.equals("21"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences21 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences21.edit();

                                        isFirstTime = app_preferences21.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/RoyalView");
                                    }
                                    if(message.equals("22"))
                                    {
                                        Boolean isFirstTime;

                                        SharedPreferences app_preferences222 = PreferenceManager
                                                .getDefaultSharedPreferences(MyWallet.this);

                                        SharedPreferences.Editor editor2 = app_preferences222.edit();

                                        isFirstTime = app_preferences222.getBoolean("isFirstTime", true);

                                        if (isFirstTime) {

//implement your first time logic
                                            coupon_type="<---FoodCoupon--->";
                                            editor2.putBoolean("isFirstTime", false);
                                            editor2.commit();

                                        }else{
                                            coupon_type="<---FoodCoupon--->";
//app open directly
                                        }
                                        referenceFromUrl = FirebaseDatabase.getInstance().
                                                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/CouponHistory/Pavitra");
                                    }


                                    referenceFromUrl.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            String s=(String) dataSnapshot.getValue();
                                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                                            //data=data+s;
                                            String uid="";
                                            String x=firebaseAuth.getCurrentUser().getUid();
                                            for(int i=0;i<5;i++)
                                            {
                                                char c=x.charAt(i);
                                                uid=uid+c;
                                            }
                                            String coupon_details="";

                                            coupon_details="\n--------------------------------\n"+coupon_type+"\nUser ID: "+uid+"\n" +
                                                    "Coupon Amount: "+String.valueOf(l)+"\n\n";

                                            coupon_details=coupon_details+s;
                                            writeUser(coupon_details, l,path,rest);
                                            progressDialog.dismiss();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // textView.setText("Some error occured please try again!");
                                        }
                                    });
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            return;
                        }
                    }).show();





                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No sufficient balance to proceed!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Minimum Available balance should be Rs.300/-",Toast.LENGTH_SHORT).show();
                return;
            }

}
    private void writeUser(String details, int l, String path, String rest) {



        if(message.equals("0"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("FoodCourt");

        }
        if(message.equals("1"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Mansingh");
        }
        if(message.equals("2"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Hakeems");

        }
        if(message.equals("3"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("SouthExpress");

        }
        if(message.equals("4"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Bamboo");

        }
        if(message.equals("5"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("FoodFactory");

        }
        if(message.equals("6"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("PunjabiChilli");

        }
        if(message.equals("7"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Volga");

        }
        if(message.equals("8"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Virasat");

        }
        if(message.equals("9"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Bawarchi");

        }
        if(message.equals("10"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("7Spice");

        }
        if(message.equals("11"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("PariFoodZone");

        }
        if(message.equals("12"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Cooks");

        }
        if(message.equals("13"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Bhukkads");

        }
        if(message.equals("14"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("FoodzInn");

        }
        if(message.equals("15"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Mejbaan");

        }
        if(message.equals("16"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("ChawlaSquare");

        }
        if(message.equals("17"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("NewMazbaan");

        }
        if(message.equals("18"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Zayka");

        }
        if(message.equals("19"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("FoodDessert");

        }
        if(message.equals("20"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("IndianMeal");

        }
        if(message.equals("21"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("RoyalView");

        }
        if(message.equals("22"))
        {
            db = FirebaseDatabase.getInstance().getReference().child("CouponHistory").child("Pavitra");

        }

        db.setValue(details);
        //Toast.makeText(CheckOut.this,"Success",Toast.LENGTH_SHORT).show();
        /*progressDialog.dismiss();
        Intent i = new Intent(getApplicationContext(),OrderMessage.class);
        i.putExtra("key", message);
        startActivity(i);*/

        Toast.makeText(getApplicationContext(),"Coupon Generated successfully!",Toast.LENGTH_LONG).show();
        DatabaseReference user_amount= FirebaseDatabase.getInstance().getReference().child("Users");
        String d=firebaseAuth.getCurrentUser().getUid();
        String z="";
        for(int i=0;i<5;i++)
        {
            char c=d.charAt(i);
            z=z+c;
        }
        final DatabaseReference current_data = user_amount.child(z);
        int bal=amt-l;
        String rem= String.valueOf(bal);
        current_data.child(path).setValue(rem);
        Toast.makeText(getApplicationContext(),"Available balance: "+rem,Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(couponamount,String.valueOf(l));
        editor.putString(restuarantname,rest);
        Random rand = new Random();
        long  n = rand.nextInt(9999999) + 1000000;
        editor.putString(number,String.valueOf(n));
        editor.commit();

        get_alert();
        //startActivity(new Intent(JoinNetwork.this,RestuarantsList.class));

    }
}



