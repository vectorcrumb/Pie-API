package com.pie.ingredients;

import java.util.ArrayList;
import java.util.HashMap;

import com.pie.ingredients.PortCatalog.PWMPort;
import com.pie.ingredients.PortCatalog.USBPort;
import com.pie.robot.io.DriverInput;
import com.pie.robot.io.RobotOutput;

public class TankDrive4 extends Ingredient {
	
	private PWMPort frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor;
	private USBPort leftControl, rightControl;
	private int leftReversed, rightReversed;
	
	private DriverInput driverIn;
	private RobotOutput robotOut;
	
	public TankDrive4(String name, HashMap<String, Object> data) {
		super(name);
		
		this.frontLeftMotor = PWMPort.valueOf((String) data.get("motor-front-left"));
		this.frontRightMotor = PWMPort.valueOf((String) data.get("motor-front-right"));
		this.rearLeftMotor = PWMPort.valueOf((String) data.get("motor-rear-left"));
		this.rearRightMotor = PWMPort.valueOf((String) data.get("motor-rear-right"));
		ArrayList<String> joysticks = (ArrayList<String>) data.get("joysticks");
		
		this.leftReversed = (String) data.get("left-reversed") == "true" ? -1 : 1;
		this.rightReversed = (String) data.get("right-reversed") == "true" ? -1 : 1;
		
		this.leftControl = USBPort.valueOf(joysticks.get(0));
		this.rightControl = USBPort.valueOf(joysticks.get(1));
		
		this.driverIn = DriverInput.getInstance();
		this.robotOut = RobotOutput.getInstance();
	}
	
	@Override
	public void update() {
		double leftSpeed = driverIn.get(leftControl).getY() * leftReversed;
		double rightSpeed = driverIn.get(rightControl).getY() * rightReversed;
		
		robotOut.get(frontLeftMotor).set(leftSpeed);
		robotOut.get(rearLeftMotor).set(leftSpeed);
		robotOut.get(frontRightMotor).set(rightSpeed);
		robotOut.get(rearRightMotor).set(rightSpeed);
	}
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
	

}
