
public class ReadersWriter {

	private void exec() {
		Data d = new Data();
		new Writer("Writer", d).start();
		for(int i=0; i<3; i++) {
			new Reader("Reader_"+i, d).start();
		}
	}
	public static void main(String[] args) {
		ReadersWriter rw=new ReadersWriter();
		rw.exec();
	}

}
