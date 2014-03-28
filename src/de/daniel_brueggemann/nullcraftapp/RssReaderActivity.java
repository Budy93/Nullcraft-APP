package de.daniel_brueggemann.nullcraftapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import de.daniel_brueggemann.nullcraftapp.rss.RSSItem;
import de.daniel_brueggemann.nullcraftapp.rss.RSSParser;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class RssReaderActivity extends ListActivity implements OnClickListener
{
	private ArrayList<RSSItem> itemlist = null;
	private RSSListAdaptor rssadaptor = null;
	public Button test;
	public static String url;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_feed);
		Bundle zielkorb = getIntent().getExtras();
		url = zielkorb.getString("url");
		test = (Button) findViewById(R.id.backfeed);
		ActivityRegistry.register(this);
		test.setOnClickListener(this);
		itemlist = new ArrayList<RSSItem>();
		
		new RetrieveRSSFeeds().execute();
	}
	
	@Override
	public void onClick(View v)
	{
		if(v == test)
		{
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
		}
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		RSSItem data = itemlist.get(position);
		String URL = data.link;
		Bundle urltransfer = new Bundle();
		urltransfer.putString("datenpaket1", URL);
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(URL));
		startActivity(intent);
		/*
		Intent in = new Intent(this, Newsreader.class);
		in.putExtras(urltransfer);
		// Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.link));
		
		startActivity(in);
		*/
	}
	
	private void retrieveRSSFeed(String urlToRssFeed, ArrayList<RSSItem> list)
	{
		try
		{
			URL url = new URL(urlToRssFeed);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader xmlreader = parser.getXMLReader();
			RSSParser theRssHandler = new RSSParser(list);
			
			xmlreader.setContentHandler(theRssHandler);
			
			InputSource is = new InputSource(url.openStream());
			
			xmlreader.parse(is);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
		Intent in = new Intent(this, Newsreaderselect.class);
		startActivity(in);
	}
	
	private class RetrieveRSSFeeds extends AsyncTask<Void, Void, Void>
	{
		private ProgressDialog progress = null;
		
		@Override
		protected Void doInBackground(Void... params)
		{
			retrieveRSSFeed(url, itemlist);
			rssadaptor = new RSSListAdaptor(RssReaderActivity.this,
			        R.layout.rssitemview, itemlist);
			return null;
		}
		
		@Override
		protected void onCancelled()
		{
			super.onCancelled();
		}
		
		@Override
		protected void onPreExecute()
		{
			progress = ProgressDialog.show(RssReaderActivity.this, null,
			        "Lade RSS Feed...");
			
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Void result)
		{
			setListAdapter(rssadaptor);
			
			progress.dismiss();
			
			super.onPostExecute(result);
		}
		
		@Override
		protected void onProgressUpdate(Void... values)
		{
			super.onProgressUpdate(values);
		}
	}
	
	private class RSSListAdaptor extends ArrayAdapter<RSSItem>
	{
		private List<RSSItem> objects = null;
		
		public RSSListAdaptor(Context context, int textviewid,
		        List<RSSItem> objects)
		{
			super(context, textviewid, objects);
			
			this.objects = objects;
		}
		
		@Override
		public int getCount()
		{
			return ((null != objects) ? objects.size() : 0);
		}
		
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		
		@Override
		public RSSItem getItem(int position)
		{
			return ((null != objects) ? objects.get(position) : null);
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			
			if(null == view)
			{
				LayoutInflater vi = (LayoutInflater) RssReaderActivity.this
				        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.rssitemview, null);
			}
			
			RSSItem data = objects.get(position);
			
			if(null != data)
			{
				TextView title = (TextView) view.findViewById(R.id.txtTitle);
				TextView date = (TextView) view.findViewById(R.id.txtDate);
				TextView description = (TextView) view
				        .findViewById(R.id.txtDescription);
				
				title.setText(data.title);
				date.setText("on " + data.date);
				description.setText(data.description);
			}
			
			return view;
		}
	}
}
