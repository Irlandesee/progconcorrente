class Programma {
  private String nome;
  private String startTime;
  private int durationInMin=0;
  Programma (String nome, String start, int minutes) {
    this.nome=nome;
    this.startTime=start;
    this.durationInMin=minutes;
  }
  public String toString () {
    return nome + " (inizia alle "+ startTime+ ")";
  }
  public String getProgName(){
	  return nome;
  }
  public int durationProg(){
	  return durationInMin;
  }
}
