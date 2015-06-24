package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgencyDPAvailabilityActivity extends Activity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_dp_availability);

        btnReturn = (Button) findViewById(R.id.agency_dp_availability_return_button);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ageReturn();
            }
        });
    }

    private void ageReturn()
    {
        Intent iReturn = new Intent(AgencyDPAvailabilityActivity.this, AgencyHomeActivity.class);
        iReturn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iReturn);
    }

}