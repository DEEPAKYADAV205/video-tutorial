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
public class ResturantSignup extends Activity {
	// UI references.
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordAgainEditText;
	private EditText phoneEditText, areaEditText, branchEditText,
			addressEditText, emailEditText, logintypeEditText,orgnameEditText,orgtypeEditText,empcountEditText,alternateEditText,brandnameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_resignup);

		// Set up the signup form.
		usernameEditText = (EditText) findViewById(R.id.username_edit_text);
		phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
		areaEditText = (EditText) findViewById(R.id.area_edit_text);
		branchEditText = (EditText) findViewById(R.id.branch_edit_text);
		addressEditText = (EditText) findViewById(R.id.address_edit_text);
		passwordEditText = (EditText) findViewById(R.id.password_edit_text);
		passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
		logintypeEditText = (EditText) findViewById(R.id.logintype_edit_text);
		emailEditText = (EditText) findViewById(R.id.email_edit_text);
		orgnameEditText = (EditText) findViewById(R.id.orgname_edit_text);
		orgtypeEditText = (EditText) findViewById(R.id.orgtype_edit_text);
		empcountEditText = (EditText) findViewById(R.id.empcount_edit_text);
		brandnameEditText = (EditText) findViewById(R.id.brand_edit_text);
		alternateEditText = (EditText) findViewById(R.id.alternate_edit_text);

		

		// Set up the submit button click handler
		Button mActionButton = (Button) findViewById(R.id.action_button);
		mActionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (  ( !usernameEditText.getText().toString().equals("")) && ( !phoneEditText.getText().toString().equals("")) && ( !areaEditText.getText().toString().equals("")) && ( !branchEditText.getText().toString().equals("")) && ( !addressEditText.getText().toString().equals(""))&& ( !emailEditText.getText().toString().equals(""))&& ( !logintypeEditText.getText().toString().equals(""))&& ( !alternateEditText.getText().toString().equals(""))&& ( !emailEditText.getText().toString().equals("")) && ( !orgnameEditText.getText().toString().equals(""))&& ( !orgtypeEditText.getText().toString().equals(""))&& ( !empcountEditText.getText().toString().equals("")) && ( !brandnameEditText.getText().toString().equals("")) )
				{
					if(passwordEditText.getText().toString().equals(passwordAgainEditText.getText().toString())){
							
					signup();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"One or more fields are empty", Toast.LENGTH_SHORT).show();
				}
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
			Toast.makeText(ResturantSignup.this,
					validationErrorMessage.toString(), Toast.LENGTH_LONG)
					.show();
			return;
		}

		// Set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(ResturantSignup.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		// Set up a new Parse user
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.put("name", logintype);
		user.put("phone", phone);
		ParseObject resturant = new ParseObject("resturant");
		resturant.put("username", username);
		resturant.put("password", password);
		resturant.put("phone", phone);
		resturant.put("area", area);
		resturant.put("branch", branch);
		resturant.put("address", address);
		resturant.put("name", logintype);
		resturant.put("email", email);

		// Call the Parse signup method
		resturant.saveInBackground();
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				dialog.dismiss();
				if (e != null) {
					// Show the error message
					Toast.makeText(ResturantSignup.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					// Start an intent for the dispatch activity
					Intent intent = new Intent(ResturantSignup.this,
							DispatchActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			}
		});
	}
}
