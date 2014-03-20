package de.daniel_brueggemann.nullcraftapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthread;
import de.daniel_brueggemann.nullcraftapp.utilapi.Networkthreadimpl;

public class Readers implements Runnable
{
	String Server;
	volatile static BufferedReader reader;
	
	@Override
	public void run()
	{
		Networkthread Network = new Networkthreadimpl();
		Server=Network.getServerdaten();	
		URL url=null;
        try
        {
	        url = new URL(Server);
        }
        catch (MalformedURLException e1)
        {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
        }
		try
		{
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			setreader(reader);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setreader(BufferedReader readers)
	{
		reader = readers;
	}
	
	public BufferedReader getreader()
	{
		return reader;
	}
}
