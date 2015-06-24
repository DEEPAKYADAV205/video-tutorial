package delivery.dummyorder;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

@SuppressLint("NewApi")
public class DT_AgentMain extends Activity implements
		ActionBar.OnNavigationListener {
	TextView tv;
	EditText et;
	Button bt, bt_skip;
	String name;
	// action bar
	private ActionBar actionBar;

	// Refresh menu item
	private MenuItem refreshMenuItem;
	// create number database
	Intent i = null;
	// ImageView im = null;
	EditText tv1, tv2, tv3, tv4, tv5, tv6;
	boolean flag = false;
	// SQLiteDatabase db = null;
	String mobile;
	JSONParser jsonParser = new JSONParser();

	private static String url_create_product = "http://www.vsstechnologyapp.in/deliverytrack_db/demody.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afterloginagent);
		et = (EditText) findViewById(R.id.editText_number);
		bt = (Button) findViewById(R.id.button_submitnumber);
		// bt_skip = (Button) findViewById(R.id.button_skip);
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
			Intent i = new Intent(DT_AgentMain.this, ResturantSignup.class);
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
			startActivity(new Intent(DT_AgentMain.this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void submit_Number(View v) {
		name = et.getText().toString();
		if (name.length() < 10 || name.equals("") || name == null) {
			/* if (et.isEmpty()) */
			Toast.makeText(getApplicationContext(), "Please Enter Mobile No.",
					Toast.LENGTH_SHORT).show();
		} else {
			name = et.getText().toString();
			new RegisterSqlite().execute();
			// /* Toast.makeText(getApplicationContext(), name,
			// Toast.LENGTH_SHORT)
			// .show();*/
			Intent i = new Intent(this, MapActivity.class);
			i.putExtra("number", name);
			startActivity(i);
			/*
			 * Intent i = new Intent(getApplicationContext(),
			 * AndroidGPSTrackingActivity.class); i.putExtra("number","name");
			 * startActivity(i);
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

	public class RegisterSqlite extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			String id = name;
			String lat = "33.00";
			String lng = "88.00";
			ServiceHandler sh = new ServiceHandler();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("lat", lat));
			params.add(new BasicNameValuePair("lng", lng));
			params.add(new BasicNameValuePair("id", id));
			// params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			String jsonStr = sh.makeServiceCall(url_create_product,
					ServiceHandler.POST, params);
			String str = jsonStr;

			// Note that create product url accepts POST method
			// JSONObject json = jsonParser.makeHttpRequest(url_create_product,
			// "POST", params);

			// check log cat for response
			Log.d("Create Response", "Successfull");
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}
	}

}
