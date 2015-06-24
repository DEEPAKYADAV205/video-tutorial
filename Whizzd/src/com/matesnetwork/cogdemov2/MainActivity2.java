package com.matesnetwork.cogdemov2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matesnetwork.Cognalys.VerifyMobile;

import delivery.dummyorder.ParseLoginActivity;
import delivery.dummyorder.R;

public class MainActivity2 extends ActionBarActivity {
	Button test;
	EditText mobilenum;
	EditText countrycode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main2);
		test = (Button) findViewById(R.id.test);
		
		mobilenum = (EditText) findViewById(R.id.editText1);
		countrycode = (EditText) findViewById(R.id.editText2);
		countrycode.setText(VerifyMobile
				.getCountryCode(getApplicationContext()));
		
		test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mobile = countrycode.getText().toString()
						+ mobilenum.getText().toString();
				Intent in = new Intent(MainActivity2.this, VerifyMobile.class);

				in.putExtra("app_id", "940b585984164338ac13039");
				in.putExtra("access_token", "e1f80d794a215f0c9a4273f7045e729e35fa26bd");
				in.putExtra("mobile", mobile);
				if (mobile.length() == 0) {
					countrycode.setError("Please enter mobile number");
				} else {
					if (CheckNetworkConnection
							.isConnectionAvailable(getApplicationContext())) {
						startActivityForResult(in, VerifyMobile.REQUEST_CODE);
						
					
					} else {
						Toast.makeText(getApplicationContext(),
								"no internet connection", Toast.LENGTH_SHORT)
								.show();
					}
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);

		if (arg0 == VerifyMobile.REQUEST_CODE) {
			String message = arg2.getStringExtra("message");
			int result = arg2.getIntExtra("result", 0);

			
			Intent i = new Intent(MainActivity2.this,ParseLoginActivity.class);
			startActivity(i);

		}
	}

}
