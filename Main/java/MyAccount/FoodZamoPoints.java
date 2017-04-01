package com.user.foodzamo.MyAccount;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.R;

import static android.R.attr.password;

public class FoodZamoPoints extends AppCompatActivity {
TextView points;
    Button redeem_points;
    private DatabaseReference db;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String foodzamo_points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_zamo_points);

        points=(TextView)findViewById(R.id.points);
        redeem_points=(Button)findViewById(R.id.redeem_points);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        String uid=firebaseAuth.getCurrentUser().getUid();
        String short_id="";

        for(int i=0;i<5;i++)
        {
            char c=uid.charAt(i);
            short_id=short_id+c;
        }
        progressDialog.setMessage("Loading.. Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Users/"+short_id+"/11FoodZamoPoints");
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                foodzamo_points=s;
                points.setText("Your FoodZamo Points: "+s);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        redeem_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fz_points= Integer.parseInt(foodzamo_points);

                if(fz_points<1000)
                {
                    Toast.makeText(getApplicationContext(),"Minimum points should be 1000!",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

                }
            }
        });
    }
}
