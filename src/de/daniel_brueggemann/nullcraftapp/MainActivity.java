package de.daniel_brueggemann.nullcraftapp;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import de.daniel_brueggemann.nullcraftapp.utilapi.EmcInterface;
import de.daniel_brueggemann.nullcraftapp.utilapi.EmcInterfaceImpl;
import de.daniel_brueggemann.nullcraftapp.utilapi.GJSON_pruefer;
import de.daniel_brueggemann.nullcraftapp.utilapi.GJSON_pruefer_impl;
import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthread;
import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthreadimpl;

/**
 * Nullapp für den Server Nullcraft
 * @author Daniel Brüggemann
 * @version Beta 0.8.1
 */
// Zwischenablage: btn.setBackgroundResource(R.drawable.soundoff);
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
	private final static String ServerURL = "bau.nullcraft.de";
	public static TextView Latenz;
	private final static String wartungsURL = "http://daniel-brueggemann.de/minecraft/dev/Nullcraftapp/wartung/Wartung";
	public static TextView CPRightstext;
	public static Button News;
	public static TextView wartungnews;
	public static ProgressDialog progress = null;
	public static Button updatecheck;
	// public final int aktuelle_version = 822713;
	public final String Votelinksmsg = "http://craftlist.de/vote/3" + "\n"
	        + "\n" + "http://www.minecraft-serverlist.net/vote/12186" + "\n"
	        + "\n" + "http://minecraft-server.eu/vote/index/80600";
	private static long timestart = 0;
	private static long speicher = 0;
	private static long timeend = 0;
	public static final long sec = 1000000000;
	public final int versionsnr = 822713;
	public CountDownTimer counddown;
	private static int mId = 0;
	public static CheckBox darferinnern;
	public SharedPreferences.Editor editor;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		// The activity is about to become visible.
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		// Another activity is taking focus (this activity is about to be
		// "paused").
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop()
	{
		super.onStop();
		// The activity is no longer visible (it is now "stopped")
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
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
		CPRightstext = (TextView) findViewById(R.id.CPRights);
		CPRightstext.setTextColor(Color.GREEN);
		News = (Button) findViewById(R.id.news);
		News.setOnClickListener(this);
		wartungnews = (TextView) findViewById(R.id.wartungen);
		// news1.setText("");
		wartungnews.setText("");
		updatecheck = (Button) findViewById(R.id.updatebtn);
		updatecheck.setOnClickListener(this);
		speicher = 0;
		timeend = 0;
		darferinnern = (CheckBox) findViewById(R.id.voteerinnern);
		// darferinnern.setTextColor(Color.GREEN);
		// Get the app's shared preferences
		SharedPreferences app_preferences = PreferenceManager
		        .getDefaultSharedPreferences(this);
		
		// Get the value for the run counter
		boolean erlaubnis = app_preferences.getBoolean("erlaubnis", false);
		
		darferinnern.setChecked(erlaubnis);
		darferinnern.setOnClickListener(this);
		editor = app_preferences.edit();
		if(erlaubnis == true)
		{
			votetimer();
			//counddown.start();
		}
		// createNotification();
		
		// progress = ProgressDialog.show(null, TestURL, ServerURL);
		network_aufgabe();
		// progress.cancel();
	}
	
	/**
	 * Führt die entsprechenden Abfragen im Netzwerk in einen anderen Thread durch.
	 */
	private void network_aufgabe()
	{
		boolean ok = false;
		ok = sicherung(5);
		/*
		 * timestart = System.nanoTime(); if(speicher == 0 & timeend == 0) { ok
		 * = true; timeend = timestart; } else if(timeend != 0) { speicher =
		 * timestart - timeend; speicher = speicher / sec; if(speicher < 5) { //
		 * diaglogesp("PFFS-SYSTEM",
		 * "Es kann nur alle 5sek neugeladen werden, diese Meldung erscheint auch beim jeden neustart der App bitte dann 5sek warten. Danke"
		 * );
		 * 
		 * Toast.makeText( this,
		 * "PFFS-SYSTEM: Es kann nur alle 5sek neugeladen werden, diese Meldung erscheint auch beim jeden neustart der App bitte dann 5sek warten. Danke"
		 * , Toast.LENGTH_SHORT).show();
		 * 
		 * ok = false; } else { timeend = timestart; ok = true; } }
		 */
		if(ok == true)
		{
			Runnable r = new Runnable()
			{
				@Override
				public void run()
				{
					Networkthread Network = new Networkthreadimpl();
					final EmcInterface emc = new EmcInterfaceImpl();
					// String[] emc_text = new String[2];
					final String[] emc_text = emc.EMC_abfrage();
					@SuppressWarnings("rawtypes")
					final HashMap JSON = Network.pingserver(ServerURL);
					final String wrtext = emc.EMC_wartung(wartungsURL);
					@SuppressWarnings("rawtypes")
					final HashMap Nullapi = Network.getNullApiServerinfo();
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							netzwerkthreadSetGUI(emc, emc_text, JSON, wrtext,
							        Nullapi);
						}
					});
				}
			};
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menue, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;
		switch (item.getItemId())
		{
			case R.id.test:
				speicher = 0;
				timeend = 0;
				intent = new Intent(this, ImpressActivity.class);
				startActivity(intent);
				// ActivityRegistry.finishthis();
				return true;
			case R.id.Beendenme:
				bendendiagloge();
				return true;
			case R.id.News:
				/*
				 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text
				 * = new String[2]; emc_text=emc.EMC_abfrage(); if
				 * (emc_text[1].equals("true")) { Toast.makeText(this,
				 * "Notabschaltung",Toast.LENGTH_LONG).show(); Bundle Transfer =
				 * new Bundle(); Transfer.putString("grund", emc_text[0]);
				 * Intent in = new Intent(this, Emc.class);
				 * in.putExtras(Transfer); startActivity(in); return true; }
				 */
				speicher = 0;
				timeend = 0;
				network_aufgabe();
				Intent in = new Intent(MainActivity.this,
				        Newsreaderselect.class);
				startActivity(in);
				return true;
			case R.id.Voten_menu:
				dialoge_vote();
				return true;
			case R.id.Updat:
				boolean ok = false;
				ok = sicherung(5);
				/*
				 * timestart = System.nanoTime(); if(speicher == 0 & timeend ==
				 * 0) { ok = true; timeend = timestart; } else if(timeend != 0)
				 * { speicher = timestart - timeend; speicher = speicher / sec;
				 * if(speicher < 5) { Toast.makeText( this,
				 * "PFFS-SYSTEM: Es kann nur alle 5sek neugeladen werden",
				 * Toast.LENGTH_LONG).show(); ok = false; } else { timeend =
				 * timestart; ok = true; } }
				 */
				if(ok == true)
				{
					Runnable r = new Runnable()
					{
						@Override
						public void run()
						{
							final EmcInterface emc = new EmcInterfaceImpl();
							// String[] emc_text = new String[2];
							final int v_test = emc.TEM_Update_Ceck(wartungsURL);
							runOnUiThread(new Runnable()
							{
								public void run()
								{
									if(v_test == versionsnr)
									{
										// String a=""+v_test;
										diaglogesp("Update nicht verfügbar",
										        "Es ist derzeit kein Update verfügbar");
									}
									else if(v_test > versionsnr)
									{
										// String a=""+v_test;
										diaglogesp("Update verfügbar",
										        "Es steht ein Update zur verfügung, bitte update deine Version");
									}
									else
									{
										diaglogesp("Test Version?",
										        "Deine Version ist zu neu, nutzt du eine Testversion?");
									}
								}
							});
						}
					};
					Thread updatecheck = new Thread(r);
					updatecheck.start();
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		if(v == But)
		{
			network_aufgabe();
		}
		else if(v == Vote)
		{
			// dialoge_vote();
			Intent in = new Intent(MainActivity.this, VoteActivity.class);
			startActivity(in);
			/*
			 * final Intent intent = new Intent(Intent.ACTION_VIEW);
			 * intent.setData
			 * (Uri.parse("http://forum.nullcraft.de/pages/vote/"));
			 * startActivity(intent);
			 */
		}
		else if(v == Dynmap)
		{
			speicher = 0;
			timeend = 0;
			if(android.os.Build.VERSION.SDK_INT >= 10)
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
			 * AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			 * alertDialog.setTitle("Version");
			 * alertDialog.setMessage("Version: Alpha 0.8.0.E2" + "\n" +
			 * "Codename: Vanny" + "\n" + "Autor: Budy93");
			 * alertDialog.setPositiveButton("OK", new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int which) { dialog.cancel(); }
			 * }); alertDialog.show();
			 */
			String versiontext = "Version: Beta 0.8.2.3.E1" + "\n"
			        + "Codename: Rock" + "\n" + "Autor: Budy93" + "\n"
			        + "E-mail: dev@daniel-brueggemann.de";
			diaglogesp("Version", versiontext);
			Toast.makeText(
			        this,
			        "Version: Beta 0.8.2.3.E1" + "\n" + "Codename: Rock" + "\n"
			                + "Autor: Budy93", Toast.LENGTH_LONG).show();
		}
		else if(v == Impressum)
		{
			/*
			 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text =
			 * new String[2]; emc_text=emc.EMC_abfrage(); if
			 * (emc_text[1].equals("true")) { Toast.makeText(this,
			 * "Notabschaltung",Toast.LENGTH_LONG).show(); Bundle Transfer = new
			 * Bundle(); Transfer.putString("grund", emc_text[0]); Intent in =
			 * new Intent(this, Emc.class); in.putExtras(Transfer);
			 * startActivity(in); }
			 */
			speicher = 0;
			timeend = 0;
			network_aufgabe();
			Intent in = new Intent(MainActivity.this, ImpressActivity.class);
			startActivity(in);
		}
		else if(v == Beenden)
		{
			bendendiagloge();
		}
		else if(v == News)
		{
			/*
			 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text =
			 * new String[2]; emc_text=emc.EMC_abfrage(); if
			 * (emc_text[1].equals("true")) { Toast.makeText(this,
			 * "Notabschaltung",Toast.LENGTH_LONG).show(); Bundle Transfer = new
			 * Bundle(); Transfer.putString("grund", emc_text[0]); Intent in =
			 * new Intent(this, Emc.class); in.putExtras(Transfer);
			 * startActivity(in); }
			 */
			speicher = 0;
			timeend = 0;
			network_aufgabe();
			Intent in = new Intent(MainActivity.this, Newsreaderselect.class);
			startActivity(in);
		}
		else if(v == updatecheck)
		{
			boolean ok = false;
			ok = sicherung(5);
			/*
			 * timestart = System.nanoTime(); if(speicher == 0 & timeend == 0) {
			 * ok = true; timeend = timestart; } else if(timeend != 0) {
			 * speicher = timestart - timeend; speicher = speicher / sec;
			 * if(speicher < 5) { Toast.makeText( this,
			 * "PFFS-SYSTEM: Es kann nur alle 5sek neugeladen werden, diese Meldung erscheint auch beim jeden neustart der App bitte dann 5sek warten. Danke"
			 * , Toast.LENGTH_SHORT).show(); // diaglogesp("PFFS-SYSTEM",
			 * "Es kann nur alle 5sek neugeladen werden, diese Meldung erscheint auch beim jeden neustart der App bitte dann 5sek warten. Danke"
			 * ); ok = false; } else { timeend = timestart; ok = true; } } //
			 * todo
			 */
			if(ok == true)
			{
				Runnable r = new Runnable()
				{
					@Override
					public void run()
					{
						final EmcInterface emc = new EmcInterfaceImpl();
						// String[] emc_text = new String[2];
						final int v_test = emc.TEM_Update_Ceck(wartungsURL);
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								if(v_test == versionsnr)
								{
									// String a=""+v_test;
									diaglogesp("Update nicht verfügbar",
									        "Es ist derzeit kein Update verfügbar");
								}
								else if(v_test > versionsnr)
								{
									// String a=""+v_test;
									diaglogesp("Update verfügbar",
									        "Es steht ein Update zur verfügung, bitte update deine Version");
								}
								else
								{
									diaglogesp("Test Version?",
									        "Deine Version ist zu neu, nutzt du eine Testversion?");
								}
							}
						});
					}
				};
				Thread updatecheck = new Thread(r);
				updatecheck.start();
			}
		}
		else if(v == darferinnern)
		{
			boolean checked = ((CheckBox) v).isChecked();
			editor.putBoolean("erlaubnis", checked);
			editor.commit(); // Very important
			if(checked == true)
			{
				//counddown.start();
				diaglogesp(
				        "Voteerinnern",
				        "Danke das du dich f\u00FCr die Vote-Erinnerungsfunktion entschieden hast. Du wirst alle 24h mit einer Vibration und eine Notiz daran erinnert. Wenn du dies nicht mehr willst, entferne einfach dann den Hacken. Bitte beende mit dieser Funktion nicht die APP mit X oder \u00FCber das Men\u00FC mit Beenden, da sonst der Countdown beendet wird.");
				diaglorestart();
			}
			else
			{
				diaglorestart();
				//myTimer.cancel;
				//counddown.cancel();
			}
		}
	}
	
	/**
	 * Dialoge Interface das die App beendet.
	 */
	private void bendendiagloge()
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
				        //counddown.cancel();
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed()
	{
		bendendiagloge();
	}
	
	/**
	 * Startet einen einfachen Dialoge zur Wiedergabe von speziellen Nachrichten
	 * @param Title Titel der Dialogmeldung
	 * @param texte Text der Dialogemeldung
	 */
	private void diaglogesp(String Title, String texte)
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle(Title);
		
		// Setting Dialog Message
		alertDialog2.setMessage(texte);
		
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
	
	/**
	 * Gibt einen Alertdialoge mit den Votelinks aus.
	 */
	private void dialoge_vote()
	{
		final SpannableString m = new SpannableString(Votelinksmsg);
		Linkify.addLinks(m, Linkify.WEB_URLS);
		
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		final TextView message = new TextView(this);
		message.setText(m);
		message.setMovementMethod(LinkMovementMethod.getInstance());
		alertDialog2.setTitle("Votelinks");
		// alertDialog2.setMessage(m);
		alertDialog2.setView(message);
		alertDialog2.setPositiveButton("Online sehen",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        final Intent intent = new Intent(Intent.ACTION_VIEW);
				        intent.setData(Uri
				                .parse("http://forum.nullcraft.de/pages/vote/"));
				        startActivity(intent);
			        }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("Zurück zur APP",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        dialog.cancel();
			        }
		        });
		alertDialog2.show();
	}
	
	/**
	 * Misst die Sperrzeit.
	 * @param zeit Die zu wartene Zeit.
	 * @return True bei warte Zeit ist um, false wenn sie nicht um ist.
	 */
	private boolean sicherung(int zeit)
	{
		int warten = zeit;
		boolean ok = false;
		timestart = System.nanoTime();
		if(speicher == 0 & timeend == 0)
		{
			ok = true;
			timeend = timestart;
		}
		else if(timeend != 0)
		{
			speicher = timestart - timeend;
			speicher = speicher / sec;
			if(speicher < warten)
			{
				Toast.makeText(
				        this,
				        "PFFS-SYSTEM: Es kann nur alle 5sek neugeladen werden, diese Meldung erscheint auch beim jeden neustart der App bitte dann 5sek warten. Danke",
				        Toast.LENGTH_SHORT).show();
				ok = false;
			}
			else
			{
				timeend = timestart;
				ok = true;
			}
		}
		// todo
		if(ok == true)
		{
			return ok;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Setzt die GUI-Elemente für die App
	 * @param emc Übergabe des EMC interfaces
	 * @param emc_text Wartungstexte
	 * @param JSON JSON Objekt der Serverdaten
	 * @param wrtext Wartungstext^2
	 */
	@SuppressWarnings("rawtypes")
	private void netzwerkthreadSetGUI(final EmcInterface emc,
	        final String[] emc_text, final HashMap JSON, final String wrtext,
	        final HashMap Nullapi)
	{
		if(emc_text[1].equals("true"))
		{
			/*
			 * Toast.makeText(this, "Notabschaltung", Toast.LENGTH_LONG)
			 * .show();
			 */
			Bundle Transfer = new Bundle();
			CPRightstext.setTextColor(Color.CYAN);
			CPRightstext.setText(emc.toString());
			Transfer.putString("grund", emc_text[0]);
			Intent in = new Intent(MainActivity.this, Emc.class);
			in.putExtras(Transfer);
			startActivity(in);
		}
		else
		{
			CPRightstext.setTextColor(Color.GREEN);
			CPRightstext.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
			CPRightstext.setText("Developer: Budy93, ©Berlin 2014");
		}
		// final HashMap JSOnnot =
		// Network.testServer(TestURL);
		/*
		 * if(JSOnnot !=null) { final Object emc = JSOnnot.get("emc"); String
		 * help=emc.toString(); final Object gruende_json= JSOnnot.get("Grund");
		 * String gruende=gruende_json.toString();
		 * if(help.equalsIgnoreCase("Abschalten")) {
		 * Testurl.setTextColor(Color.CYAN); Testurl.setText(emc.toString());
		 * Toast.makeText(this, "Notabschaltung",Toast.LENGTH_LONG).show();
		 * Bundle Transfer = new Bundle(); Transfer.putString("grund", gruende);
		 * Intent in = new Intent(this, Emc.class); in.putExtras(Transfer);
		 * startActivity(in); } else { Testurl.setTextColor(Color.GREEN);
		 * Testurl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		 * Testurl.setText("Developer: Budy93, ©Berlin 2014" ); } }
		 */
		GJSON_pruefer pr = new GJSON_pruefer_impl();
		boolean statustest = false;
		boolean errortest = false;
		boolean nullapion = false;
		statustest = pr.check_key(JSON, "status");
		errortest = pr.check_key(JSON, "error");
		nullapion = pr.check_key(Nullapi, "status");
		// JSON == null || JSON.containsKey("error") ||
		// JSON.get("status").equals("false")
		if(nullapion == false)
		{
			if(statustest == true)
			{
				if(JSON == null || JSON.get("status").equals("false"))
				{
					text.setTextColor(Color.RED);
					text.setText("Offline");
					Player.setText("-");
					Playermay.setText("-");
					Modt.setText("");
					Serverversion.setText("-");
					Latenz.setText("-");
					wartungnews.setText(wrtext);
				}
				else
				{
					// latency new
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
					wartungnews.setText(wrtext);
				}
			}
			else if(errortest == true)
			{
				text.setTextColor(Color.RED);
				text.setText("Offline");
				Player.setText("-");
				Playermay.setText("-");
				Modt.setText("");
				Serverversion.setText("-");
				Latenz.setText("-");
				wartungnews.setText(wrtext);
			}
		}
		else if(nullapion == true)
		{
			boolean online = false;
			boolean spieler = false;
			boolean spielermax = false;
			boolean modt = false;
			boolean pingsystem = false;
			boolean versiona = false;
			online = pr.check_key(Nullapi, "status");
			spieler = pr.check_key(Nullapi, "players");
			spielermax = pr.check_key(Nullapi, "slots");
			modt = pr.check_key(Nullapi, "motd");
			pingsystem = pr.check_key(Nullapi, "latency");
			versiona = pr.check_key(Nullapi, "version");
			if(Nullapi == null || JSON.get("status").equals("false"))
			{
				text.setTextColor(Color.RED);
				text.setText("Offline");
				Player.setText("-");
				Playermay.setText("-");
				Modt.setText("");
				Serverversion.setText("-");
				Latenz.setText("-");
				wartungnews.setText(wrtext);
			}
			else if(online == true && spieler == true && spielermax == true
			        && modt == true && pingsystem == true && versiona == true)
			{
				text.setTextColor(Color.GREEN);
				text.setText("Online");
				final Object PlayerOI = Nullapi.get("players");
				Player.setText(PlayerOI.toString());
				final Object PlayerMax = Nullapi.get("slots");
				Playermay.setText(PlayerMax.toString());
				final Object MODT = Nullapi.get("motd");
				Modt.setText(MODT.toString());
				final Object ServerV = Nullapi.get("version");
				Serverversion.setText(ServerV.toString());
				final Object Letente = Nullapi.get("latency");
				Latenz.setText(Letente.toString());
				wartungnews.setText(wrtext);
			}
			else
			{
				text.setTextColor(Color.GREEN);
				text.setText("Online");
				if(spieler == true)
				{
					final Object PlayerOI = Nullapi.get("players");
					Player.setText(PlayerOI.toString());
				}
				else
				{
					Player.setText("-");
				}
				if(spielermax == true)
				{
					final Object PlayerMax = Nullapi.get("slots");
					Playermay.setText(PlayerMax.toString());
				}
				else
				{
					Playermay.setText("-");
				}
				if(modt == true)
				{
					final Object MODT = Nullapi.get("motd");
					Modt.setText(MODT.toString());
				}
				else
				{
					Modt.setText("-");
				}
				if(pingsystem == true)
				{
					final Object Letente = Nullapi.get("latency");
					Latenz.setText(Letente.toString());
				}
				else
				{
					Latenz.setText("-");
				}
				if(versiona == true)
				{
					final Object ServerV = Nullapi.get("version");
					Serverversion.setText(ServerV.toString());
				}
				else
				{
					Serverversion.setText("-");
				}
			}
		}
		else
		{
			text.setTextColor(Color.RED);
			text.setText("Offline");
			Player.setText("-");
			Playermay.setText("-");
			Modt.setText("");
			Serverversion.setText("-");
			Latenz.setText("-");
			wartungnews.setText(wrtext);
		}
	}
	
	/**
	 * Erstellt einen Countdown, der alle 24 eine Notifikation abschickt.
	 */
	private void votetimer()
	{
		/*
		Timer myTimer = new Timer(); // Timer erzeugen
		final Handler uiHandler = new Handler();
		myTimer.schedule(new TimerTask() 
		{
		    @Override
		    public void run() 
		    {
		        uiHandler.post(new Runnable() 
		        {
		            @Override
		            public void run() 
		            {
		            	if(android.os.Build.VERSION.SDK_INT < 16)
						{
							dialoge_vote();
							diaglogesp("VOTEN", "Bitte vergiss nicht heute zu voten");
						}
						if(android.os.Build.VERSION.SDK_INT >= 16)
						{
							createNotification();
						}
		            }
		        });
		    };
		}, 60L, 60L * 1000);//Intervall = 60000 Millisekunden, 0 Millisekunden bis zum ersten Start.
		
		*/
		
		
		/*
		 * 86400000 = 24h
		 * Test: 20000 = 20 sek
		 */
		/*
		counddown = new CountDownTimer(3600000, 1000)
		{
			
			public void onTick(long millisUntilFinished)
			{
				//
			}
			
			public void onFinish()
			{
				if(android.os.Build.VERSION.SDK_INT < 16)
				{
					dialoge_vote();
					diaglogesp("VOTEN", "Bitte vergiss nicht heute zu voten");
				}
				if(android.os.Build.VERSION.SDK_INT >= 16)
				{
					createNotification();
				}
			}
		};
		*/
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				runOnUiThread(new Runnable()
				{
					public void run()
					{
						/*
						 * 86400000 = 24h
						 * Test: 20000 = 20 sek
						 */
						counddown = new CountDownTimer(360000, 1000)
						{
							
							public void onTick(long millisUntilFinished)
							{
								//
							}
							
							public void onFinish()
							{
								if(android.os.Build.VERSION.SDK_INT < 16)
								{
									dialoge_vote();
									diaglogesp("VOTEN", "Bitte vergiss nicht heute zu voten");
								}
								if(android.os.Build.VERSION.SDK_INT >= 16)
								{
									createNotification();
								}
							}
						};
						counddown.start();
					}
				});
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
	
	/**
	 * Erstellt eine Notification für den Nutzer
	 * Nur Kompertibel mit Android API 16
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void createNotification()
	{
		/*
		 * // Prepare intent which is triggered if the // notification is
		 * selected Intent intent = new Intent(this, MainActivity.class);
		 * PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
		 * 0);
		 * 
		 * // Build notification // Actions are just fake Notification noti =
		 * new Notification.Builder(this) .setContentTitle("Notification Title")
		 * .setContentText("Click here to read")
		 * .setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
		 * .build(); NotificationManager notificationManager =
		 * (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // hide
		 * the notification after its selected noti.flags |=
		 * Notification.FLAG_AUTO_CANCEL;
		 * 
		 * notificationManager.notify(0, noti);
		 */
		//counddown.cancel();
		Vibrator vibra = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		boolean vibrator = vibra.hasVibrator();
		if(vibrator == true)
		{
			vibra.vibrate(500);
		}
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
		        this).setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Voten")
		        .setContentText("Denk bitte daran zu Voten");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, VoteActivity.class);
		
		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(VoteActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
		        PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, mBuilder.build());
		mId++;
		votetimer();
		// dialoge_vote();
	}
	
	/**
	 * Restart der APP
	 * @param Title Titel der Dialogmeldung
	 * @param texte Text der Dialogemeldung
	 */
	private void diaglorestart()
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle("Restart");
		
		// Setting Dialog Message
		alertDialog2.setMessage("Die APP muss nun neu gestart werden");
		
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("OK",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        dialog.cancel();
				        ActivityRegistry.finishAll();
			        }
		        });
		alertDialog2.show();
	}
}
