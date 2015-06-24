package delivery.dummyorder;



import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

public class DeliveryAlarm extends Activity {
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deliveryalarm);
		
		
		try {
			
	        //Create a new PendingIntent and add it to the AlarmManager
	        Intent intent = new Intent(this, RingAlarm.class);
	        PendingIntent pendingIntent = PendingIntent.getActivity(this,
	            12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
	        AlarmManager am =
	            (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
	        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
	        		2*60*60,pendingIntent);
			
		  } catch (Exception e) {}
	}
}
