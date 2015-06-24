package delivery.dummyorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DT_AgentGPS extends Activity {

	Button btnShowLocation, btnShowOnMap;
	// server
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	// private static String url_create_product =
	// "http://dyandroidapps.netii.net/demody.php";

	private static String url_create_product = "http://www.vsstechnologyapp.in/deliverytrack_db/demody.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	// GPSTracker class
	// GPSTracker gps;
	String lat = "", lng = "";
	String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dtagentgps);
		// id = getIntent().getExtras().getString("number");

		// Intent i = new Intent(this, GPSTracker.class);
		// i.putExtra("number", id);
		// startActivity(i);

		// Toast.makeText(getApplicationContext(), "number is" + id,
		// Toast.LENGTH_SHORT).show();
		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

		btnShowOnMap = (Button) findViewById(R.id.btnmap);
	}

	public void showOnmap(View v1) {

		Intent map = new Intent(this, MapActivity.class);
		startActivity(map);

	}
}
