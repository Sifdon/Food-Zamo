package com.user.foodzamo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.user.foodzamo.GetCashback.ListRestr;
import com.user.foodzamo.JoinUs.JoinNetwork;
import com.user.foodzamo.MyAccount.CardView;
import com.user.foodzamo.Notifications.NotificationsActivity;
import com.user.foodzamo.Offers.OffersToday;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.PromoCodes.PromoCodesActivity;
import com.user.foodzamo.Register.Login;
import com.user.foodzamo.Register.UserProfile;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.TermsandConditions.SharedPref;
import com.user.foodzamo.WriteComplaint.WriteCompl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Blured blur;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    ImageView internet_check_image;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser mFirebaseUser;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
    SharedPreferences sharedpreferences_address;
    public static final String mypreference_address = "saveaddress";
    public static final String name = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

blur=new Blured();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.setNavigationIcon(null);

        internet_check_image=(ImageView)findViewById(R.id.not_internet_connection);
        progressDialog=new ProgressDialog(this);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        sharedpreferences_address = getSharedPreferences(mypreference_address,
                Context.MODE_PRIVATE);
        editor.remove(Name);
        editor.remove(price);
        editor.commit();
        firebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();
        //animating fabview
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

//Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_SHORT).show();

        //checking and assigning internet connection


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Necessary statement for setting desired colour in navigation view
        navigationView.setItemIconTintList(null);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.user_id);

        if (mFirebaseUser == null) {
            // User has  not signed in, launch the Sign In activity
            nav_user.setText(" SignUp and Get Cashback/Promocode ID");
        }
        else
        {
            String s=firebaseAuth.getCurrentUser().getUid();
            String x="";
            for(int i=0;i<5;i++)
            {
                char c=s.charAt(i);
                x=x+c;
            }
            nav_user.setText("         Your Cashback ID:\n           "+x);
        }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // starting module for offers

            if(isInternetOn()==0)
                Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
            else

                startActivity(new Intent(getApplicationContext(), OffersToday.class));


        } else if (id == R.id.restuaransts) {

            //Starting module for restuarant
            startActivity(new Intent(MainActivity.this, RestuarantsList.class));

        } else if (id == R.id.nav_slideshow) {

            //starting module for order food
            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            else
            {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove(Name);
                editor.commit();

                if (sharedpreferences_address.contains(name)) {
                    //name.setText(sharedpreferences.getString(Name, ""));
                    startActivity(new Intent(MainActivity.this, SelectRestuarant.class));
                }
                else
                startActivity(new Intent(MainActivity.this, SelectArea.class));
            }

        } else if (id == R.id.nav_manage) {
            //startActivity(new Intent(MainActivity.this, Login.class));
            //starting module for MyAccount
            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            else
            //startActivity(new Intent(MainActivity.this, MyAccount.class));
                startActivity(new Intent(MainActivity.this,CardView.class));


        } else if (id == R.id.nav_share) {

            //module for write a complaint
            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            else
            startActivity(new Intent(MainActivity.this, WriteCompl.class));

        } else if (id == R.id.join_with_us) {


            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            else
                startActivity(new Intent(MainActivity.this, JoinNetwork.class));
            //startActivity(new Intent(MainActivity.this, GridViews.class));

        }
        else if(id==R.id.terms_and_conditions)
        {
            startActivity(new Intent(MainActivity.this, SharedPref.class));
        }
        else if(id==R.id.promo_codes)
        {
            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            //Toast.makeText(MainActivity.this,"Promo Codes",Toast.LENGTH_SHORT).show();
            else {
                if(isInternetOn()==0)
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(MainActivity.this, PromoCodesActivity.class));
            }
        }
        else if(id==R.id.notifications)
        {
            if (mFirebaseUser == null) {
                // User has  not signed in, launch the Sign In activity
                startActivity(new Intent(this, Login.class));

            }
            else {
                if(isInternetOn()==0)
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                else
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:
                Toast.makeText(MainActivity.this,"U clicked option1",Toast.LENGTH_SHORT).show();
                Log.d("Raj", "Fab 1");
                break;
            case R.id.fab2:
                Toast.makeText(MainActivity.this,"U clicked option2",Toast.LENGTH_SHORT).show();

                break;
        }
    }
    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;


        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;


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


}
