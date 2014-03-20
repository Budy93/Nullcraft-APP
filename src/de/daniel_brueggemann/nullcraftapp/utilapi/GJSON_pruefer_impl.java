/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import com.google.gson.Gson;
import java.util.HashMap;

/**
 * @author Daniel Brüggemann
 * @version Beta 0.8.2.2.E1
 */
public class GJSON_pruefer_impl implements GJSON_pruefer
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.GJSON_pruefer#check_key(java.util.
	 * HashMap, java.lang.String)
	 */
	@Override
	public boolean check_key(HashMap JSONObject, String Key)
	{
		/*
		boolean rueck=JSONObject.containsKey(Key);
		return false;
		*/
		
		if(JSONObject.containsKey(Key))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.GJSON_pruefer#check_key_inhalt(java
	 * .util.HashMap, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public boolean check_key_inhalt(HashMap JSON, String Key, String Inhalt,
	        boolean if_checket)
	{
		if(if_checket = false)
		{
			boolean test;
			test = check_key(JSON, Key);
			if(test = false)
			{
				return false;
			}
			else if(test = true)
			{
				if_checket = true;
			}
		}
		else if(if_checket = true)
		{
			final Object check = JSON.get(Key);
			String teststring = check.toString();
			if(teststring.equalsIgnoreCase(Inhalt))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}
