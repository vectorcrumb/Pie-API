package com.pie.lib;

import java.util.Vector;

import com.pie.ingredients.Cookable;

public class SubsystemCoordinator {
	
	private Vector<Cookable> subsystems;
	private static SubsystemCoordinator instance;
	
	private SubsystemCoordinator () {
		this.subsystems = new Vector<Cookable>();
		// TODO: Add IO components getInstance()
	}
	
	public static SubsystemCoordinator getInstance() {
		if (instance == null) {
			instance = new SubsystemCoordinator();
		}
		return instance;
	}
	
	public void updateSubsystems() {
		for (Cookable subsystem : subsystems) {
			subsystem.update();
		}
	}
	
	public void stopSubsystems() {
		for (Cookable subsystem : subsystems) {
			subsystem.disable();
		}
	}
	
	public void addSubsytem(Cookable subsystem) {
		subsystems.addElement(subsystem);
	}
	
	// TODO: Add removeSubsystem()
}
