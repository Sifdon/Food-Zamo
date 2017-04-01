package com.user.foodzamo;

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

public class SaiBhojGroup extends AppCompatActivity {
Button b1,b2,b3;
    ProgressDialog progressDialog;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "setflag";
    public static final String flag = "flag";
    public static final String restname = "restname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sai_bhoj_group);

        progressDialog=new ProgressDialog(this);
        b1=(Button)findViewById(R.id.sai_bhoj);
        b2=(Button)findViewById(R.id.saifruit_rest);
        b3=(Button)findViewById(R.id.chawla_rest);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(flag, "success");
        //editor.putString(restname,message);


        editor.commit();

        b1.setOnClickListener(new View.OnClickListener() {
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
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", s);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //textView.setText("Some error occured please try again!");
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", "Database error!!!");
                        startActivity(i);
                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
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
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", s);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //textView.setText("Some error occured please try again!");
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", "Database error!!!");
                        startActivity(i);
                    }
                });
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
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
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", s);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //textView.setText("Some error occured please try again!");
                        Intent i = new Intent(SaiBhojGroup.this, MerchantHomeDelivery.class);
                        i.putExtra("order", "Database error!!!");
                        startActivity(i);
                    }
                });
            }
        });

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
