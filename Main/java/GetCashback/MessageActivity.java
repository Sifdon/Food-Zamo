package com.user.foodzamo.GetCashback;

import android.app.ProgressDialog;
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

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    ListView listView;
    private DatabaseReference db;
    ProgressDialog progressDialog;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("BillIDServices");
        textView=(TextView)findViewById(R.id.message_text);
progressDialog.setMessage("Processing your Request...");
progressDialog.show();
DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
        getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/BillIDServices/rbtTMPL739PSqKPZUlDARuOIHAm2/The City FoodCourt");
       databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               String s=(String) dataSnapshot.getValue();
               //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
               textView.setText(s);
               progressDialog.dismiss();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
textView.setText("Some error occured please try again!");
           }
       });

        //listView.setAdapter(firebaseListAdapter);

    }
}
