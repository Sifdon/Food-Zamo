package com.user.foodzamo.GetCashback;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.MainActivity;
import com.user.foodzamo.MerchActivity;
import com.user.foodzamo.MyAccount.FoodZamoPoints;
import com.user.foodzamo.R;
import com.user.foodzamo.VerifyActivity;

import java.util.List;

public class ListRestr extends ListActivity {
    String pin_number;

    String[] values = new String[] { "The City FoodCourt", "The ManSingh",
            "Shri South Express", "Punjabi Chilli", "The Yellow Chilli","Cooks FastFood and Bakery","Moti Mahal","Volga Restuarant",
            "Bawarchi Xpress","Bamboo Restuarant","Virasat Restuarant","7 Spice Restuarant",
            "Shri Bhukkads","Pari FoodZone","Foodz Inn","Zayka Restuarant","Mejbaan","Chawla Square","New Mazbaan","Chopal Chawla",
    "FoodDessert","FoodFactory","BBQHut","IndianMeal","RoyalView","Uzbekk","Albaek","Thadka","Hakeems","WildChefHouse","MughalDarbar",
    "Heavens","ShahiSwad","LuckyChicken","Pavitra","SaiBhoj","SaiFruitSake","MakkhanBhoj","Ajanta","Testing"};
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);



        // use your custom layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_cashback, R.id.label, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        //startActivity(new Intent(this,EnterBill.class));
        Intent i = new Intent(getApplicationContext(), VerifyActivity.class);
        i.putExtra("key", item);
        startActivity(i);

    }


}