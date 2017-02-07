package com.pie.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class DSControllerFactory {

	public enum DSControllerCatalog {
		JOYSTICK,
		XBOX360
	}
	
	public static GenericHID createDSController (DSControllerCatalog controllerType, int port) {
		GenericHID controller;
		switch (controllerType) {
		case JOYSTICK:
			controller = new Joystick(port);
			break;
		case XBOX360:
			controller = new XboxController(port);
			break;
		default:
			throw new IllegalArgumentException("Invalid DS Controller type!");
		}
		return controller;
	}
	
}
