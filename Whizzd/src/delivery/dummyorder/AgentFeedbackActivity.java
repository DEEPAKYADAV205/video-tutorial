package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 30-05-2015.
 */
public class AgentFeedbackActivity extends Activity {

    Button btnReturn, btnSee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_ratings_feedback);

        btnReturn = (Button) findViewById(R.id.agent_feedback_return_button);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ageReturn();
            }
        });

        btnSee = (Button) findViewById(R.id.agent_feedback_see_button);

        btnSee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ageSee();
            }
        });
    }

    private void ageReturn()
    {
        Intent iHome = new Intent(AgentFeedbackActivity.this, AgentHomeActivity.class);
        iHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iHome);
    }

    private void ageSee()
    {

    }

}