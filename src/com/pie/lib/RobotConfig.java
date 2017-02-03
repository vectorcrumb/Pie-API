package com.pie.lib;

import java.util.HashMap;

import com.pie.ingredients.PiePort;
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
	
	private PieParser parser;
	
	public static RobotConfig getInstance() {
		if (instance == null) {
			instance = new RobotConfig();
		}
		return instance;
	}
	
	private RobotConfig() {
		this.parser = PieParser.getInstance(PieConstants.robotFile);
		this.robotOut = RobotOutput.getInstance();
		this.driverIn = DriverInput.getInstance();
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
