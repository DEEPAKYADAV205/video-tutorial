package delivery.dummyorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DeliveredOrders extends Activity implements
		ActionBar.OnNavigationListener {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewDeliveredAdapter adapter;
	private List<Deliver> deliverlist = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.deliveredorders);
		// Execute RemoteDataTask AsyncTask
		new RemoteDataTask().execute();
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(DeliveredOrders.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Loading Delivered Orders");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			deliverlist = new ArrayList<Deliver>();
			try {
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"delivered");
				query.whereEqualTo("status", "Delivered");
				query.orderByDescending("_created_at");
				ob = query.find();
				for (ParseObject delivered : ob) {

					Deliver map = new Deliver();
					map.setName((String) delivered.get("name"));
					map.setAddress((String) delivered.get("address"));
					map.setAmount((String) delivered.get("amount"));
					map.setOrder((String) delivered.get("order"));
					map.setStatus((String) delivered.get("status"));
					map.setId((String)delivered.getObjectId());
					
					map.setDate((Date) delivered.getCreatedAt());
					deliverlist.add(map);
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
			adapter = new ListViewDeliveredAdapter(DeliveredOrders.this, deliverlist);
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