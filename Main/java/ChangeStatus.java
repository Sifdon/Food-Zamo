package com.user.foodzamo;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeStatus extends AppCompatActivity {
Button b1,b2;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);


        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("HomeDeliveryAvailable");
        b1=(Button)findViewById(R.id.button_availabe);
        b2=(Button)findViewById(R.id.button_closed);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No internet connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("ChawlaSquare").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("ChopalChawla").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Cooks").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("FoodDessert").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("FoodFactory").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("IndianMeal").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                //db.child("Mejbaan").setValue("");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Moti Mahal").setValue("8989739122$500$*50*#700#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("RoyalView").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("SaiBhoj").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("SaiFruitSake").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Shri SouthExpress").setValue("8989739122$300$*0*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("The Punjabi Chilli").setValue("8989739122$300$*20*#300#%40%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Virasat").setValue("8989739122$500$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");

                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Albaek").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("BBQHut").setValue("8989739122$400$*20*#700#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Hakeems").setValue("8989739122$400$*20*#700#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Mejbaan").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Thadka").setValue("8989739122$300$*20*#500#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Volga").setValue("8989739122$400$*20*#700#%50%Thanks for ordering. Please wait for a verification call to confirm your order.\n(Payment mode Cash on Delivery.)\nUpdate Food Zamo app for better services and performance.");



                Toast.makeText(getApplicationContext(),"Opened!",Toast.LENGTH_LONG).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No internet connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("ChawlaSquare").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("ChopalChawla").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Cooks").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("FoodDessert").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("FoodFactory").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("IndianMeal").setValue("0");
                //db.child("Mejbaan").setValue("");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Moti Mahal").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("RoyalView").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("SaiBhoj").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("SaiFruitSake").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Shri SouthExpress").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("The Punjabi Chilli").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Virasat").setValue("0");


                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Albaek").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("BBQHut").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Hakeems").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Mejbaan").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Thadka").setValue("0");
                db.child("rbtTMPL739PSqKPZUlDARuOIHAm2").child("Volga").setValue("0");

//Volga

                Toast.makeText(getApplicationContext(),"Closed!",Toast.LENGTH_LONG).show();


            }
        });

    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(),BillNumbers.class));
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
