package voxel.model;

import voxel.model.loader.Loader;
import voxel.model.loader.OBJFileLoader;

public class Assets {
	
	public static RawModel OBJ_CUBE;
	public static ModelTexture TEX_GREEN;
	
	public static void init(Loader loader) {
		System.out.println("Initializing Assets...");
		System.out.print("\tInitializing Objects...");
		OBJ_CUBE = loader.loadToVAO(OBJFileLoader.loadOBJ("cube"));
		System.out.print("Done!\n\tInitializing Textures...");
		TEX_GREEN = new ModelTexture(loader.loadTexture("Green"));
		System.out.print("Done!\n");
		System.out.println("Done!");
	}
	
}
