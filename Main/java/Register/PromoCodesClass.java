package com.user.foodzamo.Register;

/**
 * Created by user on 11/12/2016.
 */

public class PromoCodesClass {

    public String _movies_promocodes;
    public String _shopping_promocodes;
    public String _coffee_promocodes;
    public String _other_promocodes;
    public String _hair_promocodes;




    public PromoCodesClass() {
        // Default constructor required for calls to DataSnapshot.getValue(OffersClass.class)
    }

    public PromoCodesClass(String _movies_promocodes,String _shopping_promocodes,String _coffee_promocodes,String _other_promocodes,
                           String _hair_promocodes) {
        this._movies_promocodes=_movies_promocodes;
        this._shopping_promocodes=_shopping_promocodes;
        this._coffee_promocodes=_coffee_promocodes;
        this._other_promocodes=_other_promocodes;
        this._hair_promocodes=_hair_promocodes;

    }

    // [START post_to_map]

    // [END post_to_map]

}
// [END post_class]

