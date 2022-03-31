import java.io.Serializable;

public class Notizia implements Serializable {
	private static final long serialVersionUID = 1L;
	TipoNotizia tipo;
	String contenuto;
	Notizia(TipoNotizia t, String s){
		contenuto=s;
		tipo=t;
	}
	public synchronized void annulla() {
		contenuto="";
	}
	public synchronized String leggi() {
		return contenuto;
	}
	public synchronized void incrementa(String X) {
		contenuto=contenuto+X;
	}
	public String leggiTipo() {
		return this.tipo.name();
	}
}
