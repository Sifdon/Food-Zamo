package com.user.foodzamo.OrderFood.FragmentOne;

import java.util.ArrayList;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.OrderFood.Global;
import com.user.foodzamo.OrderFood.RestuarantsMenus.AlbaekSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.BambooSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.BawarchiSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.BbqHutSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.BhukkadsSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ChawlaGKMSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ChawlaSquareSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ChopalChawla;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ChopalChawlaSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.CooksSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.DemoSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.FoodCourtSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.FoodDessertSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.FoodFactorySubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.FoodzSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.HakeemsSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.HeavensSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.IndianSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.LuckySubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.MakkhanSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ManSinghSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.Mejbaan;
import com.user.foodzamo.OrderFood.RestuarantsMenus.MejbaanSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.MotiMahalSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.MughalSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.NewMazbaanSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.OrderMessage;
import com.user.foodzamo.OrderFood.RestuarantsMenus.PariSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.PavitraSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.PunjabiChilli;
import com.user.foodzamo.OrderFood.RestuarantsMenus.PunjabiChilliSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.RoyalSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.SaiBhoj;
import com.user.foodzamo.OrderFood.RestuarantsMenus.SaiBhojSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.SaiFruitSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ShahiSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.SouthSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.SpiceSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ThadkaSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.UzbekSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.VirasatSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.VolgaSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.WildSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.YellowChilliSubMenu;
import com.user.foodzamo.OrderFood.RestuarantsMenus.Zayka;
import com.user.foodzamo.OrderFood.RestuarantsMenus.ZaykaSubMenu;
import com.user.foodzamo.OrderFood.SelectArea;
import com.user.foodzamo.R;
import com.user.foodzamo.Restuarants.RestuarantsList;
import com.user.foodzamo.WriteComplaint.WriteCompl;

public class SelectRestuarant extends Activity {
    /** Called when the activity is first created. */
    Global global;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String price = "cost";
    SharedPreferences sharedpreferences_available;
    public static final String mypreference_available = "mypreferenceavailable";
    public static final String text="text";
    ProgressDialog progressDialog;
    String available_message;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restuarant);

        global=new Global();
        progressDialog=new ProgressDialog(this);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences_available=getSharedPreferences(mypreference_available,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Name);
        editor.remove(price);
        editor.commit();
        SharedPreferences.Editor editor2 = sharedpreferences_available.edit();
        editor2.remove(text);
        editor2.commit();
        //Toast.makeText(SelectRestuarant.this,s, Toast.LENGTH_LONG).show();

        ArrayList<ItemDetails> image_details = GetSearchResults();

        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));

        lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Object o = lv1.getItemAtPosition(position);
                ItemDetails obj_itemDetails = (ItemDetails)o;

progressDialog.setMessage("Loading...");
                progressDialog.show();
                progressDialog.setCancelable(false);

 if(position==0) {

     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Shri SouthExpress");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                   display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, SouthSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });


 }
else if(position==1) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/The Punjabi Chilli");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
                  else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, PunjabiChilliSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }

else if(position==2) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Moti Mahal");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, MotiMahalSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==3) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Cooks");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, CooksSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==4) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Volga");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, VolgaSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==5) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Bawarchi");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, BawarchiSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==6) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Virasat");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, VirasatSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==7) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/7Spice");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, SpiceSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }

else if(position==8) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodzInn");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, FoodzSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==9) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Zayka");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, ZaykaSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==10) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Mejbaan");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, MejbaanSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==11) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/ChawlaSquare");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, ChawlaSquareSubMenu.class));
             }

         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==12) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/NewMajbaan");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, NewMazbaanSubMenu.class));
             }

         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==13) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/ChopalChawla");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, ChopalChawlaSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==14) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodDessert");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, FoodDessertSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==15) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/FoodFactory");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, FoodFactorySubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==16) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/BBQHut");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, BbqHutSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==17) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/IndianMeal");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, IndianSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==18) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/RoyalView");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, RoyalSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }

