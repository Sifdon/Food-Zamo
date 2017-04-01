package com.user.foodzamo.OrderFood.FragmentOne;
//menu
import java.util.ArrayList;

public class DataCollection {
	public String name="";
	public int image=0;
	public String type="";
	public String cost="";
	
	public static ArrayList<DataCollection> data_arr=null;
	public DataCollection(String name,String type,int image,String cost){
		
		this.image=image;
		this.name=name;
		this.type=type;
		this.cost=cost;
	}

}
