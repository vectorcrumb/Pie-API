package com.pie.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class DSControllerFactory {

	public enum DSControllerCatalog {
		JOYSTICK
	}
	
	public static GenericHID createDSController (DSControllerCatalog controllerType, int port) {
		GenericHID controller;
		switch (controllerType) {
		case JOYSTICK:
			controller = new Joystick(port);
			break;
		default:
			throw new IllegalArgumentException("Invalid DS Controller type!");
		}
		return controller;
	}
	
}
