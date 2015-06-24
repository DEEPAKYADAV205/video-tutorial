package delivery.dummyorder;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostOrders extends Activity implements
		ActionBar.OnNavigationListener {

	protected static final String TAG = null;
	Button post1;
	EditText nameedittext, orderedittext, amountedittext, addressedittext,
			teedittext, tdeedittext, statusedittext;
	private ActionBar actionBar;
	private ParseGeoPoint geoPoint;
	TextView total, total1;
	String id;
	Integer sum = 0;
	Integer sum1 = 0;
	Integer total2, x;
	// Refresh menu item
	private MenuItem refreshMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post1);
		actionBar = getActionBar();
		Intent i = getIntent();

		id = i.getStringExtra("id");
		Toast.makeText(PostOrders.this, "post" + id, Toast.LENGTH_LONG).show();
		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(true);
		post1 = (Button) findViewById(R.id.post_button2);
		nameedittext = (EditText) findViewById(R.id.name_edittext);
		total = (TextView) findViewById(R.id.te_edittext);
		total1 = (TextView) findViewById(R.id.tde_edittext);
		orderedittext = (EditText) findViewById(R.id.order_edittext);
		amountedittext = (EditText) findViewById(R.id.amount_edittext);
		addressedittext = (EditText) findViewById(R.id.address_edittext);
		statusedittext = (EditText) findViewById(R.id.status_edittext);

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("delivered");
		query.whereContains("resturantid", id);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
				// check for ParseException

				for (final ParseObject whatever : list) {
					sum += (Integer) whatever.get("resturanttotal");
				}
				// there is your SUM
				if (sum != null) {
					total.setText("Rs." + sum + "/-");
				} else {
					total.setText("Rs NA/-");
				}

			}
		});
		ParseQuery<ParseUser> query1 = ParseUser.getQuery();
		query1.whereContains("objectId", id);
		query1.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> list, ParseException e) {
				// check for ParseException

				for (final ParseObject whatever : list) {
					sum1 = (Integer) whatever.get("total");
				}
				// there is your SUM

				total1.setText("Rs." + sum1 + "/-");
			}
		});

		total2 = sum1;
		post1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if ((!nameedittext.getText().toString().equals(""))
						&& (!orderedittext.getText().toString().equals(""))
						&& (!amountedittext.getText().toString().equals(""))
						&& (!addressedittext.getText().toString().equals(""))) {

					order(id);
				} else {
					Toast.makeText(getApplicationContext(),
							"One or more fields are empty", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});
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
			Intent i = new Intent(PostOrders.this, AddAgent.class);
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
			startActivity(new Intent(PostOrders.this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void order(String id) {

		String name = nameedittext.getText().toString().trim();
		String order1 = orderedittext.getText().toString().trim();
		String amount = amountedittext.getText().toString().trim();
		String address = addressedittext.getText().toString().trim();
		String status = statusedittext.getText().toString().trim();
		final String resturantid = id;
		Toast.makeText(PostOrders.this, "resid" + resturantid,
				Toast.LENGTH_LONG).show();
		String todayearning = amount;
		int total = Integer.parseInt(amount);
		String amount1 = amountedittext.getText().toString();
		x = Integer.valueOf(amount1);
		// Set up a progress dialog

		final ProgressDialog dialog = new ProgressDialog(PostOrders.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		final ParseObject order = new ParseObject("order");

		order.put("name", name);
		order.put("order", order1);
		order.put("amount", amount);
		order.put("address", address);
		order.put("status", status);
		order.put("tearning", todayearning);
		order.put("total", total);
		order.put("resturantid", resturantid);

		// Call the Parse signup method
		order.saveInBackground(new SaveCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// Saved successfully.
					dialog.dismiss();
					Log.d(TAG, "User update saved!");
					String id = order.getObjectId();

					Log.d(TAG, "The object id is: " + id);
					Toast.makeText(PostOrders.this, "your order is posted",
							Toast.LENGTH_LONG).show();

					Intent i = new Intent(PostOrders.this, OrderDetails.class);
					i.putExtra("id", id);
					i.putExtra("resturantid", resturantid);

					startActivity(i);
				} else {
					// The save failed.
					Log.d(TAG, "User update error: " + e);
				}
			}
		});

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}
