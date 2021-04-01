package fr.killax.escape.app.thread;

import fr.killax.escape.app.Config;

public abstract class AbstractThread extends Thread {
	
	public static enum ThreadState {
		CREATED,
		RUNNIG,
		PAUSED,
		STOPPED
	}
	
	protected ThreadState state;
	
	// FPS Cap
	protected final long OPTIMAL_TIME = 1000000000 / Config.TARGET_FPS;
	protected long lastLoopTime = System.nanoTime();
	private long lastFpsTime = 0;
	private int fps = 0;
	private int lastFPS = 0;
   
	
	public AbstractThread() {
		this.state = ThreadState.CREATED;
	}
	
	public void start() {
		this.state = ThreadState.RUNNIG;
		super.start();
	}
	
	public abstract void run();
	
	public void setState(ThreadState state) { this.state = state; }
	
	protected double calibrate() {
		long now = System.nanoTime();
	    long updateLength = now - lastLoopTime;
	    double delta = updateLength / ((double)OPTIMAL_TIME);
	    this.lastLoopTime = now;
	    this.lastFpsTime += updateLength;
	    this.fps++;
	    
	    if (this.lastFpsTime >= 1000000000)
	    {
	    	this.lastFPS = fps;
	    	this.lastFpsTime = 0;
	    	this.fps = 0;
	    }
	    return delta;
	}
	
	public int getFPS() { return this.lastFPS; }
	
}
