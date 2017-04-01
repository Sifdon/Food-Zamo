package com.user.foodzamo.WriteComplaint;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.user.foodzamo.GetCashback.EnterBill;
import com.user.foodzamo.GetCashback.MessageActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.Complaint;
import com.user.foodzamo.Register.User;
import com.user.foodzamo.Restuarants.RestuarantsList;

public class WriteCompl extends AppCompatActivity {
EditText write_complaint;
    Button button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //disable keyboard until you press on edit text
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_write_compl);

        write_complaint=(EditText)findViewById(R.id.write_complaint);
        button=(Button)findViewById(R.id.submit_complaint);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Complaints");
        progressDialog=new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        progressDialog.setMessage("Processing your complaint...");
        progressDialog.show();
        if(checkconnection()==0)
        {
            Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        String write=write_complaint.getText().toString();
        if(TextUtils.isEmpty(write))
        {
            Toast.makeText(getApplicationContext(),"Write something before your submit!",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        else
        {
            String s = firebaseAuth.getCurrentUser().getUid();
            String x="";
            for(int i=0;i<5;i++)
            {
                char c=s.charAt(i);
                x=x+c;
            }
            //DatabaseReference current_data = db.child(s);
            writeNewUser(x,write_complaint.getText().toString());
            progressDialog.dismiss();
        }

    }
});
    }
    private void writeNewUser(String userId, String name) {
        Complaint user = new Complaint(name);

        db.child(userId).setValue(user);
        get_alert();
        //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(EnterBill.this,MessageActivity.class));

    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), RestuarantsList.class));
    }
    public int checkconnection()
    {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
            return 1;
            //tvstatus.setText("Network Available");
        }
        else
        {
            // Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
            return 0;
            //tvstatus.setText("Network Not Available");
        }
    }
    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                WriteCompl.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Success!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("We have received your complaint. We will get back to you soon.");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                startActivity(new Intent(getApplicationContext(),RestuarantsList.class));
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
