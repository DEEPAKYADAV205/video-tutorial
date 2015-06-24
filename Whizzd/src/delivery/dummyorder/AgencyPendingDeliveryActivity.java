package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgencyPendingDeliveryActivity extends Activity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_pending_delivery);

        btnReturn = (Button) findViewById(R.id.agent_pending_delivery_button);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ageReturn();
            }
        });
    }

    private void ageReturn()
    {
        Intent iDelivery = new Intent(AgencyPendingDeliveryActivity.this, AgentHomeActivity.class);
        iDelivery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iDelivery);
    }

}