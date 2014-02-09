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

/**
 * @author A380
 *
 */
public class MainActivity extends Activity implements OnClickListener
{
	public static TextView text;
	public static TextView texti;
	public static Button But;
	public static Button Version;
	public static Button Vote;
	public static Button Dynmap;
	public static TextView Player;
	public static TextView Playermay;
	public static TextView Modt;
	public static TextView Serverversion;
	public static Button Beenden;
	
	@Override
	protected void onStart()
	{
		super.onStart();
		// The activity is about to become visible.
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		// Another activity is taking focus (this activity is about to be
		// "paused").
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		// The activity is no longer visible (it is now "stopped")
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// The activity is about to be destroyed.
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState)
	{
		// Just for testing, allow network access in the main thread
		// NEVER use this is productive code
		/*
		 * StrictMode.ThreadPolicy policy = new StrictMode.
		 * ThreadPolicy.Builder().permitAll().build();
		 * StrictMode.setThreadPolicy(policy);
		 */
		// .permitAll().build();
		if(android.os.Build.VERSION.SDK_INT > 9)
		{
			final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			        .permitNetwork().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		But = (Button) findViewById(R.id.button1);
		text = (TextView) findViewById(R.id.textView2);
		But.setOnClickListener(this);
		Version = (Button) findViewById(R.id.version);
		Version.setOnClickListener(this);
		Vote = (Button) findViewById(R.id.Votel);
		Vote.setOnClickListener(this);
		Dynmap = (Button) findViewById(R.id.Dyn);
		Dynmap.setOnClickListener(this);
		Player = (TextView) findViewById(R.id.playerO);
		Playermay = (TextView) findViewById(R.id.playerM);
		Serverversion = (TextView) findViewById(R.id.VersionM);
		Modt = (TextView) findViewById(R.id.modtM);
		Beenden = (Button) findViewById(R.id.Beenden);
		Beenden.setOnClickListener(this);
		/*
		 * Vermerk hier Android Timer mit tasklevel setzen
		 */
		final HashMap JSON = pingServer("bau.nullcraft.de");
		if(JSON == null || JSON.get("status").equals("false"))
		{
			text.setTextColor(Color.RED);
			text.setText("Offline Maxi on Work");
		}
		else
		{
			/*
			 * System.out.println("There are " + JSON.get("players") +
			 * " out of " + JSON.get("players_max") + " online!");
			 * System.out.println("MoTD is (" + JSON.get("motd") + ")"); public
			 * TextView Player; public TextView Playermay; public TextView Modt;
			 * public TextView Serverversion;
			 */
			text.setTextColor(Color.GREEN);
			text.setText("Online");
			final Object PlayerOI = JSON.get("players");
			Player.setText(PlayerOI.toString());
			final Object PlayerMax = JSON.get("players_max");
			Playermay.setText(PlayerMax.toString());
			final Object MODT = JSON.get("motd");
			Modt.setText(MODT.toString());
			final Object ServerV = JSON.get("version");
			Serverversion.setText(ServerV.toString());
		}
	}
	
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */
	@Override
	public void onClick(View v)
	{
		if(v == But)
		{
			text.setText("Funktionstest");
			// TODO Auto-generated method stub
			
			final HashMap JSON = pingServer("bau.nullcraft.de");
			if(JSON == null || JSON.get("status").equals("false"))
			{
				text.setTextColor(Color.RED);
				text.setText("Offline Maxi on Work");
			}
			else
			{
				text.setTextColor(Color.GREEN);
				text.setText("Online");
				final Object PlayerOI = JSON.get("players");
				Player.setText(PlayerOI.toString());
				final Object PlayerMax = JSON.get("players_max");
				Playermay.setText(PlayerMax.toString());
				final Object MODT = JSON.get("motd");
				Modt.setText(MODT.toString());
				final Object ServerV = JSON.get("version");
				Serverversion.setText(ServerV.toString());
			}
		}
		else if(v == Vote)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://forum.nullcraft.de/pages/vote/"));
			startActivity(intent);
		}
		else if(v == Dynmap)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://cluster01.nullcraft.de:8123/"));
			startActivity(intent);
		}
		else if(v == Version)
		{
			/*
			 * SingleButtton.setOnClickListener(new View.OnClickListener() {
			 * 
			 * public void onClick(View arg0) { // Creating alert Dialog with
			 * one Button
			 * 
			 * AlertDialog alertDialog = new
			 * AlertDialog.Builder(AlertDialogActivity.this).create();
			 * 
			 * // Setting Dialog Title alertDialog.setTitle("Alert Dialog");
			 * 
			 * // Setting Dialog Message
			 * alertDialog.setMessage("Welcome to Android Application");
			 * 
			 * // Setting Icon to Dialog alertDialog.setIcon(R.drawable.tick);
			 * 
			 * // Setting OK Button alertDialog.setButton("OK", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * public void onClick(DialogInterface dialog,int which) { // Write
			 * your code here to execute after dialog closed
			 * Toast.makeText(getApplicationContext(),"You clicked on OK",
			 * Toast.LENGTH_SHORT).show(); } });
			 * 
			 * // Showing Alert Message alertDialog.show();
			 * 
			 * } });
			 */
			final AlertDialog alertDialog = new AlertDialog.Builder(this)
			        .create();
			alertDialog.setTitle("Version");
			alertDialog.setMessage("Version: Alpha 0.6"+"\n"+"Codename: Saui"+"\n"+"Autor: Budy93");
			alertDialog.show();
			Toast.makeText(this,
					"Version: Alpha 0.6"+"\n"+"Codename: Saui"+"\n"+"Autor: Budy93",
			        Toast.LENGTH_LONG).show();
		}
		else if(v == Beenden)
		{
			bendendiagloge();
		}
	}
	public static void tasklevel()
	{
		final HashMap JSON = pingServer("bau.nullcraft.de");
		if(JSON == null || JSON.get("status").equals("false"))
		{
			text.setTextColor(Color.RED);
			text.setText("Offline Maxi on Work");
		}
		else
		{
			text.setTextColor(Color.GREEN);
			text.setText("Online");
			final Object PlayerOI = JSON.get("players");
			Player.setText(PlayerOI.toString());
			final Object PlayerMax = JSON.get("players_max");
			Playermay.setText(PlayerMax.toString());
			final Object MODT = JSON.get("motd");
			Modt.setText(MODT.toString());
			final Object ServerV = JSON.get("version");
			Serverversion.setText(ServerV.toString());
		}
	}
	
	public static HashMap pingServer(String server)
	{
		try
		{
			final URL url = new URL("http://api.iamphoenix.me/get/?server_ip="
			        + server);
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
	
	public void bendendiagloge()
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle("Beenden?");
		
		// Setting Dialog Message
		alertDialog2.setMessage("Willst du die App wirlich beenden?");
		
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("Ja",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        // Write your code here to execute after dialog
				        Toast.makeText(getApplicationContext(),
				                "Es war nett mit dir. :(", Toast.LENGTH_SHORT)
				                .show();
				        ActivityRegistry.finishAll();
			        }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("Nein",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        dialog.cancel();
			        }
		        });
		alertDialog2.show();
	}
}
