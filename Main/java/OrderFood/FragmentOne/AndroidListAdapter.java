package com.user.foodzamo.OrderFood.FragmentOne;
//menu
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.user.foodzamo.OrderFood.Global;
import com.user.foodzamo.R;

import java.util.ArrayList;


public class AndroidListAdapter extends ArrayAdapter<DataCollection>{
	SharedPreferences sharedpreferences;
	SharedPreferences.Editor editor ;
	public static final String mypreference = "mypref";
	public static final String Name = "nameKey";
	public static final String price = "cost";
	private final Context context;
	private final ArrayList<DataCollection> values;
	private ViewHolder viewHolder;
	private final int resourceId;
public int[] x=new int[1000];
	public AndroidListAdapter(Context context, int resourceId,ArrayList<DataCollection> values,SharedPreferences sharedpreferences,SharedPreferences.Editor editor) {
		super(context, resourceId, values);
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.values = values;
		this.resourceId = resourceId;
		this.sharedpreferences=sharedpreferences;
		this.editor=editor;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resourceId, parent, false);
			
				
			viewHolder = new ViewHolder();
			viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
			viewHolder.item_cost=(TextView) convertView.findViewById(R.id.item_cost);
			viewHolder.plus_button=(ImageView) convertView.findViewById(R.id.cart_plus_img);
			viewHolder.minus_button=(ImageView) convertView.findViewById(R.id.cart_minus_img);
			viewHolder.qunatity=(TextView) convertView.findViewById(R.id.product_quantity);
				convertView.setTag(viewHolder);
			
			
		}else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final DataCollection list_obj=values.get(position);
		viewHolder.tv_title.setText(list_obj.name);
		viewHolder.iv_image.setImageResource(list_obj.image);
		viewHolder.item_cost.setText("Rs. "+list_obj.cost+"/-");
		viewHolder.plus_button.setImageResource(R.drawable.ic_add_circle_black_24dp);
		viewHolder.minus_button.setImageResource(R.drawable.ic_remove_circle_black_24dp);
		String f= String.valueOf(x[position]);
        viewHolder.qunatity.setText(f);


		final TextView relativeLayout=viewHolder.qunatity;
final TextView tv=viewHolder.tv_title;
		viewHolder.plus_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"Item added to cart!",Toast.LENGTH_SHORT).show();
				String s= String.valueOf(tv.getText());
				int c= Integer.parseInt(sharedpreferences.getString(price,""));
				int f= Integer.parseInt(list_obj.cost);
				c=c+f;
				String d= String.valueOf(c);
				editor.putString(Name,sharedpreferences.getString(Name,"")+"$"+tv.getText().toString()+"#"+list_obj.cost+"*");
				editor.putString(price,d);
				editor.commit();
				//Toast.makeText(getContext(), "U clicked "+list_obj.cost,Toast.LENGTH_SHORT).show();
				x[position]++;
				String quant= String.valueOf(x[position]);
					relativeLayout.setText(quant);

			}
		});
		viewHolder.minus_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
x[position]--;
				Toast.makeText(context,"Item removed from cart!",Toast.LENGTH_SHORT).show();
String s= String.valueOf(tv.getText());
				//Toast.makeText(getContext(), "U clicked "+s,Toast.LENGTH_SHORT).show();
				String quant= String.valueOf(x[position]);
				if(x[position]<=-1) {
					relativeLayout.setText("0");
					x[position]=0;
				}
				else {
					int c= Integer.parseInt(sharedpreferences.getString(price,""));
					int f= Integer.parseInt(list_obj.cost);
					c=c-f;
					String d= String.valueOf(c);
					//editor.putString(Name,sharedpreferences.getString(Name,"")+"\n"+tv.getText().toString());
					String s1=sharedpreferences.getString(Name,"");
					int pos=s1.length();
					for (int i=s1.length()-1;i>=0;i--)
					{
						char c1=s1.charAt(i);
						if(c1=='$') {
							pos = i;
							break;
						}
					}
					String strings="";
					for (int i=0;i<pos;i++)
					{
						char c1=s1.charAt(i);
						strings=strings+c1;
					}
					editor.putString(Name, strings);
					editor.putString(price,d);
					editor.commit();
					relativeLayout.setText(quant);
				}
			}
		});
		return convertView;
	}
	
	
	
	
	
	public class ViewHolder {

		 TextView tv_msg;
		 ImageView iv_image;
		 TextView tv_title;
		TextView item_cost;
        ImageView plus_button;
		ImageView minus_button;
		TextView qunatity;
	}

}
