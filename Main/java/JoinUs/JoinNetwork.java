package com.user.foodzamo.JoinUs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.MainActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.Cashback;
import com.user.foodzamo.Register.Join;
import com.user.foodzamo.Restuarants.RestuarantsList;

public class JoinNetwork extends Activity {
EditText your_name,company_name,phone_number,mail_id,description;
    Button send;
    FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    String uid;
    String data="";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_join_network);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("join_us");
        uid=firebaseAuth.getCurrentUser().getUid();
        your_name=(EditText)findViewById(R.id.your_name);
        company_name=(EditText)findViewById(R.id.company_name);
        phone_number=(EditText)findViewById(R.id.phone_number);
        mail_id=(EditText)findViewById(R.id.mail_id);
        description=(EditText)findViewById(R.id.description_of_your_company);

        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(your_name.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter your name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(company_name.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter your Company name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone_number.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter your phone number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mail_id.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter your Mail Id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(description.getText().toString().length()<100)
                {
                    Toast.makeText(getApplicationContext(),"Minimum description should be 100 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Proceeding your Request....");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String short_id="";
                for(int i=0;i<5;i++)
                {
                    char c=uid.charAt(i);
                    short_id=short_id+c;
                }
                data="\n\nUser ID"+short_id+"\nName:  "+your_name.getText().toString()+"\nCompany Name: "+company_name.getText().toString()+"\nPhone Number: "+
                        phone_number.getText().toString()+"\nMail Id: "+mail_id.getText().toString()+"\nDescription: "+description.getText().toString()+"\n\n";
                DatabaseReference referenceFromUrl=null;

                    referenceFromUrl = FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/join_us/join_us");

                referenceFromUrl.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String s=(String) dataSnapshot.getValue();
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                        data=data+s;
                        writeUser(data);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                       // textView.setText("Some error occured please try again!");
                    }
                });


            }
        });

    }
    private void writeUser(String details) {
        Join user = new Join(details);

            db = FirebaseDatabase.getInstance().getReference().child("join_us");


        db.setValue(user);
        //Toast.makeText(CheckOut.this,"Success",Toast.LENGTH_SHORT).show();
        /*progressDialog.dismiss();
        Intent i = new Intent(getApplicationContext(),OrderMessage.class);
        i.putExtra("key", message);
        startActivity(i);*/
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"We have received your request!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(JoinNetwork.this,RestuarantsList.class));

    }
    public final int isInternetOn() {

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
