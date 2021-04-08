package fr.killax.escape.app;

import fr.killax.escape.app.thread.DrawThread;
import fr.killax.escape.app.thread.GameThread;
import fr.killax.escape.assets.I18N;

import java.util.Timer;
import java.util.TimerTask;

import fr.killax.escape.app.thread.AbstractThread.ThreadState;
import fr.killax.escape.manager.SceneManager;
import fr.killax.escape.scene.LoadingScene;
import fr.killax.escape.scene.MainMenuScene;
import fr.killax.escape.scene.transition.FadeInTransition;

public class App {
	
	// SINGLETON PATTERN
	private static App instance;
	
	public static App instance() {
		if (instance == null) instance = new App();
		return instance;
	}
	
	private GameThread gThread;
	private DrawThread dThread;
	private MainFrame frame;
	private SceneManager sceneManager;

	public App() {
		I18N.init();
		this.gThread = new GameThread();
		this.dThread = new DrawThread();
		this.frame = new MainFrame();
		this.sceneManager = new SceneManager();
	}
	
	public void start() {
		this.gThread.start();
		this.dThread.start();
		this.frame.setContentPane(this.sceneManager);
		this.sceneManager.setScene(new LoadingScene(), new FadeInTransition());
		this.frame.addKeyListener(sceneManager);
		this.frame.addMouseListener(sceneManager);
		this.frame.addMouseMotionListener(sceneManager);
		this.frame.addMouseWheelListener(sceneManager);
		
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				sceneManager.setScene(new MainMenuScene(), new FadeInTransition());
			}
			
		}, 5000);
	}
	
	public SceneManager getSceneManager() { return this.sceneManager; }
	
	public MainFrame getFrame() { return this.frame; }
	
	public void pauseGraphics(boolean pause) {
		this.dThread.setState(pause ? ThreadState.PAUSED : ThreadState.RUNNIG);
	}
	
	public void pauseGame(boolean pause) {
		this.gThread.setState(pause ? ThreadState.PAUSED : ThreadState.RUNNIG);
	}
	
	public void quit() {
		this.gThread.setState(ThreadState.STOPPED);
		this.dThread.setState(ThreadState.STOPPED);
	}
	
	public void setFPS(int fps) {
		this.gThread.setFPSCap(fps);
		this.dThread.setFPSCap(fps);
	}
	
	public int getFPS() {
		return (this.gThread.getFPS() + this.dThread.getFPS()) / 2;
	}
}
