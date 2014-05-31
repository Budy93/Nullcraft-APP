/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Empfäng das Bootsignal vom Android OS-System
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
