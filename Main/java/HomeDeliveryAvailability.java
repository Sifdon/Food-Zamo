package com.user.foodzamo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeDeliveryAvailability extends AppCompatActivity {
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery_availability);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.availability);
        btnDisplay = (Button) findViewById(R.id.change_status);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                String  link="https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Shri SouthExpress";
                if(message.equals("Albaek")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Albaek";
                }
                if(message.equals("RoyalView")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/RoyalView";
                }
                if(message.equals("FoodFactory")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodFactory";
                }
                if(message.equals("Punjabi Chilli")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/The Punjabi Chilli";
                }
                if(message.equals("Volga Restuarant")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Volga";
                }
                if(message.equals("Foodz Inn")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodzInn";
                }
                if(message.equals("Cooks FastFood and Bakery")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Cooks";
                }
                if(message.equals("Shri South Express")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Shri SouthExpress";
                }
                if(message.equals("Moti Mahal")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Moti Mahal";
                }
                if(message.equals("Bawarchi Xpress")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Bawarchi";
                }
                if(message.equals("Virasat Restuarant")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Virasat";
                }
                if(message.equals("7 Spice Restuarant")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/7Spice";
                }
                if(message.equals("Pari FoodZone")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/PariFoodZone";
                }
                if(message.equals("Zayka Restuarant")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Zayka";
                }
                if(message.equals("Mejbaan")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Mejbaan";
                }
                if(message.equals("Chawla Square")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/ChawlaSquare";
                }
                if(message.equals("New Mazbaan")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/NewMajbaan";
                }
                if(message.equals("Chopal Square")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/ChopalChawla";
                }
                if(message.equals("FoodDessert")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodDessert";
                }
                if(message.equals("BBQHut")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/BBQHut";
                }
                if(message.equals("IndianMeal")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/IndianMeal";
                }
                if(message.equals("Uzbekk")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Uzbekk";
                }
                if(message.equals("Thadka")) {
                    link = "https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Thadka";
                }



                db= FirebaseDatabase.getInstance().getReferenceFromUrl(link);
                if(radioSexButton.getText().equals("Available")) {
                    db.setValue("1");
                    Toast.makeText(getApplicationContext(),"Home Delivery Available",Toast.LENGTH_LONG).show();
                }
                else {
                    db.setValue("0");
                    Toast.makeText(getApplicationContext(),"Home Delivery NOT Available",Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(getApplicationContext(), MerchActivity.class);
                i.putExtra("key", message);
                startActivity(i);

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
}
