package com.user.foodzamo.UI;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.foodzamo.R;


public class UIActivity extends Activity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
TextView restaurant_about,restaurant_location, restaurant_number;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        Bundle b = getIntent().getExtras();
        final String message = b.getString("key", "");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        imageView=(ImageView)findViewById(R.id.restaurant_image);
        //restauarant_about=(TextView)findViewById(R.id.restaurant_about);
        restaurant_about=(TextView)findViewById(R.id.restaurant_about_description);
        //restaurant_location=(TextView)findViewById(R.id.restaurant_location);
        restaurant_location=(TextView)findViewById(R.id.restaurant_location_description);
        //restaurant_contact=(TextView)findViewById(R.id.restaurant_contact);
        restaurant_number=(TextView)findViewById(R.id.restaurant_contact_description);

        if(message.equals("0")) {
            String about="This Food Court is located in DD mall. It is one of the biggest food court in Madhya Pradesh.It's seating has nice view around the gwalior zoo and park." +
                    "The food here is hygenic and clean.It has varieties of dishes like south indian, chinese, pizza etc." +
                    "It is famous for a dish named \"Raj Kachori\" loved by many foodies. For more updates follow us on facebook and " +
                    "visit our website!";
            imageView.setBackground(getResources().getDrawable(R.drawable.foodcourt_cover));
            collapsingToolbarLayout.setTitle("The City FoodCourt");
            restaurant_number.setText("0751-401 9463");
            restaurant_location.setText("Din Dayal City Mall,M.L.B Road,, Gwalior, Madhya Pradesh 474001");
            restaurant_about.setText(about);

        }
        else if(message.equals("1")) {
            String about="Man Singh is located in 3rd floor of DD Mall. The Ambience of the restaurent is very nice. When you" +
                    "enter inside you see a handmade huge painting on the wall very classy. Sitting area is very well setup." +
                    "service staff is very well groomed and well mannered.";
            imageView.setBackground(getResources().getDrawable(R.drawable.man_singh_cover));
            collapsingToolbarLayout.setTitle("The ManSingh");
            restaurant_number.setText("0751-4019404, 9589833379");
            restaurant_location.setText(" Maharani Laxmibai Marg,Deen Dayal Mall, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("2")) {
            String about="Moti Mahal is located next to regency hotel. Good location and parking facility." +
                    "It is same as other moti mahal restaurent in India. Well designed, good sitting space" +
                    "for long tables. Food is also very tasty and even good service is provided.";
            imageView.setBackground(getResources().getDrawable(R.drawable.motimahal_cover_pic));
            collapsingToolbarLayout.setTitle("Moti Mahal");
            restaurant_number.setText("088893 87788");
            restaurant_location.setText(" Near Bus stand , Hotel Regency Square area, New Balwant Nagar, Gwalior, Madhya Pradesh 474002");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("3")) {
            String about="Since 1971, Hakeems is known for preparing in traditional style blending the finest" +
                    "oriental spices to produce authentic cuisines having a variety of subtle and exotic" +
                    "flavours without over spicing.It is well known for \"Mutton Biryani\"";
            imageView.setBackground(getResources().getDrawable(R.drawable.hakeems));
            collapsingToolbarLayout.setTitle("Hakeems");
            restaurant_number.setText("0751-6450001");
            restaurant_location.setText("Shop No. 13/C, Kailash Vihar, Near Hotel Radience,, City Center, Gwalior, Madhya Pradesh 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("4")) {
            String about="This restaurant is well known for bringing the flavour of south india. The \"Hyderabadi Chicken Dum Biryani\"" +
                    " here makes the food lovers go crazy. The spices used for making dum her special and hygenic.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            collapsingToolbarLayout.setTitle("Shri South Express");
            restaurant_number.setText("9516166868, 9516166869");
            restaurant_location.setText("Pintu Park, Patel Plaza, Airport Road, Deen Dayal Nagar, Gwalior, Madhya Pradesh 474020");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("5")) {
            String about="Restaurant is opened near bal bhagvan. It is completely build with bamboo between the trees and also" +
                    " gives a a breathtaking view of captain roop singh stadium." +
                    " It is recently inspected by union minister with good reviews.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bamboo_cover_pic));
            collapsingToolbarLayout.setTitle("Bamboo Restuarant");
            restaurant_number.setText("0751-4066649");
            restaurant_location.setText("nfront of Bal Bhavan, near Railway Station, Gwalior");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("6")) {
            String about="The Food Factory, Gwalior opens" +
                    " its door to the fun loving Janta of Gwalior, offering Gwaliorities an unparalleled culinary adventure.";
            imageView.setBackground(getResources().getDrawable(R.drawable.food_factory_cover_pic));
            collapsingToolbarLayout.setTitle("The Food Factory");
            restaurant_number.setText("0751-4040486, +919425112486, +919039523111");
            restaurant_location.setText("Alkananda Tower-2, City Centre, 474011 Gwalior, India");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("7")) {
            String about="One of the finest multi cuisine restaurant in Gwalior City. It's just 2 kilometer from DD Mall. Home delivery and take away services are" +
                    " available. The restaurant is famous for its spiciness and taste. Make your taste buds go balle balle.Banquet Hall for parties available." +
                    "Follow us on Facebook! To know more about us visit wwww.punjabichilligwalior.com";
            imageView.setBackground(getResources().getDrawable(R.drawable.punjabi_chilli));
            collapsingToolbarLayout.setTitle("The Punjabi Chilli");
            restaurant_number.setText("+919425112486, +919039523111");
            restaurant_location.setText("Behind Old Chacha General Stores, Inderganj Chowk, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("8")) {
            String about="Decades old restaurant but still got its charm. You will never get tired of visiting this place." +
                    "Awesome menu. Freakingly low prices .Finger licking food. Non veg quantity is more than you can" +
                    "expect per plate. Good and quick parcel service too.";
            imageView.setBackground(getResources().getDrawable(R.drawable.volga_cover));
            collapsingToolbarLayout.setTitle("Volga Restuarant");
            restaurant_number.setText("0751 408 7100");
            restaurant_location.setText("Jayendraganj, Shinde Ki Chhawani, Beside Syndicate Bank, Gwalior, Madhya Pradesh 474001");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("9")) {
            String about="It is near by to dd mall. The food here is delicious and hygenic." +
                    "It has good staff with good ambience.";
            imageView.setBackground(getResources().getDrawable(R.drawable.virasat_cover_pic));
            collapsingToolbarLayout.setTitle("Virasat Restuarant");
            restaurant_number.setText("094251 09101");
            restaurant_location.setText("Roshni Ghar Mohalla, Lashkar, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("10")) {
            String about="It is present in busiest area of gwalior lashkar." +
                    "Its food is famous for starters." +
                    "It has good ambience..";
            imageView.setBackground(getResources().getDrawable(R.drawable.bawarchi_cover_pic));
            collapsingToolbarLayout.setTitle("Bawarchi Xpress");
            restaurant_number.setText(" 0751-4011322");
            restaurant_location.setText("41, Manik Vilas Colony, Lashkar, Near Hotel Landmark, Gwalior, Madhya Pradesh 474002");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("11")) {
            String about="The best place to hangout with family and friends." +
                    "It is well known for its spicy lavish food. Paneer dishes are famous here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.spice_cover_pic));
            collapsingToolbarLayout.setTitle("7 Spice Restuarant");
            restaurant_number.setText("099810 22744");
            restaurant_location.setText("Lalitpur Colony, Lashkar, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("12")) {
            String about="It is near phool bagh front of manas bhawan.Its architecture is excellent." +
                    "The ambience inside the hotel will attarct you. The food here is delicious.";
            imageView.setBackground(getResources().getDrawable(R.drawable.parifoodzone_cover_pic));
            collapsingToolbarLayout.setTitle("Pari FoodZone & Restuarant");
            restaurant_number.setText("0751-4061574");
            restaurant_location.setText("In Front Of Manas Bhawan, M.L.B Road, Phool Bagh, 474001 Gwalior, India");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("13")) {
            String about="Awesome place to hang out with . You will be finding best cakes here." +
                    "The chinese cuisine is famous with lot of customers.";
            imageView.setBackground(getResources().getDrawable(R.drawable.cooks_cover_pic));
            collapsingToolbarLayout.setTitle("Cooks FastFood and Bakery");
            restaurant_number.setText("099930 41410");
            restaurant_location.setText("Patankar Bazaar, Maina Wali Gali, Khurjewala Mohalla, Lashkar, Gwalior, Madhya Pradesh 474001");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("14")) {
            String about="Shree bhukkads makes you feel more hungry as soon as you enter this place." +
                    "And the cuisines leave you craving for more everytime.The staff is very co-operative and service.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bhukkads_cover_pic));
            collapsingToolbarLayout.setTitle("Shree Bhukkads Food Lounge");
            restaurant_number.setText("080979 63797");
            restaurant_location.setText("Near Center Point Complex, Sarwoday Girls Hostel, Phoolbagh, 474001 Gwalior, India");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("15")) {
            String about="Restaurant serves the best quality of food with a wide variety in north" +
                    "indian.South indian as well as chinese food.Foodz inn is an iso 9001:2008" +
                    "certified restaurant which also deals in bakery and fast food." +
                    "WEBSITE:http://foodzinn.co.in/." ;
            imageView.setBackground(getResources().getDrawable(R.drawable.foodzin_cover_pic));
            collapsingToolbarLayout.setTitle("Foodz Inn Restuarant");
            restaurant_number.setText("0751-4010632");
            restaurant_location.setText("Ganesh Complex, Near Parimal Complex, AG Office Rd, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("16")) {
            String about="The food here is very delicious and hygenic. It also have accomodation facility." +
                    "The rate here is cheap with quality food.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            collapsingToolbarLayout.setTitle("Hotel SunBeam, Mejbaan Restuarant");
            restaurant_number.setText("0751-2210202");
            restaurant_location.setText("City Center, Mahalgaon, Beside Honda Showroom, Gwalior, Madhya Pradesh 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("17")) {
            String about="You will be introduced with actual flavour of punjabi here." +
                    "The non veg curries with roti naan here is treat for foodies.";
            imageView.setBackground(getResources().getDrawable(R.drawable.chawla_square));
            collapsingToolbarLayout.setTitle("Chawla Square Restuarant");
            restaurant_number.setText("0751-4028901, 9977007162");
            restaurant_location.setText("City Center, Patel Nagar, Gwalior, Madhya Pradesh 474002\nGoleka Mandir, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("18")) {
            String about="Lots of varieties available here with good ambience." +
                    "Starters are well known here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            collapsingToolbarLayout.setTitle("New Mezbaan Restuarant");
            restaurant_number.setText("+919826227400");
            restaurant_location.setText("Lalitpur Colony, Lashkar, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("19")) {
            String about="Leading non vegetarian food chain that is highly popular because of its exotic spices." +
                    "Fish tikka ,mutton masala ,chicken masala and many more are famous here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.copalchawla_logo));
            collapsingToolbarLayout.setTitle("Chopal Chick-Inn Restuarant");
            restaurant_number.setText("0751-4048800");
            restaurant_location.setText("City Center, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("20")) {
            String about="It is one of the best restaurant in gwalior. Know for its starters." +
                    "It is located in busiest area in gwalior." +
                    "It has good ambience.";
            imageView.setBackground(getResources().getDrawable(R.drawable.food_dessert));
            collapsingToolbarLayout.setTitle("Food & Dessert Restuarant");
            restaurant_number.setText("097700 07730");
            restaurant_location.setText("Baijnath Apartments, Beside Millenium Plaza, University Rd, Govindpuri, 474011 Gwalior, India");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("21")) {
            String about="One and Only Barbeque Hut in Gwalior.U can find different varieties of delicious barbeques here." +
                    "The ambience and experience here will leave you delighted.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bbqhut));
            collapsingToolbarLayout.setTitle("BBQ Hut");
            restaurant_number.setText("094257 81319");
            restaurant_location.setText("91, University Rd, Vivekanand Colony, Thatipur, Gwalior, Madhya Pradesh 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("22")) {
            String about="This place offers one of the better choices of cusines in the city of Gwalior. The food is piping hot and tastes awesome. " +
                    "Situated in phoolbagh, this vegetarian friendly restaurant provides a wide range of chinese and indian dishes";
            imageView.setBackground(getResources().getDrawable(R.drawable.zayka_cover_pic));
            collapsingToolbarLayout.setTitle("Zayka Family Restaurant");
            restaurant_number.setText("7512434785");
            restaurant_location.setText("Opposite Bank of India,Phool Bagh, Gwalior, Madhya Pradesh 474002");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("23")) {
            String about="South Indian delicious dishes available here." +
                    "The meals here resemble the south cuisine and the ambience here" +
                    "is good.";
            imageView.setBackground(getResources().getDrawable(R.drawable.indian_meal_cover_pic));
            collapsingToolbarLayout.setTitle("Indian Meal Restuarant");
            restaurant_number.setText("9691676684");
            restaurant_location.setText("Gate no 2, Infornt of Central Bank ATM, City Centre, Gwalior - 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("24")) {
            String about="This restaurant is meant for pure vegetarian lovers." +
                    "There are varied vegetarian dishes available here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.royalview_cover_pic));
            collapsingToolbarLayout.setTitle("Royal View Restuarant");
            restaurant_number.setText("0751-2434445");
            restaurant_location.setText("11, New Govindpuri, Sachin Tendulkar Road, 474001 Gwalior, India");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("25")) {
            String about="The spicy tadka dishes are famous here. The spices here used are epic!!" +
                    "U will experience spicy corner in each and every dish.";
            imageView.setBackground(getResources().getDrawable(R.drawable.thadka_cover_pic));
            collapsingToolbarLayout.setTitle("Thadka Restuarant");
            restaurant_number.setText("+919826488444");
            restaurant_location.setText("Plot no. 15 , Patel nagar , City Centre, Gwalior - 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("26")) {
            String about="Well known for its non veg dishes.U will be elated by the chicken" +
                    "dishes available here. The rates here are cheap and the quality is best.";
            imageView.setBackground(getResources().getDrawable(R.drawable.albaek));
            collapsingToolbarLayout.setTitle("Albaek Restuarant");
            restaurant_number.setText("Not Available");
            restaurant_location.setText("Near Income Tax Office, City Center, Gwalior, Madhya Pradesh 474009");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("27")) {
            String about="Good ambience and quality of food is available here." +
                    "Good staff and service. Cheap rates with delicious food serving.";
            imageView.setBackground(getResources().getDrawable(R.drawable.pavitra_cover_pic));
            collapsingToolbarLayout.setTitle("Pavitra Restuarant");
            restaurant_number.setText("+919301603999");
            restaurant_location.setText("Green Garden Road, City Center , Gwalior - 474011");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("28")) {
            String about="Sai bhoj restaurant a family run restaurant which has been serving" +
                    " high quality indian vegetarian food ." +
                    "WEBSITE:http://egwalior.in/saibhoj";
            imageView.setBackground(getResources().getDrawable(R.drawable.saibhoj_logo));
            collapsingToolbarLayout.setTitle("Sai Bhoj Restuarant");
            restaurant_number.setText("0751-4033293, 9826426077");
            restaurant_location.setText("Railway Station Road, Gwalior, Madhya Pradesh 474002");
            restaurant_about.setText(""+about);
        }
        else if(message.equals("29")) {
            String about="Different milk shakes are available here. With varied " +
                    "desserts and salads.";
            imageView.setBackground(getResources().getDrawable(R.drawable.saifruitsake_logo));
            collapsingToolbarLayout.setTitle("Sai Fruit Sake Restuarant");
            restaurant_number.setText("0751-4033293, 9826426077");
            restaurant_location.setText("Railway Station Road, Gwalior, Madhya Pradesh 474002");
            restaurant_about.setText(""+about);
        }
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
       //actionBar.setDisplayHomeAsUpEnabled(true);






        dynamicToolbarColor();

        toolbarTextAppernce();
    }


    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.foodcourt_cover);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
