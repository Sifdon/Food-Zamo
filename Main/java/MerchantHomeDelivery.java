package com.user.foodzamo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MerchantHomeDelivery extends Activity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_home_delivery);

        Bundle b = getIntent().getExtras();
        final String message = b.getString("order", "");
        textView=(TextView)findViewById(R.id.home_delivery_orders);


        if(!message.equals(""))
        textView.setText(message);
        else
        textView.setText("No Orders!!!");

    }
}
