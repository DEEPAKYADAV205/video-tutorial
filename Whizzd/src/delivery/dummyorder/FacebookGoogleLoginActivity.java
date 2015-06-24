package delivery.dummyorder;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookGoogleLoginActivity extends FragmentActivity {

	Button loginBtn;
	Button login, register;
	Button glogin;

	// Your Facebook APP ID
	private static String APP_ID = "440174976140622"; // Replace your App ID
														// here

	// Instance of Facebook Class
	private Facebook facebook;
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		facebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);

		setContentView(R.layout.activity_fg);
		login = (Button) findViewById(R.id.login_button1);
		register = (Button) findViewById(R.id.signup_button1);
		glogin = (Button) findViewById(R.id.btnGplus);

		loginBtn = (Button) findViewById(R.id.btnFb);

		glogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent ii = new Intent(FacebookGoogleLoginActivity.this, WelcomeActivity.class);
				startActivity(ii);

			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(FacebookGoogleLoginActivity.this, ParseLoginActivity.class);
				startActivity(i);

			}
		});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(FacebookGoogleLoginActivity.this, WelcomeActivity.class);
				startActivity(i);

			}
		});
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loginToFacebook();
			}
		});
	}

	public void loginToFacebook() {
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							Intent i = new Intent(FacebookGoogleLoginActivity.this, WelcomeActivity.class);
							startActivity(i);

						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}

}