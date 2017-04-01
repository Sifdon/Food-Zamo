package com.user.foodzamo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class ShowDelivery extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    ListView listView;
    private DatabaseReference db;
    ProgressDialog progressDialog;
    TextView southexpress,punjabichilli,foodfactory,foodndessert,cooks,
    royalview,motimahal,virasat,chawlasquare,chopalchawla,indianmeal,saibhoj,saifruit,albaek,bbqhut,hakeems,mejbaan,thadka,volga;
String data_string="";
    SharedPreferences sharedpreferences;
    public static final String mypreference = "savedata";
    public static final String data= "data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delivery);


        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if(!sharedpreferences.contains(data))
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(data,"2189");
            editor.commit();
        }

        //mediaPlayer = MediaPlayer.create(this, R.raw.music);
        albaek=(TextView) findViewById(R.id.albaek_list);
        bbqhut=(TextView) findViewById(R.id.bbqhut_list);
        hakeems=(TextView) findViewById(R.id.hakeems_list);
        mejbaan=(TextView) findViewById(R.id.mejbaan_list);
        thadka=(TextView) findViewById(R.id.thadka_list);

        southexpress=(TextView) findViewById(R.id.southexpress_list);
        punjabichilli=(TextView) findViewById(R.id.punjabichilli_list);
        foodfactory=(TextView) findViewById(R.id.foodfactory_list);
        foodndessert=(TextView) findViewById(R.id.fooddessert_list);
        cooks=(TextView) findViewById(R.id.cooksfastfood_list);
        royalview=(TextView) findViewById(R.id.royalview_list);
        motimahal=(TextView) findViewById(R.id.motimahal_list);
        virasat=(TextView) findViewById(R.id.virasat_list);
        chawlasquare=(TextView) findViewById(R.id.chawlasquare_list);
        chopalchawla=(TextView) findViewById(R.id.chopalchawla_list);
        indianmeal=(TextView) findViewById(R.id.indianmeal_list);
        saibhoj=(TextView) findViewById(R.id.saibhoj_list);
        saifruit=(TextView) findViewById(R.id.saifruit_list);
        volga=(TextView)findViewById(R.id.volga_list);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();


        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Shri South Express/address");
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                southexpress.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/The Punjabi Chilli/address");
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                punjabichilli.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference3=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Moti Mahal/address");
        databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                motimahal.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference4=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodFactory/address");
        databaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                foodfactory.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference5=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/FoodDessert/address");
        databaseReference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                foodndessert.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference6=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Cooks FastFood/address");
        databaseReference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                cooks.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference7=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chawla Square/address");
        databaseReference7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                chawlasquare.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference8=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/RoyalView/address");
        databaseReference8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                royalview.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference9=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Virasat/address");
        databaseReference9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                virasat.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference10=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Chopal Chawla/address");
        databaseReference10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                chopalchawla.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference11=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/IndianMeal/address");
        databaseReference11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                indianmeal.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference12=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiBhoj/address");
        databaseReference12.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                saibhoj.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference13=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/SaiFruitSake/address");
        databaseReference13.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                saifruit.setText(s);
                data_string=data_string+s;
                int x=data_string.length();
                String len= String.valueOf(x);

                //Toast.makeText(getApplicationContext(),len,Toast.LENGTH_LONG).show();

                String pref=sharedpreferences.getString(data,"");
                int pref_int= Integer.parseInt(pref);

                if(x!=pref_int) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    String chng= String.valueOf(x);
                    editor.putString(data, chng);
                    editor.commit();

                    //mediaPlayer.start();

                }

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });
        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference14=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Albaek/address");
        databaseReference14.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                albaek.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference15=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/BBQHut/address");
        databaseReference15.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                bbqhut.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference16=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Hakeems/address");
        databaseReference16.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                hakeems.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference17=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Mejbaan/address");
        databaseReference17.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                mejbaan.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference18=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Thadka/address");
        databaseReference18.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                thadka.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });

        progressDialog.setMessage("loading orders...Please wait!");
        progressDialog.show();

        DatabaseReference databaseReference19=FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDelivery/Volga/address");
        databaseReference19.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                ///textView.setText(s);
                volga.setText(s);
                data_string=data_string+s;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });
//Volga

    }
    @Override
    public void onBackPressed()
    {
        //mediaPlayer.release();
        //mediaPlayer.stop();
        finish();
        startActivity(new Intent(getApplicationContext(),BillNumbers.class));
    }
    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(1200*1000);
            } catch (Exception e) {

            }
            Intent intent;
            // Start main activity
            Intent i = new Intent(getApplicationContext(), ShowDelivery.class);
            startActivity(i);


        }
    }

}