else if(position==19) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Albaek");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, AlbaekSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
else if(position==20) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Thadka");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, ThadkaSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
 else if(position==21) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Hakeems");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, HakeemsSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
 else if(position==22) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/ChopalChawla");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, ChawlaGKMSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }


 else if(position==23) {
     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/Pavitra");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, PavitraSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });

 }
 else if(position==24) {

     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/SaiBhoj");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, SaiBhojSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });
         }
 else if(position==25) {

     DatabaseReference databaseReference2= FirebaseDatabase.getInstance().
             getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/HomeDeliveryAvailable/rbtTMPL739PSqKPZUlDARuOIHAm2/SaiFruitSake");
     databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             available_message=(String) dataSnapshot.getValue();
             progressDialog.dismiss();
             if(available_message.equals("0"))
                 get_alert_one();
             else if(available_message.equals("2"))
                 get_alert_two();
             else {
                 display(available_message);
                 startActivity(new Intent(SelectRestuarant.this, SaiFruitSubMenu.class));
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             //textView.setText("Some error occured please try again!");
         }
     });
 }




else
    Toast.makeText(SelectRestuarant.this,String.valueOf(position),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private ArrayList<ItemDetails> GetSearchResults(){
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();

//0
        item_details = new ItemDetails();
        item_details.setName("\nShri South Express");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//1
        item_details = new ItemDetails();
        item_details.setName("\nPunjabi Chilli Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//2
        item_details = new ItemDetails();
        item_details.setName("\nMoti Mahal");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//3
        item_details = new ItemDetails();
        item_details.setName("\nCooks Fast Food & Bakery");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//4
        item_details = new ItemDetails();
        item_details.setName("\nVolga Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);

//5
        item_details = new ItemDetails();
        item_details.setName("\nBawarchi Xpress");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//6
        item_details = new ItemDetails();
        item_details.setName("\nVirasat, The Heritage");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//7
        item_details = new ItemDetails();
        item_details.setName("\n7 Spice Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//8
        item_details = new ItemDetails();
        item_details.setName("\nFoodz Inn Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//9
        item_details = new ItemDetails();
        item_details.setName("\nZayka Family Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//10
        item_details = new ItemDetails();
        item_details.setName("\nMejbaan Multicuisine Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//11
        item_details = new ItemDetails();
        item_details.setName("\nChawla Square");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//12
        item_details = new ItemDetails();
        item_details.setName("\nNew Mazbaan Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//13
        item_details = new ItemDetails();
        item_details.setName("\nChopal Chawla Chick-Inn(City Center)");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//14
        item_details = new ItemDetails();
        item_details.setName("\nFood & Dessert Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//15
        item_details = new ItemDetails();
        item_details.setName("\nThe Food Factory");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//16
        item_details = new ItemDetails();
        item_details.setName("\nBBQ Hut");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//17
        item_details = new ItemDetails();
        item_details.setName("\nIndian Meal Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//18
        item_details = new ItemDetails();
        item_details.setName("\nRoyal View Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);

//19
        item_details = new ItemDetails();
        item_details.setName("\nAlbaek Non-Veg Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//20
        item_details = new ItemDetails();
        item_details.setName("\nThadka Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//21
        item_details = new ItemDetails();
        item_details.setName("\nHakeems Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);

//22
        item_details = new ItemDetails();
        item_details.setName("\nChopal Chawla Chick-Inn(GKM)");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//23

        item_details = new ItemDetails();
        item_details.setName("\nPavitra Restaurant");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//24
        item_details = new ItemDetails();
        item_details.setName("\nSai Bhoj");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);
//25
        item_details = new ItemDetails();
        item_details.setName("\nSai Fruit Sake");
        item_details.setItemDescription("Maximum Delivery Time: 60 Minutes");
        item_details.setPrice("");
        item_details.setImageNumber(6);
        results.add(item_details);













        return results;
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(), RestuarantsList.class));
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
    public void get_alert_one()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                SelectRestuarant.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("Restaurant is closed now! Kindly try ordering online after sometime");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.restuarant_closed);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void get_alert_two()
    {
       final AlertDialog alertDialog = new AlertDialog.Builder(
                SelectRestuarant.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("Servery Busy! Please try after sometime!");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.server_busy);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                alertDialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void display(String available_message)
    {

        SharedPreferences.Editor editor2 = sharedpreferences_available.edit();
        editor2.putString(text,available_message);


        editor2.commit();
    }

}