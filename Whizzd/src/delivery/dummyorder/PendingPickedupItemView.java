package delivery.dummyorder;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class PendingPickedupItemView extends Activity {
	// Declare Variables
	String name;
	String order;
	String amount;
	String address;
	String status;
	String date;
	String position;
	String location;
	String id;
	String orderid;
	String resturantid;
	String agentid;
	String sum1;
	int sum = 0;
	TextView txtname, txtorder, txtamount, txtaddress, txtstatus, txtdate,
			txtlocation, txtorderid, txtresturantid, txtagentid;
	Button delivered;
	Integer debit = 0, debit1 = 0, total = 0, total1 = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml

		setContentView(R.layout.pendingorderitemview);
		delivered = (Button) findViewById(R.id.del);
		Intent i = getIntent();
		// Get the result of rank
		name = i.getStringExtra("name");
		// Get the result of country
		order = i.getStringExtra("order");
		// Get the result of population
		amount = i.getStringExtra("amount");
		address = i.getStringExtra("address");
		status = i.getStringExtra("status");
		date = i.getStringExtra("date");

		id = i.getStringExtra("id");
		resturantid = i.getStringExtra("resturantid");
		agentid = i.getStringExtra("agent");

		delivered.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				delivered();

				ParseObject.createWithoutData("pickedup", id)
						.deleteEventually();
			}
		});

		// Get the result of flag

		// Locate the TextViews in singleitemview.xml
		txtname = (TextView) findViewById(R.id.name);
		txtorder = (TextView) findViewById(R.id.order);
		txtamount = (TextView) findViewById(R.id.amount);
		txtaddress = (TextView) findViewById(R.id.address);
		txtstatus = (TextView) findViewById(R.id.status);
		txtresturantid = (TextView) findViewById(R.id.resturantid);
		txtorderid = (TextView) findViewById(R.id.orderid);
		txtagentid = (TextView) findViewById(R.id.agent);
		txtdate = (TextView) findViewById(R.id.time);

		// Locate the ImageView in singleitemview.xml

		// Set results to the TextViews
		txtname.setText(name);
		txtorder.setText(order);
		txtamount.setText(amount);
		txtaddress.setText(address);
		txtstatus.setText(status);
		txtresturantid.setText(resturantid);
		txtorderid.setText(id);
		txtagentid.setText(agentid);
		txtdate.setText(date);

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class

	}

	public void delivered() {

		String name = txtname.getText().toString().trim();
		String order1 = txtorder.getText().toString().trim();
		String amount = txtamount.getText().toString().trim();
		String address = txtaddress.getText().toString().trim();
		String resturantid = txtresturantid.getText().toString().trim();
		String orderid = txtorderid.getText().toString().trim();
		final String agentid = txtagentid.getText().toString().trim();
		String status = "Delivered";
		total = Integer.parseInt(amount);
		total1 = 30;

		final ParseObject delivered = new ParseObject("delivered");

		delivered.put("name", name);
		delivered.put("order", order1);
		delivered.put("amount", amount);
		delivered.put("address", address);
		delivered.put("status", status);
		delivered.put("resturantid", resturantid);
		delivered.put("orderid", orderid);
		delivered.put("agentid", agentid);
		delivered.put("total", total1);
		delivered.put("resturanttotal", total);
		// Call the Parse signup method
		delivered.saveInBackground();

		Toast.makeText(PendingPickedupItemView.this, "Your order is Delivered",
				Toast.LENGTH_LONG).show();
		ParseQuery<ParseUser> query1 = ParseUser.getQuery();
		query1.whereContains("objectId", agentid);

		query1.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> list, ParseException e) {
				// check for ParseException
				for (final ParseObject whatever : list) {
					Number sum1 = whatever.getNumber("total");
					// sum1 = (String) whatever.get("total");
					// sum=Integer.parseInt(sum1);
					Toast.makeText(PendingPickedupItemView.this,
							"dbsum1" + sum1, Toast.LENGTH_LONG).show();
				}
				Toast.makeText(PendingPickedupItemView.this, "dbsum1" + sum1,
						Toast.LENGTH_LONG).show();
			
			}
		});
		debit1 = sum - total1 + total;
		Toast.makeText(PendingPickedupItemView.this,
				"sum1" + sum1 + " " + total + " " + total1, Toast.LENGTH_LONG)
				.show();

		ParseQuery<ParseUser> query = ParseQuery.getUserQuery();
		// Retrieve the object by id
		query.getInBackground(agentid, new GetCallback<ParseUser>() {
			public void done(ParseUser user, ParseException e) {
				if (e == null) {
					// Now let's update it with some new data. In this
					// case, only cheatMode and score
					// will get sent to the Parse Cloud. playerName
					// hasn't changed.
					Toast.makeText(getBaseContext(), "debit1" + debit1,
							Toast.LENGTH_SHORT).show();
					user.put("total", debit1);
					// user.add("total",debit1);
					user.saveInBackground();
				}
			}
		});

		Intent i = new Intent(PendingPickedupItemView.this,
				AgentHomeActivity.class);
		i.putExtra("id", agentid);
		startActivity(i);

		
	}

}