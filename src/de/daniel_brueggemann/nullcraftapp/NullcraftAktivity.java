package de.daniel_brueggemann.nullcraftapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NullcraftAktivity extends Activity implements OnClickListener
{
	public Button btn;
	public TextView text;
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 *  globally 
     *TextView myAwesomeTextView = (TextView)findViewById(r.id.myAwesomeTextView);
     *in your OnCreate() method
     *myAwesomeTextView.setText("My Awesome Text");
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nullcraft_aktivity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nullcraft_aktivity, menu);
		btn=(Button)findViewById(R.id.butti);
		text=(TextView)findViewById(R.id.texti);
		btn.setOnClickListener(this);
		return true;
	}

	@Override
    public void onClick(View arg0)
    {
	    text.setText("Test");
	    
    }
	
}
