
public class Stopwatch {
	
	private long startTimeMs;
	private long stopTimeMs;
	private boolean running;
	
	
	public Stopwatch() {
		startTimeMs = 0;
		stopTimeMs = 0;
		running = false;
	}
	
	public void start() {
		running = true;
		startTimeMs = System.currentTimeMillis();
		stopTimeMs = startTimeMs;
	}
	
	public void stop() {
		running = false;
		stopTimeMs = System.currentTimeMillis();
	}
	
	public long getDuration() {
		if(running) 
			return System.currentTimeMillis() - startTimeMs;
			
		else
			return stopTimeMs - startTimeMs;
	}
	
}
