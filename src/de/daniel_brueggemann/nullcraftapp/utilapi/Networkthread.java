/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * Netzwerkthread Interface, stellt die Kommunikation sicher.
 * @author Daniel Brüggemann
 *
 */
public interface Networkthread
{
	/**
	 * Fragt alle Wichtigen Daten des Servers ab.
	 * @param Server String der Server URL
	 * @return HashMap mit JSON objekt
	 * @deprecated
	 */
	@SuppressWarnings("rawtypes")
	public HashMap pingserver(String Server);
	
	/**
	 * Abfrage von Spezialdaten vom Server
	 * @param Server String der Server URL
	 * @return HashMap mit JSON objekt
	 */
	@SuppressWarnings("rawtypes")
	public HashMap testServer(String Server);
	
	/**
	 * Ruft den aktuellen Serverstatus ab.
	 * @return HashMap aller Serverdaten
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getNullApiServerinfo();
	
	/**
	 * Fragt die NULLAPI nach bestimmten Werten ab
	 * @param URLparameter Die entsprechenden abfrage Parameter
	 * @return HashMap der Daten
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getNullApiOtherinfo(String URLparameter);
	
	/**
	 * Nicht in Nutzung
	 * @deprecated
	 * @return Serverdaten
	 */
	public String getServerdaten();
}
