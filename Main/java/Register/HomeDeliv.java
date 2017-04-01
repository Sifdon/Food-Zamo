package com.user.foodzamo.Register;

/**
 * Created by user on 10/31/2016.
 */

public class HomeDeliv {

    public String address;


    public HomeDeliv (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public HomeDeliv(String address) {

        this.address = address;

    }

}
