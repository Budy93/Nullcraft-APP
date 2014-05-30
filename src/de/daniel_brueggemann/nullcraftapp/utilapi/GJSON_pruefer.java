/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * @author Daniel Br�ggemann
 * @version Beta 8.2.5.E2
 */
public interface GJSON_pruefer
{
	/**
	 * Pr�ft auf vorhandensein des jeweiligen Keys in JSON objekt, bei fehlen gibts ein false by vorhanden true.
	 * @param JSONObject HashMap-objekt mit netobjekten.
	 * @param Key Der zu Pr�fende KEY
	 * @return R�ckgabe eines Boolean auf Vorhandensein des Servers.
	 */
	@SuppressWarnings("rawtypes")
	public boolean check_key(HashMap JSONObject, String Key);
	
	/**
	 * Pr�ft ob eine gepr�fte JSON Variable einen bestimmten Wert enth�lt.
	 * @param JSON
	 * @param Key
	 * @param Inhalt
	 * @param if_checket
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean check_key_inhalt(HashMap JSON, String Key, String Inhalt,
	        boolean if_checket);
}
