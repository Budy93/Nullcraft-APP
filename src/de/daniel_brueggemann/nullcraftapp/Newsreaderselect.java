/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 *
 */
public class Newsreaderselect extends Activity implements OnClickListener
{
	public static final String techurl = "http://forum.nullcraft.de/forums/technische-informationen.18/index.rss";
	public static final String ankuendurl = "http://forum.nullcraft.de/forums/neuigkeiten.4/index.rss";
	public static final String historyurl = "http://wiki.nullcraft.de/index.php?title=Spezial:Letzte_%C3%84nderungen&feed=atom";
	public static Button Technews;
	public static Button Aenderungsnews;
	public static Button History;
	public static Button Impressum;
	
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_select);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		Technews = (Button) findViewById(R.id.backfeed);
		Aenderungsnews = (Button) findViewById(R.id.ankuedinews);
		History = (Button) findViewById(R.id.aenderungshist);
		Impressum=(Button)findViewById(R.id.impressum_newsselect);
		Impressum.setOnClickListener(this);
		Technews.setOnClickListener(this);
		Aenderungsnews.setOnClickListener(this);
		History.setOnClickListener(this);
		/*
		 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text = new
		 * String[2]; emc_text = emc.EMC_abfrage();
		 * if(emc_text[1].equals("true")) { Toast.makeText(this,
		 * "Notabschaltung", Toast.LENGTH_LONG).show(); Bundle Transfer = new
		 * Bundle(); Transfer.putString("grund", emc_text[0]); Intent in = new
		 * Intent(this, Emc.class); in.putExtras(Transfer); startActivity(in); }
		 */
	}
	
	@Override
	public void onClick(View v)
	{
		if(v == Technews)
		{
			/*
			 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text =
			 * new String[2]; emc_text = emc.EMC_abfrage();
			 * if(emc_text[1].equals("true")) { Toast.makeText(this,
			 * "Notabschaltung", Toast.LENGTH_LONG) .show(); Bundle Transfer =
			 * new Bundle(); Transfer.putString("grund", emc_text[0]); Intent in
			 * = new Intent(this, Emc.class); in.putExtras(Transfer);
			 * startActivity(in); }
			 */
			Bundle Transfer = new Bundle();
			Transfer.putString("url", techurl);
			Intent in = new Intent(this, RssReaderActivity.class);
			in.putExtras(Transfer);
			startActivity(in);
		}
		else if(v == Aenderungsnews)
		{
			/*
			 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text =
			 * new String[2]; emc_text = emc.EMC_abfrage();
			 * if(emc_text[1].equals("true")) { Toast.makeText(this,
			 * "Notabschaltung", Toast.LENGTH_LONG) .show(); Bundle Transfer =
			 * new Bundle(); Transfer.putString("grund", emc_text[0]); Intent in
			 * = new Intent(this, Emc.class); in.putExtras(Transfer);
			 * startActivity(in); }
			 */
			Bundle Transfer = new Bundle();
			Transfer.putString("url", ankuendurl);
			Intent in = new Intent(this, RssReaderActivity.class);
			in.putExtras(Transfer);
			startActivity(in);
		}
		else if(v == History)
		{
			/*
			 * EmcInterface emc = new EmcInterfaceImpl(); String[] emc_text =
			 * new String[2]; emc_text = emc.EMC_abfrage();
			 * if(emc_text[1].equals("true")) { Toast.makeText(this,
			 * "Notabschaltung", Toast.LENGTH_LONG) .show(); Bundle Transfer =
			 * new Bundle(); Transfer.putString("grund", emc_text[0]); Intent in
			 * = new Intent(this, Emc.class); in.putExtras(Transfer);
			 * startActivity(in); }
			 */
			Bundle Transfer = new Bundle();
			Transfer.putString("url", historyurl);
			Intent in = new Intent(this, MainActivity.class);
			in.putExtras(Transfer);
			startActivity(in);
		}
		else if(v==Impressum)
		{
			Intent in = new Intent(Newsreaderselect.this, ImpressActivity.class);
			startActivity(in);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_news, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;
		switch (item.getItemId())
		{
			case R.id.main_news:
				intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return true;
			case R.id.main_beenden:
				bendendiagloge();
				return true;
			case R.id.news_impresse:
				intent = new Intent(this, ImpressActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
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
	
	public void onBackPressed()
	{
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
	}
}
