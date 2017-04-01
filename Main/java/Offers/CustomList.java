package com.user.foodzamo.Offers;

/**
 * Created by user on 12/8/2016.
 */


import android.app.Activity;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.user.foodzamo.R;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] title;
    private final String[] description;
    public CustomList(Activity context,
                      String[] title, String[] description) {
        super(context, R.layout.list_single, title);
        this.context = context;
        this.title = title;
        this.description = description;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);

        TextView txtdesc = (TextView) rowView.findViewById(R.id.offer_description);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setText(title[position]);
        txtdesc.setText(description[position]);


        return rowView;
    }
}
