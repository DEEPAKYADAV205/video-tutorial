package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgentOrdersAvailableActivity extends Activity {

    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_order_availability);

        btnAccept = (Button) findViewById(R.id.agent_order_availability_button);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ageReturn();
            }
        });
    }

    private void ageReturn()
    {
        Intent iAvailable = new Intent(AgentOrdersAvailableActivity.this, AgentHomeActivity.class);
        iAvailable.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iAvailable);
    }

}