
class Newspaper extends Thread {
	String name;
	Broadcast<String> broadcast;
	Newspaper(Broadcast<String> b, String magazineName) {
		this.broadcast=b;
		this.name=magazineName;
	}
	public void run() {
		for(int i=0; i<4; i++){
			System.out.println(this.name + ": attendo news ...");
			try {
				String msg=broadcast.receive();
				System.out.println(this.name+": "+msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}

