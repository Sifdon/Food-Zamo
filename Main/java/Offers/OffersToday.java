package com.user.foodzamo.Offers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.OffersClass;
import com.user.foodzamo.Register.UserProfile;
import com.user.foodzamo.Restuarants.RestuarantsList;

public class OffersToday extends Activity {
    private FirebaseAuth firebaseAuth;
TextView daily_offers,mansingh_offers,foodcourt_offers,southexpress_offers,motimahal_offers,
    punjabichilli_offers,yellowchillli_offers,cooks_offers;
    ProgressDialog progressDialog;
    String[] data=new String[100];
    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;

TextView volga_offers,bamboo_offers,bawarchi_offers,virasat_offers;
    TextView spice_offers,bhukkads_offers,parifood_offers,foodzinn_offers,zayka_offers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_today);

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Offers");
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        daily_offers=(TextView)findViewById(R.id.daily_offers);
        mansingh_offers=(TextView)findViewById(R.id.mansingh_offers);
        foodcourt_offers=(TextView)findViewById(R.id.foodcourt_offers);
        southexpress_offers=(TextView)findViewById(R.id.southexpress_offers);
        motimahal_offers=(TextView)findViewById(R.id.motmahal_offers);
        punjabichilli_offers=(TextView)findViewById(R.id.punjabichilli_offers);
        yellowchillli_offers=(TextView)findViewById(R.id.yellowchilli_offers);
        cooks_offers=(TextView)findViewById(R.id.cooks_offers);
        volga_offers=(TextView)findViewById(R.id.volgo_offers);
        bamboo_offers=(TextView)findViewById(R.id.bamboo_offers);
        bawarchi_offers=(TextView)findViewById(R.id.bawarchi_offers);
        virasat_offers=(TextView)findViewById(R.id.virasat_offers);
        spice_offers=(TextView)findViewById(R.id.spice_offers);
        bhukkads_offers=(TextView)findViewById(R.id.bhukkads_offers);
        parifood_offers=(TextView)findViewById(R.id.parifood_offers);
        foodzinn_offers=(TextView)findViewById(R.id.foodzinn_offers);
        zayka_offers=(TextView)findViewById(R.id.zayka_offers);


        //daily offers

    }
    @Override
    public void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading Offers...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                OffersClass post = dataSnapshot.getValue(OffersClass.class);
                // [START_EXCLUDE]
                daily_offers.setText(post.AADailyOffers);
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
                zayka_offers.setText(post.Zayka);


                progressDialog.dismiss();

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(OffersToday.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                daily_offers.setText("Error occurred please try again..");
                progressDialog.dismiss();
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
        startActivity(new Intent(OffersToday.this, RestuarantsList.class));
    }
}
