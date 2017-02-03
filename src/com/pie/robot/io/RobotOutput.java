package com.pie.robot.io;

import java.util.HashMap;

import com.pie.ingredients.PiePort;
import com.pie.robot.controllers.PWMMotorFactory;
import com.pie.robot.controllers.PWMMotorFactory.MotorCatalog;

import edu.wpi.first.wpilibj.SpeedController;

public class RobotOutput {
	
	private static RobotOutput instance;
	private HashMap<PiePort, SpeedController> pwmOutputs;
	
	public static RobotOutput getInstance() {
		if (instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}
	
	private RobotOutput() {
		// TODO Auto-generated constructor stub
	}
	
	public void populatePWM(HashMap<PiePort, MotorCatalog> pwmDefs) {
		pwmDefs.entrySet().forEach((entry) -> {
			String port = entry.getKey().toString();
			int pwmPin = Integer.parseInt(port.substring(port.length() - 1));
			SpeedController motorController = PWMMotorFactory.createMotor(entry.getValue(), pwmPin);
			this.pwmOutputs.put(entry.getKey(), motorController);
		});	
	}
	
	public SpeedController get(PiePort pwmPort) {
		return this.pwmOutputs.get(pwmPort);
	}
	
}
