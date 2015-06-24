package delivery.dummyorder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Udit Paul on 31-05-2015.
 */
public class AgentEarningActivity extends Activity {
	Button ret;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_earning);
		ret = (Button)findViewById(R.id.delivered_button2);

	}
}
