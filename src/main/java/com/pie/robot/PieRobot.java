package com.pie.robot;

import com.pie.lib.RobotConfig;
import com.pie.lib.SubsystemCoordinator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class PieRobot extends IterativeRobot {
	
	RobotConfig configurator;
	SubsystemCoordinator coordinator;
	
	public PieRobot() {
		configurator = RobotConfig.getInstance();
		coordinator = SubsystemCoordinator.getInstance();
	}
	
	public void robotInit() {
		double begTime = Timer.getFPGATimestamp();
		configurator.configureRobot();
		double endTime = Timer.getFPGATimestamp();
		System.out.println(String.format("Finished setting up the robot. Took: %1$.5f", endTime - begTime));
	}
	
	public void autonomousInit() {
		
	}

	public void autonomousPeriodic() {
		
	}
	
	public void teleopInit() {
	}
	
	public void teleopPeriodic() {
		while(isOperatorControl() && isEnabled()) {
    		coordinator.updateSubsystems();
    	}
	}
	
	public void disabledInit() {
		coordinator.stopSubsystems();
	}
	
	public void disabledPeriodic() {
		
	}
	
}

