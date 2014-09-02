package com.tasteit.app;

import java.util.ArrayList;

import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tasteit.app.R;
import com.tasteit.app.adapters.TitleNavigationAdapter;
import com.tasteit.app.helpers.GPSTracker;
import com.tasteit.app.helpers.SpinnerNavItem;

public class RestaurantesMapActivity extends ActionBarActivity implements OnNavigationListener  {

	private GoogleMap mapRestaurante;
	
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;
    // Navigation adapter
    private TitleNavigationAdapter adapter;
    private int SelectedPosition;
    //GPSTracker class
    GPSTracker gps;
    double latitude;
    double longitude;
    LatLng myPosition;
    Marker myMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurantes_map);
		
		Intent intent = getIntent();
		SelectedPosition = intent.getIntExtra("SelectedPosition", SelectedPosition);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		// Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Principal", R.drawable.ic_action_map));
        navSpinner.add(new SpinnerNavItem("Mapa", R.drawable.ic_action_map));
        navSpinner.add(new SpinnerNavItem("Mi Perfil", R.drawable.ic_action_person));   
         
        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);
 
        // assigning the spinner navigation     
        getSupportActionBar().setListNavigationCallbacks(adapter, this);
		
        gps = new GPSTracker(RestaurantesMapActivity.this);
        
        // check if GPS enabled     
        if(gps.canGetLocation()){
             
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            myPosition = new LatLng(latitude, longitude);
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Tu localización es - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        
		//Comprobar si el mapa esta disponible
		setUpMap();
		
		myMarker = mapRestaurante.addMarker(new MarkerOptions().position(myPosition).title("Aqui estoy"));
		mapRestaurante.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
	}
	
	private void setUpMap(){
		if(mapRestaurante == null){
			mapRestaurante = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapRestaurantes)).getMap();
			
			// Check if we were successful in obtaining the map.
	        if (mapRestaurante != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurantes_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		getSupportActionBar().setSelectedNavigationItem(SelectedPosition);
		return false;
	}
}
