package com.pie.lib;

import java.util.HashMap;

import com.pie.ingredients.PiePort;
import com.pie.lib.util.FlashDriveFinder;
import com.pie.lib.util.PieConstants;
import com.pie.robot.controllers.DSControllerFactory.DSControllerCatalog;
import com.pie.robot.controllers.PWMMotorFactory.MotorCatalog;
import com.pie.robot.io.DriverInput;
import com.pie.robot.io.RobotOutput;

public class RobotConfig {
	
	private static RobotConfig instance;
	private RobotOutput robotOut;
	private DriverInput driverIn;
	private SubsystemCoordinator subsystems;
	private FlashDriveFinder flashDrive;
	
	private PieParser parser;
	
	public static RobotConfig getInstance() {
		if (instance == null) {
			instance = new RobotConfig();
		}
		return instance;
	}
	
	private RobotConfig() {
		this.robotOut = RobotOutput.getInstance();
		this.driverIn = DriverInput.getInstance();
		this.subsystems = SubsystemCoordinator.getInstance();
		this.flashDrive = FlashDriveFinder.getInstance();
		
		String path = flashDrive.getPath();
		System.out.print("Path to use is: ");
		System.out.println(path);
		
		this.parser = PieParser.getInstance(path);
	}
	
	public void configureRobot () {
		HashMap<PiePort, MotorCatalog> pinDefinitions = parser.getPinDefinitions();
		HashMap<PiePort, DSControllerCatalog> controlDefinitions = parser.getControlDefinitions();
		HashMap<String, Object> subsystemDefinitions = parser.getSubsystemDefinitions();
		
		robotOut.populatePWM(pinDefinitions);
		driverIn.populateDSController(controlDefinitions);
		subsystems.populateSubsystems(subsystemDefinitions);
	}
	
}
