package voxel.display;

import java.awt.Toolkit;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Window {
	
	public static int width = 800;
    public static int height = 800;
    
    public static String title = "Voxel";
	
    private static double last_time;
    private static int fps;
    
	public static long window;
	
	public static void init() {
		System.out.println("Initializing Display...");
		if(!GLFW.glfwInit())
			System.err.println("GLFW failed to initialize");
		System.out.print("\tCreating Display...");
		window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if(window == MemoryUtil.NULL) System.err.println("Could not create display");
		System.out.print("Done!\n\tSetting Dimensions...");
		GLFW.glfwSetWindowPos(window, 
				Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2, 
				Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_FALSE);
		System.out.print("Done!\n");
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwShowWindow(window);
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(0);
		GL.createCapabilities();
		System.out.println("Done!");
	}
	
	public static void beforeRender() {
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
    }

    public static void afterRender() {
    	GLFW.glfwSwapBuffers(window);
    	GLFW.glfwPollEvents();
    }
	
	public static boolean setTitle() {
        double current_time = GLFW.glfwGetTime();
        fps++;
        if (current_time - last_time >= 1.0) {
        	GLFW.glfwSetWindowTitle(window, title+" | "+fps+" fps");
        	fps = 0;
            last_time++;
        	return true;
        }
        return false;
	}
	
	public static void closeDisplay() {
		System.out.print("Closing Display...");
		GLFW.glfwTerminate();
		System.out.println("Done!");
	}
	
}
