package com.user.foodzamo.RestuarantsAbout;

/**
 * Created by user on 12/5/2016.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.foodzamo.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] fruits;
    private final Integer[] imageIds;

    public CustomArrayAdapter(Activity context, String[] fruits, Integer[] imageIds){
        super(context, R.layout.list_row, fruits);
        this.context = context;
        this.fruits = fruits;
        this.imageIds = imageIds;
    }

    static class ViewContainer{
        public ImageView imageView;
        public TextView txt1;
        public TextView txt2;
    }

    public View getView(int position, View view, ViewGroup parent){
        ViewContainer viewContainer;
        View rowView = view;

        // print the index of the row to examine
        Log.d("CustomArrayAdapter", String.valueOf(position));

        if(rowView == null){
            Log.d("CustomArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_row, null, true);

            // create a view container object
            viewContainer = new ViewContainer();
            // get references to all the views in the row
            viewContainer.txt1 = (TextView) rowView.findViewById(R.id.text1);
            viewContainer.txt2 = (TextView) rowView.findViewById(R.id.text2);
            viewContainer.imageView = (ImageView) rowView.findViewById(R.id.image);
            // assign the viewcontainer to the rowview
            rowView.setTag(viewContainer);
        }
        else{
            // view was previously created; can recycle
            Log.d("CustomArrayAdapter", "recycling");
            viewContainer = (ViewContainer) rowView.getTag();
        }
        // costumize the content of each row based on position
        viewContainer.txt1.setText(fruits[position]);
        //viewContainer.txt2.setText(fruits[position] + "...some description goes here..");
        viewContainer.txt2.setText("" );

        viewContainer.imageView.setImageResource(imageIds[position]);

        return rowView;
    }
}
