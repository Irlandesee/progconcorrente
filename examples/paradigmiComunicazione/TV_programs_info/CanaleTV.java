class CanaleTv extends Thread {
	String name;
	Blackboard<Programma> blackboard;
	Programma[] programmazione;
	CanaleTv(Blackboard<Programma> b, String n, Programma[] p) {
		this.blackboard=b; this.name=n;
		this.programmazione=p;
	}
	public void run(){
		for(int i=0; i<programmazione.length; i++) {
			System.out.println(this.name + " trasmette " + programmazione[i]);
			blackboard.write(programmazione[i]);
			try {
				Thread.sleep(programmazione[i].durationProg());
			} catch (InterruptedException e) {}
		}
	}
}
