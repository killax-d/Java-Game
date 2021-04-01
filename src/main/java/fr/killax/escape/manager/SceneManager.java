package fr.killax.escape.manager;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.scene.AbstractScene;

public class SceneManager extends Container {

	private static final long serialVersionUID = 1L;
	
	private Queue<AbstractScene> queue;
	
	public SceneManager() {
		this.queue = new LinkedList<AbstractScene>();
	}
	
	public AbstractScene getScene() {
		return this.queue.peek();
	}
	
	public void setScene(AbstractScene scene) {
		this.queue.add(scene);
		// Maybe add animation
		if (this.queue.size() > 1)
			this.queue.remove();
	}
	
	public void paint(Graphics g) { drawScene(g); }
	
	private void drawScene(Graphics g) {
		if (this.getScene() != null) this.getScene().draw(g);
		if (Config.DEBUG) drawFPS(g);
	}
	
	public void updateScene(double delta) {
		if (this.getScene() != null) this.getScene().update(delta);
	}
	
	private void drawFPS(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString(String.format("FPS : %s", App.instance().getFPS()), 10, 10 + 24);
	}
}
