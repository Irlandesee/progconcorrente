import point.Point;
import segment.Segment;
interface ServerInterface{

	final String END_STRING = "END";
	final String NEW_SEGMENT = "NEW_SEGMENT";
	final String SYMMETRICAL = "SYMMETRICAL";
	final String GET_LIST = "GET_LIST";
	final String SUCCESS = "OK";
	final String FAILURE = "KO";

	void newSegment(Point p1, Point p2) throws IOException, ClassNotFoundException;
	Point symmetrical(Point p) throws IOException, ClassNotFoundException;
	void list() throws IOException, ClassNotFoundException;
	void end() throws IOException;

}