package com.pie.lib;

public class RobotConfig {
	
	private static RobotConfig instance;
	
	
	
	public static RobotConfig getInstance() {
		if (instance == null) {
			instance = new RobotConfig();
		}
		return instance;
	}
	
	private RobotConfig() {
		
	}
	
}
