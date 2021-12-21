import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = 3l;

	int CCnumber;
	int amount;
	String opType;
	boolean successful;
	Result(int cc, int val, String ot, boolean ok){
		CCnumber=cc;
		amount=val;
		opType=ot;
		successful=ok;
	}
	public int getCCnumber() {
		return CCnumber;
	}
	public int getAmount() {
		return amount;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public String getType() {
		return opType;
	}
	public String toString() {
		return "op. "+opType+" on CC num. "+ CCnumber+(successful?" OK":" KO")+" resulting amount="+amount;
	}
}
