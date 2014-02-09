/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * @author A380
 *
 */
public class ActivityRegistry
{
	private static List<Activity> _activities;
	
	public static void register(Activity activity)
	{
		if(_activities == null)
		{
			_activities = new ArrayList<Activity>();
		}
		_activities.add(activity);
	}
	
	public static void finishAll()
	{
		for(Activity activity : _activities)
		{
			activity.finish();
		}
	}
	
}
