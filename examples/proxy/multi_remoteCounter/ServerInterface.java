import java.io.IOException;
public interface ServerInterface{

	public static final int PORT = 8888;

	public int sum(int s) throws IOException;
	public int reset() throws IOException;
	public int increment() throws IOException;
	public void close() throws IOException;

}