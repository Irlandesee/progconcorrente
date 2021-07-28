
public class TimePair {
	int period;
	int elapsed;
	TimePair(int p){
		period=p;
		elapsed=0;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getElapsed() {
		return elapsed;
	}
	public void setElapsed(int elapsed) {
		this.elapsed = elapsed;
	}
	public void incElapsed() {
		this.elapsed++;
	}	
}
