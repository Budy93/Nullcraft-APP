/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Daniel Brüggemann
 * @version 0.8
 */
public class Emc extends Activity implements OnClickListener
{
	public static TextView emc_text;
	public static Button ende;
	public static String gruende;
	
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
		setContentView(R.layout.activity_emc);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		Bundle grund_intent = getIntent().getExtras();
		gruende = grund_intent.getString("grund");
		emc_text = (TextView) findViewById(R.id.emc_text);
		emc_text.setTextColor(Color.RED);
		emc_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		gruende = gruende + "Developer: Budy93, ©Berlin 2014";
		emc_text.setText(gruende);
		ende = (Button) findViewById(R.id.emc_beenden);
		ende.setOnClickListener(this);
		
	}
	
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
				        ActivityRegistry.finishAll();
			        }
		        });
		alertDialog2.show();
	}
	
	@Override
	public void onClick(View v)
	{
		if(v == ende)
		{
			diaglogesp("Es tut uns leid",
			        "Wir danken für dein Verständnis wir sind bald wieder da");
		}
	}
}
