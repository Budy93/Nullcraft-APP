/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * @author Daniel Br�ggemann
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
		String[] r�ckgabe = new String[2];
		r�ckgabe[0] = "false";
		r�ckgabe[1] = "false";
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
						r�ckgabe[0] = gruende;
						r�ckgabe[1] = "true";
						return r�ckgabe;
					}
					else
					{
						return r�ckgabe;
					}
				}
				else
				{
					if(help.equalsIgnoreCase("Abschalten"))
					{
						r�ckgabe[0] = "Die App wurde notbeendet seitens des Entwicklers. \n"+"Es wurde kein Grund definiert \n \n";
						r�ckgabe[1] = "true";
						return r�ckgabe;
					}
					else
					{
						return r�ckgabe;
					}
				}
			}
			else if(emc_vorhanden=false)
			{
				r�ckgabe[0] = "Es wurde kein Grund definiert";
				r�ckgabe[1] = "false";
				return r�ckgabe;
			}
		}
		return r�ckgabe;
	}

	@Override
    public String EMC_wartung(String URL)
    {
		URL_wartung=URL;
		String r�ckgabe="";
		Object wartungO;
		Networkthread net=new Networkthreadimpl();
		final HashMap JWartung=net.testServer(URL_wartung);
		GJSON_pruefer prue=new GJSON_pruefer_impl();
		boolean wartungda=false;
		wartungda=prue.check_key(JWartung, "wartung");
		if(wartungda==true)
		{
			wartungO=JWartung.get("wartung");
			r�ckgabe=wartungO.toString();
			return r�ckgabe;
		}
		else
		{
			r�ckgabe="Keine geplant";
			return r�ckgabe;
		}
    }

	@Override
    public int TEM_Update_Ceck(String URL)
    {
		String URL_update=URL;
		//String r�ckgabe="082271E2";
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
