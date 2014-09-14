/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 *
 */
public class Newsreader extends Activity implements OnClickListener
{
	
	private WebView browser;
	public static TextView impress;
	
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_dis);
		ActivityRegistry.register(this);
		// ActivityRegistry.register(this);
		browser = (WebView) findViewById(R.id.webView1);
		impress=(TextView)findViewById(R.id.impressum_news_dis);
		impress.setTextColor(Color.WHITE);
		impress.setOnClickListener(this);
		WebSettings webSettings = browser.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// browser.setWebViewClient(new Browser());
		Bundle Urlextra = getIntent().getExtras();
		String url = Urlextra.getString("datenpaket1");
		// String url = "http://cluster01.nullcraft.de:8123/";
		browser.loadUrl(url);
	}
	
	public void onBackPressed()
	{
		Intent in = new Intent(this, Newsreaderselect.class);
		startActivity(in);
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
	/*
	 * public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this
	 * adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main_dynmap, menu); return true; }
	 */
	/*
	 * public boolean onOptionsItemSelected(MenuItem item) { Intent intent =
	 * null; switch (item.getItemId()) { case R.id.Hauptmene: intent = new
	 * Intent(this, MainActivity.class); startActivity(intent); return true;
	 * case R.id.Beendenmenu_dyn: bendendiagloge(); return true; case
	 * R.id.Impressum_men_dyn: intent = new Intent(this, ImpressActivity.class);
	 * startActivity(intent); return true; default: return
	 * super.onOptionsItemSelected(item); } }
	 */
	/*
	 * public void onBackPressed() { if(browser.canGoBack()) { browser.goBack();
	 * } else { // Let the system handle the back button //
	 * super.onBackPressed(); Intent in = new Intent(Dynmap.this,
	 * MainActivity.class); startActivity(in); } } public void bendendiagloge()
	 * { AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this); //
	 * final AlertDialog alertDialog2 = new AlertDialog.Builder(this) //
	 * .create();
	 * 
	 * // Setting Dialog Title alertDialog2.setTitle("Beenden?");
	 * 
	 * // Setting Dialog Message
	 * alertDialog2.setMessage("Willst du die App wirlich beenden?");
	 * 
	 * // Setting Positive "Yes" Btn alertDialog2.setPositiveButton("Ja", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int which) { // Write your code here to execute after dialog
	 * Toast.makeText(getApplicationContext(), "Es war nett mit dir. :(",
	 * Toast.LENGTH_SHORT) .show(); ActivityRegistry.finishAll(); } }); //
	 * Setting Negative "NO" Btn alertDialog2.setNegativeButton("Nein", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int which) { dialog.cancel(); } }); alertDialog2.show(); } /*
	 * public void open(View view) { String
	 * url="http://cluster01.nullcraft.de:8123/";
	 * browser.getSettings().setLoadsImagesAutomatically(true);
	 * browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	 * browser.loadUrl(url);
	 * 
	 * }
	 */

	@Override
    public void onClick(View v)
    {
	   if(v==impress)
	   {
		   Intent in = new Intent(Newsreader.this, ImpressActivity.class);
		   startActivity(in);
	   }
    }
}
