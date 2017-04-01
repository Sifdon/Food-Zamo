package com.user.foodzamo.MyAccount;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.user.foodzamo.R;
import com.user.foodzamo.WriteComplaint.WriteCompl;

public class Coupon extends Activity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "couponid";
    public static final String couponamount= "couponamount";
    public static final String restuarantname= "restuarantname";
    public static final String number = "number";
    TextView textView;
    private FirebaseAuth firebaseAuth;
    Button use_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        firebaseAuth=FirebaseAuth.getInstance();
        textView=(TextView)findViewById(R.id.status);
        use_now=(Button)findViewById(R.id.use_now);

        String s;
        if(sharedpreferences.contains(couponamount))
        {
          String amt=sharedpreferences.getString(couponamount,"");
            String rest=sharedpreferences.getString(restuarantname,"");
            String num=sharedpreferences.getString(number,"");
            String uid=firebaseAuth.getCurrentUser().getUid();
            String x="";
            for(int i=0;i<5;i++)
            {
                char c=uid.charAt(i);
                x=x+c;
            }
            textView.setText("User Id: "+x+"\nCoupon Amount: "+amt+"\nRestuarant: "+rest+"\nCoupon ID: "+num+"\n(Valid only at "+rest+")");

        }
        else
        {
            textView.setText("No Coupons are available!");
            use_now.setEnabled(false);
        }

        use_now.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View v) {

                   final Dialog dialog = new Dialog(Coupon.this);
                   dialog.setContentView(R.layout.coupon_dialog);
                   dialog.setTitle("Enter Pin!");
                   dialog.show();

                   final EditText edit_pin=(EditText)dialog.findViewById(R.id.enter_id);
                   Button button=(Button)dialog.findViewById(R.id.ok_button);
                   ImageView info=(ImageView)dialog.findViewById(R.id.information);
                   button.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String pin="";
                           pin="29296";
                               if(edit_pin.getText().toString().equals(pin))
                        {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.remove(couponamount);
                            editor.remove(restuarantname);
                            editor.remove(number);
                            editor.commit();
                            //Toast.makeText(getApplicationContext(),"Coupon Revealed Successfully!",Toast.LENGTH_LONG).show();
                            textView.setText("Coupon Redeemed Successfully!!!");
                            use_now.setEnabled(false);
                            dialog.dismiss();

                        }
                           else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong Pin!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                       }
                   });
                   info.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           get_alert();
                       }
                   });


            }
        });

    }
    public void get_alert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Coupon.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Information!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("Merchant IDs are secret pin numbers for coupon verification. This secret number will not be given to " +
                "the customer. These are kept secret with restuarant management. Kindy co-operate with us to verify your coupon. Coupons" +
                " are available after valid verification!" );

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_info_black_24dp);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                dialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
