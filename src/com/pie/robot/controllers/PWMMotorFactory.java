package com.pie.robot.controllers;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SD540;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class PWMMotorFactory {
	
	/**
	 * The PWM Motor Factory is a Factory class which generates Speed Controllers objects based on the input enum used.
	 * @author Lucas
	 *
	 */
		
	public enum MotorCatalog {
		JAGUAR,
		SD540,
		SPARK,
		TALON,
		TALONSRX,
		VICTOR,
		VICTORSP
	}
	
	public static SpeedController createMotor(MotorCatalog motorType, int pin) {
		SpeedController motorController;
		switch (motorType) {
		case JAGUAR:
			motorController = new Jaguar(pin);
			break;
		case SD540:
			motorController = new SD540(pin);
			break;
		case SPARK:
			motorController = new Spark(pin);
			break;
		case TALON:
			motorController = new Talon(pin);
			break;
		case TALONSRX:
			motorController = new TalonSRX(pin);
			break;
		case VICTOR:
			motorController = new Victor(pin);
			break;
		case VICTORSP:
			motorController = new VictorSP(pin);
			break;
		default:
			throw new IllegalArgumentException("Invalid motor type. Are you using the Motor Catalog?");
		}
		return motorController;
	}

}
