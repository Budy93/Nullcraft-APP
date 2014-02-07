package com.example.nullcraftapp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;

import de.daniel_brueggemann.nullcraftapp.R;

import java.io.BufferedReader;
import java.net.MalformedURLException;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	public TextView text;
	public TextView texti;
	public Button But;
	public Button Version;
    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    // Just for testing, allow network access in the main thread
	    // NEVER use this is productive code
	    StrictMode.ThreadPolicy policy = new StrictMode.
	    ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		But=(Button)findViewById(R.id.button1);
		text=(TextView)findViewById(R.id.textView2);
		But.setOnClickListener(this);
		Version=(Button)findViewById(R.id.version);
		Version.setOnClickListener(this);
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
	@Override
    public void onClick(View v)
    {
		if(v==But)
		{
			text.setText("Funktionstest");
		    // TODO Auto-generated method stub
			
			HashMap JSON = pingServer("bau.nullcraft.de"); 
			if (JSON == null || JSON.get("status").equals("false"))
			{
				text.setText("Offline Maxi on Work");
		    }
			else
			{
				text.setText("Online");
		    }
		}
		else if (v==Version)
		{
			/*
			 * SingleButtton.setOnClickListener(new View.OnClickListener() {

        public void onClick(View arg0) {
            // Creating alert Dialog with one Button

            AlertDialog alertDialog = new AlertDialog.Builder(AlertDialogActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Alert Dialog");

            // Setting Dialog Message
            alertDialog.setMessage("Welcome to Android Application");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.tick);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog,int which) 
                        {
                            // Write your code here to execute after dialog closed
                        Toast.makeText(getApplicationContext(),"You clicked on OK", Toast.LENGTH_SHORT).show();
                        }
                    });

            // Showing Alert Message
            alertDialog.show();

        }
    });
			 */
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Version");
			alertDialog.setMessage("Version: Alpha 0.1 Putzi");
			alertDialog.show();
			Toast.makeText(this, "Version: Alpha 0.1 Putzi", Toast.LENGTH_LONG).show();
		}
    }
	 public static HashMap pingServer(String server)
	 {
		 try
			{
				URL url = new URL("http://api.iamphoenix.me/status/?server_ip="+ server);
				try
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
					String data = reader.readLine();
					
					Gson gson = new Gson();
					return gson.fromJson(data, HashMap.class);
				}
				catch (MalformedURLException e)
				{
				e.printStackTrace();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}
}
