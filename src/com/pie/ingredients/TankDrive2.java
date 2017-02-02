package com.pie.ingredients;

import com.pie.ingredients.PortCatalog.*;
import com.pie.robot.io.IOPacket;

public class TankDrive2 implements Cookable {
	
	/**
	 * TankDrive2 is the first Pie Ingredient. It includes a constructor to store references to the ports
	 * where the motor controllers and joysticks are. The update function, implemented from Cookable, is used
	 * to process input from the Driver and Sensors (the latter being unnecessary). Disable simply sends a 
	 * null value to the output.
	 */
	
	PWMPort leftMotor, rightMotor;
	USBPort leftControl, rightControl;
	
	public TankDrive2(PWMPort leftMotor, PWMPort rightMotor, USBPort leftControl, USBPort rightControl) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.leftControl = leftControl;
		this.rightControl = rightControl;
	}

	@Override
	public IOPacket update() {
		// TODO Auto-generated method stub
		return new IOPacket();
	}

	@Override
	public IOPacket disable() {
		// TODO Auto-generated method stub
		return new IOPacket();
	}
	
	
	
}
