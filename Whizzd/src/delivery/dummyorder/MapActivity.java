package delivery.dummyorder;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends FragmentActivity implements
		OnMyLocationChangeListener, ActionBar.OnNavigationListener {

	GoogleMap googleMap;
	double latitude;
	double longitude;
	String id1;
	Button order, order1,returnb;
	private ActionBar actionBar;

	// Refresh menu item
	private MenuItem refreshMenuItem;

	// Start
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	// private static String url_update_product =
	// "http://dyandroidapps.netii.net/update.php";
	private static String url_update_product = "http://www.vsstechnologyapp.in/deliverytrack_db/update.php";

	// private static String url_create_product =
	// "http://www.vsstechnologyapp.in/deliverytrack_db/demody.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	// End

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_agent);
		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(true);
		id1 = getIntent().getExtras().getString("number");

		order = (Button) findViewById(R.id.order);
		order1 = (Button) findViewById(R.id.order1);
		returnb = (Button) findViewById(R.id.midview);

		// Getting Google Play availability status
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
													// not available
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();

		} else { // Google Play Services are available

			// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			googleMap = fm.getMap();

			// Enabling MyLocation Layer of Google Map
			googleMap.setMyLocationEnabled(true);

			// Setting event handler for location change
			googleMap.setOnMyLocationChangeListener(this);
			order.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(MapActivity.this, PendingOrders.class);
					startActivity(i);

				}
			});
			order1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(MapActivity.this, Delivered.class);
					startActivity(i);

				}
			});
			returnb.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(MapActivity.this, AgentHomeActivity.class);
					startActivity(i);

				}
			});

		}

	}

	/**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {

		case R.id.action_add:
			// location found
			Intent i = new Intent(MapActivity.this, SignUpActivity.class);
			startActivity(i);

			return true;
		case R.id.action_refresh:
			// refresh
			refreshMenuItem = item;
			// load the data from server

			return true;
		case R.id.action_help:
			// help action
			return true;
		case R.id.action_check_updates:
			// check for updates action
			return true;
		case R.id.action_check_settings:
			// check for updates action
			startActivity(new Intent(MapActivity.this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */

	@Override
	public void onMyLocationChange(Location location) {
		TextView tvLocation = (TextView) findViewById(R.id.tv_location);

		// Getting latitude of the current location
		/* double */latitude = location.getLatitude();

		// Getting longitude of the current location
		/* double */longitude = location.getLongitude();

		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);

		// Showing the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

		new UpdateNewProduct().execute();
		// Setting latitude and longitude in the TextView tv_location
		tvLocation.setText("Latitude:" + latitude + ", Longitude:" + longitude);

	}

	class UpdateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		/*
		 * @Override protected void onPreExecute() { super.onPreExecute();
		 * pDialog = new ProgressDialog(GPSTracker.this);
		 * pDialog.setMessage("New Location Updating");
		 * pDialog.setIndeterminate(false); pDialog.setCancelable(true);
		 * pDialog.show(); }
		 */

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			String lat = Double.toString(latitude);
			String lng = Double.toString(longitude);
			String id = id1;

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", id));
			params.add(new BasicNameValuePair("lat", lat));
			params.add(new BasicNameValuePair("lng", lng));

			// params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_update_product,
					"POST", params);

			// check log cat for response
			// Log.d("Create Response", json.toString());

			// check for success tag

			return null;
		}
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}