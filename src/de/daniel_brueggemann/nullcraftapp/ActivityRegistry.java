/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * @author Daniel Brüggemann
 * @version Alpha 0.5
 */
public class ActivityRegistry
{
	private static List<Activity> _activities;
	
	/**
	 * Regisstriert eine Activity
	 * @param activity die zu registrierende Activity
	 */
	public static void register(Activity activity)
	{
		if(_activities == null)
		{
			_activities = new ArrayList<Activity>();
		}
		_activities.add(activity);
	}
	
	/**
	 * Beendent alle registrierte Activitys
	 */
	public static void finishAll()
	{
		for(Activity activity : _activities)
		{
			activity.finish();
		}
	}
	
	/**
	 * Beendet eine Spezifische Activity, don't work at the moment
	 */
	public static void finishthis()
	{
		int index = _activities.size() - 1;
		Activity activity = _activities.get(index);
		activity.finish();
	}
}
