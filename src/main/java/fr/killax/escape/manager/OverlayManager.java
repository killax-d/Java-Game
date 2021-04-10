package fr.killax.escape.manager;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import fr.killax.escape.overlay.AbstractOverlay;
import fr.killax.escape.scene.transition.AbstractTransition;
import fr.killax.escape.scene.transition.FadeInTransition;

public class OverlayManager extends AbstractManager<AbstractOverlay> {

	private static final long serialVersionUID = 1L;
	
	private Queue<AbstractOverlay> queue;
	private AbstractTransition transition;
	
	protected OverlayManager() {
		this.queue = new LinkedList<AbstractOverlay>();
		this.transition = new FadeInTransition();
	}
	
	public AbstractOverlay current(boolean overlay) {
		return this.queue.peek();
	}
	
	public AbstractOverlay current() {
		return current(false);
	}
	
	public void pushOverlay(AbstractOverlay overlay, AbstractTransition transition) {
		if (overlay == null) clear();
		
		this.transition = transition; // Add Transition (can be null)
		this.queue.add(overlay);
		// Maybe add animation
		if (this.queue.size() > 1)
			this.queue.remove();
	}
	
	public void clear() { this.queue.clear(); }
	
	public void paint(Graphics g) { drawOverlay(g); }
	
	private void drawOverlay(Graphics g) {
		if (this.current(false) != null) this.current(false).draw(g);
		if (this.transition != null) this.transition.draw(g);
	}

	public void update(double delta) { updateOverlay(delta); }
	private void updateOverlay(double delta) {
		if (this.current(false) != null) this.current(false).update(delta);
		if (this.transition != null) {
			this.transition.update(delta);
			if(this.transition.isEnded()) this.transition = null;
		}
	}
	
}
