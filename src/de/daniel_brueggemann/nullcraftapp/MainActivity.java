package de.daniel_brueggemann.nullcraftapp;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 * @version Alpha 0.8
 *
 */
//Zwischenablage: btn.setBackgroundResource(R.drawable.soundoff);
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
	public static TextView Latenz;
	public final static String TestURL="http://daniel-brueggemann.de/minecraft/dev/Nullcraftapp/test";
	public static TextView Testurl;
	public static Button news;
	
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
		Latenz = (TextView) findViewById(R.id.latency_info);
		Serverversion = (TextView) findViewById(R.id.VersionM);
		Modt = (TextView) findViewById(R.id.modtM);
		Beenden = (Button) findViewById(R.id.Beenden);
		Beenden.setOnClickListener(this);
		Impressum = (Button) findViewById(R.id.impress);
		Impressum.setOnClickListener(this);
		Testurl = (TextView) findViewById(R.id.TestURL2);
		news=(Button)findViewById(R.id.news);
		news.setOnClickListener(this);
		/*
		 * Vermerk hier Android Timer mit tasklevel setzen
		 */
		network_aufgabe();
	}
	
	/**
	 * Abfrage der jeweiligen JSON Objekte.
	 */
	public void network_aufgabe()
	{
		Networkthread Network = new Networkthreadimpl();
		final HashMap JSON = Network.pingserver(ServerURL);
		// /*
		final HashMap JSOnnot = Network.testServer(TestURL);
		if(JSOnnot !=null)
		{
			final Object emc = JSOnnot.get("emc");
			String help=emc.toString();
			final Object gruende_json= JSOnnot.get("Grund");
			String gruende=gruende_json.toString();
			if(help.equalsIgnoreCase("Abschalten"))
			{
				Testurl.setTextColor(Color.CYAN);
				Testurl.setText(emc.toString());
				Toast.makeText(this, "Notabschaltung",Toast.LENGTH_LONG).show();
				Bundle Transfer = new Bundle();
				Transfer.putString("grund", gruende);
				Intent in = new Intent(this, Emc.class);
				in.putExtras(Transfer);
				startActivity(in);
			}
			else
			{
				Testurl.setTextColor(Color.RED);
				Testurl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
				Testurl.setText("Developer: Budy93, ©Berlin 2014");
			}
		}
			//*/
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
			//latency new
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
			final Object Letente = JSON.get("latency");
			Latenz.setText(Letente.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menue, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;
		switch (item.getItemId())
		{
			case R.id.test:
				intent = new Intent(this, ImpressActivity.class);
				startActivity(intent);
				//ActivityRegistry.finishthis();
				return true;
			case R.id.Beendenme:
				bendendiagloge();
				return true;
			case R.id.News:
				EmcInterface emc = new EmcInterfaceImpl();
				String[] emc_text = new String[2];
				emc_text=emc.EMC_abfrage();
				if (emc_text[1].equals("true"))
				{
					Toast.makeText(this, "Notabschaltung",Toast.LENGTH_LONG).show();
					Bundle Transfer = new Bundle();
					Transfer.putString("grund", emc_text[0]);
					Intent in = new Intent(this, Emc.class);
					in.putExtras(Transfer);
					startActivity(in);
					return true;
				}
				Intent in = new Intent(MainActivity.this, Newsreaderselect.class);
				startActivity(in);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		if(v == But)
		{
			/*
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
				final Object Letente = JSON.get("latency");
				Latenz.setText(Letente.toString());
			}
			*/
			network_aufgabe();
		}
		else if(v == Vote)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://forum.nullcraft.de/pages/vote/"));
			startActivity(intent);
		}
		else if(v == Dynmap)
		{
			if(android.os.Build.VERSION.SDK_INT > 14)
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
			/*
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Version");
			alertDialog.setMessage("Version: Alpha 0.8.0.E2" + "\n"
			        + "Codename: Vanny" + "\n" + "Autor: Budy93");
			alertDialog.setPositiveButton("OK",
			        new DialogInterface.OnClickListener()
			        {
				        public void onClick(DialogInterface dialog, int which)
				        {
					        dialog.cancel();
				        }
			        });
			alertDialog.show();
			*/
			String versiontext="Version: Beta 0.8.1" + "\n"
			        + "Codename: Vanny" + "\n" + "Autor: Budy93";
			diaglogesp("Version",versiontext);
			Toast.makeText(
			        this,
			        "Version: Beta 0.8.1" + "\n" + "Codename: Vanny"
			                + "\n" + "Autor: Budy93", Toast.LENGTH_LONG).show();
		}
		else if(v == Impressum)
		{
			EmcInterface emc = new EmcInterfaceImpl();
			String[] emc_text = new String[2];
			emc_text=emc.EMC_abfrage();
			if (emc_text[1].equals("true"))
			{
				Toast.makeText(this, "Notabschaltung",Toast.LENGTH_LONG).show();
				Bundle Transfer = new Bundle();
				Transfer.putString("grund", emc_text[0]);
				Intent in = new Intent(this, Emc.class);
				in.putExtras(Transfer);
				startActivity(in);
			}
			Intent in = new Intent(MainActivity.this, ImpressActivity.class);
			startActivity(in);
		}
		else if(v == Beenden)
		{
			bendendiagloge();
		}
		else if(v==news)
		{
			EmcInterface emc = new EmcInterfaceImpl();
			String[] emc_text = new String[2];
			emc_text=emc.EMC_abfrage();
			if (emc_text[1].equals("true"))
			{
				Toast.makeText(this, "Notabschaltung",Toast.LENGTH_LONG).show();
				Bundle Transfer = new Bundle();
				Transfer.putString("grund", emc_text[0]);
				Intent in = new Intent(this, Emc.class);
				in.putExtras(Transfer);
				startActivity(in);
			}
			Intent in = new Intent(MainActivity.this, Newsreaderselect.class);
			startActivity(in);
		}
	}
	
	/**
	 * Not working Task System
	 */
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
	
	/**
	 * Dialoge Interface das die App beendet.
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
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed()
	{
		bendendiagloge();
	}
	
	/**
	 * Startet einen einfachen Dialoge zur Wiedergabe von speziellen Nachrichten
	 * @param Title Titel der Dialogmeldung
	 * @param grund Text der Dialogemeldung
	 */
	public void diaglogesp(String Title, String grund)
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle(Title);
		
		// Setting Dialog Message
		alertDialog2.setMessage(grund);
		
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("OK",
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
