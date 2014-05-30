/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;

/**
 * @author Daniel Brüggemann
 *
 */
public class Networkthreadimpl implements Networkthread
{
	String Serverdaten;
	@SuppressWarnings("rawtypes")
	public static HashMap send = null;
	private final String NCAPI = "Zensiert";
	private final String NCServerinfo = "Zensiert";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.Networkthread#pingserver(java.lang
	 * .String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap pingserver(String Server)
	{
		/*
		 * if(android.os.Build.VERSION.SDK_INT > 9) { final
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder() .permitNetwork().build();
		 * StrictMode.setThreadPolicy(policy); }
		 */
		/*
		 * String URL = "http://api.iamphoenix.me/get/?server_ip=" + Server;
		 * HashMap map=null; new Networkcalltask().execute(URL); map =
		 * getsend(); return map;
		 */
		{
			/*
			 * if(android.os.Build.VERSION.SDK_INT > 9) { final
			 * StrictMode.ThreadPolicy policy = new
			 * StrictMode.ThreadPolicy.Builder() .permitNetwork().build();
			 * StrictMode.setThreadPolicy(policy); }
			 */
			try
			{
				final URL url = new URL(
				        "http://api.iamphoenix.me/get/?server_ip=" + Server);
				try
				{
					final BufferedReader reader = new BufferedReader(
					        new InputStreamReader(url.openStream()));
					final String data = reader.readLine();
					
					final Gson gson = new Gson();
					return gson.fromJson(data, HashMap.class);
				}
				catch (final MalformedURLException e)
				{
					e.printStackTrace();
				}
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.Networkthread#testServer(java.lang
	 * .String)
	 */
	@SuppressWarnings("rawtypes")
	public HashMap testServer(String Server)
	{
		/*
		 * if(android.os.Build.VERSION.SDK_INT > 9) { final
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder() .permitNetwork().build();
		 * StrictMode.setThreadPolicy(policy); }
		 */
		try
		{
			final URL url = new URL(Server);
			try
			{
				/*
				 * Serverdaten=Server; Readers re = new Readers(); new
				 * Thread(re).start(); Future<BufferedReader> getreader =
				 * (Future<BufferedReader>) re.getreader();
				 * Future<BufferedReader> rest=getreader; final BufferedReader
				 * reader = new BufferedReader(re.getreader());
				 */
				final BufferedReader reader = new BufferedReader(
				        new InputStreamReader(url.openStream()));
				final String data = reader.readLine();
				
				final Gson gson = new Gson();
				return gson.fromJson(data, HashMap.class);
			}
			catch (final MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String getServerdaten()
	{
		return Serverdaten;
	}
	
	/*
	 * public static HashMap retriveHashmap(String Server) { try { final URL url
	 * = new URL("http://api.iamphoenix.me/get/?server_ip=" + Server); try {
	 * final BufferedReader reader = new BufferedReader( new
	 * InputStreamReader(url.openStream())); final String data =
	 * reader.readLine();
	 * 
	 * final Gson gson = new Gson(); return gson.fromJson(data, HashMap.class);
	 * } catch (final MalformedURLException e) { e.printStackTrace(); // return
	 * null; } } catch (final IOException e) { e.printStackTrace(); // return
	 * null; } return null; }
	 * 
	 * public static void setsend(HashMap r) { send = r; }
	 * 
	 * public HashMap getsend() { return send; }
	 */
	
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap getNullApiServerinfo()
	{
		try
		{
			final URL url = new URL(NCServerinfo);
			try
			{
				final BufferedReader reader = new BufferedReader(
				        new InputStreamReader(url.openStream()));
				final String data = reader.readLine();
				
				final Gson gson = new Gson();
				return gson.fromJson(data, HashMap.class);
			}
			catch (final MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap getNullApiOtherinfo(String URLparameter)
	{
		try
		{
			final URL url = new URL(NCAPI + URLparameter);
			try
			{
				/*
				 * Serverdaten=Server; Readers re = new Readers(); new
				 * Thread(re).start(); Future<BufferedReader> getreader =
				 * (Future<BufferedReader>) re.getreader();
				 * Future<BufferedReader> rest=getreader; final BufferedReader
				 * reader = new BufferedReader(re.getreader());
				 */
				final BufferedReader reader = new BufferedReader(
				        new InputStreamReader(url.openStream()));
				final String data = reader.readLine();
				
				final Gson gson = new Gson();
				return gson.fromJson(data, HashMap.class);
			}
			catch (final MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
/*
 * class Networkcalltask extends AsyncTask<String, Void, HashMap> { private
 * ProgressDialog progress = null;
 * 
 * @Override protected void onPreExecute() { // progress =
 * ProgressDialog.show(null, null, "Lade Serverdaten");
 * 
 * super.onPreExecute(); }
 * 
 * protected HashMap doInBackground(String... Server) { String url = Server[0];
 * HashMap result = Networkthreadimpl.retriveHashmap(url); return result; }
 * 
 * protected void onPostExecute(HashMap result) { // progress.dismiss();
 * Networkthreadimpl.setsend(result); // super.onPostExecute(result); } }
 */
