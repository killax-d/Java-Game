package fr.killax.escape.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.scene.AbstractScene;
import fr.killax.escape.scene.transition.AbstractTransition;
import fr.killax.escape.scene.transition.FadeInTransition;

public class SceneManager extends AbstractSceneManager {

	private static final long serialVersionUID = 1L;
	
	private Queue<AbstractScene> queue;
	private AbstractTransition transition;
	
	public SceneManager() {
		this.queue = new LinkedList<AbstractScene>();
		this.transition = new FadeInTransition();
	}
	
	public AbstractScene getScene() {
		return this.queue.peek();
	}
	
	public void setScene(AbstractScene scene, AbstractTransition transition) {
		if (scene == null) return;
		
		App.instance().setFPS(scene.getFPSCap());
		
		this.transition = transition; // Add Transition
		this.queue.add(scene);
		// Maybe add animation
		if (this.queue.size() > 1)
			this.queue.remove();
	}
	
	public void paint(Graphics g) { drawScene(g); }
	
	private void drawScene(Graphics g) {
		if (this.getScene() != null) this.getScene().draw(g);
		if (this.transition != null) this.transition.draw(g);
		if (Config.DEBUG) drawFPS(g);
	}
	
	public void updateScene(double delta) {
		if (this.getScene() != null) this.getScene().update(delta);
		if (this.transition != null) {
			this.transition.update(delta);
			if(this.transition.isEnded()) this.transition = null;
		}
	}
	
	private void drawFPS(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString(String.format("FPS : %s", App.instance().getFPS()), 10, 10 + 24);
	}
}
