package com.pie.lib.util;

import java.io.File;

public class FlashDriveFinder {
	
	private static FlashDriveFinder instance;
	
	public static FlashDriveFinder getInstance() {
		if (instance == null) {
			instance = new FlashDriveFinder();
		}
		return instance;
	}
	
	private FlashDriveFinder() {
		
	}
	
	public String getPath() {
		File mediaDir = new File("/media");
		File[] dirs = mediaDir.listFiles();
		if (dirs != null && dirs.length != 0) {
			for (File subFile : dirs) {
				if (subFile.isDirectory()) {
					File target = new File(subFile, PieConstants.robotFile);
					if (target.exists()) {
						return target.getAbsolutePath();
					}
				}
			}
		}
		return PieConstants.defaultConfigFileDir + PieConstants.robotFile;
	}

}
