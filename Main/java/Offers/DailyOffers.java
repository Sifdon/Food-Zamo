package com.user.foodzamo.Offers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.ConnectivityReceiver;
import com.user.foodzamo.MyApplication;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.OffersClass;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.WriteComplaint.WriteCompl;

public class DailyOffers extends AppCompatActivity {
    ListView list;

    String[] title = {
            "Offers of the Day",
            "Offers @ The City FoodCourt",
            "Offers @ The ManSingh",
            "Offers @ MotiMahal Deluxe Restaurant",
            "Offers @ Hakeems Restaurant",
            "Offers @ Shri SouthExpress",
            "Offers @ Bamboo Restaurant",
            "Offers @ The Food Factory",
            "Offers @ Punjabi Chilli Restaurant",
            "Offers @ Volga Restaurant",
            "Offers @ Virasat, The Heritage",
            "Offers @ Bawarchi Xpress",
            "Offers @ 7 Spice Restaurant",
            "Offers @ Pari FoodZone&Restaurant",
            "Offers @ Zayka Restaurant",
            "Offers @ Shree Bhukkads The Food Lounge",
            "Offers @ Foodz Inn Restaurant",
            "Offers @ Hotel SunBeam, Mejbaan Restaurant",
            "Offers @ Chawla Square Restaurant",
            "Offers @ New Mezbaan Restaurant",
            "Offers @ Cooks Fast Food&Bakery",
            "Offers @ Chopal Chicken",
            "Offers @ Food&Dessert Restaurant",
            "Offers @ BBQ Hut",
            "Offers @ Indian Meal Restaurant",
            "Offers @ Royal View Restaurant",
            "Offers @ Thadka Restaurant",
            "Offers @ Albaek Non-Veg Restaurant",
            "Offers @ Pavitra Restaurant",
            "Offers @ Sai Bhoj Restaurant",
            "Offers @ Sai Fruit Restaurant"



    } ;
    String[] description={
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading...",
            "Loading..."

    };
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_offers);

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Offers");
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        //checkConnection();



    }
    @Override
    public void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading Offers...");
        progressDialog.show();
        //progressDialog.setCancelable(false);
        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                OffersClass post = dataSnapshot.getValue(OffersClass.class);


                // [START_EXCLUDE]
               /* daily_offers.setText(post.AADailyOffers);
                mansingh_offers.setText(post.ManSingh);
                foodcourt_offers.setText(post.FoodCourt);
                southexpress_offers.setText(post.SouthExpress);
                motimahal_offers.setText(post.MotiMahal);
                punjabichilli_offers.setText(post.Punjabi);
                yellowchillli_offers.setText(post.YellowChilli);
                cooks_offers.setText(post.CooksFast);
                volga_offers.setText(post.Volga);
                bamboo_offers.setText(post.Bamboo);
                bawarchi_offers.setText(post.Bawarchi);
                virasat_offers.setText(post.Virasat);
                spice_offers.setText(post.Spice7);
                bhukkads_offers.setText(post.Bhukkads);
                parifood_offers.setText(post.PariFoodZone);
                foodzinn_offers.setText(post.FoodzInn);
                zayka_offers.setText(post.Zayka);*/

                        description[0]=post.AADailyOffers;
                //"Offers of the Day",
                        description[1]=post.FoodCourt;
                //"Offers @ The City FoodCourt",
                        description[2]=post.ManSingh;
                //"Offers @ The ManSingh",
                        description[3]=post.MotiMahal;
                       // "Offers @ MotiMahal Deluxe Restaurant",
                                description[4]=post.Hakeems;
                        //"Offers @ Hakeems Restaurant",
                                description[5]=post.SouthExpress;
                        //"Offers @ Shri SouthExpress",
                                description[6]=post.Bamboo;
                        //"Offers @ Bamboo Restaurant",
                                description[7]=post.FoodFactory;
                        //"Offers @ The Food Factory",
                                description[8]=post.Punjabi;
                        //"Offers @ Punjabi Chilli Restaurant",
                                description[9]=post.Volga;
                       // "Offers @ Volga Restaurant",
                                description[10]=post.Virasat;
                        //"Offers @ Virasat, The Heritage",
                                description[11]=post.Bawarchi;
                        //"Offers @ Bawarchi Xpress",
                                description[12]=post.Spice7;
                        //"Offers @ 7 Spice Restaurant",
                                description[13]=post.PariFoodZone;
                        //"Offers @ Pari FoodZone&Restaurant",
                                description[14]=post.Zayka;
                        //"Offers @ Zayka Restaurant",
                                description[15]=post.Bhukkads;
                       // "Offers @ Shree Bhukkads",
                                description[16]=post.FoodzInn;
                        //"Offers @ Foodz Inn Restaurant",
                                description[17]=post.Mejbaan;
                        ////"Offers @ Mejbaan Restaurant",
                                description[18]=post.ChawlaSquare;
                        //"Offers @ Chawla Square Restaurant",
                                description[19]=post.NewMezbaan;
                //"Offers @ New Mejbaan"
                                description[20]=post.CooksFast;
                       //// "Offers @ Cooks Fast Food&Bakery",
                                description[21]=post.ChopalChicken;
                        //"Offers @ Chopal Chicken",
                                description[22]=post.FoodDessert;
                        //"Offers @ Food&Dessert Restaurant",
                                description[23]=post.BBQHut;
                        //"Offers @ BBQ Hut",
                                description[24]=post.IndianMeal;
                        //"Offers @ Indian Meal Restaurant",
                                description[25]=post.RoyalView;
                        //"Offers @ Royal View Restaurant",
                                description[26]=post.Thadka;
                        //"Offers @ Uzbekk, King of Chicken",
                                description[27]=post.Albaek;
                        //"Offers @ Albaek Chicken",
                description[28]=post.Pavitra;
                //"Offers @ Pavitra Restaurant",
                description[29]=post.SaiBhoj;
                        //"Offers @ Sai Bhoj Restaurant",
                description[30]=post.SaiFruit;
                        //"Offers @ Sai Fruit Restaurant",





                progressDialog.dismiss();
                CustomList adapter = new
                        CustomList(DailyOffers.this, title, description);
                list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(DailyOffers.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                //daily_offers.setText("Error occurred please try again..");
                progressDialog.dismiss();
                CustomList adapter = new
                        CustomList(DailyOffers.this, title, description);
                list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

        // Listen for comments
        //mAdapter = new CommentAdapter(this, mCommentsReference);
        //mCommentsRecycler.setAdapter(mAdapter);
    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(DailyOffers.this, RestuarantsList.class));
    }


    /**
     * Callback will be triggered when there is change in
     * network connection
     */


}
