package com.tasteit.app.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.R.integer;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.tasteit.app.R;
import com.tasteit.app.adapters.CardsAdapter;

public class CardPublicacionesLayuotFragment extends Fragment {
	
    private ListView cardsList;
    private View rootView;
    private String urlImageProfile = "https://lh5.googleusercontent.com/-ytIgZhggXPY/U1kRcEI6AII/AAAAAAAAKYw/yP7pP-BDJrk/w844-h846-no/20140323_142859.jpg";

    public CardPublicacionesLayuotFragment() {
        // nop
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_publicaciones_fragment_layout, container, false);
        cardsList = (ListView) rootView.findViewById(R.id.cards_lista_publicaciones);
        //setupList();
        LlenarListado l = new LlenarListado();
        l.execute();        
        return rootView;
    }
    
    private final class ListItemButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = cardsList.getFirstVisiblePosition(); i <= cardsList.getLastVisiblePosition(); i++) {
                if (v == cardsList.getChildAt(i - cardsList.getFirstVisiblePosition()).findViewById(R.id.list_item_card_btnComentar)) {
                    // PERFORM AN ACTION WITH THE ITEM AT POSITION i
                    Toast.makeText(getActivity(), "Clicked on Left Action Button of List Item " + i, Toast.LENGTH_SHORT).show();
                } else if (v == cardsList.getChildAt(i - cardsList.getFirstVisiblePosition()).findViewById(R.id.list_item_card_btnCompartir)) {
                    // PERFORM ANOTHER ACTION WITH THE ITEM AT POSITION i
                    Toast.makeText(getActivity(), "Clicked on Right Action Button of List Item " + i, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private final class ListItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "Clicked on List Item " + position, Toast.LENGTH_SHORT).show();
        }
    }
    
    private final class ListItemImageClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "Click Imagen", Toast.LENGTH_SHORT).show();
		}
    	
    }
    
	public class LlenarListado extends AsyncTask<integer, integer, Bitmap>
	{	
		@Override
		protected Bitmap doInBackground(integer... params) {
			// TODO Auto-generated method stub
			//android.os.Debug.waitForDebugger();
			Bitmap bitmap = null;
			try{
				bitmap = GetImageFromURL();
			}catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getActivity(), "Aqui paso algo ..." + e.toString(), Toast.LENGTH_LONG).show();
			}
			return bitmap;
		}
		
		public Bitmap GetImageFromURL()
		{
			 URL imageUrl = null;
			 HttpURLConnection conn = null;
			 Bitmap imagen = null;
			 
			 try {
			 
				 imageUrl = new URL("http://www.adondevamos.pe/blog/wp-content/uploads/2010/05/chilis.jpg");
				 conn = (HttpURLConnection) imageUrl.openConnection();
				 conn.setDoInput(true);
				 conn.connect();
				  
				 BitmapFactory.Options options = new BitmapFactory.Options();
				 options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2
				 InputStream input = conn.getInputStream();
				 imagen = BitmapFactory.decodeStream(input);
				 
				 return imagen;
			 } catch (IOException e) {
			 
				 e.printStackTrace();
			 
			 }
			 return imagen;
		}
		
		protected void onPostExecute(Bitmap result) {

			setupList(result);
		}
		
	    public void setupList(Bitmap bitmap) {
	    	CardsAdapter cardAdapter = createAdapter(bitmap);
	        cardsList.setAdapter(cardAdapter);
	        cardsList.setOnItemClickListener(new ListItemClickListener());
	    }

	    public CardsAdapter createAdapter(Bitmap bitmap) {
	        ArrayList<String> items = new ArrayList<String>();

	        for (int i = 0; i < 100; i++) {
	            items.add(i, "Comentario de publicacion " + i);
	        }

	        return new CardsAdapter(getActivity(), items, new ListItemButtonClickListener(), bitmap, new ListItemImageClickListener(), urlImageProfile);
	    }
	}
}
