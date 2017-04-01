package com.user.foodzamo.Register;

/**
 * Created by user on 10/28/2016.
 */

public class User {

    public String restuarant;
    public String billnumber;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String restuarant, String billnumber) {
        this.restuarant = restuarant;
        this.billnumber = billnumber;
    }

}
