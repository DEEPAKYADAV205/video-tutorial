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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PendingPickedupOrder extends Activity implements
		ActionBar.OnNavigationListener {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewPendingPickedupAdapter adapter;
	private List<Pickedup> orderlist = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.pendingpickeduporders);
		// Execute RemoteDataTask AsyncTask
		new RemoteDataTask().execute();
	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(PendingPickedupOrder.this);
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
			orderlist = new ArrayList<Pickedup>();
			try {
				Intent i = getIntent();
				String id = i.getStringExtra("agentid");
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"pickedup");

				query.orderByDescending("_created_at");
				query.whereContains("agentid", id);
				ob = query.find();
				for (ParseObject order : ob) {

					Pickedup map = new Pickedup();
					map.setName((String) order.get("name"));
					map.setAddress((String) order.get("address"));
					map.setAmount((String) order.get("amount"));
					map.setOrder((String) order.get("order"));
					map.setStatus((String) order.get("status"));

					map.setId((String) order.getObjectId());
					map.setResturantid((String) order.get("resturantid"));
					map.setAgenttid((String) order.get("agentid"));
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
			adapter = new ListViewPendingPickedupAdapter(PendingPickedupOrder.this,
					orderlist);
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