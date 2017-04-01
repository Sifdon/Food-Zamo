package com.user.foodzamo.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.user.foodzamo.MainActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Restuarants.RestuarantsList;

import java.util.Map;

public class UserProfile extends AppCompatActivity {
    private ListView listView;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    ProgressDialog progressDialog;
    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    String x="";
    int flag=0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        textView=(TextView)findViewById(R.id.text_user_profile);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("Users");
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Offers");

        String s = firebaseAuth.getCurrentUser().getUid();
        String uid="";
        for(int i=0;i<5;i++)
        {
            char c=s.charAt(i);
            uid=uid+c;
        }
        Toast.makeText(UserProfile.this,uid, Toast.LENGTH_SHORT).show();



       // Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
progressDialog.setMessage("Loading...");
        progressDialog.show();
        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                OffersClass post = dataSnapshot.getValue(OffersClass.class);
                // [START_EXCLUDE]
                textView.setText(post.AADailyOffers);
                progressDialog.dismiss();

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(UserProfile.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                textView.setText("Error");
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
        startActivity(new Intent(getApplicationContext(), RestuarantsList.class));
    }
}
