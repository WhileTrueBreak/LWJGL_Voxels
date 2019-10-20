package voxel.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import voxel.display.Window;

public class Input {
	
	@SuppressWarnings("unused")
	private static GLFWCursorPosCallback cursorPos;
	@SuppressWarnings("unused")
	private static GLFWScrollCallback mouseScroll;
	@SuppressWarnings("unused")
	private static GLFWMouseButtonCallback mouseButton;
	public static CursorPosInput cursorPosInput = new CursorPosInput();
	public static MouseScrollInput mouseScrollInput = new MouseScrollInput();
	public static MouseButtonInput mouseButtonInput = new MouseButtonInput();
	
	public static void init() {
		System.out.print("Initialising Inputs... ");
		GLFW.glfwSetCursorPosCallback(Window.window, cursorPos = cursorPosInput);
		GLFW.glfwSetScrollCallback(Window.window, mouseScroll = mouseScrollInput);
		GLFW.glfwSetMouseButtonCallback(Window.window, mouseButton = mouseButtonInput);
		System.out.println("Done!");
	}
	
}
