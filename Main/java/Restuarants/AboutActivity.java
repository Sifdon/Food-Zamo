package com.user.foodzamo.Restuarants;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.R;

import org.w3c.dom.Text;

public class AboutActivity extends Activity {
    ImageView imageView, calling_button;

    TextView restuarant_name, restuarant_number, restuarant_location;
    TextView restuarant_about;
    RestuarantAbout restuarantAbout;
    String[] phone_numbers = {"111111", "222222", "33333", "444444", "55555", "666666", "77777", "88888", "99999", "000000","12345","345678"};
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Bundle b = getIntent().getExtras();
        final String message = b.getString("key", "");
        x = Integer.parseInt(message);
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        imageView = (ImageView) findViewById(R.id.background_image);
        restuarant_name = (TextView) findViewById(R.id.restuarant_name);
        //restuarant_mail = (TextView) findViewById(R.id.restuarnt_email);
        restuarant_number = (TextView) findViewById(R.id.restuarant_number);
        restuarant_location = (TextView) findViewById(R.id.resuarant_location);
        restuarant_about = (TextView) findViewById(R.id.restuarant_about);
        calling_button = (ImageView) findViewById(R.id.calling_button);

        restuarantAbout = new RestuarantAbout();


        if(message.equals("0")) {
            String about="This food court is present in DD mall.Its seating has nice view around the gwalior zoo and park.\n" +
                    "The food here is hygenic and clean.It has varieites of dishes like south indian, chinese, pizza etc.\n" +
                    "It is known for a dish named \"Raj Kachori\" loved by many foodies. For more updates follow us on facebook and " +
                    "visit our website!";
           imageView.setBackground(getResources().getDrawable(R.drawable.foodcourt_cover));
            restuarant_name.setText("The City FoodCourt");
            restuarant_number.setText("Phone:\n0751-401 9463");
            restuarant_location.setText("Address:\nDindayal Mall, 3rd Floor, Din Dayal City Mall,M.L.B Road,, Gwalior, Madhya Pradesh 474001");
            restuarant_about.setText("About the Restuarant:\n\n"+about);

        }
        else if(message.equals("1")) {
            String about="The ManSingh is located in 3rd floor of DD Mall. The Ambience of the restaurant is very nice. When you\n" +
                    "enter inside you see a handmade huge painting on the wall very classy. Sitting area is very well setup.\n" +
                    "service staff is very well groomed and well mannered.";
            imageView.setBackground(getResources().getDrawable(R.drawable.man_singh_cover));
            restuarant_name.setText("The ManSingh");
            restuarant_number.setText("Phone:\n0751 405 5404");
            restuarant_location.setText("Address:\n Maharani Laxmibai Marg,Deen Dayal Mall, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("2")) {
            String about="Moti Mahal is located next to regency hotel. Good location and parking facility.\n" +
                    "It is same as other moti mahal restaurent in India. Well designed, good sitting space\n" +
                    "for long tables. Food is also very tasty and even good service is provided.";
            imageView.setBackground(getResources().getDrawable(R.drawable.motimahal_cover_pic));
            restuarant_name.setText("Moti Mahal");
            restuarant_number.setText("Phone:\n088893 87788");
            restuarant_location.setText("Address:\n Near Bus stand , Hotel Regency Square area, New Balwant Nagar, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("3")) {
            String about="Since 1971, Hakeems is known for preparing in traditional style blending the finest\n" +
                    "oriental spices to produce authentic cuisines having a variety of subtle and exotic\n" +
                    "flavours without over spicing.It is well known for \"Mutton Biryani\"";
            imageView.setBackground(getResources().getDrawable(R.drawable.hakeems));
            restuarant_name.setText("Hakeems");
            restuarant_number.setText("Phone:\n0751 645 0001");
            restuarant_location.setText("Address:\nShop No. 13/C, Kailash Vihar, Near Hotel Radience,, City Center, Gwalior, Madhya Pradesh 474011\n\n");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("4")) {
            String about="This restaurant is well known for bringing the flavour of south india. The \"Hyderabadi Chicken Dum Biryani\"\n" +
                    "Her makes the food lovers go crazy. The spices used for making dum her special and hygenic.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("Shri South Express");
            restuarant_number.setText("Phone:\n098263 89104");
            restuarant_location.setText("Address:\nPintu Park, Patel Plaza, Airport Road, Deen Dayal Nagar, Gwalior, Madhya Pradesh 474020");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("5")) {
            String about="Restaurant is opened near bal bhagvan. It is completely build with bamboo between the trees and also" +
                    " gives a a breathtaking view of captain roop singh stadium." +
                    " It is recently inspected by union minister with good reviews.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bamboo_cover_pic));
            restuarant_name.setText("Bamboo Restaurant");
            restuarant_number.setText("Phone:\n0751-4066649");
            restuarant_location.setText("Address:\nnfront of Bal Bhavan, near Railway Station, Gwalior");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("6")) {
            String about="The Food Factory, Gwalior opens" +
                    " its door to the fun loving Janta of Gwalior, offering Gwaliorities an unparalleled culinary adventure.";
            imageView.setBackground(getResources().getDrawable(R.drawable.food_factory_cover_pic));
            restuarant_name.setText("The Food Factory");
            restuarant_number.setText("Phone:\n0751-4040486\n+919425112486\n+919039523111");
            restuarant_location.setText("Address:\nAlkananda Tower-2, City Centre, 474011 Gwalior, India");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("7")) {
            String about="One of the finest multi cuisine restuarant in Gwalior City. It's just 2 kilometer from DD Mall. Home delivery and take away services are" +
                    " available. The restuarant is famous for its spiciness and taste. Make your taste buds go balle balle.Banquet Hall for parties available.\n" +
                    "Follow us on Facebook! To know more about us visit wwww.punjabichilligwalior.com";
            imageView.setBackground(getResources().getDrawable(R.drawable.punjabi_chilli));
            restuarant_name.setText("The Punjabi Chilli");
            restuarant_number.setText("Phone:\n0751-4040486\n+919425112486\n+919039523111");
            restuarant_location.setText("Address:\nBehind Old Chacha General Stores, Inderganj Chowk, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("8")) {
            String about="Decades old restaurant but still got its charm. You will never get tired of visiting this place.\n" +
                    "Awesome menu. Freakingly low prices .Finger licking food. Non veg quantity is more than you can\n" +
                    "expect per plate. Good and quick parcel service too.";
            imageView.setBackground(getResources().getDrawable(R.drawable.volga_cover));
            restuarant_name.setText("Volga Restuarant");
            restuarant_number.setText("Phone:\n0751 408 7100");
            restuarant_location.setText("Address:\nJayendraganj, Shinde Ki Chhawani, Beside Syndicate Bank, Gwalior, Madhya Pradesh 474001");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("9")) {
            String about="It is near by to dd mall. The food here is delicious and hygenic.\n" +
                    "It has good staff with good ambience.";
            imageView.setBackground(getResources().getDrawable(R.drawable.virasat_cover_pic));
            restuarant_name.setText("Virasat Restuarant");
            restuarant_number.setText("Phone:\n094251 09101");
            restuarant_location.setText("Address:\nRoshni Ghar Mohalla, Lashkar, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("10")) {
            String about="It is present in busiest area of gwalior lashkar.\n" +
                    "Its food is famous for starters.\n" +
                    "It has good ambience..";
            imageView.setBackground(getResources().getDrawable(R.drawable.bawarchi_cover_pic));
            restuarant_name.setText("Bawarchi Xpress");
            restuarant_number.setText("Phone:\n 0751 401 1322");
            restuarant_location.setText("Address:\n41, Manik Vilas Colony, Lashkar, Near Hotel Landmark, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("11")) {
            String about="The best place to hangout with family and friends.\n" +
                    "It is well known for its spicy lavish food. Paneer dishes are famous here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.spice_cover_pic));
            restuarant_name.setText("7 Spice Restuarant");
            restuarant_number.setText("Phone:\n099810 22744");
            restuarant_location.setText("Address:\nLalitpur Colony, Lashkar, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("12")) {
            String about="It is near phool bagh front of manas bhawan.Its architecture is excellent.\n" +
                    "The ambience inside the hotel will attarct you. The food here is delicious.";
            imageView.setBackground(getResources().getDrawable(R.drawable.parifoodzone_cover_pic));
            restuarant_name.setText("Pari FoodZone & Restuarant");
            restuarant_number.setText("Phone:\n0751 406 1574");
            restuarant_location.setText("Address:\nIn Front Of Manas Bhawan, M.L.B Road, Phool Bagh, 474001 Gwalior, India");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("13")) {
            String about="Awesome place to hang out with . You will be finding best cakes here.\n" +
                    "The chinese cuisine is famous with lot of customers.";
            imageView.setBackground(getResources().getDrawable(R.drawable.cooks_cover_pic));
            restuarant_name.setText("Cooks FastFood and Bakery");
            restuarant_number.setText("Phone:\n099930 41410");
            restuarant_location.setText("Address:\nPatankar Bazaar, Maina Wali Gali, Khurjewala Mohalla, Lashkar, Gwalior, Madhya Pradesh 474001");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("14")) {
            String about="Shree bhukkads makes you feel more hungry as soon as you enter this place.\n" +
                    "And the cuisines leave you craving for more everytime.The staff is very co-operative and service.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bhukkads_cover_pic));
            restuarant_name.setText("Shree Bhukkads Food Lounge");
            restuarant_number.setText("Phone:\n 080979 63797");
            restuarant_location.setText("Address:\nNear Center Point Complex, Sarwoday Girls Hostel, Phoolbagh, 474001 Gwalior, India");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("15")) {
            String about="Restaurant serves the best quality of food with a wide variety in north\n" +
                    "indian.South indian as well as chinese food.Foodz inn is an iso 9001:2008\n" +
                    "certified restaurant which also deals in bakery and fast food.\n" +
                    "WEBSITE:http://foodzinn.co.in/.\n" ;
            imageView.setBackground(getResources().getDrawable(R.drawable.foodzin_cover_pic));
            restuarant_name.setText("Foodz Inn Restuarant");
            restuarant_number.setText("Phone:\n0751 401 0632");
            restuarant_location.setText("Address:\nGanesh Complex, Near Parimal Complex, AG Office Rd, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("16")) {
            String about="The food here is very delicious and hygenic. It also have accomodation facility.\n" +
                    "The rate here is cheap with quality food.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("Hotel SunBeam, Mejbaan Restuarant");
            restuarant_number.setText("Phone:\n0751 221 0202");
            restuarant_location.setText("Address:\nCity Center, Mahalgaon, Beside Honda Showroom, Gwalior, Madhya Pradesh 474011");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("17")) {
            String about="You will be introduced with actual flavour of punjabi here.\n" +
                    "The non veg curries with roti naan here is treat for foodies.";
            imageView.setBackground(getResources().getDrawable(R.drawable.chawla_square));
            restuarant_name.setText("Chawla Square Restuarant");
            restuarant_number.setText("Phone:\n099106 50090");
            restuarant_location.setText("Address:\nCity Center, Patel Nagar, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("18")) {
            String about="Lots of varieties available here with good ambience.\n" +
                    "Starters are well known here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("New Mezbaan Restuarant");
            restuarant_number.setText("Phone:\n+919826227400");
            restuarant_location.setText("Address:\nLalitpur Colony, Lashkar, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("19")) {
            String about="Leading non vegetarian food chain that is highly popular because of its exotic spices.\n" +
                    "Fish tikka ,mutton masala ,chicken masala and many more are famous here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("Chopal Chick-Inn Restuarant");
            restuarant_number.setText("Phone:\n0751-4048800");
            restuarant_location.setText("Address:\nCity Center, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("20")) {
            String about="It is one of the best restaurant in gwalior. Know for its starters.\n" +
                    "It is located in busiest area in gwalior.\n" +
                    "It has good ambience.";
            imageView.setBackground(getResources().getDrawable(R.drawable.food_dessert));
            restuarant_name.setText("Food & Dessert Restuarant");
            restuarant_number.setText("Phone:\n097700 07730");
            restuarant_location.setText("Address:\nBaijnath Apartments, Beside Millenium Plaza, University Rd, Govindpuri, 474011 Gwalior, India");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("21")) {
            String about="One and Only Barbeque Hut in Gwalior.U can find different varieties of delicious barbeques here.\n" +
                    "The ambience and experience here will leave you delighted.";
            imageView.setBackground(getResources().getDrawable(R.drawable.bbqhut));
            restuarant_name.setText("BBQ Hut");
            restuarant_number.setText("Phone:\n094257 81319");
            restuarant_location.setText("Address:\n91, University Rd, Vivekanand Colony, Thatipur, Gwalior, Madhya Pradesh 474011");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("22")) {
            String about="This place offers one of the better choices of cusines in the city of Gwalior. The food is piping hot and tastes awesome. " +
                    "Situated in phoolbagh, this vegetarian friendly restaurant provides a wide range of chinese and indian dishes";
            imageView.setBackground(getResources().getDrawable(R.drawable.zayka_cover_pic));
            restuarant_name.setText("Zayka Family Restaurant");
            restuarant_number.setText("Phone:\n7512434785");
            restuarant_location.setText("Address:\nOpposite Bank of India,Phool Bagh, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("23")) {
            String about="South Indian delicious dishes available here.\n" +
                    "The meals here resemble the south cuisine and the ambience here\n" +
                    "is good.";
            imageView.setBackground(getResources().getDrawable(R.drawable.indian_meal_cover_pic));
            restuarant_name.setText("Indian Meal Restuarant");
            restuarant_number.setText("Phone:\n9691676684");
            restuarant_location.setText("Address:\nGate no 2, Infornt of Central Bank ATM, City Centre, Gwalior - 474011");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("24")) {
            String about="This restaurant is meant for pure vegetarian lovers.\n" +
                    "There are varied vegetarian dishes available here.";
            imageView.setBackground(getResources().getDrawable(R.drawable.royalview_cover_pic));
            restuarant_name.setText("Royal View Restuarant");
            restuarant_number.setText("Phone:\nNot Available");
            restuarant_location.setText("Address:\n11, New Govindpuri, Sachin Tendulkar Road, 474001 Gwalior, India");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("25")) {
            String about="The spicy tadka dishes are famous here. The spices here used are epic!!\n" +
                    "U will experience spicy corner in each and every dish.";
            imageView.setBackground(getResources().getDrawable(R.drawable.thadka_cover_pic));
            restuarant_name.setText("Thadka Restuarant");
            restuarant_number.setText("Phone:\n+(91)-9826488444");
            restuarant_location.setText("Address:\nPlot no. 15 , Patel nagar , City Centre, Gwalior - 474011");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("26")) {
            String about="Well known for its non veg dishes.U will be elated by the chicken\n" +
                    "dishes available here. The rates here are cheap and the quality is best.";
            imageView.setBackground(getResources().getDrawable(R.drawable.albaek));
            restuarant_name.setText("Albaek Restuarant");
            restuarant_number.setText("Phone:\nNot Available");
            restuarant_location.setText("Address:\nNear Income Tax Office, City Center, Gwalior, Madhya Pradesh 474009");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("27")) {
            String about="Good ambience and quality of food is available here.\n" +
                    "Good staff and service. Cheap rates with delicious food serving.";
            imageView.setBackground(getResources().getDrawable(R.drawable.pavitra_cover_pic));
            restuarant_name.setText("Pavitra Restuarant");
            restuarant_number.setText("Phone:\n+(91)-9301603999");
            restuarant_location.setText("Address:\nGreen Garden Road, City Center , Gwalior - 474011");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("28")) {
            String about="Sai bhoj restaurant a family run restaurant which has been serving\n" +
                    "high quality indian vegetarian food .\n" +
                    "WEBSITE:http://egwalior.in/saibhoj";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("Sai Bhoj Restuarant");
            restuarant_number.setText("Phone:\n098264 26077");
            restuarant_location.setText("Address:\nRailway Station Road, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }
        else if(message.equals("29")) {
            String about="Different milk shakes are available here. With varied \n" +
                    "desserts and salads.";
            imageView.setBackground(getResources().getDrawable(R.drawable.south_express_cover));
            restuarant_name.setText("Sai Fruit Sake Restuarant");
            restuarant_number.setText("Phone:\nNot Available");
            restuarant_location.setText("Address:\nRailway Station Road, Gwalior, Madhya Pradesh 474002");
            restuarant_about.setText("About the Restuarant:\n\n"+about);
        }


    }
}
