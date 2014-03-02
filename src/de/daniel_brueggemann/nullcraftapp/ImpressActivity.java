package de.daniel_brueggemann.nullcraftapp;

import de.daniel_brueggemann.nullcraftapp.R;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Brüggemann
 * @version Alpha 0.8
 *
 */
public class ImpressActivity extends Activity implements OnClickListener
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public Button back;
	public TextView impressum;
	public Button change;
	public Button apache;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_impresse);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityRegistry.register(this);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		change = (Button) findViewById(R.id.changelog);
		change.setOnClickListener(this);
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		apache = (Button)findViewById(R.id.lizenz);
		apache.setOnClickListener(this);
		impressum = (TextView) findViewById(R.id.impress);
		String Text = "Version: Alpha 0.8.0.E1"
		        + "\n"
		        + "Codename: Krähe.Vanny"
		        + "\n"
		        + "Autor: Budy93"
		        + "\n"
		        + "\n"
		        + "Angaben gemäß § 5 TMG:"
		        + "\n"
		        + "\n"
		        + "Daniel Brüggemann"
		        + "\n"
		        + "Weskammstr. 13"
		        + "\n"
		        + "12279 Berlin"
		        + "\n"
		        + "\n"
		        + "Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten durch Dritte zur Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien etc. wird hiermit ausdrücklich widersprochen. Der Betreiber der APP behalten sich ausdrücklich rechtliche Schritte im Falle der unverlangten Zusendung von Werbeinformationen etc., etwa durch Spam-Mails , vor."
		        + "\n"
		        + "\n"
		        + "Email: webmaster@daniel-brueggemann.de"
		        + "\n"
		        + "\n"
		        + "Haftung für Inhalte:"
		        + "\n"
		        + "\n"
		        + "Die Inhalte dieser APP wurden mit größter Sorgfalt erstellt. Für die Richtigkeit, Vollständigkeit und Aktualität der Inhalte können wir jedoch keine Gewähr übernehmen. Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf dieser APP nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen."
		        + "\n"
		        + "\n"
		        + "Haftung für Links:"
		        + "\n"
		        + "\n"
		        + "Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar. Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen."
		        + "\n"
		        + "\n"
		        + "Urheberrecht:"
		        + "\n"
		        + "\n"
		        + "Die durch die APP-Betreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers. Downloads und Kopien dieser APP und desen Code sind nur für den privaten, nicht kommerziellen Gebrauch gestattet. Soweit die Inhalte auf dieser APP nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen."
		        + "\n"
		        + "\n"
		        + "Verwendete Drittsoftware:"
		        + "\n"
		        + "\n"
		        + "Diese App verwendet die fremd API Software Google-Gson ( https://code.google.com/p/google-gson/ ) von inder123, joel.leitch@gmail.com und limpbizkit die unter der Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0) steht, ein Auszug der Lizenz ist in der .apk enthalten und ist zus\u00E4tzlich mit den Button Lizenz Google-Gson abrufbar."
		        + "\n"
		        + "\n"
		        + "Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten durch Dritte zur Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien wird hiermit ausdrücklich widersprochen. Die Betreiber der APP behalten sich ausdrücklich rechtliche Schritte im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor."
		        + "\n"
		        + "Quelle: http://www.e-recht24.de/muster-disclaimer.htm";
		impressum.setText(Text);
		/*
		 * But = (Button) findViewById(R.id.button1); text = (TextView)
		 * findViewById(R.id.impresse); But.setOnClickListener(this); Version =
		 * (Button) findViewById(R.id.version);
		 * Version.setOnClickListener(this); Vote = (Button)
		 * findViewById(R.id.Votel); Vote.setOnClickListener(this); Dynmap =
		 * (Button) findViewById(R.id.Dyn); Dynmap.setOnClickListener(this);
		 * Player = (TextView) findViewById(R.id.playerO); Playermay =
		 * (TextView) findViewById(R.id.playerM); Serverversion = (TextView)
		 * findViewById(R.id.VersionM); Modt = (TextView)
		 * findViewById(R.id.modtM); Beenden = (Button)
		 * findViewById(R.id.Beenden); Beenden.setOnClickListener(this);
		 * Impressum = (Button) findViewById(R.id.impress);
		 * Impressum.setOnClickListener(this);
		 */
		
	}
	
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */
	public boolean onCreateOptionsMenu(Menu menu)
	{ // Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;
		switch (item.getItemId())
		{
			case R.id.item3:
				intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return true;
			case R.id.item2:
				bendendiagloge();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onClick(View v)
	{
		if(v==apache)
		{
			credit();
		}
		else if(v == back)
		{
			Intent in = new Intent(ImpressActivity.this, MainActivity.class);
			startActivity(in);
		}
		else if(v == change)
		{
			String changtext;
			String changtext_Alpha071E3;
			String changtext_Alpha071E2;
			String changtext_Alpha071E1;
			String changtext_Alpha07;
			String changtext_Alpha06;
			String changtext_Alpha05;
			/*
			 * ===Changelog=== Alpha 0.5 Codename: Putzi Serverstatus Aktuelle
			 * Userzahl Maximaler Userzahl MODT Version von Nullcraft Link zur
			 * Dynmap Autolink zur Voteseite
			 * 
			 * ===Changelog=== Version: Alpha 0.6 Codename: Saui Changelog:
			 * Beenden Option Integriert.Background ArbeitenFarbige
			 * Serverstatusnachrichten Rotieren der App wurde Blockiert !Motd
			 * Text wurde gefixt
			 * 
			 * ===Changelog=== Version: Alpha 0.7 Codename: Krähe
			 * 
			 * Changelog: Fehlerbehebung bei Offline Status einiger TextViewer.
			 * Putzi entfernen aus SourceBessere Putzisicherung einbauen.
			 * Entfernung der StrictMode.ThreaspolicyActivity Registrierung
			 * überarbeiten um gemeldete Fehler beim Beenden der App - zu
			 * verhindern
			 * 
			 * ===Changelog=== Version: Alpha 0.7.1.E1 Codename: Krähe
			 * 
			 * Changelog: Serverstatus farbig je nach versionActivtyregister
			 * EingeführtZurückbutton beendet nun die AppNetwork Interface
			 * eingefügtImpressum eingefüghtImpressum Activity eingefügt
			 * Vorbereitung zur Integrierung der Dynmap ab API-Level: 13
			 * 
			 * ===Changelog=== Version: Alpha 0.7.1.E2 Codename: Krähe
			 * 
			 * Changelog: Dynmap eingefügtVerbesserung der Performenz für
			 * Android-Api<13Zurückbutton beendet nun die AppNeues Layout,
			 * eingeführt.
			 * 
			 * ===Changelog=== Version: Alpha 0.7.1.E3 Codename: Krähe.Vanny
			 * 
			 * Changelog: Dynmap fehler behoben.Fehler im Layout behoben.
			 * Changelog Button eingeführtVorbereitung für Version Vany mit IRC
			 * Class.Versionstext erhilt OK Button der fehlte.
			 */
			changtext_Alpha071E3 = "Version: Alpha 0.7.1.E3"
			        + "\n"
			        + "* Codename: Krähe.Vanny"
			        + "\n"
			        + "* Changelog:"
			        + "\n"
			        + "* Dynmap fehler behoben.Fehler im Layout behoben."
			        + "\n"
			        + "* Changelog Button eingeführtVorbereitung für Version Vany mit IRC"
			        + "\n"
			        + "* Class.Versionstext erhilt OK Button der fehlte."
			        + "\n" + "\n" + "----------------" + "\n" + "\n";
			changtext_Alpha071E2 = "Version: Alpha 0.7.1.E2 "
			        + "\n"
			        + "* Codename: Krähe"
			        + "\n"
			        + "* Changelog:"
			        + "\n"
			        + "* Dynmap eingefügtVerbesserung der Performenz für"
			        + "\n"
			        + "* Android-Api<13Zurückbutton beendet nun die AppNeues Layout,eingeführt."
			        + "\n" + "\n" + "\n" + "----------------" + "\n" + "\n";
			changtext_Alpha071E1 = "Version: Alpha 0.7.1.E1"
			        + "\n"
			        + "* Codename: Krähe"
			        + "\n"
			        + "* Changelog:"
			        + "\n"
			        + "* Serverstatus farbig je nach versionActivtyregister"
			        + "\n"
			        + "* EingeführtZurückbutton beendet nun die AppNetwork Interface"
			        + "\n"
			        + "* eingefügtImpressum eingefüghtImpressum Activity eingefügt"
			        + "\n"
			        + "* Vorbereitung zur Integrierung der Dynmap ab API-Level: 13"
			        + "/n" + "\n" + "\n" + "----------------" + "\n" + "\n";
			changtext_Alpha07 = "Version: Alpha 0.7"
			        + "\n"
			        + "* Codename: Krähe"
			        + "\n"
			        + "* Changelog:"
			        + "\n"
			        + "* Fehlerbehebung bei Offline Status einiger TextViewer."
			        + "\n"
			        + "* Putzi entfernen aus SourceBessere Putzisicherung einbauen."
			        + "\n"
			        + "* Entfernung der StrictMode.ThreaspolicyActivity Registrierung"
			        + "\n"
			        + "* überarbeiten um gemeldete Fehler beim Beenden der App - zu"
			        + "* verhindern" + "\n" + "\n" + "----------------" + "\n"
			        + "\n";
			changtext_Alpha06 = "Version: Alpha 0.6"
			        + "\n"
			        + "* Codename: Saui"
			        + "\n"
			        + "* Changelog:"
			        + "\n"
			        + "* Beenden Option"
			        + "\n"
			        + "* Integriert.Background ArbeitenFarbige Serverstatusnachrichten"
			        + "\n"
			        + "* Rotieren der App wurde Blockiert !Motd Text wurde gefixt"
			        + "\n" + "\n" + "----------------" + "\n" + "\n";
			changtext_Alpha05 = "Version: Alpha 0.5"
			        + "\n"
			        + "* Codename: Putzi"
			        + "\n"
			        + "* Serverstatus"
			        + "\n"
			        + "* Aktuelle Userzahl"
			        + "\n"
			        + "* Maximaler Userzahl"
			        + "\n"
			        + "* MODT Version von Nullcraft Link zur Dynmap Autolink zur Voteseite"
			        + "\n" + "\n" + "----------------" + "\n" + "\n";
			changtext = changtext_Alpha071E3 + changtext_Alpha071E2
			        + changtext_Alpha071E1 + changtext_Alpha07
			        + changtext_Alpha06 + changtext_Alpha05;
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Version");
			alertDialog.setMessage(changtext);
			alertDialog.setPositiveButton("OK",
			        new DialogInterface.OnClickListener()
			        {
				        public void onClick(DialogInterface dialog, int which)
				        {
					        dialog.cancel();
				        }
			        });
			alertDialog.show();
		}
		/*
		 * if(v==Impressum) { AlertDialog.Builder alertDialog = new
		 * AlertDialog.Builder(this); alertDialog.setTitle("Impressum");
		 * alertDialog
		 * .setMessage("Version: Alpha 0.7"+"\n"+"Codename: Krähe"+"\n"
		 * +"Autor: Budy93"+"\n"+"Angaben gemäß § 5 TMG:"+"\n"+
		 * "\n"+"Daniel Brüggemann"
		 * +"\n"+"Weskammstr. 13"+"\n"+"12279 Berlin"+"\n"
		 * +"\n"+"Email:webmaster@daniel-brueggemann.de"+"\n"+"\n"+
		 * "Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten durch Dritte zur Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien wird hiermit ausdrücklich widersprochen. Die Betreiber der Seiten behalten sich ausdrücklich rechtliche Schritte im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor."
		 * ); alertDialog.setPositiveButton("OK", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int which) { dialog.cancel(); } });
		 * alertDialog.show(); } else if(v == Beenden) { bendendiagloge(); }
		 */
	}
	
	public void bendendiagloge()
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle("Beenden?");
		
		// Setting Dialog Message
		alertDialog2.setMessage("Willst du die App wirlich beenden?");
		
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("Ja",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        // Write your code here to execute after dialog
				        Toast.makeText(getApplicationContext(),
				                "Es war nett mit dir. :(", Toast.LENGTH_SHORT)
				                .show();
				        ActivityRegistry.finishAll();
			        }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("Nein",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        dialog.cancel();
			        }
		        });
		alertDialog2.show();
	}
	
	public void credit()
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// final AlertDialog alertDialog2 = new AlertDialog.Builder(this)
		// .create();
		
		// Setting Dialog Title
		alertDialog2.setTitle("Apache License, Version 2.0");
		
		// Setting Dialog Message
		alertDialog2
		        .setMessage("Apache License\n\nVersion 2.0, January 2004\n\nhttp://www.apache.org/licenses/\n\nTERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\n\n1. Definitions.\n\n\"License\" shall mean the terms and conditions for use, reproduction, and distribution as defined by Sections 1 through 9 of this document.\n\n\"Licensor\" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.\n\n\"Legal Entity\" shall mean the union of the acting entity and all other entities that control, are controlled by, or are under common control with that entity. For the purposes of this definition, \"control\" means (i) the power, direct or indirect, to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.\n\n\"You\" (or \"Your\") shall mean an individual or Legal Entity exercising permissions granted by this License.\n\n\"Source\" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.\n\n\"Object\" form shall mean any form resulting from mechanical transformation or translation of a Source form, including but not limited to compiled object code, generated documentation, and conversions to other media types.\n\n\"Work\" shall mean the work of authorship, whether in Source or Object form, made available under the License, as indicated by a copyright notice that is included in or attached to the work (an example is provided in the Appendix below).\n\n\"Derivative Works\" shall mean any work, whether in Source or Object form, that is based on (or derived from) the Work and for which the editorial revisions, annotations, elaborations, or other modifications represent, as a whole, an original work of authorship. For the purposes of this License, Derivative Works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work and Derivative Works thereof.\n\n\"Contribution\" shall mean any work of authorship, including the original version of the Work and any modifications or additions to that Work or Derivative Works thereof, that is intentionally submitted to Licensor for inclusion in the Work by the copyright owner or by an individual or Legal Entity authorized to submit on behalf of the copyright owner. For the purposes of this definition, \"submitted\" means any form of electronic, verbal, or written communication sent to the Licensor or its representatives, including but not limited to communication on electronic mailing lists, source code control systems, and issue tracking systems that are managed by, or on behalf of, the Licensor for the purpose of discussing and improving the Work, but excluding communication that is conspicuously marked or otherwise designated in writing by the copyright owner as \"Not a Contribution.\"\n\n\"Contributor\" shall mean Licensor and any individual or Legal Entity on behalf of whom a Contribution has been received by Licensor and subsequently incorporated within the Work.\n\n2. Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.\n\n3. Grant of Patent License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable (except as stated in this section) patent license to make, have made, use, offer to sell, sell, import, and otherwise transfer the Work, where such license applies only to those patent claims licensable by such Contributor that are necessarily infringed by their Contribution(s) alone or by combination of their Contribution(s) with the Work to which such Contribution(s) was submitted. If You institute patent litigation against any entity (including a cross-claim or counterclaim in a lawsuit) alleging that the Work or a Contribution incorporated within the Work constitutes direct or contributory patent infringement, then any patent licenses granted to You under this License for that Work shall terminate as of the date such litigation is filed.\n\n4. Redistribution. You may reproduce and distribute copies of the Work or Derivative Works thereof in any medium, with or without modifications, and in Source or Object form, provided that You meet the following conditions:\n\n    You must give any other recipients of the Work or Derivative Works a copy of this License; and\n    You must cause any modified files to carry prominent notices stating that You changed the files; and\n    You must retain, in the Source form of any Derivative Works that You distribute, all copyright, patent, trademark, and attribution notices from the Source form of the Work, excluding those notices that do not pertain to any part of the Derivative Works; and\n    If the Work includes a \"NOTICE\" text file as part of its distribution, then any Derivative Works that You distribute must include a readable copy of the attribution notices contained within such NOTICE file, excluding those notices that do not pertain to any part of the Derivative Works, in at least one of the following places: within a NOTICE text file distributed as part of the Derivative Works; within the Source form or documentation, if provided along with the Derivative Works; or, within a display generated by the Derivative Works, if and wherever such third-party notices normally appear. The contents of the NOTICE file are for informational purposes only and do not modify the License. You may add Your own attribution notices within Derivative Works that You distribute, alongside or as an addendum to the NOTICE text from the Work, provided that such additional attribution notices cannot be construed as modifying the License.\n\n    You may add Your own copyright statement to Your modifications and may provide additional or different license terms and conditions for use, reproduction, or distribution of Your modifications, or for any such Derivative Works as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.\n\n5. Submission of Contributions. Unless You explicitly state otherwise, any Contribution intentionally submitted for inclusion in the Work by You to the Licensor shall be under the terms and conditions of this License, without any additional terms or conditions. Notwithstanding the above, nothing herein shall supersede or modify the terms of any separate license agreement you may have executed with Licensor regarding such Contributions.\n\n6. Trademarks. This License does not grant permission to use the trade names, trademarks, service marks, or product names of the Licensor, except as required for reasonable and customary use in describing the origin of the Work and reproducing the content of the NOTICE file.\n\n7. Disclaimer of Warranty. Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its Contributions) on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including, without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work and assume any risks associated with Your exercise of permissions under this License.\n\n8. Limitation of Liability. In no event and under no legal theory, whether in tort (including negligence), contract, or otherwise, unless required by applicable law (such as deliberate and grossly negligent acts) or agreed to in writing, shall any Contributor be liable to You for damages, including any direct, indirect, special, incidental, or consequential damages of any character arising as a result of this License or out of the use or inability to use the Work (including but not limited to damages for loss of goodwill, work stoppage, computer failure or malfunction, or any and all other commercial damages or losses), even if such Contributor has been advised of the possibility of such damages.\n\n9. Accepting Warranty or Additional Liability. While redistributing the Work or Derivative Works thereof, You may choose to offer, and charge a fee for, acceptance of support, warranty, indemnity, or other liability obligations and/or rights consistent with this License. However, in accepting such obligations, You may act only on Your own behalf and on Your sole responsibility, not on behalf of any other Contributor, and only if You agree to indemnify, defend, and hold each Contributor harmless for any liability incurred by, or claims asserted against, such Contributor by reason of your accepting any such warranty or additional liability.\n\nEND OF TERMS AND CONDITIONS");
		
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("Lizenz Online",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
			        	final Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse("http://www.apache.org/licenses/LICENSE-2.0"));
						startActivity(intent);
			        }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("Fertig",
		        new DialogInterface.OnClickListener()
		        {
			        public void onClick(DialogInterface dialog, int which)
			        {
				        dialog.cancel();
			        }
		        });
		alertDialog2.show();
	}
	
	public void onBackPressed()
	{
		
		// Let the system handle the back button
		// super.onBackPressed();
		Intent in = new Intent(ImpressActivity.this, MainActivity.class);
		startActivity(in);
	}
}
