package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PendingItemView extends Activity {
	// Declare Variables
	String name;
	String order;
	String amount;
	String address;
	String status;
	String date;
	String position;
	String id;
	String resturantid;
	TextView txtname, txtorder, txtamount, txtaddress, txtstatus, txtdate,txtresturantid,txtorderid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.pendingorderitemview);

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

		// Get the result of flag

		// Locate the TextViews in singleitemview.xml
		txtname = (TextView) findViewById(R.id.name);
		txtorder = (TextView) findViewById(R.id.order);
		txtamount = (TextView) findViewById(R.id.amount);
		txtaddress = (TextView) findViewById(R.id.address);
		txtstatus = (TextView) findViewById(R.id.status);
		txtresturantid = (TextView) findViewById(R.id.resturantid);
		txtorderid = (TextView) findViewById(R.id.orderid);
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
		txtdate.setText(date);

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class

	}

}