package com.tasteit.app.adapters;

import java.util.ArrayList;

import com.tasteit.app.helpers.SpinnerNavItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tasteit.app.R;

public class TitleNavigationAdapter extends BaseAdapter {

	private ImageView imgIcon;
    private TextView txtTitulo;
    private ArrayList<SpinnerNavItem> spinnerNavItem;
    private Context context;
 
    public TitleNavigationAdapter(Context context,
            ArrayList<SpinnerNavItem> spinnerNavItem) {
        this.spinnerNavItem = spinnerNavItem;
        this.context = context;
    }

	
	@Override
	public int getCount() {
		return spinnerNavItem.size();
	}

	@Override
	public Object getItem(int position) {
		return spinnerNavItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_title_navigation, null);
        }
         
        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitulo = (TextView) convertView.findViewById(R.id.txtTituloItemDropList);
         
        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
        imgIcon.setVisibility(View.GONE);
        txtTitulo.setText(spinnerNavItem.get(position).getTitle());
        return convertView;
	}
	
	 @Override
	    public View getDropDownView(int position, View convertView, ViewGroup parent) {
	        if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.list_item_title_navigation, null);
	        }
	         
	        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
	        txtTitulo = (TextView) convertView.findViewById(R.id.txtTituloItemDropList);
	         
	        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());        
	        txtTitulo.setText(spinnerNavItem.get(position).getTitle());
	        return convertView;
	    }


}
