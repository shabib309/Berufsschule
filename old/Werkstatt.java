import java.io.*;
import java.util.*;

public class Werkstatt {
	
	private final int anzahlGeschenke = 50;
	private final int anzahlWichtel = 15;
	
	private Geschenk[] geschenke;		// Die gesamte Wunschliste
	
	private int geschenknummer = 0;		// derzeitig bearbeitetes Geschenk
	
	private Wichtel[] wichtel;			// Alle arbeitenden Wichtel
	
	// Basiszeiteinheit der Wichtel
	private int runde = 1;
	
	
	public Werkstatt(){
		// Wir leiten die ganze Ausgabe in eine Datei um.
		try{
			System.setOut(new PrintStream(new FileOutputStream("Werkstattlog.txt")));	
		}
		catch (Exception e) {System.out.println(e);}

		geschenke = WerkstattTools.generiereGeschenke(anzahlGeschenke);
		wichtel = WerkstattTools.generiereWichtel(anzahlWichtel);
	}
	
	private Geschenk naechstesGeschenk(){
		if(geschenknummer < geschenke.length)
			return geschenke[geschenknummer++];
		return null;
	}

	private boolean sindAlleFertig() {
		if(geschenknummer < geschenke.length)
			return false;
		else
			for(int i = 0; i < wichtel.length; i++){
				if(wichtel[i].arbeitetNoch())
					return false;
			}
		return true;
	}
	
	private boolean arbeit() {
		System.out.println("----------------------------------------");
		System.out.println("Runde " + runde++);
		for(int i = 0; i < wichtel.length; i++){
			Wichtel w = wichtel[i];
			if(w.arbeitetNoch())
				w.arbeiteWeiter();
			else {
				int gNr = geschenknummer;
				Geschenk g = naechstesGeschenk();
				if(g != null) {
					System.out.println(w + " bearbeitet nun #" + gNr + " \n --> " + g);
					w.arbeite(g);
					
				}
			}
		}	
		return !sindAlleFertig();
	}
	
	private void zeigeLeistungen() {
		System.out.println("----------------------------------------");
		System.out.println("Leistungsindex: ");
		for(int i = wichtel.length - 1; i >= 0; i--) {
			if(i == wichtel.length - 4)
				System.out.println("----------------------------------------");
			Wichtel w = wichtel[i];
			System.out.println(wichtel.length - i + ". Platz: " + w + " mit einer Effizienz von " + w.effizienz() + " Geschenke pro Runde.");
		}
	}
	
	private void sortiere(){
		Arrays.sort(wichtel);
	}
	
	// Eine Werkstatt wird angelegt, dann werden alle Geschenke bearbeitet, 
	// bis die Liste durch ist. Dann sortieren wir die Wichtel nach Effizienz
	// und geben sie entsprechend aus.
	// Momentan auskommentiert, sollte am Schluss alles kompilieren und dem Logfile gleichen.
	// Erstellen Sie bis dahin eigenen Testaufrufe von Methoden.
	public static void main(String[] args) {
		Werkstatt werkstatt = new Werkstatt();
		while(werkstatt.arbeit());
		//werkstatt.sortiere();
		werkstatt.zeigeLeistungen();
		
		// TODO Aufgabe 5): Klonen der drei besten Wichtel
		Wichtel roterSuperWichtel = null;
		Wichtel blauerSuperWichtel = null;
		Wichtel gelberSuperWichtel = null;
		for(int i = werkstatt.wichtel.length - 1; i >= 0; i--){
			Wichtel w = werkstatt.wichtel[i];
			if(roterSuperWichtel == null && w instanceof RoterWichtel) roterSuperWichtel = new RoterWichtel(w);
			if(blauerSuperWichtel == null && w instanceof BlauerWichtel) blauerSuperWichtel = new BlauerWichtel(w);
			if(gelberSuperWichtel == null && w instanceof GelberWichtel) gelberSuperWichtel = new GelberWichtel(w);
			if(roterSuperWichtel != null && blauerSuperWichtel != null && gelberSuperWichtel != null) break;
		}
		System.out.println("Folgende Wichtel wurden geklont:" + roterSuperWichtel + " " + blauerSuperWichtel +" "+ gelberSuperWichtel);
	}
}