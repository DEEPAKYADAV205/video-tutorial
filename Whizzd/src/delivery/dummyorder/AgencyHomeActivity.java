package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgencyHomeActivity extends Activity {

	Button btnDP, btnOrder,track;
	String agentid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agency_home);
Intent i = getIntent();
agentid = i.getStringExtra("id");

		btnDP = (Button) findViewById(R.id.agency_dp_button);
		track = (Button) findViewById(R.id.agenttrack);
		
		btnDP.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				ageDP();
			}
		});
		track.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(AgencyHomeActivity.this,DT_SupervisorMain.class);
				startActivity(i);
				
			}
		});


		btnOrder = (Button) findViewById(R.id.agency_order_button);

		btnOrder.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				ageOrder();
			}
		});
	}

	private void ageDP() {
		Intent iDP = new Intent(AgencyHomeActivity.this,
				AgencyDPAvailabilityActivity.class);
		iDP.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(iDP);
	}

	private void ageOrder() {
		Intent iOrder = new Intent(AgencyHomeActivity.this,
				AgencyTodayOrderActivity.class);
		iOrder.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(iOrder);
	}

	private void agePickup() {
		Intent iPickup = new Intent(AgencyHomeActivity.this,
				OrderDetails.class);
		iPickup.putExtra("agentid", agentid);
		iPickup.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(iPickup);
	}

	private void ageDelivery() {
		Intent iDelivery = new Intent(AgencyHomeActivity.this,
				AgencyPendingDeliveryActivity.class);
		iDelivery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(iDelivery);
	}

}