package delivery.dummyorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Udit Paul on 29-05-2015.
 */
public class RestaurantFeedbackActivity extends Activity {

    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_rating);

        btnReturn = (Button) findViewById(R.id.restaurant_feedback_return_button);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                resReturn();
            }
        });
    }

    private void resReturn()
    {
        Intent iDelivery = new Intent(RestaurantFeedbackActivity.this, Details.class);
        iDelivery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iDelivery);
    }

}