package com.user.foodzamo.MyAccount;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.user.foodzamo.OrderFood.Global;
import com.user.foodzamo.R;

public class MyProfile extends Activity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "saveaddress";
    public static final String name = "name";
    public static final String address= "address";
    public static final String number = "phone_number";
    public static final String area= "area";
    final Context context = this;
    ImageView button;
    TextView textView;
    FirebaseAuth firebaseAuth;
    Global global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        global=new Global();

        textView=(TextView)findViewById(R.id.user_details);
        button=(ImageView) findViewById(R.id.change_data);
if(sharedpreferences.contains(name))
{
    String s="";
    String id=firebaseAuth.getCurrentUser().getUid();
    String short_id="";
    for(int i=0;i<5;i++)
    {
        char c=id.charAt(i);
        short_id=short_id+c;
    }
    s=s+"Cashback/Promocodes Id: "+short_id+"\n\n";
    s=s+"Name: "+sharedpreferences.getString(name,"")+"\n\n";
    s=s+"Address: "+sharedpreferences.getString(address,"")+"\n\n";
    s=s+"Phone Number: "+sharedpreferences.getString(number,"")+"\n\n";
    s=s+"Area: "+sharedpreferences.getString(area,"")+", Gwalior";
    textView.setText(s);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // custom dialog
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.alert_dialog);
            dialog.setTitle("Change Address!");
            dialog.show();

         final   EditText edit_name=(EditText) dialog.findViewById(R.id.name);
         final   EditText edit_address=(EditText) dialog.findViewById(R.id.address);
         final   EditText edit_number=(EditText) dialog.findViewById(R.id.phone_number);

            //Creating the instance of ArrayAdapter containing list of language names
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (getApplicationContext(),R.layout.list_autocomplete,global.products);
            final AutoCompleteTextView autocomplete=(AutoCompleteTextView)dialog.findViewById(R.id.area);

            Resources res = getResources();
            int color = res.getColor(android.R.color.black);
            autocomplete.setText("");
            autocomplete.setThreshold(1);//will start working from first character
            autocomplete.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

            Button save_address=(Button)dialog.findViewById(R.id.save_address);

            edit_name.setText(sharedpreferences.getString(name,""));
            edit_address.setText(sharedpreferences.getString(address,""));
            edit_number.setText(sharedpreferences.getString(number,""));


            save_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(autocomplete.length()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Your Area",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String check=autocomplete.getText().toString();
                    int flag=0;
                    for(int i=0;i<global.products.length;i++)
                    {
                        if(check.equals(global.products[i]))
                        {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {
                        Toast.makeText(getApplicationContext(),check,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(name, edit_name.getText().toString());
                    editor.putString(address, edit_address.getText().toString());
                    editor.putString(number, edit_number.getText().toString());
                    editor.putString(area, autocomplete.getText().toString());

                    editor.commit();
                    dialog.dismiss();
                    String id=firebaseAuth.getCurrentUser().getUid();
                    String short_id="";
                    for(int i=0;i<5;i++)
                    {
                        char c=id.charAt(i);
                        short_id=short_id+c;
                    }
                    Toast.makeText(getApplicationContext(),"Address Saved",Toast.LENGTH_SHORT).show();
                    String s="Cashback/Promocodes Id: "+short_id+"\n\n";;
                    s=s+"Name: "+sharedpreferences.getString(name,"")+"\n\n";
                    s=s+"Address: "+sharedpreferences.getString(address,"")+"\n\n";
                    s=s+"Phone Number: "+sharedpreferences.getString(number,"")+"\n\n";
                    s=s+"Area: "+sharedpreferences.getString(area,"")+", Gwalior";
                    textView.setText(s);
                }
            });

        }
    });

}
        else {
    textView.setText("Address Not Saved!");

}


    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), CardView.class));
    }
}
