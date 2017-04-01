package com.user.foodzamo.Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.R;

import java.util.Random;

public class OTPActivity extends Activity {
    SharedPreferences sharedpreferences;
    ProgressDialog progressDialog;
    public static final String mypreference = "savedetails";
    public static final String name_number = "name";
    public static final String mail= "mail";
    public static final String number = "number";
    public static final String rand= "rand";

    EditText edit_number,edit_mail,edit_password;
    Button button;

    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        //web view
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setVisibility(View.INVISIBLE);
        wv1.setWebViewClient(new MyBrowser());

        edit_number=(EditText)findViewById(R.id.edit_text_name);
        edit_mail=(EditText)findViewById(R.id.edit_text_email);
        edit_password=(EditText)findViewById(R.id.edit_text_password);

        button=(Button)findViewById(R.id.button_register);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        progressDialog=new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_number.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your Mobile Number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                char ph=edit_number.getText().toString().charAt(0);
                if(!(ph=='9'||ph=='8'||ph=='7'))
                {
                    Toast.makeText(getApplicationContext(),"Invalid Mobile Number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_mail.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your Mail ID!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_password.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your Name!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Registering user...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Complaints").child("PhoneNumbers");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(edit_number.getText().toString())) {
                            // run some code
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Phone number already in use!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Random r = new Random();
                            int i1 = r.nextInt(9999 - 1000) + 1000;

                            String rn= String.valueOf(i1);
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            //details stored in shared preferences!
                            editor.putString(name_number, edit_password.getText().toString());
                            editor.putString(mail, edit_mail.getText().toString());
                            editor.putString(number,edit_number.getText().toString());
                            editor.putString(rand,rn);

                            editor.commit();



                            if (sharedpreferences.contains(name_number)) {
                                //name.setText(sharedpreferences.getString(Name, ""));

                                //textView.setText("SMS sent");

                                String message="Your One time password(OTP) for Food Zamo is "+rn+". Regards Food Zamo.";
                                String url = "http://api.infoskysolution.com/SendSMS/sendmsg.php?uname=FDZAMOT&pass=thisisas&send=FDZAMO&dest=91"+edit_number.getText().toString()+"&msg="+message;

                                //wv1.getSettings().setLoadsImagesAutomatically(true);
                                // wv1.getSettings().setJavaScriptEnabled(true);
                                //wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                                wv1.loadUrl(url);
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Are you sure ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory( Intent.CATEGORY_HOME );
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
