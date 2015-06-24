package delivery.dummyorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

/**
 * Activity which displays a login screen to the user.
 */
public class FreeLancer extends Activity {
	// UI references.
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordAgainEditText;
	private EditText phoneEditText, areaEditText, branchEditText,alternateEditText,
			addressEditText, emailEditText, logintypeEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.freelancer);

		// Set up the signup form.
		usernameEditText = (EditText) findViewById(R.id.username_edit_text);
		phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
		alternateEditText = (EditText) findViewById(R.id.alternate_edit_text);
		areaEditText = (EditText) findViewById(R.id.area_edit_text);
		branchEditText = (EditText) findViewById(R.id.branch_edit_text);
		addressEditText = (EditText) findViewById(R.id.address_edit_text);
		passwordEditText = (EditText) findViewById(R.id.password_edit_text);
		passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
		logintypeEditText = (EditText) findViewById(R.id.logintype_edit_text);
		emailEditText = (EditText) findViewById(R.id.email_edit_text);
		passwordAgainEditText
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == R.id.edittext_action_signup
								|| actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
							signup();
							return true;
						}
						return false;
					}
				});

		// Set up the submit button click handler
		Button mActionButton = (Button) findViewById(R.id.action_button);
		mActionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				signup();
			}
		});
	}

	private void signup() {
		String username = usernameEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();
		String passwordAgain = passwordAgainEditText.getText().toString()
				.trim();
		String phone = phoneEditText.getText().toString().trim();
		String area = areaEditText.getText().toString().trim();
		String branch = branchEditText.getText().toString().trim();
		String address = addressEditText.getText().toString().trim();
		String logintype = logintypeEditText.getText().toString().trim();

		String email = emailEditText.getText().toString().trim();

		// Validate the sign up data
		boolean validationError = false;
		StringBuilder validationErrorMessage = new StringBuilder(
				getString(R.string.error_intro));
		if (username.length() == 0) {
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_username));
		}

		if (password.length() == 0) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_password));
		}
		if (!password.equals(passwordAgain)) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_mismatched_passwords));
		}
		validationErrorMessage.append(getString(R.string.error_end));

		// If there is a validation error, display the error
		if (validationError) {
			Toast.makeText(FreeLancer.this,
					validationErrorMessage.toString(), Toast.LENGTH_LONG)
					.show();
			return;
		}

		// Set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(FreeLancer.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		// Set up a new Parse user
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.put("name", logintype);
		user.put("phone", phone);
		ParseObject agent = new ParseObject("agent");
		agent.put("username", username);
		agent.put("password", password);
		agent.put("phone", phone);
		agent.put("area", area);
		agent.put("branch", branch);
		agent.put("address", address);
		agent.put("name", logintype);
		agent.put("email", email);

		// Call the Parse signup method
		agent.saveInBackground();
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				dialog.dismiss();
				if (e != null) {
					// Show the error message
					Toast.makeText(FreeLancer.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					// Start an intent for the dispatch activity
					Intent intent = new Intent(FreeLancer.this,
							ParseLoginActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			}
		});
	}
}
