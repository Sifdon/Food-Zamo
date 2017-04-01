package com.user.foodzamo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.user.foodzamo.WriteComplaint.WriteCompl;

public class TrackingOrder extends Activity {
TextView confirmed_order,we_have_started,delivered_successfully;
    EditText edit_user_id;
    Button send_user;
    String status="";
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_order);

        confirmed_order=(TextView)findViewById(R.id.confirmed_order);
        we_have_started=(TextView)findViewById(R.id.we_have_started);
        delivered_successfully=(TextView)findViewById(R.id.ordered_delivered);
        edit_user_id=(EditText)findViewById(R.id.user_id);
        send_user=(Button)findViewById(R.id.send_to_user);


        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");
        confirmed_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmed_order.setTextColor(Color.RED);
                delivered_successfully.setTextColor(Color.BLACK);
                we_have_started.setTextColor(Color.BLACK);
                status="Your order has been confirmed!";
            }
        });

        we_have_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                we_have_started.setTextColor(Color.RED);
                confirmed_order.setTextColor(Color.BLACK);
                delivered_successfully.setTextColor(Color.BLACK);
                status="Our service boy has started from our restuarant. We will be soon at your destination";
            }
        });

        delivered_successfully.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivered_successfully.setTextColor(Color.RED);
                we_have_started.setTextColor(Color.BLACK);
                confirmed_order.setTextColor(Color.BLACK);
                status="Order Delivered Successfully! ";
            }
        });

        send_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please select some status!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String uid=edit_user_id.getText().toString();
                if(uid.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"User ID is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(edit_user_id.getText().toString().length()==5))
                {
                    Toast.makeText(getApplicationContext(),"User ID is of 5 digits",Toast.LENGTH_SHORT).show();
                    return;
                }
                String url="https://foodzamo-80ed4.firebaseio.com/Users/"+uid;
                DatabaseReference databaseReference1=null;
                databaseReference1 = FirebaseDatabase.getInstance().
                        getReferenceFromUrl(url);
                databaseReference1.child("1Notifications").setValue(status);
                get_alert();

            }
        });
    }

        public void get_alert()
        {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    TrackingOrder.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Success!");
            alertDialog.setCancelable(false);
            // Setting Dialog Message
            alertDialog.setMessage("Message send to the user!.");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.tick);

            // Setting OK Button
            alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    finish();
                    Intent i = new Intent(getApplicationContext(), MerchActivity.class);
                    i.putExtra("key", message);
                    startActivity(i);
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }
    public int isInternetOn() {

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
