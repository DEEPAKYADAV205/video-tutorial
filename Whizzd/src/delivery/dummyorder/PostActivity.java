package delivery.dummyorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Activity which displays a login screen to the user, offering registration as well.
 */
public class PostActivity extends Activity {
  // UI references.
  private EditText postEditText;
  private TextView characterCountTextView;
  private Button postButton;
  EditText nameedittext, orderedittext, amountedittext, addressedittext, statusedittext,phoneedittext;

  private int maxCharacterCount = Application.getConfigHelper().getPostMaxCharacterCount();
  private ParseGeoPoint geoPoint;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_post);

    Intent intent = getIntent();
    Location location = intent.getParcelableExtra(Application.INTENT_EXTRA_LOCATION);
    geoPoint = new ParseGeoPoint(location.getLatitude(), location.getLongitude());

    postEditText = (EditText) findViewById(R.id.post_edittext);
    nameedittext = (EditText) findViewById(R.id.name);
    orderedittext = (EditText) findViewById(R.id.order);
    amountedittext = (EditText) findViewById(R.id.amount);
    addressedittext = (EditText) findViewById(R.id.address);
    statusedittext = (EditText) findViewById(R.id.status);
    phoneedittext = (EditText) findViewById(R.id.phone);
    postEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      @Override
      public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        updatePostButtonState();
        updateCharacterCountTextViewText();
      }
    });

    characterCountTextView = (TextView) findViewById(R.id.character_count_textview);

    postButton = (Button) findViewById(R.id.post_button);
    postButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        post();
      }
    });

    updatePostButtonState();
    updateCharacterCountTextViewText();
  }

  private void post () {
    String text = postEditText.getText().toString().trim();
    String name1 = nameedittext.getText().toString().trim();
    String order1 = orderedittext.getText().toString().trim();
    String amount1 = amountedittext.getText().toString().trim();
    String status1 = "pending";
    String phone1 = phoneedittext.getText().toString().trim();
    String address1 = addressedittext.getText().toString().trim();
   

    // Set up a progress dialog
    final ProgressDialog dialog = new ProgressDialog(PostActivity.this);
    dialog.setMessage(getString(R.string.progress_post));
    dialog.show();

    // Create a post.
    AnywallPost post = new AnywallPost();

    // Set the location to the current user's location
    post.setLocation(geoPoint);
    post.setText(text);
    post.setName(name1);
    post.setAmount(amount1);
    post.setPhone(phone1);
    post.setOrder(order1);
    post.setAddress(address1);
    post.setStatus(status1);
    post.setUser(ParseUser.getCurrentUser());
    ParseACL acl = new ParseACL();

    // Give public read access
    acl.setPublicReadAccess(true);
    post.setACL(acl);

    // Save the post
    post.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        dialog.dismiss();
        finish();
      }
    });
  }

  private String getPostEditTextText () {
    return postEditText.getText().toString().trim();
  }

  private void updatePostButtonState () {
    int length = getPostEditTextText().length();
    boolean enabled = length > 0 && length < maxCharacterCount;
    postButton.setEnabled(enabled);
  }

  private void updateCharacterCountTextViewText () {
    String characterCountString = String.format("%d/%d", postEditText.length(), maxCharacterCount);
    characterCountTextView.setText(characterCountString);
  }
}
