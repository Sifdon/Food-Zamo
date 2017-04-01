package com.user.foodzamo.Restuarants;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.R;
import com.user.foodzamo.WriteComplaint.WriteCompl;


import java.util.ArrayList;
import java.util.List;

public class RestuarantsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth firebaseAuth;
    ListView listView;
    private DatabaseReference db;
    TextView user_id;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
    SharedPreferences sharedpreferences_address;
    public static final String mypreference_address = "saveaddress";
    public static final String name = "name";
    final public static int SEND_SMS = 101;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text = "text";
    SharedPreferences sharedpreferences_available;
    SharedPreferences sharedpreferences_marshmallow;
    public static final String mypreference_permission = "granted";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarants_list);


        firebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        sharedpreferences_address = getSharedPreferences(mypreference_address,
                Context.MODE_PRIVATE);
        editor.remove(Name);
        editor.remove(price);
        editor.commit();

        sharedpreferences_available=getSharedPreferences(mypreference_available,Context.MODE_PRIVATE);
        sharedpreferences_marshmallow=getSharedPreferences(mypreference_permission,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.remove(text);
        editor2.commit();
        //String s1=firebaseAuth.getCurrentUser().getUid();


        //for quicker connection
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        user_id=(TextView)findViewById(R.id.user_id);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getApplicationContext(),"Permission is granting!",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            //button.setEnabled(false);
        } else {
            //Toast.makeText(getApplicationContext(),"Permission granted!",Toast.LENGTH_SHORT).show();

        }
        if (mFirebaseUser == null) {
            // User has  not signed in, launch the Sign In activity
            user_id.setText(" Register and Get Cashback ID and PromoCodes");
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
            String first = "This word is ";
            String next = "<font color='#ffffff'>"+x+"</font>";
            //t.setText(Html.fromHtml("         Your 5 digit Cashback ID: "+next+"\n(Show above 5 digit ID at bill counter to get 10% cashback!)"));
            user_id.setText(Html.fromHtml("         Your 5 digit Cashback ID: "+next));
        }

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList,sharedpreferences, sharedpreferences_address, firebaseAuth, mFirebaseUser, sharedpreferences_marshmallow);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();


    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    String s=firebaseAuth.getCurrentUser().getUid();
                    String short_id="";
                    for(int i=0;i<5;i++)
                    {
                        char c=s.charAt(i);
                        short_id=short_id+c;
                    }
                    collapsingToolbar.setTitle(getString(R.string.app_name)+"  Cashback ID: "+short_id);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.orderfood_pic_latest,
                R.drawable.restaurants_pic_latest,
                R.drawable.offers_pic_latest,
                R.drawable.my_account_latest,
                R.drawable.promocodes_latest,
                R.drawable.notifications_pic_latest,
                R.drawable.write_a_complaint_latest,
                R.drawable.join_our_network_latest,
                R.drawable.about_us_latest,
                R.drawable.how_to_get_cashback_latest
               };

        Album a = new Album("Order Food", "Get delicious food items delivered at your door step!", covers[0]);
        albumList.add(a);

        a = new Album("Restaurants", "Know more information about your favourite restaurants.", covers[1]);
        albumList.add(a);

        a = new Album("Offers", "Updates on latest offers, food fests and many more!", covers[2]);
        albumList.add(a);

        a = new Album("My Account", "Look at your wallet, address and generate food coupons.", covers[3]);
        albumList.add(a);

        a = new Album("Promo Codes", "Check this for offers or discounts other than food.", covers[4]);
        albumList.add(a);

        a = new Album("Notifications", "Watch out for notifications, announcements.", covers[5]);
        albumList.add(a);

        a = new Album("Drop a Complaint", "Write complaints on food issues.", covers[6]);
        albumList.add(a);

        a = new Album("Join our Network", "Wish to partner with us? Fill this form!", covers[7]);
        albumList.add(a);

        a = new Album("About Us", "Know about our team, motive and other information.", covers[8]);
        albumList.add(a);

        a = new Album("How to get Cashback?", "Look for cashback schemes, terms & conditions.", covers[9]);
        albumList.add(a);



        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               //Toast.makeText(getApplicationContext(),"Permission has been granted!",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor3 = sharedpreferences_marshmallow.edit();
                //editor.putString(name, edit_name.getText().toString()+"\n");
                editor3.remove(mypreference_permission);
                editor3.commit();
                //textView.setText("You can send SMS!");
                //button.setEnabled(true);
            } else {
                //Toast.makeText(getApplicationContext(),"Request Cancelled!",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor3 = sharedpreferences_marshmallow.edit();
                //editor.putString(name, edit_name.getText().toString()+"\n");
                editor3.putString(mypreference_permission,"rejected");
                editor3.commit();
                //Toast.makeText(getApplicationContext(),"Home delivery option blocked for you!",Toast.LENGTH_SHORT).show();
                get_alert();
                //textView.setText("You can not send SMS!");
                //button.setEnabled(false);
            }
        }
    }
    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                RestuarantsList.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Restricted!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("'Order Food' option is blocked. To unblock please grant the permission! " +
                "If you face any problem please restart the app or reinstall it");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.blocked_image);

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
