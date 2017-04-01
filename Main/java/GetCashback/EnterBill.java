package com.user.foodzamo.GetCashback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.foodzamo.MainActivity;
import com.user.foodzamo.MerchActivity;
import com.user.foodzamo.MerchantHomeDelivery;
import com.user.foodzamo.OrderFood.RestuarantsMenus.OrderMessage;
import com.user.foodzamo.R;
import com.user.foodzamo.Register.Cashback;
import com.user.foodzamo.Register.HomeDeliv;
import com.user.foodzamo.Register.Login;
import com.user.foodzamo.Register.User;
import com.user.foodzamo.Register.UserProfile;

public class EnterBill extends Activity {
EditText editText;
    Button button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    String bill_number;
    EditText edit_text_bill_amount,edit_text_discount;
    TextView textView;
    String previous_amount="0";
    String user_id,cashback_amount,cashback_percentage;
    String message;
    int prev;
    String fin="";
    String restro="";
    DatabaseReference databaseReference2=null;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_enter_bill);

        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("BillNumbers");
        progressDialog=new ProgressDialog(this);

        editText=(EditText)findViewById(R.id.edit_text_user_id);
        button=(Button)findViewById(R.id.proceed_button);
        edit_text_bill_amount=(EditText)findViewById(R.id.edit_text_amount);
        edit_text_discount=(EditText)findViewById(R.id.edit_text_discount);

        Bundle b = getIntent().getExtras();
        message = b.getString("order", "");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill_number=edit_text_bill_amount.getText().toString();
                if(checkconnection()==0)
                {
                    Toast.makeText(EnterBill.this, "No Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(edit_text_bill_amount.getText().toString())||TextUtils.isEmpty(edit_text_discount.getText().toString()))
                {
                    Toast.makeText(EnterBill.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(editText.getText().toString()))
                {
                    Toast.makeText(EnterBill.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(editText.getText().toString()))
                {
                    Toast.makeText(EnterBill.this, "User Id length is 5 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog alertDialog = new AlertDialog.Builder(EnterBill.this).create();
                alertDialog.setTitle("Alert Dialog");
                String s="User ID: "+editText.getText().toString()+"\n"+"Bill Amount: Rs."+edit_text_bill_amount.getText().toString()+"/-\n";
                s=s+"Cashback: "+edit_text_discount.getText().toString()+"%\n";

                alertDialog.setMessage("Details:\n\n"+s);
                alertDialog.setIcon(R.drawable.ic_info_black_24dp);

                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();

                        progressDialog.setMessage("Loading....");
                        progressDialog.show();
                        //registerUser();
                        final String s = editText.getText().toString();
                        String x="";

                        user_id=editText.getText().toString();
                        cashback_amount=edit_text_bill_amount.getText().toString();
                        cashback_percentage=edit_text_discount.getText().toString();

                        fin="\n\nCashback Details:\n\nUser Id: "+user_id+"\n"+"\nAmount: "+cashback_amount+"\nCashback Percentage: "
                                +cashback_percentage+"\n\n--------------------------------------------------\n";
                        DatabaseReference referenceFromUrl=null;

                        if(message.equals("The ManSingh"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The ManSingh/details");
                        if(message.equals("The City FoodCourt"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The City FoodCourt/details");
                        if(message.equals("Shri South Express"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Shri South Express/details");
                        if(message.equals("Punjabi Chilli"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/The Punjabi Chilli/details");
                        if(message.equals("Cooks FastFood and Bakery"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Cooks FastFood/details");
                        if(message.equals("Bawarchi Xpress"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Bawarchi/details");
                        if(message.equals("Volga Restuarant"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Volga Restuarant/details");
                        if(message.equals("Bamboo Restuarant"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Bamboo Restuarant/details");
                        if(message.equals("Bawarchi Xpress"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Bawarchi Xpress/details");
                        if(message.equals("Virasat Restuarant"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Virasat Restuarant/details");
                        if(message.equals("7 Spice Restuarant"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/7 Spice Restuarant/details");
                        if(message.equals("Shri Bhukkads"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Shri Bhukkads/details");
                        if(message.equals("Pari FoodZone"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Pari FoodZone/details");
                        if(message.equals("Foodz Inn"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Foodz Inn/details");
                        if(message.equals("Zayka Restuarant"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Zayka Restuarant/details");
                        if(message.equals("Mejbaan"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Mejbaan/details");
                        if(message.equals("Chawla Square"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Chawla Square/details");
                        if(message.equals("New Mazbaan"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/New Mazbaan/details");
                        if(message.equals("FoodDessert"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/FoodDessert/details");
                        if(message.equals("FoodFactory"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/FoodFactory/details");
                        if(message.equals("BBQHut"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/BBQHut/details");
                        if(message.equals("IndianMeal"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/IndianMeal/details");
                        if(message.equals("RoyalView"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/RoyalView/details");
                        if(message.equals("Hakeems"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Hakeems/details");
                        if(message.equals("Pavitra"))
                            referenceFromUrl = FirebaseDatabase.getInstance().
                                    getReferenceFromUrl("https://foodzamo-80ed4.firebaseio.com/Cashbacks/Pavitra/details");






                        referenceFromUrl.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s=(String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                fin=fin+s;
                                writeUser(fin);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });




                        //DatabaseReference current_data = db.child(s);
                        writeNewUser(s,message,bill_number);

                        DatabaseReference user_amount= FirebaseDatabase.getInstance().getReference().child("Users");
                        final DatabaseReference current_data = user_amount.child(s);
                        String cashback=edit_text_bill_amount.getText().toString();
                        final int g= Integer.parseInt(cashback);
                        String url="";

                        if(message.equals("The ManSingh"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/The Mansingh Cashback";
                        if(message.equals("The City FoodCourt"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/FoodCourt Cashback";
                        if(message.equals("Shri South Express"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Shri SouthExpress Cashback";
                        if(message.equals("Punjabi Chilli"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Punjabi Chilli Restuarant Cashback";
                        if(message.equals("The Yellow Chilli"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Yelllow Chilli Restuarant Cashback";
                        if(message.equals("Moti Mahal"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Moti Mahal";
                        if(message.equals("Cooks FastFood and Bakery"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Cooks FastFood and Bakery";
                        if(message.equals("Bawarchi"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Bawarchi Xpress";
                        if(message.equals("Volga Restuarant"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Volga Cashback";
                        if(message.equals("Bamboo Restuarant"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Bamboo Cashback";
                        if(message.equals("Bawarchi Xpress"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Bawarchi Cashback";
                        if(message.equals("Virasat Restuarant"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Virasat Cashback";
                        if(message.equals("7 Spice Restuarant"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/7Spice Cashback";
                        if(message.equals("Shri Bhukkads"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Bhukkads Cashback";
                        if(message.equals("Pari FoodZone"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/PariFoodZone Cashback";
                        if(message.equals("Foodz Inn"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/FoodzInn Cashback";
                        if(message.equals("Zayka Restuarant"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Zayka Cashback";
                        if(message.equals("Mejbaan"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Mejbaan";
                        if(message.equals("Chawla Square"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Chawla Square";
                        if(message.equals("New Mazbaan"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/New Mazbaan";
                        if(message.equals("FoodDessert"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/FoodDessert";
                        if(message.equals("FoodFactory"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/FoodFactory";
                        if(message.equals("BBQHut"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/BBQHut";
                        if(message.equals("IndianMeal"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/IndianMeal";
                        if(message.equals("RoyalView"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/RoyalView";
                        if(message.equals("Hakeems"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Hakeems";
                        if(message.equals("Pavitra"))
                            url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/Pavitra";



                        DatabaseReference databaseReference1=null;
                        databaseReference1 = FirebaseDatabase.getInstance().
                                getReferenceFromUrl(url);
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                previous_amount=(String) dataSnapshot.getValue();
                                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                                //textView.setText(s);
                                String d=edit_text_discount.getText().toString();
                                final int h= Integer.parseInt(d);
                                progressDialog.dismiss();
                                prev= Integer.parseInt(previous_amount);
                                prev=prev+(g*h)/100;
                                String insert_value= String.valueOf(prev);

                                String[] csh_backs={"The Mansingh Cashback","FoodCourt Cashback","Shri SouthExpress Cashback",
                                        "Punjabi Chilli Restuarant Cashback","Yelllow Chilli Restuarant Cashback",
                                        "Cooks FastFood and Bakery","Moti Mahal", "Volga Cashback",
                                        "Bamboo Cashback", "Bawarchi Cashback","Virasat Cashback","7Spice Cashback",
                                        "Bhukkads Cashback","PariFoodZone Cashback","FoodzInn Cashback","Zayka Cashback",
                                        "Mejbaan","Chawla Square","New Mazbaan","Chopal Chawla",
                                "FoodDessert","FoodFactory","BBQHut","IndianMeal","RoyalView","Hakeems","WildChefHouse",
                                "MughalDarbar","Heavens","Pavitra"};

                                if(message.equals("The ManSingh"))
                                    restro=csh_backs[0];
                                if(message.equals("The City FoodCourt"))
                                    restro=csh_backs[1];
                                if(message.equals("Shri South Express"))
                                    restro=csh_backs[2];
                                if(message.equals("Punjabi Chilli"))
                                    restro=csh_backs[3];
                                if(message.equals("The Yellow Chilli"))
                                    restro=csh_backs[4];
                                if(message.equals("Cooks FastFood and Bakery"))
                                    restro=csh_backs[5];
                                if(message.equals("Moti Mahal"))
                                    restro=csh_backs[6];
                                if(message.equals("Volga Restuarant"))
                                    restro=csh_backs[7];
                                if(message.equals("Bamboo Restuarant"))
                                    restro=csh_backs[8];
                                if(message.equals("Bawarchi Xpress"))
                                    restro=csh_backs[9];
                                if(message.equals("Virasat Restuarant"))
                                    restro=csh_backs[10];
                                if(message.equals("7 Spice Restuarant"))
                                    restro=csh_backs[11];
                                if(message.equals("Shri Bhukkads"))
                                    restro=csh_backs[12];
                                if(message.equals("Pari FoodZone"))
                                    restro=csh_backs[13];
                                if(message.equals("Foodz Inn"))
                                    restro=csh_backs[14];
                                if(message.equals("Zayka Restuarant"))
                                    restro=csh_backs[15];
                                if(message.equals("Mejbaan"))
                                    restro=csh_backs[16];
                                if(message.equals("Chawla Square"))
                                    restro=csh_backs[17];
                                if(message.equals("New Mazbaan"))
                                    restro=csh_backs[18];
                                if(message.equals("Chopal Chawla"))
                                    restro=csh_backs[19];
                                if(message.equals("FoodDessert"))
                                    restro=csh_backs[20];
                                if(message.equals("FoodFactory"))
                                    restro=csh_backs[21];
                                if(message.equals("BBQHut"))
                                    restro=csh_backs[22];
                                if(message.equals("IndianMeal"))
                                    restro=csh_backs[23];
                                if(message.equals("RoyalView"))
                                    restro=csh_backs[24];
                                if(message.equals("Hakeems"))
                                    restro=csh_backs[25];
                                if(message.equals("WildChefHouse"))
                                    restro=csh_backs[26];
                                if(message.equals("MughalDarbar"))
                                    restro=csh_backs[27];
                                if(message.equals("Heavens"))
                                    restro=csh_backs[28];
                                if(message.equals("Pavitra"))
                                    restro=csh_backs[29];





                                current_data.child(restro).setValue(insert_value);
                                Toast.makeText(EnterBill.this,"Success",Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(EnterBill.this).setTitle("Quit!").setMessage("Notify the User "+g/h).setCancelable(true)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Toast.makeText(getApplicationContext(),"g value "+g+"\nh value "+h,Toast.LENGTH_LONG).show();
                                                //System.exit(0);
                                                String url="https://foodzamo-80ed4.firebaseio.com/Users/"+s;
                                                DatabaseReference databaseReference1=null;
                                                databaseReference1 = FirebaseDatabase.getInstance().
                                                        getReferenceFromUrl(url);
                                                int p=(g*h)/100;
                                                flag=1;
                                                databaseReference1.child("1Notifications").setValue("Dear Customer, your wallet is credited with Rs."+p+" from "+message);
                                                progressDialog.setMessage("Crediting Points...");
                                                progressDialog.setCancelable(false);
                                                progressDialog.show();


                                                String  points_url="https://foodzamo-80ed4.firebaseio.com/Users/"+s+"/11FoodZamoPoints";


                                                databaseReference2 = FirebaseDatabase.getInstance().
                                                        getReferenceFromUrl(points_url);

                                                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String previous_points=(String) dataSnapshot.getValue();
                                                        String amount=edit_text_bill_amount.getText().toString();
                                                        final int a= Integer.parseInt(amount);
                                                        int points=a/10;
                                                        int prev= Integer.parseInt(previous_points);
                                                        int total_points=prev+points;
                                                        String tot_points= String.valueOf(total_points);
                                                        databaseReference2.setValue(tot_points);
                                                        Toast.makeText(getApplicationContext(),"Points Credited",Toast.LENGTH_SHORT).show();
                                                        progressDialog.dismiss();
                                                        //Toast.makeText(EnterBill.this,"Success",Toast.LENGTH_SHORT).show();
                                                        //progressDialog.dismiss();
                                                        Intent i = new Intent(getApplicationContext(),MerchActivity.class);
                                                        i.putExtra("key", message);
                                                        startActivity(i);

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                textView.setText("Some error occured please try again!");
                            }
                        });
                    }
                });

                alertDialog.show();







                //startActivity(new Intent(EnterBill.this, UserProfile.class));
            }
        });

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        db.child(userId).setValue(user);

        //startActivity(new Intent(EnterBill.this,MessageActivity.class));

    }

   /* @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(EnterBill.this, MainActivity.class));
    }*/
    public int checkconnection()
    {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
            return 1;
            //tvstatus.setText("Network Available");
        }
        else
        {
            // Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
            return 0;
            //tvstatus.setText("Network Not Available");
        }
    }
    private void writeUser(String details) {
        Cashback user = new Cashback(details);

        if(message.equals("The ManSingh")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("The ManSingh");
        }
        if(message.equals("The City FoodCourt")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("The City FoodCourt");
        }
        if(message.equals("Shri South Express")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Shri South Express");
        }
        if(message.equals("Punjabi Chilli")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("The Punjabi Chilli");
        }
        if(message.equals("Cooks FastFood and Bakery")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Cooks FastFood");
        }
        if(message.equals("Volga Restuarant")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Volga Restuarant");
        }
        if(message.equals("Bamboo Restuarant")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Bamboo Restuarant");
        }
        if(message.equals("Bawarchi Xpress")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Bawarchi Xpress");
        }
        if(message.equals("Virasat Restuarant")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Virasat Restuarant");
        }
        if(message.equals("7 Spice Restuarant")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("7 Spice Restuarant");
        }
        if(message.equals("Shri Bhukkads")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Shri Bhukkads");
        }
        if(message.equals("Pari FoodZone")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Pari FoodZone");
        }
        if(message.equals("Foodz Inn")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Foodz Inn");
        }
        if(message.equals("Zayka Restuarant")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Zayka Restuarant");
        }
        if(message.equals("Mejbaan")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Mejbaan");
        }
        if(message.equals("Chawla Square")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Chawla Square");
        }
        if(message.equals("New Mazbaan")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("New Mazbaan");
        }
        if(message.equals("FoodDessert")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("FoodDessert");
        }
        if(message.equals("FoodFactory")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("FoodFactory");
        }
        if(message.equals("BBQHut")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("BBQHut");
        }
        if(message.equals("IndianMeal")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("IndianMeal");
        }
        if(message.equals("RoyalView")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("RoyalView");
        }
        if(message.equals("Hakeems")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Hakeems");
        }
        if(message.equals("Pavitra")) {
            db = FirebaseDatabase.getInstance().getReference().child("Cashbacks").child("Pavitra");
        }

        db.setValue(user);

        //startActivity(new Intent(CheckOut.this,OrderMessage.class));

    }
}
