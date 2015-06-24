package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class SingleItemView extends Activity {
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

	TextView txtname, txtorder, txtamount, txtaddress, txtstatus, txtdate,
			txtlocation, txtorderid, txtresturantid, txtagentid;
	Button pickedup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
		pickedup = (Button) findViewById(R.id.pickedup);

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
		location = i.getStringExtra("location");
		id = i.getStringExtra("id");
		resturantid = i.getStringExtra("resturantid");

		Toast.makeText(SingleItemView.this, id, Toast.LENGTH_LONG).show();

		pickedup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				pickedup();

				ParseObject.createWithoutData("order", id).deleteEventually();

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

	public void pickedup() {

		String name = txtname.getText().toString().trim();
		String order1 = txtorder.getText().toString().trim();
		String amount = txtamount.getText().toString().trim();
		String address = txtaddress.getText().toString().trim();
		String resturantid = txtresturantid.getText().toString().trim();
		ParseUser currentUser = ParseUser.getCurrentUser();
	String agentid = currentUser.getObjectId();
	Toast.makeText(SingleItemView.this, "agentid"+agentid,
			Toast.LENGTH_LONG).show();
	Toast.makeText(SingleItemView.this, "Order is pickedup by"+currentUser,
			Toast.LENGTH_LONG).show();
		String status = "Pickedup";

		final ParseObject pickedup = new ParseObject("pickedup");

		pickedup.put("name", name);
		pickedup.put("order", order1);
		pickedup.put("amount", amount);
		pickedup.put("address", address);
		pickedup.put("status", status);
		pickedup.put("resturantid", resturantid);
		pickedup.put("agentid", agentid);

		// Call the Parse signup method
pickedup.saveInBackground();
		Toast.makeText(SingleItemView.this, "Order is pickedup",
				Toast.LENGTH_LONG).show();
		Intent i = new Intent(SingleItemView.this, AgentHomeActivity.class);
		startActivity(i);

	}

}