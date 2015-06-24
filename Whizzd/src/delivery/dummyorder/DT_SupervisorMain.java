package delivery.dummyorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DT_SupervisorMain extends Activity implements
		ActionBar.OnNavigationListener {

	Button bt;
	EditText et1, et2, et3, et4, et5;
	private ProgressDialog pDialog;
	String id1, id2, id3, id4, id5;
	TextView tv1, tv2;
	private ActionBar actionBar;

	// Refresh menu item
	private MenuItem refreshMenuItem;

	// private static String url =
	// "http://dyandroidapps.netii.net/dygettinglatlng.php";
	private static String url = "http://www.vsstechnologyapp.in/deliverytrack_db/gettingresult.php";
	// JSON parser class
	// JSONPARSER1 jsonParser = new JSONPARSER1();

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTACTS = "products";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LNG = "lng";
	private static final String TAG_ID = "id";

	// contacts JSONArray
	// JSONArray contacts = null;
	JSONArray contacts = null;
	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.supervisor);

		tv1 = (TextView) findViewById(R.id.tv_dtmain_1);

		et1 = (EditText) findViewById(R.id.edittext_gettingmobnumber1);
		et2 = (EditText) findViewById(R.id.edittext_gettingmobnumber2);
		et3 = (EditText) findViewById(R.id.edittext_gettingmobnumber3);
		et4 = (EditText) findViewById(R.id.edittext_gettingmobnumber4);
		et5 = (EditText) findViewById(R.id.edittext_gettingmobnumber5);
		bt = (Button) findViewById(R.id.btn_supervisor);

		contactList = new ArrayList<HashMap<String, String>>();
		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(true);
		ParseUser user = new ParseUser();
		ParseUser name = ParseUser.getCurrentUser();

		// Log.d("result", name);
		/*
		 * Toast.makeText(getApplicationContext(),(CharSequence) name,
		 * Toast.LENGTH_SHORT) .show();
		 */

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
			Intent i = new Intent(DT_SupervisorMain.this, ResturantSignup.class);
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
			startActivity(new Intent(DT_SupervisorMain.this,
					SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void track(View v) {
		// new LoadAllproductss().execute();
		id1 = et1.getText().toString();
		id2 = et2.getText().toString();
		id3 = et3.getText().toString();
		id4 = et4.getText().toString();
		id5 = et5.getText().toString();
		if (id2.equals(null) || id2.equals("")) {
			id2 = "55";
		}
		if (id1.equals(null) || id1.equals("")) {
			id1 = "56";
		}
		if (id4.equals(null) || id4.equals("")) {
			id4 = "55";
		}
		if (id5.equals(null) || id5.equals("")) {
			id5 = "55";
		}
		if (id3.trim().matches("") || id3.trim().length() < 10
				|| id3.trim().length() > 10) {
			Toast.makeText(getApplicationContext(),
					"Please Enter Valid 10 Digit Number", Toast.LENGTH_SHORT)
					.show();
		} else {

			new GetContacts().execute();
			// Toast.makeText(getApplicationContext(), id,
			// Toast.LENGTH_SHORT).show();
			// new RetrieveTask().execute();
		}
	}

	// private class GetContacts extends AsyncTask<Void, ArrayList<String>,
	// Void>
	// class GetContacts extends AsyncTask<String, String, String> {
	private class GetContacts extends AsyncTask<Void, ArrayList<String>, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(DT_SupervisorMain.this);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected Void doInBackground(Void... arg0) {
			/*
			 * // protected String doInBackground(String... params) {
			 * 
			 * // ServiceHandler sh = new ServiceHandler();
			 * 
			 * // updating UI from Background Thread runOnUiThread(new
			 * Runnable() { public void run() { // Check for success tag int
			 * success;
			 */
			// try {
			ServiceHandler sh = new ServiceHandler();
			// Building Parameters

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String pass1 = id1;
			String pass2 = id2;
			String pass3 = id3;
			String pass4 = id4;
			String pass5 = id5;
			// String id = id1;
			params.add(new BasicNameValuePair("id", pass1));
			params.add(new BasicNameValuePair("id1", pass2));
			params.add(new BasicNameValuePair("id2", pass3));
			params.add(new BasicNameValuePair("id3", pass4));
			params.add(new BasicNameValuePair("id4", pass5));
			// params.add(new BasicNameValuePair("name", "deepak"));

			String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,
					params);
			System.out.print(jsonStr);
			Log.d("Response Is This", "> " + jsonStr);
			String str = jsonStr;
			try {
				if (jsonStr != null) {
					JSONObject joj = new JSONObject(str);
					contacts = joj.getJSONArray(TAG_CONTACTS);
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String lat = c.getString(TAG_LAT);
						String lng = c.getString(TAG_LNG);
						String id = c.getString(TAG_ID);

						Log.d("response", lat);
						Log.d("response", lng);

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("lat", lat);
						map.put("lng", lng);
						map.put("id", id);

						contactList.add(map);
					}

					if (contactList != null) {
						Intent intentmap = new Intent(DT_SupervisorMain.this,
								SupervisorMapActivity.class);
						intentmap.putExtra("arraylist", contactList);
						intentmap.putExtra("tempid1", id1);
						intentmap.putExtra("tempid2", id2);
						intentmap.putExtra("tempid3", id3);
						intentmap.putExtra("tempid4", id4);
						intentmap.putExtra("tempid5", id5);
						// intentmap.putExtra("number_id", id1);
						startActivityForResult(intentmap, 500);
					} /*
					 * else { // products with pid not found
					 * Toast.makeText(getApplicationContext(),
					 * "No Record Found.Please Check Mobile Number",
					 * Toast.LENGTH_SHORT).show(); }
					 */

				} /*
				 * else { Toast.makeText(getApplicationContext(),
				 * "No Record Found.Please Check Mobile Number",
				 * Toast.LENGTH_SHORT).show(); }
				 */
			} catch (JSONException e) {
				e.printStackTrace();
			}

			/*
			 * Toast.makeText(getApplicationContext(),
			 * "No Record Found.Please Check Mobile Number",
			 * Toast.LENGTH_SHORT).show();
			 */

			return null;
		}

		protected void onPostExecute(Void result) {
			// dismiss the dialog once got all details
			pDialog.cancel();
			/*
			 * if (pDialog != null && pDialog.isShowing()) { pDialog.cancel(); }
			 */

		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}
