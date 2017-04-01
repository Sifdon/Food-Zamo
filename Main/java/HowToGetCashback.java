package com.user.foodzamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HowToGetCashback extends AppCompatActivity {
TextView get_cahsback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_get_cashback);
        String s="1) Register and get a unique cashback ID.\n\n" +
                "2) Provide your ID to the restaurants at the time of bill payments to get cashback in your wallet. (To check the details of cashback amounts @ My Account---> My Wallet---> Restaurant)\n\n" +
                "3) Acquire food coupons in the restaurants from the available money in the foodzamo wallet. (minimum amount for coupon is Rs.300/-)\n\n" +
                "4) In case of any confusion regarding cashback feel free to consult the restaurant manager.\n\n" +
                "5) Coupons generated through cashback will be valid only on table bills.\n\n" +
                "6) If you need any promocodes you won't be receiving any cashback.\n\n" +
                "7) Uninstalling the app may lead to loss of money in the wallets in some cases.\n\n" +
                "8) Food Coupons are valid only on next visit and you may not receive cashback if restaurant is offering any discounts.\n\n"+
                "9) Cashback will not be available on previous bills which are done other than current day.\n\n"+
                "10) For more details and latest offers visit us at www.foodzamo.com and follow us on facebook\n";
        get_cahsback=(TextView)findViewById(R.id.how_to_get_cashback);
        get_cahsback.setText(s);

    }
}
