/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * @author Daniel Brüggemann
 *
 */
public class EmcInterfaceImpl implements EmcInterface
{
	public final static String EMCURL = "http://daniel-brueggemann.de/minecraft/dev/Nullcraftapp/wartung/Wartung";
	private String URL_wartung;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.daniel_brueggemann.nullcraftapp.EmcInterface#EMC_abfrage()
	 */
	public String[] EMC_abfrage()
	{
		String[] rückgabe = new String[2];
		rückgabe[0] = "false";
		rückgabe[1] = "false";
		boolean emc_vorhanden=false;
		boolean grundtest=false;
		Networkthread Network = new Networkthreadimpl();
		// /*
		final HashMap JSOnnot = Network.testServer(EMCURL);
		GJSON_pruefer pr=new GJSON_pruefer_impl();
		emc_vorhanden=pr.check_key(JSOnnot, "emc");
		grundtest=pr.check_key(JSOnnot, "Grund");
		if(JSOnnot != null)
		{
			if(emc_vorhanden==true)
			{
				final Object emc = JSOnnot.get("emc");
				String help = emc.toString();
				if(grundtest==true)
				{
					final Object gruende_json = JSOnnot.get("Grund");
					String gruende = gruende_json.toString();
					if(help.equalsIgnoreCase("Abschalten"))
					{
						rückgabe[0] = gruende;
						rückgabe[1] = "true";
						return rückgabe;
					}
					else
					{
						return rückgabe;
					}
				}
				else
				{
					if(help.equalsIgnoreCase("Abschalten"))
					{
						rückgabe[0] = "Die App wurde notbeendet seitens des Entwicklers. \n"+"Es wurde kein Grund definiert \n \n";
						rückgabe[1] = "true";
						return rückgabe;
					}
					else
					{
						return rückgabe;
					}
				}
			}
			else if(emc_vorhanden=false)
			{
				rückgabe[0] = "Es wurde kein Grund definiert";
				rückgabe[1] = "false";
				return rückgabe;
			}
		}
		return rückgabe;
	}

	@Override
    public String EMC_wartung(String URL)
    {
		URL_wartung=URL;
		String rückgabe="";
		Object wartungO;
		Networkthread net=new Networkthreadimpl();
		final HashMap JWartung=net.testServer(URL_wartung);
		GJSON_pruefer prue=new GJSON_pruefer_impl();
		boolean wartungda=false;
		wartungda=prue.check_key(JWartung, "wartung");
		if(wartungda==true)
		{
			wartungO=JWartung.get("wartung");
			rückgabe=wartungO.toString();
			return rückgabe;
		}
		else
		{
			rückgabe="Keine geplant";
			return rückgabe;
		}
    }

	@Override
    public int TEM_Update_Ceck(String URL)
    {
		String URL_update=URL;
		//String rückgabe="082271E2";
		Object update;
		Networkthread net=new Networkthreadimpl();
		final HashMap JUpdate=net.testServer(URL_update);
		GJSON_pruefer prue=new GJSON_pruefer_impl();
		boolean updatekeycheck=false;
		updatekeycheck=prue.check_key(JUpdate, "is_version");
		if(updatekeycheck==true)
		{
			update=JUpdate.get("is_version");
			Integer a=Integer.valueOf(update.toString());
			//int u=a;
			return a;
		}
		else
		{
			return 822712;
		}
    }
	
}
