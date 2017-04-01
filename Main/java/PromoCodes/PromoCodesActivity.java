package com.user.foodzamo.PromoCodes;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.user.foodzamo.Offers.OffersToday;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.OffersClass;
import com.user.foodzamo.Register.PromoCodesClass;

public class PromoCodesActivity extends AppCompatActivity {
TextView movies_text,shopping_text,coffee_text,others_text,hairsalon_text;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String[] data=new String[100];
    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_codes);

        firebaseAuth=FirebaseAuth.getInstance();
        String user_id=firebaseAuth.getCurrentUser().getUid();
        String short_id="";
        for(int i=0;i<5;i++)
        {
            char c=user_id.charAt(i);
            short_id=short_id+c;
        }
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(short_id);
        progressDialog=new ProgressDialog(this);


        movies_text=(TextView)findViewById(R.id.movies_promocodes);
        shopping_text=(TextView)findViewById(R.id.shopping_promocodes);
        coffee_text=(TextView)findViewById(R.id.coffee_promocodes);
        others_text=(TextView)findViewById(R.id.other_promocodes);
        hairsalon_text=(TextView)findViewById(R.id.hairsaloons_promocodes);





    }
    @Override
    public void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading Promocodes...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PromoCodesClass post = dataSnapshot.getValue(PromoCodesClass.class);
                // [START_EXCLUDE]

                movies_text.setText(post._movies_promocodes);
                shopping_text.setText(post._shopping_promocodes);
                coffee_text.setText(post._coffee_promocodes);
                others_text.setText(post._other_promocodes);
                hairsalon_text.setText(post._hair_promocodes);

                progressDialog.dismiss();

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(PromoCodesActivity.this, "Failed to load promocodes. Please try again.",
                        Toast.LENGTH_SHORT).show();

                movies_text.setText("Error");
                shopping_text.setText("Error");
                coffee_text.setText("Error");
                others_text.setText("Error");

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
}
