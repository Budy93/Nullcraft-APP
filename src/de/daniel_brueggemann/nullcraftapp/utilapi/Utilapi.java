/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.util.HashMap;

/**
 * Utilapi Interface, übernimmt kleine Aufgaben, die öfter genutz werden. Für die Nullapp
 * @author Daniel Brüggemann
 *
 */
public interface Utilapi
{
	/**
	 * Wandelt das HashObjekt aus setHash über den geforderten Key zu einen Stringobject.
	 * @param Key Key der abgefragt werden soll.
	 * @see setHash
	 * @return
	 */
	public String hashtostring(String Key);
	
	/**
	 * Setzt das HashMapobject für die weitere Verwendung
	 * @param hash HashMapobject
	 */
	@SuppressWarnings("rawtypes")
	public void setHash(HashMap hash);
	
	/**
	 * Erstellt eine Datei
	 * @param pathe Pfad der zu erstellenden Datei
	 */
	public void filecreatewriter(String pathe);
	
	/**
	 * Schreibt Daten in eine spezifische Datei
	 * @param Dateiname Datei in die geschrieben werden soll.
	 * @param Eingabe Was in die Datei geschrieben werden soll
	 */
	public void filewriter(String Dateiname, String Eingabe);
	
	/**
	 * Liest die Daten Zeilenweise aus einer Datei
	 * @param Dateiname Name der Datei bzw. Pfad
	 * @param Zeilenanzahl Anzahl der einzulesenen Zeilen
	 * @return Stringobjekt mit allen Zeilen der Datei
	 */
	public String[] filereader(String Dateiname, int Zeilenanzahl);
	
	/**
	 * Löscht eine bestimmte Datei.
	 * @param Dateiname Dateiname bzw Pfad der Datei
	 */
	public void fileeraser(String Dateiname);
	
	/**
	 * Generierd Zufallszahlen
	 * @param Zahlenbereich Zahlengröße die zu generieren sind.
	 * @return Zufallszahl
	 */
	public int randomgenrator(int Zahlenbereich);
	
	/**
	 * Schreibt in die Logdatei der App fehlerdaten.
	 * @param Eingabe Fehlerwerte
	 */
	public void log(String Eingabe);
	
	/**
	 * Prüft nach Updates der App
	 * @param Version aktuelle App version
	 * @return true bei Update, false wenn keine vorhanden sind.
	 */
	public boolean updatecheck(int Version);
	
	/**
	 * Prüft auf Netzwerkanschluss.
	 * @return true bei vorhandenen Internet false bei fehlenden.
	 */
	public boolean checkNetzwerkprovider();
	
	/**
	 * Prüft ob eine Datei Vorhanden ist
	 * @param Dateipfad Dateipfad der gesuchten Datei
	 * @return true wenn sie vorhanden ist, false wenn sie fehlt.
	 */
	public boolean filexist(String Dateipfad);
	
	/**
	 * Gibt die Zeilenanzahl einer Datei aus.
	 * @param Path Pfad der Datei
	 * @return gibt Zeilenanzahl zurück
	 */
	public int fileleng(String Path);
	
	/**
	 * Erstellt den Pfad auf dem Laufwerk
	 */
	public void pathcreate();
	
	/**
	 * Prüft ob überhaupt eine SD-Karte vorhanden ist.
	 * @return true wenn überhaupt vorhanden
	 */
	public Boolean sdkartevorhanden();
}
