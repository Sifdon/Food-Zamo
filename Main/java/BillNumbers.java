package com.user.foodzamo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BillNumbers extends AppCompatActivity {
TextView bill_count;
    Button check;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    ProgressDialog progressDialog;
    Button home_delivery,home_delivery_availability, phone_numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_numbers);


        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        bill_count=(TextView)findViewById(R.id.display_bill_count);
        check=(Button) findViewById(R.id.button_check);
        home_delivery=(Button)findViewById(R.id.button_home_deliveries);
        home_delivery_availability=(Button)findViewById(R.id.button_home_deliveries_availability);
        phone_numbers=(Button)findViewById(R.id.button_generate_phone_number);


        phone_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),PhoneNumbersGenerate.class));
            }
        });

      home_delivery_availability.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(isInternetOn()==0)
        {
            Toast.makeText(getApplicationContext(),"No internet!",Toast.LENGTH_SHORT).show();
            return;
        }
        finish();
        startActivity(new Intent(getApplicationContext(),ChangeStatus.class));
    }
});

        home_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No internet!",Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
                startActivity(new Intent(getApplicationContext(),ShowDelivery.class));
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No internet!",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Data loading...Please wait!");
                progressDialog.show();
                //progressDialog.setCancelable(false);

                DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/BillNumbers");
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       long x=dataSnapshot.getChildrenCount();
                        String s= String.valueOf(x);
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                        bill_count.setText("Count: "+s);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        check.setText("Some error occured please try again!");
                    }
                });

            }
        });


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
    @Override
    public void onBackPressed()
    {
        finish();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
