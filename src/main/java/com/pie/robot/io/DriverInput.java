package com.pie.robot.io;

import java.util.HashMap;

import com.pie.ingredients.PiePort;
import com.pie.robot.controllers.DSControllerFactory;
import com.pie.robot.controllers.DSControllerFactory.DSControllerCatalog;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

public class DriverInput {
	
	/**
	 * DriverInput is in charge of taking the data from the Driver Station and feeding it through an IOPacket as the return of sendData
	 */
	
	private static DriverInput instance;
	private HashMap<PiePort, GenericHID> usbInputs;
	
	public static DriverInput getInstance() {
		if (instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}
	
	private DriverInput() {
		// TODO Auto-generated constructor stub
		this.usbInputs = new HashMap<>();
	}
	
	public void populateDSController(HashMap<PiePort, DSControllerCatalog> usbDefs) {
		usbDefs.entrySet().forEach((entry) -> {
			String port = entry.getKey().toString();
			int usbPin = Integer.parseInt(port.substring(port.length() - 1));
			GenericHID usbController = DSControllerFactory.createDSController(entry.getValue(), usbPin);
			this.usbInputs.put(entry.getKey(), usbController);
		});
	}
	
	public GenericHID get(PiePort usbPort) {
		return this.usbInputs.get(usbPort);
	}
	
//	public double getY(PiePort usbPort) {
//		GenericHID controller = this.usbInputs.get(usbPort);
//		if (controller instanceof Joystick) {
//			return ((Joystick) controller).getY();
//		} else {
//			throw new IllegalArgumentException("Hand not specified for an Xbox controller!");
//		}
//	}
	
	public double getY(PiePort usbPort, Hand hand) {
		GenericHID controller = this.usbInputs.get(usbPort);
		if (controller instanceof XboxController) {
			return ((XboxController) controller).getY(hand);
		} else if (controller instanceof Joystick) {
			return ((Joystick) controller).getY();
		}
		return 0;
	}
	
//	public double getX(PiePort usbPort) {
//		GenericHID controller = this.usbInputs.get(usbPort);
//		if (controller instanceof Joystick) {
//			return ((Joystick) controller).getX();
//		} else {
//			throw new IllegalArgumentException("Hand not specified for an Xbox controller!");
//		}
//	}
	
	public double getX(PiePort usbPort, Hand hand) {
		GenericHID controller = this.usbInputs.get(usbPort);
		if (controller instanceof XboxController) {
			return ((XboxController) controller).getX(hand);
		} else if (controller instanceof Joystick) {
			return ((Joystick) controller).getX();
		}
		return 0;
	}
	
}
