package com.pie.ingredients;

import com.pie.ingredients.PortCatalog.PWMPort;
import com.pie.ingredients.PortCatalog.USBPort;
import com.pie.lib.logger.Logger;
import com.pie.robot.io.DriverInput;
import com.pie.robot.io.RobotOutput;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class TankDrive2 extends Ingredient {
	
	/**
	 * TankDrive2 is the first Pie Ingredient. It includes a constructor to store references to the ports
	 * where the motor controllers and joysticks are. The update function, implemented from Cookable, is used
	 * to process input from the Driver and Sensors (the latter being unnecessary). Disable simply sends a 
	 * null value to the output.
	 */
	
	private PWMPort leftMotor, rightMotor;
	private USBPort leftControl, rightControl;
	private int leftReversed, rightReversed;
	private String controlType;
	private boolean debugLog;
	
	private DriverInput driverIn;
	private RobotOutput robotOut;
	private Logger logger;
	
	public TankDrive2(String name, HashMap<String, Object> data) {
		super(name);
		
		// Get port for left motor, right motor and list of joysticks. This is necessary data
		this.leftMotor = PWMPort.valueOf((String) data.get("motor-left"));
		this.rightMotor = PWMPort.valueOf((String) data.get("motor-right"));
		ArrayList<String> joysticks = (ArrayList<String>) data.get("joysticks");
		
		// Check for the reversed keys. If they don't exist, set them to false
		if (data.containsKey("left-reversed")) {
			this.leftReversed = Objects.equals(data.get("left-reversed"), "true") ? -1 : 1;
		} else {
			this.leftReversed = 1;
		}
		if (data.containsKey("right-reversed")) {
			this.rightReversed = Objects.equals(data.get("right-reversed"), "true") ? -1 : 1;
		} else {
			this.rightReversed = 1;
		}
		
		// Check for a control key. Default to tank drive
		if (data.containsKey("control")) {
			this.controlType = (String) data.get("control");
		} else {
			this.controlType = "tank";
		}
		
		// If the control type is not arcade, save both joysticks. Else, save only the first.
		if (joysticks.size() > 1) {
			this.leftControl = USBPort.valueOf(joysticks.get(0));
			this.rightControl = USBPort.valueOf(joysticks.get(1));
		} else {
			this.leftControl = USBPort.valueOf(joysticks.get(0));
			this.rightControl = null;
		}
		
		// Check for debugger
		if (data.containsKey("debug")) {
			this.debugLog = Objects.equals(data.get("debug"), "true");
		} else {
			this.debugLog = false;
		}
		
		// Get singleton instances
		this.driverIn = DriverInput.getInstance();
		this.robotOut = RobotOutput.getInstance();
	}

	@Override
	public void update() {
		
		double leftSpeed, rightSpeed;
		
		switch (this.controlType) {
			case "tank":
				leftSpeed = driverIn.getY(leftControl, Hand.kLeft) * leftReversed;
				rightSpeed = driverIn.getY(rightControl != null ? rightControl : leftControl, Hand.kRight) * rightReversed;
				break;
			case "arcade":
				leftSpeed = leftReversed * (driverIn.getY(leftControl, Hand.kRight) + driverIn.getX(leftControl, Hand.kRight));
				rightSpeed = rightReversed * (driverIn.getY(leftControl, Hand.kRight) - driverIn.getX(leftControl, Hand.kRight));
				break;
			default:
				leftSpeed = 0;
				rightSpeed = 0;
				break;
		}
		
		if (this.debugLog) System.out.println(String.format("Left motor: %1$.2f ; Right motor: %2$.2f",
				                                            leftSpeed, rightSpeed));
		
		robotOut.get(leftMotor).set(leftSpeed);
		robotOut.get(rightMotor).set(rightSpeed);
	}

	@Override
	public void disable() {
		robotOut.get(leftMotor).set(0);
		robotOut.get(rightMotor).set(0);
	}
	
}
