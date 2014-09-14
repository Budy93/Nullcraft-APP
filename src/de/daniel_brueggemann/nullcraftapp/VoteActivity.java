/**
 * 
 */
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
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import de.daniel_brueggemann.nullcraftapp.utilapi.EmcInterface;
import de.daniel_brueggemann.nullcraftapp.utilapi.EmcInterfaceImpl;
import de.daniel_brueggemann.nullcraftapp.utilapi.GJSON_pruefer;
import de.daniel_brueggemann.nullcraftapp.utilapi.GJSON_pruefer_impl;
import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthread;
import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthreadimpl;
import de.daniel_brueggemann.nullcraftapp.utilapi.votealarmReciver;

/**
 * Diese Aktivity wird durch den Notifya gestartet und ermöglich prüfung der Votes, ist eine
 * übergangslösung
 * @author Daniel Brüggemann
 *
 */
public class VoteActivity extends Activity implements OnClickListener
{
	public final String Votelinksmsg = "http://craftlist.de/vote/3"
			+ "\n"
	        + "\n"
	        + "http://www.minecraft-serverlist.net/vote/12186"
	        + "\n"
	        + "\n"
	        + "http://minecraft-server.eu/vote/index/80600"
	        + "\n"
	        + "\n"
	        + "http://www.planetminecraft.com/server/nullcraft-network/vote/";
	public static TextView voteja;
	public static TextView craftlist;
	public static TextView serverlist;
	public static TextView serverEU;
	public static TextView planteminecraft;
	public static Button impressum;
	public final String craftlink="http://craftlist.de/vote/3";
	public final String serverlink="http://www.minecraft-serverlist.net/vote/12186";
	public final String serverEUlink="http://minecraft-server.eu/vote/index/80600";
	public final String plantlink="http://www.planetminecraft.com/server/nullcraft-network/vote/";
	public static Button zuruck;
	public static Button pruef;
	public static EditText benutzername;
	private static long timestart = 0;
	private static long speicher = 0;
	private static long timeend = 0;
	public static final long sec = 1000000000;
	public votealarmReciver alarm = new votealarmReciver();
	
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
		setContentView(R.layout.activity_voten);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		voteja=(TextView)findViewById(R.id.votelinks);
		zuruck = (Button) findViewById(R.id.zurucke_vote);
		zuruck.setOnClickListener(this);
		impressum=(Button)findViewById(R.id.impressum_vote);
		impressum.setOnClickListener(this);
		//Definierung der Votelinks und Klickbar machen
		craftlist=(TextView)findViewById(R.id.Craftlist);
		craftlist.setTextColor(Color.BLUE);
		craftlist.setOnClickListener(this);
		serverlist=(TextView)findViewById(R.id.ServerNet);
		serverlist.setTextColor(Color.BLUE);
		serverlist.setOnClickListener(this);
		serverEU=(TextView)findViewById(R.id.ServerEU);
		serverEU.setTextColor(Color.BLUE);
		serverEU.setOnClickListener(this);
		planteminecraft=(TextView)findViewById(R.id.plantminecraft);
		planteminecraft.setTextColor(Color.BLUE);
		planteminecraft.setOnClickListener(this);
		pruef = (Button) findViewById(R.id.prue_bt);
		pruef.setOnClickListener(this);
		benutzername = (EditText) findViewById(R.id.eingabebenuzt);
		dialoge_vote();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.votemenue, menu);
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
			case R.id.vote_hbmenue:
				intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return true;
			case R.id.vote_beenden:
				bendendiagloge();
				return true;
			case R.id.vote_impressum:
				intent = new Intent(this, ImpressActivity.class);
				startActivity(intent);
				return true;
			case R.id.vote_dyn:
				/*
				 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text
				 * = new String[2]; emc_text=emc.EMC_abfrage(); if
				 * (emc_text[1].equals("true")) { Toast.makeText(this,
				 * "Notabschaltung",Toast.LENGTH_LONG).show(); Bundle Transfer =
				 * new Bundle(); Transfer.putString("grund", emc_text[0]);
				 * Intent in = new Intent(this, Emc.class);
				 * in.putExtras(Transfer); startActivity(in); }
				 */
				Intent in = new Intent(this, Dynmap.class);
				startActivity(in);
				return true;
			default:
				return super.onOptionsItemSelected(item);
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		if(v == pruef)
		{
			try
			{
				String player = benutzername.getText().toString();
				checkvote(player);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(v == zuruck)
		{
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else if(v==impressum)
		{
			Intent in=new Intent(this, ImpressActivity.class);
			startActivity(in);
		}
		else if(v==craftlist)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(craftlink));
			startActivity(intent);
		}
		else if(v==serverlist)
		{
			final Intent in=new Intent(Intent.ACTION_VIEW);
			in.setData(Uri.parse(serverlink));
			startActivity(in);
		}
		else if(v==serverEU)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri
			        .parse(serverEUlink));
			startActivity(intent);
		}
		else if(v==planteminecraft)
		{
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri
			        .parse(plantlink));
			startActivity(intent);
		}
	}
	
	/**
	 * Prüft beim Server 
	 * @param player
	 */
	public void checkvote(String player)
	{
		final String p = player;
		boolean ok = false;
		ok = sicherung(5);
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
					final HashMap vote = Network
					        .getNullApiOtherinfo("/has_voted.php?player=" + p);
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							abfragesystem(p, emc_text, vote);
						}
					});
				}
			};
			Thread t = new Thread(r);
			try
            {
	            t.start();
            }
            catch (Exception e)
            {
            	Toast.makeText(
    			        this,
    			        "Fehler 219: "+e.toString(),
    			        Toast.LENGTH_LONG).show();
            }
		}
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
				Toast.makeText(this,
				        "PFFS-SYSTEM: Es kann nur alle 5 sek geprüft werden",
				        Toast.LENGTH_SHORT).show();
				ok = false;
			}
			else
			{
				timeend = timestart;
				ok = true;
			}
		}
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
	 * Prüft ob der Spieler gevotet hat.
	 * @param player Spielername
	 * @param emc_text Wartungstexte
	 * @param vote HashMap der Serverabfrage
	 */
	@SuppressWarnings("rawtypes")
	private void abfragesystem(final String player, final String[] emc_text,
	        final HashMap vote)
	{
		if(emc_text[1].equals("true"))
		{
			Bundle Transfer = new Bundle();
			Transfer.putString("grund", emc_text[0]);
			Intent in = new Intent(VoteActivity.this, Emc.class);
			in.putExtras(Transfer);
			startActivity(in);
		}
		else
		{
			GJSON_pruefer pr = new GJSON_pruefer_impl();
			boolean ok = pr.check_key(vote, "voted");
			if(ok == true)
			{
				final Object habenvote = vote.get("voted");
				if(habenvote.toString().equals("1"))
				{
					voteja.setText("Der Spieler " + player + " hat gevotet");
				}
				else
				{
					voteja.setText("Der Spieler " + player
					        + " hat nicht gevotet");
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed()
	{
		
		// Let the system handle the back button
		// super.onBackPressed();
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
	}
}
