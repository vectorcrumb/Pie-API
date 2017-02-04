package com.pie.ingredients;

import java.util.ArrayList;
import java.util.HashMap;

import com.pie.ingredients.PortCatalog.*;
import com.pie.robot.io.DriverInput;
import com.pie.robot.io.RobotOutput;

public class TankDrive2 extends Ingredient {
	
	/**
	 * TankDrive2 is the first Pie Ingredient. It includes a constructor to store references to the ports
	 * where the motor controllers and joysticks are. The update function, implemented from Cookable, is used
	 * to process input from the Driver and Sensors (the latter being unnecessary). Disable simply sends a 
	 * null value to the output.
	 */
	
	private PWMPort leftMotor, rightMotor;
	private USBPort leftControl, rightControl;
	private DriverInput driverIn;
	private RobotOutput robotOut;
	
	public TankDrive2(String name, HashMap<String, Object> data) {
		super(name);
		
		PWMPort leftMotor = PWMPort.valueOf((String) data.get("motor-left"));
		PWMPort rightMotor = PWMPort.valueOf((String) data.get("motor-right"));
		ArrayList<String> joysticks = (ArrayList<String>) data.get("joysticks");
		
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.leftControl = USBPort.valueOf(joysticks.get(0));
		this.rightControl = USBPort.valueOf(joysticks.get(1));
		
		this.driverIn = DriverInput.getInstance();
		this.robotOut = RobotOutput.getInstance();
	}

	@Override
	public void update() {
		double leftSpeed = driverIn.get(leftControl).getY();
		double rightSpeed = driverIn.get(rightControl).getY();
		
		robotOut.get(leftMotor).set(leftSpeed);
		robotOut.get(rightMotor).set(rightSpeed);
	}

	@Override
	public void disable() {
		robotOut.get(leftMotor).set(0);
		robotOut.get(rightMotor).set(0);
	}
	
}
