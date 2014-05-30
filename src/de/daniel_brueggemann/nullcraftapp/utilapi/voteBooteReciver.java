/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Erstellt den Alarm nach den Restart
 * @author Daniel Brüggemann
 *
 */
public class voteBooteReciver extends BroadcastReceiver
{
	votealarmReciver alarm = new votealarmReciver();
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
		{
			alarm.setAlarm(context);
		}
	}
}
