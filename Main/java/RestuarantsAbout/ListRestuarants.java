package com.user.foodzamo.RestuarantsAbout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.user.foodzamo.MerchActivity;
import com.user.foodzamo.R;
import com.user.foodzamo.Restuarants.AboutActivity;
import com.user.foodzamo.UI.UIActivity;


public class ListRestuarants extends AppCompatActivity {

    private ListView list;
    CustomArrayAdapter adapter;
    String[] fruits = {
            "\nThe City FoodCourt",
            "\nThe ManSingh",
            "\nMotiMahal Deluxe Restaurant",
            "\nHakeems Restaurant",
            "\nShri SouthExpress",
            "\nBamboo Restaurant",
            "\nThe Food Factory",
            "\nPunjabi Chilli Restaurant",
            "\nVolga Restaurant",
            "\nVirasat, The Heritage",
            "\nBawarchi Xpress",
            "\n7 Spice Restaurant",
            "\nPari FoodZone & Restaurant",
            "\nCooks Fast Food&Bakery",
            "\nShree Bhukkads",
            "\nFoodz Inn Restaurant",
            "\nHotel SunBeam, Mejbaan Restaurant",
            "\nChawla Square Restaurant",
            "\nNew Mazbaaan Restaurant",
            "\nChopal Chicken",
            "\nFood & Dessert Restaurant",
            "\nBBQ Hut",
            "\nZayka Restaurant",
            "\nIndian Meal Restaurant",
            "\nRoyal View Restaurant",
            "\nThadka Restaurant",
            "\nAlbaek Non-Veg Restaurant",
            "\nPavitra Restaurant",
            "\nSai Bhoj Restaurant",
            "\nSai Fruit Sake Restaurant"

    };
    Integer[] imageIds = {
            R.drawable.foodcourt_new,
            R.drawable.mansingh_new,
            R.drawable.motimahal_new,
            R.drawable.hakeems_new,
            R.drawable.south_express_new,
            R.drawable.bamboo_new,
            R.drawable.food_factory_new,
            R.drawable.punjabichilli_new,
            R.drawable.volga_new,
            R.drawable.virasat_new,
            R.drawable.bawarchi_new,
            R.drawable.spice_new,
            R.drawable.pari_foodzone_new,
            R.drawable.cooks_new,
            R.drawable.bhukkads_new,
            R.drawable.foodz_inn_new,
            R.drawable.south_express_new,
            R.drawable.chawla_square_new,
            R.drawable.south_express_new,
            R.drawable.copalchawla_logo,
            R.drawable.food_dessert_new,
            R.drawable.bbq_new,
            R.drawable.zayka_new,
            R.drawable.indian_meal_new,
            R.drawable.royal_view_new,
            R.drawable.thadka_new,
            R.drawable.albaek_new,
            R.drawable.pavitra_new,
            R.drawable.saibhoj_logo,
            R.drawable.saifruitsake_logo,
            R.drawable.hello1,
            R.drawable.hello2,
            R.drawable.hello3,
            R.drawable.hello4,
            R.drawable.hello5,
            R.drawable.hello1,
            R.drawable.hello2,
            R.drawable.hello3,
            R.drawable.hello4,
            R.drawable.hello5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restuarants);

        list = (ListView) findViewById(R.id.list);
        adapter = new CustomArrayAdapter(ListRestuarants.this, fruits, imageIds);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= String.valueOf(position);

                //Toast.makeText(getApplicationContext(),"Under Construction!!!",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), UIActivity.class);
                i.putExtra("key", s);
                startActivity(i);
            }
        });

    }



}
