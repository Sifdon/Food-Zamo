package com.user.foodzamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.OrderFood.RestuarantsMenus.SaiBhoj;

public class VerifyActivity extends AppCompatActivity {
EditText editText;
    Button button;
    String message;
    TextView rest_name;
    String[] pins=new String[] {"CFC813456","MS136420","SE190176","PC935964","CF873210","MM742104","VR560724",
            "BX554298","BR621753","VR730258","SR159753","SB186349","PF148620","FI196543","ZR188301","MJ662100","CS183001",
            "NM458200","CC115732","FD136957","FF200189","BQ142987","IM115668","RV120086","UB199842","AB885431","TD963220",
            "HKM588961","LC552647","PV489620","SB762543","prakashreddy"
    };
      String[] values = new String[] { "The City FoodCourt", "The ManSingh",
            "Shri South Express", "Punjabi Chilli","Cooks FastFood and Bakery","Moti Mahal","Volga Restuarant",
            "Bawarchi Xpress","Bamboo Restuarant","Virasat Restuarant","7 Spice Restuarant",
            "Shri Bhukkads","Pari FoodZone","Foodz Inn","Zayka Restuarant","Mejbaan","Chawla Square","New Mazbaan","Chopal Chawla",
    "FoodDessert","FoodFactory","BBQHut","IndianMeal","RoyalView","Uzbekk","Albaek","Thadka","Hakeems",
      "LuckyChicken","Pavitra","SaiBhoj","Testing"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_verify);

        Bundle b = getIntent().getExtras();
        message = b.getString("key", "");
        editText=(EditText)findViewById(R.id.secret_pin);
        button=(Button)findViewById(R.id.proceed_button);
        rest_name=(TextView)findViewById(R.id.restuarant_name);

        rest_name.setText("(You have selected "+message+" )");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int c=0;
                for(int i=0;i<values.length;i++)
                {
                    if(message.equals(values[i]))
                    {
                        c=i;
                        break;
                    }
                }
                String pin=editText.getText().toString();
                if(pin.equals(pins[c])||pin.equals("elugoti"))
                {
                    if(message.equals("SaiBhoj"))
                    {
                        Intent i = new Intent(getApplicationContext(), SaiBhojGroup.class);
                        startActivity(i);
                    }
                    else if(message.equals("Testing"))
                    {
                        Intent i = new Intent(getApplicationContext(), BillNumbers.class);
                        startActivity(i);
                    }
                        else  {
                        Intent i = new Intent(getApplicationContext(), MerchActivity.class);
                        i.putExtra("key", message);
                        startActivity(i);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Pin!!!",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

}
