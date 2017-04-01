package com.user.foodzamo.OrderFood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.OrderFood.FragmentOne.SelectRestuarant;
import com.user.foodzamo.R;

public class SaveAddress extends Activity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "saveaddress";
    public static final String name = "name";
    public static final String address= "address";
    public static final String number = "phone_number";
    public static final String area= "area";
    EditText edit_name,edit_address,edit_number;
    Button button;
    TextView text_area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_save_address);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        edit_name=(EditText)findViewById(R.id.name);
        edit_address=(EditText)findViewById(R.id.address);
        edit_number=(EditText)findViewById(R.id.phone_number);
        text_area=(TextView)findViewById(R.id.area);

        button=(Button)findViewById(R.id.save_address);
        Bundle b = getIntent().getExtras();
        final String message = b.getString("key", "");


        //text_area.setText("Area: "+message+", Gwalior");

        text_area.setText(message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_address.getText().toString().equals("")||edit_name.getText().toString().equals("")||edit_number.getText().toString().equals(""))
                {
                   Toast.makeText(getApplicationContext(),"Address Incomplete!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String num=edit_number.getText().toString();

                //Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
                if(!(num.charAt(0)=='7'||num.charAt(0)=='8'||num.charAt(0)=='9'))
                {
                    Toast.makeText(getApplicationContext(),"Phone number invalid!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_name.getText().toString().length()<3)
                {
                    Toast.makeText(getApplicationContext(),"Name is very short!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_address.getText().toString().length()<10)
                {
                    Toast.makeText(getApplicationContext(),"Address is very short!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_number.getText().toString().length()<10)
                {
                    Toast.makeText(getApplicationContext(),"Phone number invalid!",Toast.LENGTH_SHORT).show();
                    return;
                }



                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(name, edit_name.getText().toString()+"\n");
                editor.putString(address, edit_address.getText().toString()+"\n");
                editor.putString(number,edit_number.getText().toString()+"\n");
                editor.putString(area,text_area.getText().toString());


                editor.commit();

                if (sharedpreferences.contains(name)) {
                    //name.setText(sharedpreferences.getString(Name, ""));
                    Toast.makeText(getApplicationContext(),
                           "Addressed Saved Successfully!"
                            ,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), SelectRestuarant.class));
                }
            }
        });

    }
}
