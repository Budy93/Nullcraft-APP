/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;

import android.os.Environment;
import android.util.Log;

/**
 * Implementierung der Utilapi. Integriert auch die Logfunktion durch Filewriter
 * @author Daniel Brüggemann
 *
 */
public class UtilapiImpl implements Utilapi
{
	private static PrintWriter pw;
	private boolean ausgabe;
	
	/**
	 * Gibt die Ausgabe aus.
	 * @return the ausgabe
	 */
	private boolean isAusgabe()
	{
		return ausgabe;
	}
	
	/**
	 * Setzt ausgabe für Verbindungsprüfung
	 * @param ausgabe the ausgabe to set
	 */
	private void setAusgabe(boolean ausgabe)
	{
		this.ausgabe = ausgabe;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#hashtostring(java.
	 * lang.String)
	 */
	@Override
	public String hashtostring(String Key)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#setHash(java.util.
	 * HashMap)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void setHash(HashMap hash)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#filecreatewriter(java
	 * .lang.String)
	 */
	@Override
	public void filecreatewriter(String pathe)
	{
		try
		{
			File datei = new File(Environment.getExternalStorageDirectory()
			        + "/NullcraftAPP/" + pathe);
			datei.createNewFile();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#filewriter(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void filewriter(String Dateiname, String Eingabe)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#fileeraser(java.lang
	 * .String)
	 */
	@Override
	public void fileeraser(String Dateiname)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#randomgenrator(int)
	 */
	@Override
	public int randomgenrator(int Zahlenbereich)
	{
		int zahlen = Zahlenbereich;
		Random random = new Random();
		int randomzahl = 0;
		try
		{
			randomzahl = random.nextInt(zahlen);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return randomzahl;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#log(java.lang.String)
	 */
	@Override
	public void log(String Eingabe)
	{
		try
        {
			Boolean sdcard = sdkartevorhanden();
			if(sdcard == true)
			{
				Boolean pathcreat = filexist("/NullcraftAPP/");
				if(pathcreat == true)
				{
					Boolean logis=filexist("Log.log");
					if(logis==true)
					{
						pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory()
			                + "/NullcraftAPP/Log.log"));
						pw.append(Eingabe);
						pw.append("\n");
						pw.flush();
						pw.close();
					}
					else
					{
						filecreatewriter("Log.log");
						pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory()
				                + "/NullcraftAPP/Log.log"));
						pw.append(Eingabe);
						pw.append("\n");
						pw.close();
					}
				}
				else
				{
					pathcreate();
					filecreatewriter("Log.log");
					pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory()
			                + "/NullcraftAPP/Log.log"));
					pw.append(Eingabe);
					pw.append("\n");
					pw.close();
				}
			}
	        
        }
        catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#updatecheck(int)
	 */
	@Override
	public boolean updatecheck(int Version)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#checkNetzwerkprovider
	 * ()
	 */
	@Override
	public boolean checkNetzwerkprovider()
	{
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					URLConnection urlConnection = new URL("http://google.de")
					        .openConnection();
					// urlConnection.setConnectTimeout(1000);
					urlConnection.connect();
					setAusgabe(true);
				}
				catch (Exception e)
				{
					setAusgabe(false);
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		return isAusgabe();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#filexist(java.lang
	 * .String)
	 */
	@Override
	public boolean filexist(String Dateipfad)
	{
		String Datei = Environment.getExternalStorageDirectory() + Dateipfad;
		try
		{
			// "/sdcard/backups/data.png"
			File aFile = new File(Datei);
			if(aFile.exists())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception exp)
		{
			Log.w("Kritischer Fehler Nullapp:", exp.getLocalizedMessage());
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#filereader(java.lang
	 * .String, int)
	 */
	@Override
	public String[] filereader(String Dateiname, int Zeilenanzahl)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#fileleng(java.lang
	 * .String)
	 */
	@Override
	public int fileleng(String Path)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#pathcreate()
	 */
	@Override
	public void pathcreate()
	{
		File datei = new File(Environment.getExternalStorageDirectory()
		        + "/NullcraftAPP/");
		datei.mkdir();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.daniel_brueggemann.nullcraftapp.utilapi.Utilapi#sdkartevorhanden()
	 */
	@Override
	public Boolean sdkartevorhanden()
	{
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
