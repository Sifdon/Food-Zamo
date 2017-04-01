package com.user.foodzamo.MyAccount;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.user.foodzamo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyWalletList extends Activity {
    private String[] mNames = {  "The City FoodCourt","The ManSingh","Hakeems Restaurant","Shri SouthExpress Restaurant","Bamboo Restaurant","The FoodFactory",
              "Punjabi Chilli Restaurant",  "Volga Restaurant","Virasat, The Heritage Restaurant",
            "Bawarchi Xpress","7 Spice Restaurant","Pari FoodZone & Restaurant","Cooks Fast Food&Bakery","Shree Bhukkads The Food Lounge",
            "Foodz Inn Restaurant","Hotel SunBeam, Mejbaan Restaurant","Chawla Square Restaurant","New Mazbaan Restaurant","Zayka Family Restaurant",
            "Food & Dessert Restaurant","Indian Meal Restaurant","RoyalView Restaurant","Pavitra Restaurant"
            };

    private String[] mAnimals = { "3rd Floor,DD Mall", "3rd Floor,DD Mall",
            "City Center", "Pintu Park Chauraha", "Near Roopsingh Stadium", "City Center", "Lashkar","Lashkar",
            "Lashkar","Near Hotel LandMark","Lashkar","Phool Bhagh","Lashkar","Pool Bhagh","Chetakpuri","City Center","City Center",
            "Lashkar","Pool Bhagh","Govindpuri","City Center","Govindpuri","City Center"
             };

    int[] flags = new int[]{
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee,
            R.drawable.rupee


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet_list);


        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<mNames.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "" + mNames[i]);
            hm.put("cur","" + mAnimals[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
        String[] from = { "flag","txt","cur" };

        int[] to = { R.id.flag,R.id.txt,R.id.cur,R.id.textView2};

        ListView list = (ListView)findViewById(R.id.listV_main);
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), aList, R.layout.listview_layout, from, to);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //startActivity(new Intent(getContext(), MyWallet.class));
                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                    Intent i = new Intent(getApplicationContext(), MyWallet.class);
                String x= String.valueOf(position);
                    i.putExtra("key", x);
                    startActivity(i);


                    //Toast.makeText(getApplicationContext(),"U clicked "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(),CardView.class));
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
