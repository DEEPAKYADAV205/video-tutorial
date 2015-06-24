package delivery.dummyorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ResMap extends FragmentActivity implements
		ActionBar.OnNavigationListener {
	// private ProgressDialog pDialog;
	String pass1, pass2, pass3, pass4, pass5;
	String temp1, temp2, temp3, temp4, temp5;
	String id1;
	Button po, fee, orders;
	Marker m;
	ProgressDialog pDialog;
	private ActionBar actionBar;

	// Refresh menu item
	private MenuItem refreshMenuItem;
	boolean isTapped = true;
	String title;

	// URL to get contacts JSON
	// "http://dyandroidapps.netii
	// private static String url =.net/android_db/dy.php";

	private static String url = "http://www.vsstechnologyapp.in/deliverytrack_db/gettingresult.php";

	// JSON Node names
	private static final String TAG_CONTACTS = "products";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LNG = "lng";
	private static final String TAG_ID = "id";

	// contacts JSONArray
	// JSONArray contacts = null;
	JSONArray contacts = null;
	// Hashmap for ListView

	ArrayList<HashMap<String, String>> contact = new ArrayList<HashMap<String, String>>();

	// Button btn_refresh;

	// Google Map
	private GoogleMap googleMap;
	ArrayList<HashMap<String, String>> arl = new ArrayList<HashMap<String, String>>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resmap);
		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(false);
		po = (Button) findViewById(R.id.layout_button1);
		orders = (Button) findViewById(R.id.layout_button2);
		fee = (Button) findViewById(R.id.layout_button3);
		po.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ResMap.this, PostOrders.class);
				startActivity(i);

			}
		});
		orders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ResMap.this, OrderDetails.class);
				startActivity(i);

			}
		});
		fee.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ResMap.this, Details.class);
				startActivity(i);

			}
		});
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> arl = (ArrayList<HashMap<String, String>>) getIntent()
				.getSerializableExtra("arraylist");
		if (arl != null) {

			try {
				// Loading map
				initilizeMap();
				// Changing map type
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

				for (int i = 0; i < arl.size(); i++) {
					HashMap<String, String> marker = arl.get(i);
					LatLng latlng = new LatLng(Double.parseDouble(marker
							.get("lat")), Double.parseDouble(marker.get("lng")));
					String id = marker.get("id");

					addMarker(latlng, id);

				}

				// Showing / hiding your current location
				googleMap.setMyLocationEnabled(false);
				// googleMap.setBuildingsEnabled(false).

				// Enable / Disable zooming controls
				googleMap.getUiSettings().setZoomControlsEnabled(true);

				// Enable / Disable my location button
				googleMap.getUiSettings().setMyLocationButtonEnabled(false);

				// Enable / Disable Compass icon
				// googleMap.getUiSettings().setCompassEnabled(true);

				// Enable / Disable Rotate gesture
				// googleMap.getUiSettings().setRotateGesturesEnabled(true);

				// Enable / Disable zooming functionality
				// Zoom in the Google Map
				// googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

				googleMap.getUiSettings().setZoomGesturesEnabled(true);
				new GetContacts2().execute();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"No details found,Please check Mobile No. again",
					Toast.LENGTH_SHORT).show();
		}

		// new GetContacts2().execute();

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
			Intent i = new Intent(ResMap.this, ResturantSignup.class);
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
			startActivity(new Intent(ResMap.this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void refreshMap(View v) {

		Toast.makeText(getApplicationContext(), "Map is updating",
				Toast.LENGTH_SHORT).show();
		for (int i = 0; i < contact.size(); i++) {
			HashMap<String, String> marker = contact.get(i);
			LatLng latlng = new LatLng(Double.parseDouble(marker.get("lat")),
					Double.parseDouble(marker.get("lng")));
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

		}
	}

	private void addMarker(LatLng latlng, String id) {
		id1 = id;
		// googleMap.clear();

		/*
		 * MarkerOptions markerOptions = new MarkerOptions();
		 * 
		 * // markerOptions.title(id1); markerOptions.position(latlng);
		 * //markerOptions.title(id1);
		 */m = googleMap.addMarker(new MarkerOptions()

				.position(latlng)

				.title(id1)

				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

		// Clears the previously touched position

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
		/*
		 * m.icon(BitmapDescriptorFactory
		 * .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		 * googleMap.addMarker(markerOptions).showInfoWindow();
		 */
		// my code
		googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				title = marker.getTitle();

				new Singlemarker().execute();

				/*
				 * Intent i = new Intent(getApplicationContext(),
				 * SingleMarker.class); i.putExtra("id", id1); startActivity(i);
				 */

			}
		});
		// my code
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			/*
			 * SupportMapFragment fm = (SupportMapFragment)
			 * getSupportFragmentManager() .findFragmentById(R.id.map);
			 * googleMap = fm.getMap();
			 */
			googleMap = ((SupportMapFragment) (getSupportFragmentManager()
					.findFragmentById(R.id.map))).getMap();
			// googleMap = ((MapFragment)
			// R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private class GetContacts2
			extends
			AsyncTask<ArrayList<String>, ArrayList<HashMap<String, String>>, ArrayList<HashMap<String, String>>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute(); //

			/*
			 * pDialog = new ProgressDialog(MainActivity2.class);
			 * pDialog.setMessage("Please wait...");
			 * pDialog.setCancelable(false); pDialog.show();
			 */
		}

		@Override
		protected ArrayList<HashMap<String, String>> doInBackground(
				ArrayList<String>... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			// Building Parameters

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			Intent extras = getIntent();
			if (extras != null) {
				pass1 = extras.getStringExtra("tempid1");
				pass2 = extras.getStringExtra("tempid2");
				pass3 = extras.getStringExtra("tempid3");
				pass4 = extras.getStringExtra("tempid4");
				pass5 = extras.getStringExtra("tempid5");
				// and get whatever type user account id is
			}

			params.add(new BasicNameValuePair("id", pass1));
			params.add(new BasicNameValuePair("id1", pass2));
			params.add(new BasicNameValuePair("id2", pass3));
			params.add(new BasicNameValuePair("id3", pass4));
			params.add(new BasicNameValuePair("id4", pass5));
			// params.add(new BasicNameValuePair("name", "deepak"));

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,
					params);
			if (jsonStr != null) {
				System.out.print(jsonStr);

				Log.d("Response: ", "> " + jsonStr);
			}
			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);

					// looping through All Contacts
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String lat = c.getString(TAG_LAT);
						String lng = c.getString(TAG_LNG);
						String id = c.getString(TAG_ID);
						Log.d("response", lat);
						Log.d("response", lng);// here i m getting values in log

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("lat", lat);
						map.put("lng", lng);
						map.put("id", id);

						contact.add(map);// i am getting nullpointer excetion
						// googleMap.clear();

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			return contact;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, String>> str) {
			googleMap.clear();
			m.remove();

			// initilizeMap();
			// marker.remove();
			// googleMap.clear();

			Toast.makeText(getApplicationContext(), "Map is cleared",
					Toast.LENGTH_SHORT).show();
			googleMap.clear();

			for (int j = 0; j < str.size(); j++) {
				System.out.println("Serialized data..in background");
				HashMap<String, String> marker = contact.get(j);
				LatLng latlng = new LatLng(
						Double.parseDouble(marker.get("lat")),
						Double.parseDouble(marker.get("lng")));
				String id = marker.get("id");

				addMarker(latlng, id);

			}
			try {
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						// Do something after 100ms

						initilizeMap();
						googleMap.clear();
						new GetContacts2().execute();
					}
				}, 90000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// my singlemarker code
	private class Singlemarker extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(ResMap.this);
			pDialog.setMessage("Please wait..." + title);
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... params) {
			Intent i = new Intent(getApplicationContext(), SingleMarker.class);
			i.putExtra("id", title);
			startActivity(i);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pDialog.cancel();

		}
	}

	// my single marker code

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
