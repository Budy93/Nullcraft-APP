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
 * @author Daniel Brüggemann
 * @version Alpha 0.7
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
	public static Button Impressum;
	public final static String ServerURL = "bau.nullcraft.de";
	
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
		/*
		 * if(android.os.Build.VERSION.SDK_INT > 9) { final
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder() .permitNetwork().build();
		 * StrictMode.setThreadPolicy(policy); }
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		But = (Button) findViewById(R.id.back);
		text = (TextView) findViewById(R.id.impresse);
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
		Impressum = (Button) findViewById(R.id.impress);
		Impressum.setOnClickListener(this);
		/*
		 * Vermerk hier Android Timer mit tasklevel setzen
		 */
		Networkthread Network = new Networkthreadimpl();
		final HashMap JSON = Network.pingserver(ServerURL);
		// final HashMap JSON = pingServer("bau.nullcraft.de");
		if(JSON == null || JSON.get("status").equals("false"))
		{
			text.setTextColor(Color.RED);
			text.setText("Offline Maxi on Work");
			Player.setText("-");
			Playermay.setText("-");
			Modt.setText("");
			Serverversion.setText("-");
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
			// TODO Auto-generated method stub
			Networkthread Network = new Networkthreadimpl();
			final HashMap JSON = Network.pingserver(ServerURL);
			// final HashMap JSON = pingServer("bau.nullcraft.de");
			if(JSON == null || JSON.get("status").equals("false"))
			{
				text.setTextColor(Color.RED);
				text.setText("Offline Maxi on Work");
				Player.setText("-");
				Playermay.setText("-");
				Modt.setText("");
				Serverversion.setText("-");
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
			if(android.os.Build.VERSION.SDK_INT > 13)
			{
				Intent in = new Intent(MainActivity.this, Dynmap.class);
				startActivity(in);
			}
			else
			{
				final Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://cluster01.nullcraft.de:8123/"));
				startActivity(intent);
			}
		}
		else if(v == Version)
		{
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Version");
			alertDialog.setMessage("Version: Alpha 0.7.1.E3" + "\n"
			        + "Codename: Krähe.Vanny" + "\n" + "Autor: Budy93");
			alertDialog.setPositiveButton("OK",
			        new DialogInterface.OnClickListener()
			        {
				        public void onClick(DialogInterface dialog, int which)
				        {
					        dialog.cancel();
				        }
			        });
			alertDialog.show();
			Toast.makeText(
			        this,
			        "Version: Alpha 0.7.1.E3" + "\n" + "Codename: Krähe.Vanny" + "\n"
			                + "Autor: Budy93", Toast.LENGTH_LONG).show();
		}
		else if(v == Impressum)
		{
			Intent in = new Intent(MainActivity.this, ImpressActivity.class);
			startActivity(in);
		}
		else if(v == Beenden)
		{
			bendendiagloge();
		}
	}
	
	public static void tasklevel()
	{
		Networkthread Network = new Networkthreadimpl();
		final HashMap JSON = Network.pingserver(ServerURL);
		if(JSON == null || JSON.get("status").equals("false"))
		{
			text.setTextColor(Color.RED);
			text.setText("Offline Maxi on Work");
			Player.setText("-");
			Playermay.setText("-");
			Modt.setText("");
			Serverversion.setText("-");
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
	
	/*
	 * public static HashMap pingServer(String server) { try { final URL url =
	 * new URL("http://api.iamphoenix.me/get/?server_ip=" + server); try { final
	 * BufferedReader reader = new BufferedReader( new
	 * InputStreamReader(url.openStream())); final String data =
	 * reader.readLine();
	 * 
	 * final Gson gson = new Gson(); return gson.fromJson(data, HashMap.class);
	 * } catch (final MalformedURLException e) { e.printStackTrace(); } } catch
	 * (final IOException e) { e.printStackTrace(); } return null; }
	 */
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
	
	public void onBackPressed()
	{
		bendendiagloge();
	}
}
