package com.user.foodzamo.Register;

/**
 * Created by user on 1/2/2017.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class DeliveryClass {

    public String AADailyOffers;
    public String FoodCourt;
    public String ManSingh;
    public String Punjabi;
    public String CooksFast;
    public String MotiMahal;
    public String SouthExpress;
    public String YellowChilli;
    public String Volga;
    public String Bamboo;
    public String Bawarchi;
    public String Virasat;
    public String Spice7;
    public String PariFoodZone;
    public String FoodzInn;
    public String Zayka;
    public String Hakeems;
    public String FoodFactory;
    public String Mejbaan;
    public String ChawlaSquare;
    public String Bhukkads;
    public String WildChefHouse;
    public String NewMezbaan;
    public String ChopalChicken;
    public String FoodDessert;
    public String BBQHut;
    public String IndianMeal;
    public String RoyalView;
    public String Thadka;
    public String Uzbekk;
    public String Albaek;
    public String LuckyChicken;
    public String Pavitra;
    public String SaiBhoj;
    public String SaiFruit;
    public String Ajanta;
    public String Makkhan;



    public DeliveryClass() {
        // Default constructor required for calls to DataSnapshot.getValue(OffersClass.class)
    }

    public DeliveryClass(String AADailyOffers, String FoodCourt, String ManSingh, String Punjabi,String CooksFast
            ,String MotiMahal,String SouthExpress,String YellowChilli,String Volga,String Bamboo,
                       String Bawarchi, String Virasat, String Spice7, String Bhukkads, String PariFoodZone,
                       String FoodzInn,String Zayka,String Hakeems, String FoodFactory,String Mejbaan,String ChawlaSquare,
                       String WildChefHouse,String NewMezbaan,
                       String ChopalChicken,String FoodDessert,String BBQHut,
                       String IndianMeal,String RoyalView,String Thadka,String Uzbekk,
                       String Albaek,String LuckyChicken, String Pavitra,
                       String SaiBhoj,
                       String SaiFruit,
                       String Ajanta,
                       String Makkhan) {
        this.AADailyOffers = AADailyOffers;
        this.FoodCourt = FoodCourt;
        this.ManSingh = ManSingh;
        this.Punjabi = Punjabi;
        this.CooksFast=CooksFast;
        this.MotiMahal=MotiMahal;
        this.SouthExpress=SouthExpress;
        this.YellowChilli=YellowChilli;
        this.Volga=Volga;
        this.Bamboo=Bamboo;
        this.Bawarchi=Bawarchi;
        this.Virasat=Virasat;
        this.Spice7=Spice7;
        this.Bhukkads=Bhukkads;
        this.PariFoodZone=PariFoodZone;
        this.FoodzInn=FoodzInn;
        this.Thadka=Thadka;
        this.Zayka=Zayka;
        this.Hakeems=Hakeems;
        this.FoodFactory=FoodFactory;
        this.Mejbaan=Mejbaan;
        this.ChawlaSquare=ChawlaSquare;
        this.WildChefHouse=WildChefHouse;
        this.NewMezbaan=NewMezbaan;
        this.ChopalChicken=ChopalChicken;
        this.FoodDessert=FoodDessert;
        this.BBQHut=BBQHut;
        this.IndianMeal=IndianMeal;
        this.RoyalView=RoyalView;
        this.Uzbekk=Uzbekk;
        this.Albaek=Albaek;
        this.LuckyChicken=LuckyChicken;
        this.Pavitra=Pavitra;
        this.SaiBhoj=SaiBhoj;
        this.SaiFruit=SaiFruit;
        this.Ajanta=Ajanta;
        this.Makkhan=Makkhan;

    }

    // [START post_to_map]

    // [END post_to_map]

}
