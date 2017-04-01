package com.user.foodzamo.Register;

/**
 * Created by user on 11/13/2016.
 */

public class Join {
    public String join_us;

    public Join (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Join(String join_us) {

        this.join_us = join_us;

    }
}
