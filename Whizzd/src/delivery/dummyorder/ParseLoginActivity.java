package delivery.dummyorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class ParseLoginActivity extends Activity {
	// UI references.
	private EditText usernameEditText;
	private EditText passwordEditText;
	private RadioGroup radioGroup;
	private RadioButton radioButton;
	String type = "Supervisor";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// Set up the login form.
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		radioGroup = (RadioGroup) findViewById(R.id.radiotype);

		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// checkedId is the RadioButton selected
						RadioButton rb = (RadioButton) findViewById(checkedId);
						type = (String) rb.getText();

					}
				});

		// Set up the submit button click handler
		Button actionButton = (Button) findViewById(R.id.action_button);
		actionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				login();

			}
		});
	}

	private void login() {
		String username = usernameEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();

		// Set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(ParseLoginActivity.this);
		dialog.setMessage(getString(R.string.progress_login));
		dialog.show();
		// Call the Parse login method

		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {
				dialog.dismiss();

				// ParseQuery<ParseUser> query = ParseQuery.getQuery("User");
				// String ne = user.getObjectId();

				if (user != null && user.getString("name") != null) {

					if (user.getString("name").equalsIgnoreCase("Agent")
							&& type.equalsIgnoreCase("Agent")) {

						// Start an intent for the dispatch activity
						Intent intent = new Intent(ParseLoginActivity.this,
								AgentHomeActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
								| Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("id", user.getObjectId());
						Toast.makeText(ParseLoginActivity.this,
								"Agent login Successfully", Toast.LENGTH_LONG)
								.show();
						startActivity(intent);
					} else if (user.getString("name").equalsIgnoreCase(
							"Supervisor")
							&& type.equalsIgnoreCase("Supervisor")) {
						// Start an intent for the dispatch activity
						Intent intent = new Intent(ParseLoginActivity.this,
								AgencyHomeActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
								| Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("id", user.getObjectId());
						Toast.makeText(ParseLoginActivity.this,
								"Supervisor login Successfully",
								Toast.LENGTH_LONG).show();
						startActivity(intent);
					} else if (user.getString("name").equalsIgnoreCase(
							"Resturant")
							&& type.equalsIgnoreCase("Resturant")) {
						// Start an intent for the dispatch activity
						Intent intent = new Intent(ParseLoginActivity.this,
								Details.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
								| Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("id", user.getObjectId());
						
						Toast.makeText(ParseLoginActivity.this,
								"Restaurant login Successfully",
								Toast.LENGTH_LONG).show();
						startActivity(intent);
					} else {
						Toast.makeText(
								ParseLoginActivity.this,
								"Invalid username or password or role selected",
								Toast.LENGTH_LONG).show();
					}
				}

				else {
					Toast.makeText(ParseLoginActivity.this,
							"invalid username or password", Toast.LENGTH_LONG)
							.show();
				}
			}

		});
	}

}
