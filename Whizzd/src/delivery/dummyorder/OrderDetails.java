package delivery.dummyorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OrderDetails extends Activity implements
		ActionBar.OnNavigationListener {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private List<Order> orderlist = null;
	String agentid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listview_main);
		// Execute RemoteDataTask AsyncTask
		Intent i = getIntent();
		 agentid = i.getStringExtra("agentid");
		
		new RemoteDataTask().execute();
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(OrderDetails.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Loading Orders");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			orderlist = new ArrayList<Order>();
			try {
				
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"order");

				query.whereEqualTo("status", "Pending");
				// query.whereContains("address", );
				query.orderByDescending("_created_at");
				ob = query.find();
				for (ParseObject order : ob) {

					Order map = new Order();
					map.setName((String) order.get("name"));
					map.setAddress((String) order.get("address"));
					map.setAmount((String) order.get("amount"));
					map.setOrder((String) order.get("order"));
					map.setStatus((String) order.get("status"));
					map.setId((String) order.getObjectId());
					map.setResturantid((String)order.get("resturantid"));

					map.setDate((Date) order.getCreatedAt());
					orderlist.add(map);
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(OrderDetails.this, orderlist);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}