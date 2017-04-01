package com.user.foodzamo.Restuarants;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.user.foodzamo.AboutUs.AboutUs;
import com.user.foodzamo.HowToGetCashback;
import com.user.foodzamo.JoinUs.JoinNetwork;
import com.user.foodzamo.MyAccount.CardView;
import com.user.foodzamo.Notifications.NotificationsActivity;
import com.user.foodzamo.Offers.DailyOffers;
import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.PromoCodes.PromoCodesActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.Login;
import com.user.foodzamo.RestuarantsAbout.ListRestuarants;
import com.user.foodzamo.TermsandConditions.TermsConditions;
import com.user.foodzamo.UI.UIActivity;
import com.user.foodzamo.WriteComplaint.WriteCompl;

import java.util.List;

import static com.user.foodzamo.Restuarants.RestuarantsList.name;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private final SharedPreferences sharedpreferences;
    private final SharedPreferences sharedpreferences_address;
    private final FirebaseAuth firebaseAuth;
    private final FirebaseUser mFirebaseUser;
    private Context mContext;
    private List<Album> albumList;
    SharedPreferences sharedpreferences_marshmallow;
    public static final String mypreference_permission = "granted";
private int about_position;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        TextView user_id;
        private final String mypreference = "mypref";
        private final String Name = "nameKey";
        public static final String price = "cost";
        public static final String mypreference_address = "saveaddress";
        private final String name = "name";

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList, SharedPreferences sharedpreferences,
                         SharedPreferences sharedpreferences_address, FirebaseAuth firebaseAuth, FirebaseUser mFirebaseUser,
                         SharedPreferences sharedpreferences_marshmallow) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.sharedpreferences=sharedpreferences;
        this.sharedpreferences_address=sharedpreferences_address;
        this.firebaseAuth=firebaseAuth;
        this.mFirebaseUser=mFirebaseUser;
        this.sharedpreferences_marshmallow=sharedpreferences_marshmallow;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
         holder.count.setText(album.getNumOfSongs());


        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String p= String.valueOf(position);
                //Toast.makeText(mContext,p,Toast.LENGTH_SHORT).show();
                if(p.equals("2"))
                {
                    if(isInternetOn()==0)
                        Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    else
                        mContext.startActivity(new Intent(mContext, DailyOffers.class));
                }
                else if(p.equals("1"))
                {
                       // mContext.startActivity(new Intent(mContext, OffersToday.class));
                    //Toast.makeText(mContext,"Restuarants!",Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, ListRestuarants.class));
                }
                else if(p.equals("0"))
                {
                    if(sharedpreferences_marshmallow.contains(mypreference_permission))
                    {
                        //Toast.makeText(mContext.getApplicationContext(),"This option is blocked!",Toast.LENGTH_SHORT).show();
                        get_alert();
                        return;
                    }

                   else {
                        //starting module for order food
                        if (mFirebaseUser == null) {
                            // User has  not signed in, launch the Sign In activity
                            Intent i = new Intent(mContext, Login.class);
                            //String s=String.valueOf(position);
                            //s = String.valueOf(position_item) + s;
                            mContext.startActivity(i);
                            //mContext.startActivity(new Intent(mContext, Login.class));

                        }
                        else
                        {


                            if (sharedpreferences_address.contains(name)) {
                                //name.setText(sharedpreferences.getString(Name, ""));
                                mContext.startActivity(new Intent(mContext, SelectRestuarant.class));
                            }
                            else
                                mContext.startActivity(new Intent(mContext, SelectArea.class));
                        }
                    }

                }
                else if(p.equals("3"))
                {

                    //starting module for MyAccount
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        //mContext.startActivity(new Intent(mContext, Login.class));
                        Intent i = new Intent(mContext, Login.class);
                        //String s=String.valueOf(position);
                        //s = String.valueOf(position_item) + s;

                        mContext.startActivity(i);

                    }
                    else

                        mContext.startActivity(new Intent(mContext,CardView.class));
                }
                else if(p.equals("4"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        //mContext.startActivity(new Intent(mContext, Login.class));
                        Intent i = new Intent(mContext, Login.class);
                        //String s=String.valueOf(position);
                        //s = String.valueOf(position_item) + s;

                        mContext.startActivity(i);

                    }
                    else {
                        if(isInternetOn()==0)
                            Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        else
                            mContext.startActivity(new Intent(mContext, PromoCodesActivity.class));
                    }
                }
                else if(p.equals("5"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        //mContext.startActivity(new Intent(mContext, Login.class));
                        Intent i = new Intent(mContext, Login.class);
                        //String s=String.valueOf(position);
                        //s = String.valueOf(position_item) + s;

                        mContext.startActivity(i);

                    }
                    else {
                        if(isInternetOn()==0)
                            Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        else
                            mContext.startActivity(new Intent(mContext, NotificationsActivity.class));
                    }
                }
                else if(p.equals("6"))
                {
                    //module for write a complaint
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        //mContext.startActivity(new Intent(mContext, Login.class));
                        Intent i = new Intent(mContext, Login.class);
                        //String s=String.valueOf(position);
                        //s = String.valueOf(position_item) + s;

                        mContext.startActivity(i);

                    }
                    else
                        mContext.startActivity(new Intent(mContext, WriteCompl.class));
                }
                else if(p.equals("7"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        //mContext.startActivity(new Intent(mContext, Login.class));
                        Intent i = new Intent(mContext, Login.class);
                        //String s=String.valueOf(position);
                        //s = String.valueOf(position_item) + s;

                        mContext.startActivity(i);

                    }
                    else
                        mContext.startActivity(new Intent(mContext, JoinNetwork.class));
                }
                else if(p.equals("8"))
                {
                     mContext.startActivity(new Intent(mContext, AboutUs.class));
                    //Toast.makeText(mContext,"Terms!",Toast.LENGTH_SHORT).show();
                }
                else if(p.equals("9"))
                {
                    //mContext.startActivity(new Intent(mContext, TermsConditions.class));
                    mContext.startActivity(new Intent(mContext, HowToGetCashback.class));
                }



                    /*Intent i = new Intent(mContext,AboutActivity.class);
                    i.putExtra("key", p);
                    mContext.startActivity(i);*/


            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String p= String.valueOf(position);
                //Toast.makeText(mContext,p,Toast.LENGTH_SHORT).show();
                if(p.equals("2"))
                {
                    if(isInternetOn()==0)
                        Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    else
                        mContext.startActivity(new Intent(mContext, DailyOffers.class));
                }
                else if(p.equals("1"))
                {
                    // mContext.startActivity(new Intent(mContext, OffersToday.class));
                    //Toast.makeText(mContext,"Restuarants!",Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, ListRestuarants.class));
                }
                else if(p.equals("0"))
                {
                    if(sharedpreferences_marshmallow.contains(mypreference_permission))
                    {
                        //Toast.makeText(mContext.getApplicationContext(),"This option is blocked! Please grant permission or reinstall the app.",Toast.LENGTH_SHORT).show();
                        get_alert();
                        return;
                    }
                    //starting module for order food
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else
                    {


                        if (sharedpreferences_address.contains(name)) {
                            //name.setText(sharedpreferences.getString(Name, ""));
                            mContext.startActivity(new Intent(mContext, SelectRestuarant.class));
                        }
                        else
                            mContext.startActivity(new Intent(mContext, SelectArea.class));
                    }

                }
                else if(p.equals("3"))
                {

                    //starting module for MyAccount
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else

                        mContext.startActivity(new Intent(mContext,CardView.class));
                }
                else if(p.equals("4"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else {
                        if(isInternetOn()==0)
                            Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        else
                            mContext.startActivity(new Intent(mContext, PromoCodesActivity.class));
                    }
                }
                else if(p.equals("5"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else {
                        if(isInternetOn()==0)
                            Toast.makeText(mContext,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        else
                            mContext.startActivity(new Intent(mContext, NotificationsActivity.class));
                    }
                }
                else if(p.equals("6"))
                {
                    //module for write a complaint
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else
                        mContext.startActivity(new Intent(mContext, WriteCompl.class));
                }
                else if(p.equals("7"))
                {
                    if (mFirebaseUser == null) {
                        // User has  not signed in, launch the Sign In activity
                        mContext.startActivity(new Intent(mContext, Login.class));

                    }
                    else
                        mContext.startActivity(new Intent(mContext, JoinNetwork.class));
                }
                else if(p.equals("8"))
                {
                    mContext.startActivity(new Intent(mContext, AboutUs.class));
                    //Toast.makeText(mContext,"Terms!",Toast.LENGTH_SHORT).show();
                }
                else if(p.equals("9"))
                {
                    //mContext.startActivity(new Intent(mContext, TermsConditions.class));
                    mContext.startActivity(new Intent(mContext, HowToGetCashback.class));
                }



                    /*Intent i = new Intent(mContext,AboutActivity.class);
                    i.putExtra("key", p);
                    mContext.startActivity(i);*/


            }
        });


    }
    public final int isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);

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

    /**
     * Showing popup menu when tapping on 3 dots
     */


    /**
     * Click listener for popup menu items
     */


    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                mContext).create();

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
                mContext.startActivity(new Intent(mContext.getApplicationContext(),RestuarantsList.class));
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
