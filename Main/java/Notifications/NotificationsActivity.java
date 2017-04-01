package com.user.foodzamo.Notifications;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.ListRestr;
import com.user.foodzamo.MainActivity;
import com.user.foodzamo.MerchActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.SplashScreen;

import org.w3c.dom.Text;

public class NotificationsActivity extends Activity {
TextView textView;
    private FirebaseAuth firebaseAuth;
    ListView listView;
    private DatabaseReference db;
    ProgressDialog progressDialog;
    TextView announcements;
    private static long SLEEP_TIME = 10;    // Sleep for some time
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        textView=(TextView)findViewById(R.id.my_notifications);
        announcements=(TextView)findViewById(R.id.announcements);


        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        String id=firebaseAuth.getCurrentUser().getUid();
        String uid="";
        for(int i=0;i<5;i++)
        {
            char c=id.charAt(i);
            uid=uid+c;
        }


        progressDialog.setMessage("Notifications are loading...Please wait!");
        progressDialog.show();
        //progressDialog.setCancelable(false);

        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+uid+"/1Notifications");
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                textView.setText(s);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                textView.setText("Some error occured please try again!");
            }
        });

        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Announcements");
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                announcements.setText(s);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                textView.setText("Some error occured please try again!");
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), RestuarantsList.class));
    }

}
