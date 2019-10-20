package voxel;

import org.lwjgl.glfw.GLFW;

import voxel.display.Window;
import voxel.input.Input;

public class Main implements Runnable{
	
	//thread
	private boolean running = false;
	private Thread thread;
	
	//sync
	private int FPS_CAP = 120;
	private double startTime = System.nanoTime();
	private double avgMSF = 0;
	private double timeElapsed = 0;
	
	public Main() {
		
	}
	
	private void init() {
		Window.init();
		Input.init();
	}
	
	private void loop() {
		while(!GLFW.glfwWindowShouldClose(Window.window)) {
			sync();
			Window.setTitle();
			Window.beforeRender();
			
			Window.afterRender();
		}
	}
	
	private void destroy() {
		Window.closeDisplay();
	}
	
	@Override
	public void run() {
		init();
		loop();
		destroy();
		stop();
	}
	
	private void sync() {
		while(true) {
			if(System.nanoTime()-startTime<1000000000/FPS_CAP)
				continue;
			timeElapsed += System.nanoTime()-startTime;
			avgMSF += System.nanoTime()-startTime;
			avgMSF /= 2;
			if(timeElapsed>1000000000) {
				System.out.printf("%f ms/frame\n", avgMSF/1000000);
				avgMSF = System.nanoTime()-startTime;
				timeElapsed = 0;
			}
			startTime = System.nanoTime();
			break;
		}
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
