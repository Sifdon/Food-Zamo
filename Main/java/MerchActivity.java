package com.user.foodzamo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.GetCashback.ListRestr;

public class MerchActivity extends Activity {
Button main,home_deliveries,cashback,cashback_history,track_order;
    ProgressDialog progressDialog;
   String message;
    Button home_delivery_availability;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "setflag";
    public static final String flag = "flag";
    public static final String restname = "restname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merch);

        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(flag, "success");
        editor.putString(restname,message);


        editor.commit();
        main=(Button)findViewById(R.id.main_app_layout);
        home_deliveries=(Button)findViewById(R.id.home_delivery_orders);
        cashback=(Button)findViewById(R.id.credit_cashback_layout);
        cashback_history=(Button)findViewById(R.id.cashback_history);
        track_order=(Button)findViewById(R.id.track_order);
        home_delivery_availability=(Button)findViewById(R.id.delivery_availability);

        home_delivery_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getApplicationContext(), HomeDeliveryAvailability.class);
                i.putExtra("key", message);
                startActivity(i);*/
                Toast.makeText(getApplicationContext(),"Not allowed!",Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog=new ProgressDialog(this);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MerchActivity.this,WelcomeActivity.class));
            }
        });

        track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getApplicationContext(), TrackingOrder.class);
                i.putExtra("key", message);
                startActivity(i);*/
                Toast.makeText(getApplicationContext(),"Not allowed",Toast.LENGTH_SHORT).show();
            }
        });
        cashback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(message.equals("BBQHut")||message.equals("Uzbekk")||message.equals("Albaek")||message.equals("Thadka"))
                {
                    Toast.makeText(getApplicationContext(),"Not Permitted",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MerchActivity.this, EnterBill.class);
                i.putExtra("order", message);
                startActivity(i);
            }
        });

        cashback_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(message.equals("BBQHut")||message.equals("Uzbekk")||message.equals("Albaek")||message.equals("Thadka")||message.equals("Lucky")
                        ||message.equals("SaiBhoj")||message.equals("SaiFruitSake")||message.equals("Makkhan")||message.equals("Ajanta")||message.equals("Chopal Square"))
                {
                    Toast.makeText(getApplicationContext(),"Not Permitted",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Intent i = new Intent(MerchActivity.this, CashbackHistory.class);
                    i.putExtra("cashback", message);
                    startActivity(i);
                }
            }
        });
        //punjabi chilli
        if(message.equals("Punjabi Chilli"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The Punjabi Chilli/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });

        }

        //food court
        if(message.equals("The City FoodCourt"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The City FoodCourt/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("The ManSingh"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The ManSingh/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Shri South Express"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Shri South Express/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("The Yellow Chilli"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The Yellow Chilli/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Cooks FastFood and Bakery"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Cooks FastFood/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Moti Mahal"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Moti Mahal/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Volga Restuarant"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Volga/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Bawarchi Xpress"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Bawarchi Xpress/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Bamboo Restuarant"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Bamboo/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Virasat Restuarant"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Virasat/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("7 Spice Restuarant"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/7 Spice/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Shri Bhukkads"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Bhukkads/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Pari FoodZone"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Pari FoodZone/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Foodz Inn"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Foodz Inn/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Zayka Restuarant"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Zayka/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        if(message.equals("Mejbaan"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Mejbaan/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Chawla Square"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chawla Square/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("New Mazbaan"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/New Mazbaan/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Chopal Chawla"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chopal Chawla/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("FoodDessert"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodDessert/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("FoodFactory"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodFactory/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("BBQHut"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/BBQHut/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("IndianMeal"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/IndianMeal/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("RoyalView"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/RoyalView/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Uzbekk"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Uzbekk/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Albaek"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Albaek/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Thadka"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Thadka/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Hakeems"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Hakeems/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("LuckyChicken"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/LuckyChicken/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Pavitra"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Pavitra/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("SaiBhoj"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiBhoj/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("SaiFruitSake"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiFruitSake/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("MakkhanBhoj"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/MakkhanBhoj/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }
        else if(message.equals("Ajanta"))
        {
            home_deliveries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetOn()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressDialog.setMessage("Loading orders! Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Ajanta/address");
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", s);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                            Intent i = new Intent(MerchActivity.this, MerchantHomeDelivery.class);
                            i.putExtra("order", "Database error!!!");
                            startActivity(i);
                        }
                    });
                }
            });
        }




    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Are you sure ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory( Intent.CATEGORY_HOME );
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
}
