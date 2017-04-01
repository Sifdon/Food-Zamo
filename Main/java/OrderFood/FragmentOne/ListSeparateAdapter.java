package com.user.foodzamo.OrderFood.FragmentOne;
//menu
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;


public class ListSeparateAdapter<T> extends BaseAdapter {
  
    private Context ctx;
    private BaseAdapter headerBaseAdapter;
    private int headerLayout;
    private int headerTextView;
    private HashMap<String, Integer> hashMap;
	private static final int INDEX_SECTION_HEADER = 0;
	  
    
    public ListSeparateAdapter(Context ctx, BaseAdapter headerBaseAdapter, 
            int headerLayout, int headerTextView) {
        if(ctx == null) {
            throw new IllegalArgumentException("context should not be null.");
        } else if(headerBaseAdapter == null) {
            throw new IllegalArgumentException("adapter should not be null.");
        }  else if(!isTypeTextView(ctx, headerLayout, headerTextView)) {
            throw new IllegalArgumentException("section Title should not match type.");
        }

        this.ctx = ctx;
        this.headerBaseAdapter = headerBaseAdapter;
        this.headerLayout = headerLayout;
        this.headerTextView = headerTextView;
       this.hashMap = new HashMap<String, Integer>();

        getSection();
        
        
    }

    private boolean isTypeTextView(Context context, int layoutId, int textViewId) {
        View inflatedView = View.inflate(context, layoutId, null);
        View foundView = inflatedView.findViewById(textViewId);

        return foundView instanceof TextView;
    }

    @Override
    public int getCount() {
        return headerBaseAdapter.getCount() + getSectionLength();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SectionHolder sectionHolder = null;

        switch (getItemViewType(position)) {
        case INDEX_SECTION_HEADER:
            if(view == null) {
                view = View.inflate(ctx, headerLayout, null);

                sectionHolder = new SectionHolder();
                sectionHolder.textview = (TextView) view.findViewById(headerTextView);

                view.setTag(sectionHolder);
            } else {
                sectionHolder = (SectionHolder) view.getTag();
            }
            break;

        default:
            view = headerBaseAdapter.getView(getPosition(position), 
                    convertView, parent);
            break;
        }

        if(sectionHolder != null) {
            String sectionName = getTitle(position);
            sectionHolder.textview.setText(sectionName);
        }

        return view;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return headerBaseAdapter.areAllItemsEnabled() ? 
                hashMap.size() == 0 : false;
    }

    @Override
    public int getItemViewType(int position) {
        int positionInCustomAdapter = getPosition(position);
        return hashMap.values().contains(position) ? 
                INDEX_SECTION_HEADER : 
                    headerBaseAdapter.getItemViewType(positionInCustomAdapter) + 1;
    }

    @Override
    public int getViewTypeCount() {
        return headerBaseAdapter.getViewTypeCount() + 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return hashMap.values().contains(position) ? 
                false : headerBaseAdapter.isEnabled(getPosition(position));
    }

    @Override
    public Object getItem(int position) {
        return headerBaseAdapter.getItem(getPosition(position));
    }

    @Override
    public long getItemId(int position) {
        return headerBaseAdapter.getItemId(getPosition(position));
    }

    @Override
    public void notifyDataSetChanged() {
        headerBaseAdapter.notifyDataSetChanged();
        getSection();
        super.notifyDataSetChanged();
    }

    
    
    public int getPosition(int position) {
        int pos = 0;

        Set<Entry<String, Integer>> entrySet = hashMap.entrySet();
        for(Entry<String, Integer> entry : entrySet) {
            if(entry.getValue() < position) {
            	pos++;
            }
        }

        return position - pos;
    }

    static class SectionHolder {
        public TextView textview;
    }

    private void getSection() {
        int len = headerBaseAdapter.getCount();
        int nSections = 0;
        hashMap.clear();

        for(int i=0; i<len; i++) {
        	
				
			DataCollection obj=(DataCollection) headerBaseAdapter.getItem(i);
			System.out.println(""+obj.type);
			String sectionName= obj.type;
			
			
            if(!hashMap.containsKey(sectionName)) {
                hashMap.put(sectionName, i + nSections);
                nSections ++;
            }
        }

      }

    private int getSectionLength() {
        return hashMap.size();
    }

    private String getTitle(int position) {
        String title = null;

        Set<Entry<String, Integer>> entrySet = hashMap.entrySet();
        for(Entry<String, Integer> entry : entrySet) {
            if(entry.getValue() == position) {
                title = entry.getKey();
                break;
            }
        }
        return title;
    }

}
