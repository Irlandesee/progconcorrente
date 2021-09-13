package notizia;

public class Notizia{

	private String tipo;
	private String contenuto;

	public Notizia(String _tipo, String _contenuto){
		tipo = _tipo;
		contenuto = _contenuto;
	}

	public String getTipo(){
		return tipo;
	}

	public void setTipo(String _tipo){
		tipo = _tipo;
	}

	public String getContenuto(){
		return contenuto;
	}

	public void setContenuto(String _contenuto){
		contenuto = _contenuto;
	}

}