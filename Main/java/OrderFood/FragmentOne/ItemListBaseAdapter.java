package com.user.foodzamo.OrderFood.FragmentOne;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.foodzamo.R;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	
	private Integer[] imgid = {
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle,
			R.drawable.delivery_food_vehicle




	};
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
		holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getPrice());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		ImageView itemImage;
	}
}
