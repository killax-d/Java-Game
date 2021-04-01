package fr.killax.escape.app.thread;

import fr.killax.escape.app.App;

public class DrawThread extends AbstractThread {
	
    public void run(){
    	while (state == ThreadState.RUNNIG || state == ThreadState.PAUSED) {
    		calibrate();
    		
    		if (state != ThreadState.PAUSED) {
    			App.instance().getFrame().revalidate();
    			App.instance().getFrame().repaint();
    		}
    		
    		try { Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000); } catch (Exception e) {};
    	}
    }
    
}
