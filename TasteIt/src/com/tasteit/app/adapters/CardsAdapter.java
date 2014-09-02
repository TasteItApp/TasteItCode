package com.tasteit.app.adapters;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tasteit.app.AppController;
import com.tasteit.app.R;

public class CardsAdapter extends BaseAdapter {
	
	private List<String> items;
    private final OnClickListener itemButtonClickListener;
    private final OnClickListener itemImageClickListener;
    private final Context context;
    private Bitmap bitmap;
    ImageLoader imageLoader;// = AppController.getInstance().getImageLoader();
    private String urlImageProfile;

    public CardsAdapter(Context context, List<String> items, OnClickListener itemButtonClickListener, Bitmap bitmap, 
    		OnClickListener itemImageClickListener, String urlImageProfile) {
        this.context = context;
        this.items = items;
        this.itemButtonClickListener = itemButtonClickListener;
        this.bitmap = bitmap;
        this.itemImageClickListener = itemImageClickListener;
        this.urlImageProfile = urlImageProfile;
    }

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public String getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
        
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_card, null);

            holder = new ViewHolder();
            holder.itemText = (TextView) convertView.findViewById(R.id.list_item_card_text);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.ivImagenCard);
            holder.btnComentar = (Button) convertView.findViewById(R.id.list_item_card_btnComentar);
            holder.btnCompartir = (Button) convertView.findViewById(R.id.list_item_card_btnCompartir);
            holder.fotoPerfil = (NetworkImageView)convertView.findViewById(R.id.fotoPerfil);
            holder.itemProfileName = (TextView)convertView.findViewById(R.id.nombreUsuario);
            holder.itemTimestampProfilePost = (TextView)convertView.findViewById(R.id.timestamp);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.itemText.setText(items.get(position));
        holder.itemImage.setImageBitmap(this.bitmap);
        holder.fotoPerfil.setImageUrl(urlImageProfile, imageLoader);
        holder.itemProfileName.setText("Edel Custodio Frias");
        // TimeStamp 30/07/2014 = 1406714043
        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong("1406714043"),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.itemTimestampProfilePost.setText(timeAgo);
        
        if (itemButtonClickListener != null) {
            holder.btnComentar.setOnClickListener(itemButtonClickListener);
            holder.btnCompartir.setOnClickListener(itemButtonClickListener);
        }
        
        if (itemImageClickListener != null){
        	holder.itemImage.setOnClickListener(itemImageClickListener);
        }
        
        return convertView;
	}
    
	private static class ViewHolder {
        private TextView itemText;
        private ImageView itemImage;
        private Button btnComentar;
        private Button btnCompartir;
        private NetworkImageView fotoPerfil;
        private TextView itemProfileName;
        private TextView itemTimestampProfilePost;
    }

}
