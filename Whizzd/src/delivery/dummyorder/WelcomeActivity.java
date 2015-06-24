package delivery.dummyorder;



import com.example.android.wizardpager.MainActivity;
import com.example.android.wizardpager1.MainActivity1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity which displays a registration screen to the user.
 */
public class WelcomeActivity extends Activity {
Button resturant,free;
Button signupButton;
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
resturant = (Button)findViewById(R.id.signup_resturant);
 free = (Button)findViewById(R.id.signup_free);
    // Log in button click handler
    Button loginButton = (Button) findViewById(R.id.login_button);
    loginButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        // Starts an intent of the log in activity
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
      }
    });
    resturant.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
          // Starts an intent of the log in activity
          startActivity(new Intent(WelcomeActivity.this, MainActivity1.class));
        }
      });
    free.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
          // Starts an intent of the log in activity
          startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
      });

    // Sign up button click handler
 signupButton = (Button) findViewById(R.id.signupw_button);
    signupButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        // Starts an intent for the sign up activity
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
      }
    });
  }
}
