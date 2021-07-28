class AnsaMessage extends Thread {
	Broadcast<String> broadcast;
	AnsaMessage(Broadcast<String> b) {
		this.broadcast=b;
		this.start();
	}
	public void run(){	
		String msg;
		for(int i=0; i<10; i++){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {	}
			if(i%2==0) {
				msg="Aumentano le immatricolazioni";
			} else {
				msg="Diminuiscono le immatricolazioni";				
			}
			System.out.println("News from ANSA.it: "+ msg);
			broadcast.send(msg);
		}

	}
}
