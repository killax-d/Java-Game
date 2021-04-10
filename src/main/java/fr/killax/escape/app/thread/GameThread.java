package fr.killax.escape.app.thread;

import fr.killax.escape.app.App;

public class GameThread extends AbstractThread {
	

    public void run(){
    	while (state == ThreadState.RUNNIG || state == ThreadState.PAUSED) {
			double delta = calibrate();
			
			if (state != ThreadState.PAUSED) App.instance().getSceneManager().update(delta);
			
    		try { Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000); } catch (Exception e) {};
    	}
    }
    
}
