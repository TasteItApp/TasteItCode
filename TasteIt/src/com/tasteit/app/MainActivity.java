package com.tasteit.app;

import java.util.ArrayList;

import com.tasteit.app.adapters.TitleNavigationAdapter;
import com.tasteit.app.fragment.CardPublicacionesLayuotFragment;
import com.tasteit.app.helpers.SpinnerNavItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

	// action bar
    private ActionBar actionBar;
 
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;
     
    // Navigation adapter
    private TitleNavigationAdapter adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	    
	    // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Principal", R.drawable.ic_action_map));
        navSpinner.add(new SpinnerNavItem("Mapa", R.drawable.ic_action_map));
        navSpinner.add(new SpinnerNavItem("Mi Perfil", R.drawable.ic_action_person));   
         
        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);
 
        // assigning the spinner navigation     
        actionBar.setListNavigationCallbacks(adapter, this);
		
		if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new CardPublicacionesLayuotFragment()).commit();
        }
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.card_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_message).setTitle(R.string.app_name);
            builder.setPositiveButton(R.string.dialog_ok, null);
            builder.setIcon(R.drawable.ic_launcher);
            
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch (itemPosition) {
		case 0:
			 //MainActivity
			break;
		case 1:
			//Restaurante mapa
			Intent i = new Intent();
			i.setClass(getApplicationContext(), RestaurantesMapActivity.class);
			i.putExtra("SelectedPosition", itemPosition);
    		startActivity(i);
			break;
		case 2:
			
			break;

		default:
			break;
		}
		
		return false;
	}
}