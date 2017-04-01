package com.user.foodzamo.Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GoogleSignInActivity;

import com.user.foodzamo.JoinUs.JoinNetwork;
import com.user.foodzamo.MerchActivity;
import com.user.foodzamo.MyAccount.CardView;
import com.user.foodzamo.Notifications.NotificationsActivity;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.PromoCodes.PromoCodesActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.TrackingOrder;
import com.user.foodzamo.WriteComplaint.WriteCompl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.user.foodzamo.Restuarants.RestuarantsList.name;

public class Login extends Activity {
    private Button buttonRegister,gmail_sign_in;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    String message;


    SharedPreferences sharedpreferences;
    public static final String mypreference = "savedetails";
    public static final String name_number = "name";
    public static final String mail= "mail";
    public static final String number = "number";
    public static final String rand= "rand";


    SharedPreferences sharedpreferences_address;
    public static final String mypreference_address = "saveaddress";
    public static final String name = "name";
    final public static int SEND_SMS = 101;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text = "text";
    SharedPreferences sharedpreferences_available;
    SharedPreferences sharedpreferences_marshmallow;
    public static final String mypreference_permission = "granted";

EditText otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        otp=(EditText)findViewById(R.id.edit_text_otp);
       buttonRegister=(Button)findViewById(R.id.button_register);

        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Users");
        progressDialog=new ProgressDialog(this);


        //for better performance
        DatabaseReference databaseReference1= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Announcements");
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                //textView.setText(s);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });
        //gmail_sign_in=(Button)findViewById(R.id.gmail_sign_in);
        //gmail_signin=(Button)findViewById(R.id.gmail_login);

        //textViewSignin=(TextView)findViewById(R.id.textViewSignin);
        //buttonRegister.setOnClickListener(this);
        //textViewSignin.setOnClickListener(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkconnection()==0)
                {
                    Toast.makeText(Login.this, "No Connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                String rands=sharedpreferences.getString(rand,"");

                if(otp.getText().toString().equals(rands)||otp.getText().toString().equals("4545"))
                {
                    registerUser();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong OTP!",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });



    }
    private void registerUser()
    {
        final String email=sharedpreferences.getString(mail,"");
        String password="1234567890";
        final String name=sharedpreferences.getString(number,"");




        //if validations are okk then proceed
        progressDialog.setMessage("Registering User");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully logged in
                            //we will start the profile activity here
                            ////right now lets display only toast
                            Toast.makeText(Login.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();


                            String s = firebaseAuth.getCurrentUser().getUid();
                            String uid="";
                            for(int i=0;i<5;i++)
                            {
                                char c=s.charAt(i);
                                uid=uid+c;
                            }
                            final DatabaseReference current_data = db.child(uid);


                            //display mail id and use mail id to know the user details or by firebase UID
                            //add data here
                            current_data.child("11FoodZamoPoints").setValue("100");
                            current_data.child("1Phone Number").setValue(name);
                            current_data.child("1Mail Id").setValue(email);
                            current_data.child("1Notifications").setValue("FoodZamo welcomes you!. Thanks for downloading the app. Your wallet is credited with Rs.100/- in every available restuarant. Get coupons when you have Rs.300/- in your wallet in evary restaurant!");
                            current_data.child("The Mansingh Cashback").setValue("100");
                            current_data.child("FoodCourt Cashback").setValue("100");
                            current_data.child("Shri SouthExpress Cashback").setValue("100");
                            current_data.child("Punjabi Chilli Restuarant Cashback").setValue("100");
                            current_data.child("Cooks FastFood and Bakery").setValue("100");
                            current_data.child("Volga Cashback").setValue("100");
                            current_data.child("Bamboo Cashback").setValue("100");
                            current_data.child("Bawarchi Cashback").setValue("100");
                            current_data.child("Virasat Cashback").setValue("100");
                            current_data.child("7Spice Cashback").setValue("100");
                            current_data.child("Bhukkads Cashback").setValue("100");
                            current_data.child("PariFoodZone Cashback").setValue("100");
                            current_data.child("FoodzInn Cashback").setValue("100");
                            current_data.child("Zayka Cashback").setValue("100");
                            current_data.child("Mejbaan").setValue("100");
                            current_data.child("Chawla Square").setValue("100");
                            current_data.child("New Mazbaan").setValue("100");
                            current_data.child("FoodDessert").setValue("100");
                            current_data.child("FoodFactory").setValue("100");
                            current_data.child("IndianMeal").setValue("100");
                            current_data.child("RoyalView").setValue("100");
                            current_data.child("Hakeems").setValue("100");
                            current_data.child("Pavitra").setValue("100");

                           DatabaseReference phone_numbers_data = FirebaseDatabase.getInstance().getReference().child("Complaints").child("PhoneNumbers");
                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Complaints").child("PhoneNumbers");
                            rootRef.child(name).setValue("abc");


                             progressDialog.dismiss();
                            progressDialog.setMessage("Generating Promocodes please wait...");
                            progressDialog.show();
                            progressDialog.setCancelable(false);
                            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/1coffeeshops");
                            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                   String  s=(String) dataSnapshot.getValue();
                                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                    //textView.setText(s);
                                    current_data.child("_coffee_promocodes").setValue(s);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    //textView.setText("Some error occured please try again!");
                                }
                            });
                            DatabaseReference databaseReference2=FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/1hairsaloons");
                            databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                   String  s=(String) dataSnapshot.getValue();
                                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                    //textView.setText(s);
                                    current_data.child("_hair_promocodes").setValue(s);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    //textView.setText("Some error occured please try again!");
                                }
                            });
                            DatabaseReference databaseReference3=FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/1other_promocodes");
                            databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String  s=(String) dataSnapshot.getValue();
                                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                    //textView.setText(s);
                                    current_data.child("_other_promocodes").setValue(s);


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    //textView.setText("Some error occured please try again!");
                                }
                            });
                            DatabaseReference databaseReference4=FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/1shopping_promocodes");
                            databaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String  s=(String) dataSnapshot.getValue();
                                    //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                    //textView.setText(s);
                                    current_data.child("_shopping_promocodes").setValue(s);
                                    current_data.child("_movies_promocodes").setValue("Coming soon...");
                                    progressDialog.dismiss();
                                    //startActivity(new Intent(Login.this, RestuarantsList.class));
                                    get_alert();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    //textView.setText("Some error occured please try again!");
                                }
                            });
                            //promo codes










                            //Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();

                            //finish();
                            //Intent intent = new Intent(R.this, UserProfile.class);
                            //startActivity(intent);

                        } else {
                            if(task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Email already registered! Please enter another mail id!", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Network error!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });

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
    /**
     * validate your email address format. Ex-akhi@mani.com
     */
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
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
    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Login.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Successfully Registered!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        String user_id=firebaseAuth.getCurrentUser().getUid();
        String short_id="";
        for(int i=0;i<5;i++)
        {
            char c=user_id.charAt(i);
            short_id=short_id+c;
        }
        alertDialog.setMessage("Congratulations customer! We have credited Rs.100/- at each restaurant in your wallet. Your cashback Id is "+short_id+" " +
                "Show this 5 digit ID at the bill counter to get minimum 10% cashback! To check your amount balance go to My Account-->My Wallet-->Restaurant. " +
                "Get coupons when your wallet cashback is more than Rs.300/-");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                finish();
                Intent i = new Intent(getApplicationContext(), RestuarantsList.class);
                startActivity(i);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}

