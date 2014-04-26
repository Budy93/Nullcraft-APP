/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * @author Daniel Brüggemann
 * @version Beta 8.2.5.E2
 */
public interface GJSON_pruefer
{
	/**
	 * Prüft auf vorhandensein des jeweiligen Keys in JSON objekt, bei fehlen gibts ein false by vorhanden true.
	 * @param JSONObject HashMap-objekt mit netobjekten.
	 * @param Key Der zu Prüfende KEY
	 * @return Rückgabe eines Boolean auf Vorhandensein des Servers.
	 */
	@SuppressWarnings("rawtypes")
	public boolean check_key(HashMap JSONObject, String Key);
	
	/**
	 * Prüft ob eine geprüfte JSON Variable einen bestimmten Wert enthält.
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
