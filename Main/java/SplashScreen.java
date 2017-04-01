package com.user.foodzamo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.GetCashback.ListRestr;

public class SplashScreen extends Activity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    private static String TAG = SplashScreen.class.getName();
    private static long SLEEP_TIME = 3;    // Sleep for some time
    SharedPreferences sharedpreferences_flag;
    public static final String mypreference_flag = "setflag";
    public static final String flag = "flag";
    public static final String restname= "restname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        setContentView(R.layout.activity_splash_screen);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences_flag = getSharedPreferences(mypreference_flag,
                Context.MODE_PRIVATE);
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
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Name);
        editor.commit();
        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            Intent intent;
            // Start main activity
           if(sharedpreferences_flag.contains(flag)) {
                Intent i = new Intent(getApplicationContext(), MerchActivity.class);
               i.putExtra("key", sharedpreferences_flag.getString(restname,""));
                startActivity(i);
            }
            else
            {
                intent = new Intent(SplashScreen.this, ListRestr.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }

       //startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
      //startActivity(new Intent(getApplicationContext(),BillNumbers.class));
        }
    }
}

