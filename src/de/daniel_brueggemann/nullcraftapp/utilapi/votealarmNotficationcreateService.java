/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import de.daniel_brueggemann.nullcraftapp.R;
import de.daniel_brueggemann.nullcraftapp.VoteActivity;

/**
 * Servic der den User die Erinnerung an den Vote schickt.
 * @author Daniel Brüggemann
 *
 */
public class votealarmNotficationcreateService extends IntentService
{
	private NotificationManager mNotificationManager;
	public NotificationCompat.Builder builder;
	private final String msg = "Denk bitte daran zu Voten";
	private final String title = "Voten";
	public static final int NOTIFICATION_ID = 1;
	
	/**
	 * 
	 */
	public votealarmNotficationcreateService()
	{
		super("SchedulingService");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent)
	{
		
		// Release the wake lock provided by the BroadcastReceiver.
		votealarmReciver.completeWakefulIntent(intent);
		// END_INCLUDE(service_onhandle)
		sendNotification();
	}
	
	// Post a notification indicating whether a doodle was found.
	/**
	 * Erstellt eine Notifikation
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void sendNotification()
	{
		if(android.os.Build.VERSION.SDK_INT >= 11)
		{
			Vibrator vibra = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			boolean vibrator = vibra.hasVibrator();
			if(vibrator == true)
			{
				vibra.vibrate(500);
			}
		}
		mNotificationManager = (NotificationManager) this
		        .getSystemService(Context.NOTIFICATION_SERVICE);
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		        new Intent(this, VoteActivity.class), 0);
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
		        this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(title)
		        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
		        .setContentText(msg)
		        .setSound(
		                RingtoneManager
		                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setAutoCancel(true);
		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
	
}
