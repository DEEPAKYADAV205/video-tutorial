package delivery.dummyorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SingleMarker extends FragmentActivity {

	private static String url = "http://www.vsstechnologyapp.in/deliverytrack_db/singlemarkerdy.php";
	// private ProgressDialog pDialog;
	String pass1, pass2, pass3, pass4, pass5;
	String temp1, temp2, temp3, temp4, temp5;
	ListView list;
	Button btn_more;

	String[] values = new String[] { "Order No-1", "2 Veg Biryani",
			"H No-1234,Near IIT Delhi", "Katwaria Sarai" };

	String[] values1 = new String[] { "Order No-1", "2 Non-Veg Biryani",
			"Flat no-53,Near Mother Dairy", "Alaknanda" };

	String[] values2 = new String[] { "Order No-1", "4 Paneer Biryani",
			"2 Chicken Biryani", "Off-43/1,Near C lal Chowk", "Kalkaji" };

	String[] values3 = new String[] { "Order No-1", "2 Veg Biryani",
			"2 Chicken Biryani", "H No-34/9,Near Shipra Mall", "Indirapuram" };
	// URL to get contacts JSON
	// private static String url =
	// "http://dyandroidapps.netii.net/android_db/dy.php";

	// private static String url =
	// "http://www.vsstechnologyapp.in/deliverytrack_db/gettingresult.php";

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

	Button btn_refresh;
	// Google Map
	private GoogleMap googleMap;
	ArrayList<HashMap<String, String>> arl = new ArrayList<HashMap<String, String>>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapsinglemarker);
		btn_more = (Button) findViewById(R.id.btn_more);
		// list = (ListView) findViewById(R.id.posts_listview);

		// @SuppressWarnings("unchecked")
		// ArrayList<HashMap<String, String>> arl = (ArrayList<HashMap<String,
		// String>>) getIntent()
		// .getSerializableExtra("arraylist");
		// if (arl != null) {

		try {
			// Loading map
			initilizeMap();
			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			/*
			 * for (int i = 0; i < arl.size(); i++) { HashMap<String, String>
			 * marker = arl.get(i); LatLng latlng = new
			 * LatLng(Double.parseDouble(marker .get("lat")),
			 * Double.parseDouble(marker.get("lng"))); String id =
			 * marker.get("id");
			 * 
			 * addMarker(latlng, id);
			 * 
			 * }
			 */

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

		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		/*
		 * else { Toast.makeText(getApplicationContext(),
		 * "No details found,Please check Mobile No. again",
		 * Toast.LENGTH_SHORT).show(); }
		 */
		new GetContacts2().execute();

	}

	private void addMarker(LatLng latlng, String id) {
		googleMap.clear();
		MarkerOptions markerOptions = new MarkerOptions();

		// markerOptions.title(id1);
		markerOptions.position(latlng);
		markerOptions.title(id);

		// Clears the previously touched position

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
		markerOptions.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		googleMap.addMarker(markerOptions).showInfoWindow();
		// /*
		// // List view
		// if (id.equals("9968581085")) {
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// values);
		// // Assign adapter to ListView
		// list.setAdapter(adapter);
		// }
		//
		// else if (id.equals("9716910479")) {
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// values1);
		// // Assign adapter to ListView
		// list.setAdapter(adapter);
		// } else if (id.equals("9717835999")) {
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// values2);
		// // Assign adapter to ListView
		// list.setAdapter(adapter);
		// } else {
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// values3);
		// // Assign adapter to ListView
		// list.setAdapter(adapter);
		// }*/
		/*
		 * ListAdapter adapter = new SimpleAdapter(SingleMarker.this,
		 * contactList, R.layout.simple_list_item_3, new String[] { TAG_ID,
		 * TAG_LABEL,TAG_DATA }, new int[] { R.id.name, R.id.email, R.id.mobile
		 * });
		 */

		/*
		 * // my code googleMap.setOnInfoWindowClickListener(new
		 * OnInfoWindowClickListener() {
		 * 
		 * @Override public void onInfoWindowClick(Marker marker) {
		 * 
		 * Intent intent = new Intent(getBaseContext(), SingleMarker.class);
		 * intent.putExtra("id", "id"); startActivity(intent);
		 * 
		 * } }); // my code
		 */}

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
				pass1 = extras.getStringExtra("id");
				// and get whatever type user account id is
			}

			params.add(new BasicNameValuePair("id", pass1));
			/*
			 * params.add(new BasicNameValuePair("id1", pass2)); params.add(new
			 * BasicNameValuePair("id2", pass3)); params.add(new
			 * BasicNameValuePair("id3", pass4)); params.add(new
			 * BasicNameValuePair("id4", pass5));
			 */
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
			Toast.makeText(getApplicationContext(), "Id is" + pass1,
					Toast.LENGTH_SHORT).show();
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

						new GetContacts2().execute();
					}
				}, 90000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onMore(View v) {
		// Intentint=new Intent(startActivities(this,NewOrderActivity.class));

	}

}
