/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
		browser = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = browser.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// browser.setWebViewClient(new Browser());
		String url = "http://cluster01.nullcraft.de:8123/";
		browser.loadUrl(url);
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
