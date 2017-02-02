package com.pie.ingredients;

public class PortCatalog {
	/**
	 * The PortCatalog groups all the available roboRIO ports inside enums to use
	 *  as keys for accessing the configuration data from YAML files.
	 * @author Lucas
	 */
	
	public enum PWMPort implements PiePort {
		PWM0,
		PWM1,
		PWM2,
		PWM3,
		PWM4,
		PWM5,
		PWM6,
		PWM7,
		PWM8,
		PWM9
	}
	
	public enum USBPort implements PiePort {
		USB0,
		USB1,
		USB2,
		USB3
	}

}
