/**
 * 
 */
package de.daniel_brueggemann.nullcraftapp.utilapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;

import de.daniel_brueggemann.nullcraftapp.R;

import java.io.BufferedReader;
import java.net.MalformedURLException;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 *
 */
public interface Networkthread
{
	/**
	 * Fragt alle Wichtigen Daten des Servers ab.
	 * @param Server String der Server URL
	 * @return HashMap mit JSON objekt
	 */
	public HashMap pingserver(String Server);
	
	/**
	 * Abfrage von Spezialdaten vom Server
	 * @param Server String der Server URL
	 * @return HashMap mit JSON objekt
	 */
	public HashMap testServer(String Server);
	public String getServerdaten();
}
