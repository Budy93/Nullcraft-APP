/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

/**
 * @author Daniel Brüggemann
 * @version 0.8
 *
 */
public interface EmcInterface
{
	// public final static String
	// TestURL="http://daniel-brueggemann.de/minecraft/dev/Nullcraftapp/test";
	/**
	 * @return
	 */
	public String[] EMC_abfrage();
	
	/**
	 * Abfrage nach Wartungsnews
	 * @param URL Url zur Wartung
	 * @return String Json news Objecte
	 */
	public String EMC_wartung(String URL);
	
	/**
	 * @param URL
	 * @return
	 */
	public int TEM_Update_Ceck(String URL);
}
