/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 *
 */
public class Dynmap extends Activity
{
	
	private WebView browser;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynmap);
		ActivityRegistry.register(this);
		/*
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
		*/
		browser = (WebView) findViewById(R.id.mapview);
		WebSettings webSettings = browser.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// browser.setWebViewClient(new Browser());
		String url = "http://cluster01.nullcraft.de:8123/";
		browser.loadUrl(url);
	}
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_dynmap, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;
		switch (item.getItemId())
		{
			case R.id.Hauptmene:
				intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return true;
			case R.id.Beendenmenu_dyn:
				bendendiagloge();
				return true;
			case R.id.Impressum_men_dyn:
				intent = new Intent(this, ImpressActivity.class);
				startActivity(intent);
				return true;
			case R.id.News:
				/*
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
				*/
				Intent in = new Intent(this, Newsreaderselect.class);
				startActivity(in);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void onBackPressed()
	{
		if(browser.canGoBack())
		{
			browser.goBack();
		}
		else
		{
			// Let the system handle the back button
			// super.onBackPressed();
			Intent in = new Intent(Dynmap.this, MainActivity.class);
			startActivity(in);
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
	/*
	 * public void open(View view) { String
	 * url="http://cluster01.nullcraft.de:8123/";
	 * browser.getSettings().setLoadsImagesAutomatically(true);
	 * browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	 * browser.loadUrl(url);
	 * 
	 * }
	 */
}
