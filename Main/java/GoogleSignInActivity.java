package com.user.foodzamo;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.user.foodzamo.Register.Login;
import com.user.foodzamo.Register.UserProfile;
import com.user.foodzamo.Restuarants.RestuarantsList;

public class GoogleSignInActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);


        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Users");
        progressDialog=new ProgressDialog(this);

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

        btnSignIn.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {

    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            //String personName = acct.getDisplayName();
            //String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

registerUser(email);


        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
           // btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }
    private void registerUser( final String email)
    {


        //if validations are okk then proceed
        progressDialog.setMessage("Registering User...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,"123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully logged in
                            //we will start the profile activity here
                            ////right now lets display only toast
                            Toast.makeText(GoogleSignInActivity.this, "Success", Toast.LENGTH_SHORT).show();


                            String s = firebaseAuth.getCurrentUser().getUid();
                            String uid="";
                            for(int i=0;i<5;i++)
                            {
                                char c=s.charAt(i);
                                uid=uid+c;
                            }
                            DatabaseReference current_data = db.child(uid);


                            //display mail id and use mail id to know the user details or by firebase UID
                            //add data here
                            current_data.child("11FoodZamoPoints").setValue("100");
                            current_data.child("1Mail Id").setValue(email);
                            current_data.child("1Notifications").setValue("FoodZamo welcomes you!. Thanks for downloading the app. Looking forward to serve you.");
                            current_data.child("1MainBalanace").setValue("200");
                            current_data.child("The Mansingh Cashback").setValue("100");
                            current_data.child("FoodCourt Cashback").setValue("100");
                            current_data.child("Shri SouthExpress Cashback").setValue("100");
                            current_data.child("Punjabi Chilli Restuarant Cashback").setValue("100");
                            current_data.child("Yelllow Chilli Restuarant Cashback").setValue("100");
                            current_data.child("Cooks FastFood and Bakery").setValue("100");
                            current_data.child("Moti Mahal").setValue("100");
                            current_data.child("Volga Cashback").setValue("100");
                            current_data.child("Bamboo Cashback").setValue("100");
                            current_data.child("Bawarchi Cashback").setValue("100");
                            current_data.child("Virasat Cashback").setValue("100");
                            current_data.child("7Spice Cashback").setValue("100");
                            current_data.child("Bhukkads Cashback").setValue("100");
                            current_data.child("PariFoodZone Cashback").setValue("100");
                            current_data.child("FoodzInn Cashback").setValue("100");
                            current_data.child("Zayka Cashback").setValue("100");
                            current_data.child("Mejbaan").setValue("100");
                            current_data.child("Chawla Square").setValue("100");
                            current_data.child("New Mazbaan").setValue("100");
                            current_data.child("Chopal Chawla").setValue("100");

                            //promo codes
                            current_data.child("_coffee_promocodes").setValue("Use Promocode FZCF-123 and get upto 10% discont in coffeee shops");
                            current_data.child("_movies_promocodes").setValue("No Promocodes please visit our restuarants to get promocodes for movies");
                            current_data.child("_shopping_promocodes").setValue("This Sunday use the coupon ID FZ-1234");
                            current_data.child("_other_promocodes").setValue("No Promocodes please visit our restuarants to earn promocodes");

                            progressDialog.dismiss();



                            //Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),RestuarantsList.class));
                            //finish();
                            //Intent intent = new Intent(R.this, UserProfile.class);
                            //startActivity(intent);

                        } else {
                            Toast.makeText(GoogleSignInActivity.this, "Network Error!! try again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }
                    }
                });

    }
}
