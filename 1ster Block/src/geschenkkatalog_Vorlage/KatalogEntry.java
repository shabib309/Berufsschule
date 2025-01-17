package geschenkkatalog_Vorlage;

import geschenk.IGeschenk;

public class KatalogEntry {
	
	private final IGeschenk geschenk;
	private int anzahl;
	
	public KatalogEntry(IGeschenk geschenk) {
		this.geschenk = geschenk;
		anzahl = 1;
	}
	
	public KatalogEntry(IGeschenk geschenk, int anzahl) {
		this.geschenk = geschenk;
		this.anzahl = anzahl;
	}
	
	public int getAnzahl() {
		return anzahl;
	}
	
	public String getName() {
		return geschenk.getName();
	}
	
	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("Name: ").append(geschenk.getName());
		build.append(", Typ: ").append(geschenk.geschenkType());
		build.append(", Anzahl: ").append(anzahl);
		return build.toString();
	}

}
