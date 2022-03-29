package fr.killax.escape.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Queue;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.scene.AbstractScene;
import fr.killax.escape.scene.transition.AbstractTransition;
import fr.killax.escape.scene.transition.FadeInTransition;

public class SceneManager extends AbstractManager<AbstractScene> {

	private static final long serialVersionUID = 1L;
	
	private Queue<AbstractScene> queue;
	private OverlayManager overlayManager;
	private AbstractTransition transition;
	
	public SceneManager() {
		this.queue = new LinkedList<AbstractScene>();
		this.overlayManager = new OverlayManager();
		this.transition = new FadeInTransition();
	}
	
	public OverlayManager getOverlayManager() { return this.overlayManager; }
	
	public AbstractScene current(boolean overlay) {
		if (overlay && hasOverlay()) return this.overlayManager.current();
		return this.queue.peek();
	}
	
	public boolean hasOverlay() { return this.overlayManager.current() != null; }
	
	public void pushScene(AbstractScene scene, AbstractTransition transition) {
		if (scene == null) return;
		
		App.instance().setFPS(scene.getFPSCap());
		
		this.transition = transition; // Add Transition (can be null)
		this.queue.add(scene);
		// Maybe add animation
		if (this.queue.size() > 1)
			this.queue.remove();
	}
	
	public void paint(Graphics g) { drawScene(g); }
	private void drawScene(Graphics g) {
		if (this.current(false) != null) this.current(false).draw(g);
		if (this.transition != null) this.transition.draw(g);
		if (this.overlayManager.current() != null) this.overlayManager.paint(g);
		if (Config.DEBUG) drawFPS(g);
	}

	public void update(double delta) { updateScene(delta); }
	private void updateScene(double delta) {
		if (this.current(false) != null) this.current(false).update(delta);
		if (this.overlayManager.current() != null) this.overlayManager.update(delta);
		if (this.transition != null) {
			this.transition.update(delta);
			if(this.transition.isEnded()) this.transition = null;
		}
	}
	
	private void drawFPS(Graphics g) {
		int padding = 2;
		String fps = String.format("FPS: %s", App.instance().getFPS());
		g.setFont(new Font("Arial", Font.BOLD, 12));
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(fps, g);
		
		g.setColor(new Color(0, 0, 0, 0.5f));
		g.fillRect(0, 0, (int) bounds.getWidth() + padding*2, (int) bounds.getHeight() + padding*2);
		g.setColor(Color.WHITE);
		g.drawString(fps, padding, padding + g.getFont().getSize());
	}
}
