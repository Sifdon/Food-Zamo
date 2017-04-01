package com.user.foodzamo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CashbackHistory extends AppCompatActivity {
TextView cashback_data;
    String message;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashback_history);

        Bundle b = getIntent().getExtras();
        message = b.getString("cashback", "");
        cashback_data=(TextView)findViewById(R.id.cashback_data);
        progressDialog=new ProgressDialog(this);

        DatabaseReference referenceFromUrl=null;
        progressDialog.setMessage("Loading cashback history...Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        if(message.equals("The ManSingh"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The ManSingh/details");
        if(message.equals("The City FoodCourt"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The City FoodCourt/details");
        if(message.equals("Shri South Express"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Shri South Express/details");
        if(message.equals("Punjabi Chilli"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The Punjabi Chilli/details");
        if(message.equals("Cooks FastFood and Bakery"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Cooks FastFood/details");
        if(message.equals("Volga Restuarant"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Volga Restuarant/details");
        if(message.equals("Bamboo Restuarant"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Bamboo Restuarant/details");
        if(message.equals("Bawarchi Xpress"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Bawarchi Xpress/details");
        if(message.equals("Virasat Restuarant"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Virasat Restuarant/details");
        if(message.equals("7 Spice Restuarant"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/7 Spice Restuarant/details");
        if(message.equals("Shri Bhukkads"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Shri Bhukkads/details");
        if(message.equals("Pari FoodZone"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Pari FoodZone/details");
        if(message.equals("Foodz Inn"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Foodz Inn/details");
        if(message.equals("Zayka Restuarant"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Zayka Restuarant/details");
        if(message.equals("Mejbaan"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Mejbaan/details");
        if(message.equals("Chawla Square"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Chawla Square/details");
        if(message.equals("New Mazbaan"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/New Mazbaan/details");
        if(message.equals("Chopal Square"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Chopal Square/details");
        if(message.equals("FoodDessert"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/FoodDessert/details");
        if(message.equals("FoodFactory"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/FoodFactory/details");
        if(message.equals("IndianMeal"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/IndianMeal/details");
        if(message.equals("RoyalView"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/RoyalView/details");
        if(message.equals("Hakeems"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Hakeems/details");
        if(message.equals("WildChefHouse"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/WildChefHouse/details");
        if(message.equals("MughalDarbar"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/MughalDarbar/details");
        if(message.equals("Heavens"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Heavens/details");
        if(message.equals("Demo"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Demo/details");
        if(message.equals("Pavitra"))
            referenceFromUrl = FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Pavitra/details");





        referenceFromUrl.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
              cashback_data.setText(s);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
                cashback_data.setText("Error.. Please contact the admin");
                progressDialog.dismiss();
            }
        });


    }
}
