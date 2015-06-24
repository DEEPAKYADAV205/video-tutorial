package delivery.dummyorder;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.parse.SignUpCallback;

/**
 * Activity which displays a login screen to the user.
 */
public class AddAgent extends Activity {
	// UI references.
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordAgainEditText;
	private EditText phoneEditText, areaEditText, branchEditText,
			addressEditText, emailEditText, logintypeEditText,
			supervisorEditText;

	String mobile;
	private static String url_create_product = "http://www.vsstechnologyapp.in/deliverytrack_db/demody.php";

	JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_addagent);

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
		supervisorEditText = (EditText) findViewById(R.id.logintype_edit_text);
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
				mobile = phoneEditText.toString().trim();
				new RegisterSqlite().execute();
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
		String supervisorname = supervisorEditText.getText().toString().trim();

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
			Toast.makeText(AddAgent.this, validationErrorMessage.toString(),
					Toast.LENGTH_LONG).show();
			return;
		}

		// Set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(AddAgent.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		// Set up a new Parse user
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.put("name", logintype);

		ParseObject agent = new ParseObject("resturant");
		agent.put("supervisorname", supervisorname);
		agent.put("username", username);
		agent.put("password", password);
		agent.put("phone", phone);
		agent.put("area", area);
		agent.put("branch", branch);
		agent.put("address", address);
		agent.put("name", logintype);
		agent.put("email", email);

		// Call the Parse signup method
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				dialog.dismiss();
				if (e != null) {
					// Show the error message
					Toast.makeText(AddAgent.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					// Start an intent for the dispatch activity
					Intent intent = new Intent(AddAgent.this,
							DispatchActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			}
		});
	}

	public class RegisterSqlite extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			String id = mobile;
			String lat = "33.00";
			String lng = "88.00";

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("lat", lat));
			params.add(new BasicNameValuePair("lng", lng));
			params.add(new BasicNameValuePair("id", id));
			// params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			// Note that create product url accepts POST method
			// JSONObject json = JSONParser.makeHttpRequest(url_create_product,
			// "POST", params);

			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			// check log cat for response
			Log.d("Create Response", "Successfull");
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}
	}

}
