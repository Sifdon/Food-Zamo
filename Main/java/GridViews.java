package com.user.foodzamo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.user.foodzamo.MyAccount.Coupon;
import com.user.foodzamo.MyAccount.MyProfile;
import com.user.foodzamo.MyAccount.MyWalletList;

public class GridViews extends Activity {

    GridView grid;
    String[] web = {
            " FoodZamo Points",
            "           My Wallet",
            "           My Profile",
            "       Coupon Codes"

    } ;
    int[] imageId = {
            R.drawable.sales,
            R.drawable.sales,
            R.drawable.sales,
            R.drawable.sales

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        CustomGrid adapter = new CustomGrid(GridViews.this, web, imageId);
        grid=(GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(GridViews.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                if(position==1)
                    startActivity(new Intent(getApplicationContext(),MyWalletList.class));
                else if(position==2)
                    startActivity(new Intent(getApplicationContext(),MyProfile.class));
                else if(position==3)
                    startActivity(new Intent(getApplicationContext(),Coupon.class));


            }
        });

    }
}
