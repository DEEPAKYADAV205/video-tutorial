package delivery.dummyorder;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Details extends Activity implements ActionBar.OnNavigationListener {
	Button po, fee, orders, see1, see2;
	private ActionBar actionBar;
	TextView total, total1;
	String id;
	Integer sum = 0;
	Integer sum1 = 0;

	// Refresh menu item
	private MenuItem refreshMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_home);
		actionBar = getActionBar();
		Intent i = getIntent();
		id = i.getStringExtra("id");
		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(true);
		po = (Button) findViewById(R.id.layout_button1);
		see1 = (Button) findViewById(R.id.see_button1);
		see2 = (Button) findViewById(R.id.see_button2);
		orders = (Button) findViewById(R.id.layout_button2);
		fee = (Button) findViewById(R.id.layout_button3);
		total = (TextView) findViewById(R.id.tv_total);
		total1 = (TextView) findViewById(R.id.te_edittext);
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
				Toast.makeText(Details.this, "sum" + sum, Toast.LENGTH_LONG)
						.show();
				total1.setText("Rs." + sum + "/-");
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
				Toast.makeText(Details.this, "sum" + sum1, Toast.LENGTH_LONG)
						.show();
				total.setText("Rs." + sum1 + "/-");
			}
		});

		po.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Details.this, PostOrders.class);
				i.putExtra("id", id);

				startActivity(i);

			}
		});
		orders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Details.this,
						RestaurantFeedbackActivity.class);
				i.putExtra("id", id);
				startActivity(i);

			}
		});
		fee.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Details.this, DeliveredOrders.class);
				i.putExtra("id", id);
				startActivity(i);

			}
		});
		see1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Details.this, PendingOrders.class);
				i.putExtra("id", id);
				startActivity(i);

			}
		});
		see2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Details.this, DeliveredOrders.class);
				i.putExtra("id", id);
				startActivity(i);

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
			Intent i = new Intent(Details.this, ResturantSignup.class);
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
		case R.id.changepassword:
			// help action
			return true;
		case R.id.action_check_updates:
			// check for updates action
			return true;
		case R.id.action_check_settings:
			// check for updates action
			startActivity(new Intent(Details.this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}
