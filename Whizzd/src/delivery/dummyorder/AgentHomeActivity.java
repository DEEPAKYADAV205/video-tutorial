package delivery.dummyorder;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgentHomeActivity extends Activity {

	Button btnSee1, btnSee2, btnMap, btnOrder, btnFeed;
	String agentid;
	TextView total,total1;
	Integer sum1=0,sum=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agent_home);

		btnSee1 = (Button) findViewById(R.id.agent_see_button1);
		btnSee2 = (Button) findViewById(R.id.agent_see_button2);

		btnMap = (Button) findViewById(R.id.btn_agent_map);
		btnOrder = (Button) findViewById(R.id.btn_agent_order_available);
		btnFeed = (Button) findViewById(R.id.btn_agent_feed);
		total = (TextView) findViewById(R.id.tde_edittext);
		total1 = (TextView) findViewById(R.id.te_edittext);
		Intent i = getIntent();
		agentid = i.getStringExtra("id");
		ParseQuery<ParseUser> query1 = ParseUser.getQuery();
		query1.whereContains("objectId", agentid);
		query1.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> list, ParseException e) {
				// check for ParseException

				for (final ParseObject whatever : list) {
					sum1 = (Integer) whatever.get("total");
				}
				// there is your SUM
				//Toast.makeText(AgentHomeActivity.this, sum1, Toast.LENGTH_LONG)
				//		.show();
				total.setText("Rs." + sum1 + "/-");
			}
		});
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("delivered");
		query.whereContains("agentid", agentid);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
				// check for ParseException

				for (final ParseObject whatever : list) {
					sum += (Integer) whatever.get("total");
				}
				// there is your SUM
				Toast.makeText(AgentHomeActivity.this, "sum" + sum, Toast.LENGTH_LONG)
						.show();
				total1.setText("Rs." + sum + "/-");
			}
		});

		btnSee1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				seePickup();
			}
		});

		btnSee2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				seeDelivery();
			}
		});

		btnMap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				ageOrderMap();
			}
		});

		btnOrder.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				ageOrderAvailable();
			}
		});

		btnFeed.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				ageFeedback();
			}
		});

	}

	private void seePickup() {
		Intent iPick = new Intent(AgentHomeActivity.this,
				PendingPickedupOrder.class);
	
		iPick.putExtra("agentid", agentid);
		startActivity(iPick);
	}

	private void seeDelivery() {
		Intent iDelivery = new Intent(AgentHomeActivity.this,
				OrderDetails.class);
		
		startActivity(iDelivery);
	}

	private void ageOrderMap() {
		Intent iPost = new Intent(AgentHomeActivity.this, DT_AgentMain.class);
		
		startActivity(iPost);
	}

	private void ageOrderAvailable() {
		Intent iDeliver = new Intent(AgentHomeActivity.this,
				AgentOrdersAvailableActivity.class);
		
		startActivity(iDeliver);
	}

	private void ageFeedback() {
		Intent iFeedback = new Intent(AgentHomeActivity.this,
				AgentFeedbackActivity.class);
		
		startActivity(iFeedback);
	}

	public void ageEarning(View v) {
		Intent iEarning = new Intent(AgentHomeActivity.this,
				AgentEarningActivity.class);
		
		startActivity(iEarning);
	}

}