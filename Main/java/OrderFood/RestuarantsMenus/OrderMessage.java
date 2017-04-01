package com.user.foodzamo.OrderFood.RestuarantsMenus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.UserProfile;
import com.user.foodzamo.Restuarants.RestuarantsList;

public class OrderMessage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    ListView listView;
    private DatabaseReference db;
    ProgressDialog progressDialog;

    //format "9479675817$300$*20*#500#%50%Thanks for ordering from South Express. Please wait for a phone call to confitm your order."
//adding_new
    SharedPreferences sharedpreferences_available;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text="text";
    //upto_here
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_message);

        textView=(TextView)findViewById(R.id.message_text);
        Bundle b = getIntent().getExtras();
        final String message = b.getString("key", "");
        sharedpreferences_available=getSharedPreferences(mypreference_available, Context.MODE_PRIVATE);
        String msg=sharedpreferences_available.getString(text,"");
        int ind=0;
        for(int i=msg.length()-1;i>=0;i--)
        {
            char c=msg.charAt(i);
            if(c=='%')
            {
                ind=i+1;
                break;
            }
        }
        String cnf_msg="";
        for(int i=ind;i<msg.length();i++)
        {
          char c=msg.charAt(i);
            cnf_msg=cnf_msg+c;
        }
        textView.setText(cnf_msg);
        //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

        /*progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("HomeDeliveryAvailability");

        progressDialog.setMessage("Placing your order... Please wait!");
        progressDialog.setCancelable(false);
        //progressDialog.show();
        DatabaseReference databaseReference1=null;*/
        /*if(message.equals("Albaek"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Albaek");

        }
        if(message.equals("Chawla Square"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/ChawlaSquare");

        }
        if(message.equals("Chopal Chawla"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/ChopalChawla");

        }
        if(message.equals("FoodDessert"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodDessert");

        }
        if(message.equals("FoodFactory"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodFactory");

        }
        if(message.equals("IndianMeal"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/IndianMeal");

        }
        if(message.equals("Mejbaan"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Mejbaan");

        }
        if(message.equals("RoyalView"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/RoyalView");

        }
        if(message.equals("Thadka"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
  getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Thadka");

        }
        if(message.equals("Uzbekk"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Uzbekk");

        }
        if(message.equals("Hakeems"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Hakeems");

        }
        if(message.equals("Pavitra"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Pavitra");

        }
        if(message.equals("Cooks FastFood"))
        {

databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Cooks");

        }
        if(message.equals("Bawarchi Xpress"))
        {
  databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Bawarchi");

        }
        if(message.equals("Shri South Express"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Shri SouthExpress");

        }
        if(message.equals("SaiBhoj"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
  getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/SaiBhoj");

        }
        if(message.equals("Moti Mahal"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
  getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Moti Mahal");
        }
        if(message.equals("Foodz Inn"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodzInn");
        }
        if(message.equals("BBQHut"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/BBQHut");

        }
        if(message.equals("7 Spice"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/7Spice");

        }
        if(message.equals("New Mazbaan"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/NewMajbaan");

        }
        if(message.equals("Zayka"))
        {
databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Zayka");

        }
        if(message.equals("Virasat"))
        {
  databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Virasat");

        }
        if(message.equals("The Punjabi Chilli"))
        {
 databaseReference1 = FirebaseDatabase.getInstance().
 getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/The Punjabi Chilli");

        }
        if(message.equals("Volga"))
        {
   databaseReference1 = FirebaseDatabase.getInstance().
getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailability/rbtTMPL739PSqKPZUlDARuOIHAm2/Volga");

        }


        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                String msg="";
                int ind=0;
                for(int i=s.length()-1;i>=0;i--)
                {
                    char c=s.charAt(i);
                   if(c=='%')
                   {
                       ind=i;
                       break;
                   }
                }
                for (int i=ind+1;i<s.length();i++)
                {
                    char z=s.charAt(i);
                    msg=msg+z;
                }
                textView.setText(msg);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                textView.setText("Some error occured please try again!");
            }
        });*/

        //listView.setAdapter(firebaseListAdapter);


    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), RestuarantsList.class));
    }
}

