/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author A380
 *
 */
public class EmcInterfaceImpl implements EmcInterface
{
	public final static String TestURL="http://daniel-brueggemann.de/minecraft/dev/Nullcraftapp/test";
	
	/* (non-Javadoc)
	 * @see de.daniel_brueggemann.nullcraftapp.EmcInterface#EMC_abfrage()
	 */
	public String[] EMC_abfrage()
	{
		String[] r�ckgabe=new String[2];
		r�ckgabe[0]="false";
		r�ckgabe[1]="false";
		Networkthread Network = new Networkthreadimpl();
		// /*
		final HashMap JSOnnot = Network.testServer(TestURL);
		if(JSOnnot !=null)
		{
			final Object emc = JSOnnot.get("emc");
			String help=emc.toString();
			final Object gruende_json= JSOnnot.get("Grund");
			String gruende=gruende_json.toString();
			if(help.equalsIgnoreCase("Abschalten"))
			{
				r�ckgabe[0]=gruende;
				r�ckgabe[1]="true";
				return r�ckgabe;
			}
		}
		return r�ckgabe;
	}
	
}
