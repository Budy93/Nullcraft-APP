/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;

import de.daniel_brueggemann.nullcraftapp.R;

import java.io.BufferedReader;
import java.net.MalformedURLException;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * @author A380
 *
 */
public class Networkthreadimpl implements Networkthread
{
	
	/* (non-Javadoc)
	 * @see de.daniel_brueggemann.nullcraftapp.Networkthread#pingserver(java.lang.String)
	 */
	@Override
	public HashMap pingserver(String Server)
	{
		if(android.os.Build.VERSION.SDK_INT > 9)
		{
			final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			        .permitNetwork().build();
			StrictMode.setThreadPolicy(policy);
		}
		try
		{
			final URL url = new URL("http://api.iamphoenix.me/get/?server_ip="
			        + Server);
			try
			{
				final BufferedReader reader = new BufferedReader(
				        new InputStreamReader(url.openStream()));
				final String data = reader.readLine();
				
				final Gson gson = new Gson();
				return gson.fromJson(data, HashMap.class);
			}
			catch (final MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
